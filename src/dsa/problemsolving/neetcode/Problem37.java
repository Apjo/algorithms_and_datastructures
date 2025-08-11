package dsa.problemsolving.neetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem37 {
    /*
    Given a string s, find the length of the longest substring without duplicate characters.

A substring is a contiguous sequence of characters within a string.

Example 1:

Input: s = "zxyzxyz"

Output: 3
Explanation: The string "xyz" is the longest without duplicate characters.

Example 2:

Input: s = "xxxx"

Output: 1
Constraints:

0 <= s.length <= 1000
s may consist of printable ASCII characters.
     */
    //date: 08/03/2025
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        int le=0,ans=0;
        for(int right=0; right < s.length(); right++) {
            char curr = s.charAt(right);
            freq.put(curr, freq.getOrDefault(curr, 0) + 1);
            //once frequency of a char reaches > 1, start removing it
            while(le < right && freq.get(curr) > 1) {
                //drop left most
                char toRem = s.charAt(le);
                freq.put(toRem, freq.get(toRem) -1);
                //remove from map when freq reaches 0
                if (freq.get(toRem) == 0) {
                    freq.remove(toRem);
                }
                le++;
            }
            //calculate current max len
            ans=Math.max(ans, right - le + 1);
        }
        return ans;
    }
    public static void main(String[] args) {
    }
}
