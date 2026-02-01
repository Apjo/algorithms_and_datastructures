package dsa.problemsolving.java.neetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem42 {
    /*
Given two strings s and t, return the shortest substring of s such that every character in t, including duplicates, is present in the substring. If such a substring does not exist, return an empty string "".

You may assume that the correct output is always unique.

Example 1:

Input: s = "OUZODYXAZV", t = "XYZ"

Output: "YXAZ"
Explanation: "YXAZ" is the shortest substring that includes "X", "Y", and "Z" from string t.

Example 2:

Input: s = "xyz", t = "xyz"

Output: "xyz"
Example 3:

Input: s = "x", t = "xy"

Output: ""
Constraints:

1 <= s.length <= 1000
1 <= t.length <= 1000
s and t consist of uppercase and lowercase English letters.
     */
    //date:08/10/2025
    public String minWindow(String s, String t) {
        int L1 = s.length(), L2 = t.length();
        if (L1 < L2) { return "";}
        if (s.equals(t)) {
            return s;
        }
        int le=0, ri=0, required=L2, currMinLen = Integer.MAX_VALUE, locOfMinLen=0;
        Map<Character, Integer> m1 = new HashMap<>();
        for(char cc : t.toCharArray()) {
            m1.put(cc, m1.getOrDefault(cc, 0) + 1);
        }
         while(ri < L1) {
             //continue going over to end of string S, while keeping a track of frequencies of matching chars in s and t
             //as soon as the freq. of a char drops to 0, update required param to decrement by 1
             char curr = s.charAt(ri);
             if (m1.containsKey(curr)) {
                 m1.put(curr, m1.get(curr) - 1);
                 if (m1.get(curr) >= 0) {
                     required--;
                 }
             }
            ri++;
             //now that you have "covered" all the required chars in string t
             //determine whats the best len of the substring that contains all the chars in t
            while(le <= ri && required == 0) {
                char fromLe = s.charAt(le);
                int len = ri - le;
                if (len < currMinLen) {
                    locOfMinLen = le;
                    currMinLen = len;
                }
                //now that you have got the best len, try to drop chars from left, and see whether or not there are chars that still make up string t
                if (m1.containsKey(fromLe)) {
                    m1.put(fromLe, m1.getOrDefault(fromLe, 0) + 1);
                    if (m1.get(fromLe) > 0) {
                        required++;
                    }
                }
                le++;
            }
        }
        return currMinLen == Integer.MAX_VALUE ? "" : s.substring(locOfMinLen, locOfMinLen+currMinLen);
    }

    public static void main(String[] args) {
    }
}
