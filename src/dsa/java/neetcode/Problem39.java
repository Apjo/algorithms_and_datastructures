package dsa.java.neetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem39 {
    /*
    You are given two strings s1 and s2.

Return true if s2 contains a permutation of s1, or false otherwise. That means if a permutation of s1 exists as a substring of s2, then return true.

Both strings only contain lowercase letters.

Example 1:

Input: s1 = "abc", s2 = "lecabee"

Output: true
Explanation: The substring "cab" is a permutation of "abc" and is present in "lecabee".

Example 2:

Input: s1 = "abc", s2 = "lecaabee"

Output: false
Constraints:

1 <= s1.length, s2.length <= 1000
     */
    //date: 08/04/2025
    public boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) {
            return false;
        }
        Map<Character, Integer> m1 = new HashMap<>();
        Map<Character, Integer> m2 = new HashMap<>();
        //maintain a freq map for string s1
        for(char cc : s1.toCharArray()) {
            m1.put(cc, m1.getOrDefault(cc, 0) + 1);
        }
        int windowLen = s1.length();
        //maintain a freq. map for string s2 of the fixed window len=s1.len
        for(int i=0; i < windowLen; i++) {
            char cc = s2.charAt(i);
            m2.put(cc, m2.getOrDefault(cc, 0) + 1);
        }
        if (m1.equals(m2)) {
            return true;
        }
        //continue traversing s2 starting at windowLen, while adding characters of s2 pointed to by "right",
        //dropping characters at left pointed to be "right - windowLen"
        //once the freq. of a char reaches 0, we remove it.
        //while checking at any point, whether both maps m1, and m2 are equal
        for(int right = windowLen; right < s2.length(); right++) {
            char toAdd = s2.charAt(right);
            m2.put(toAdd, m2.getOrDefault(toAdd, 0) + 1);
            char toRemo = s2.charAt(right - windowLen);
            m2.put(toRemo, m2.get(toRemo) - 1);
            if (m2.get(toRemo) == 0) {
                m2.remove(toRemo);
            }
            if (m1.equals(m2)) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
    }
}
