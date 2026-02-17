package dsa.java.neetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem38 {
    /*
    You are given a string s consisting of only uppercase english characters and an integer k.
    You can choose up to k characters of the string and replace them with any other uppercase English character.
After performing at most k replacements, return the length of the longest substring which contains only one distinct character.

Example 1:
Input: s = "XYYX", k = 2

Output: 4
Explanation: Either replace the 'X's with 'Y's, or replace the 'Y's with 'X's.

Example 2:
Input: s = "AAABABB", k = 1

Output: 5
Constraints:

1 <= s.length <= 1000
0 <= k <= s.length
     */
    //date:08/03/2025
    public int characterReplacement(String s, int k) {
        Map<Character, Integer> freq = new HashMap<>();
        int maxLen=0, left=0, freqOfMaxRepeatingChar = 0;
        for(int i=0; i < s.length(); i++) {
            char curr = s.charAt(i);
            freq.put(curr, freq.getOrDefault(curr, 0) + 1);
            freqOfMaxRepeatingChar = Math.max(freqOfMaxRepeatingChar, freq.get(curr));
            int remainingCharsToReplace = (i - left + 1) - freqOfMaxRepeatingChar;
            /*
            At any given window, maxFrequency will only be violated if the start index happens to be pointing at the char with max frequency.
            For example: "AABBA", start = 0, end = 4. If we shrink the window by moving the start pointer to the right by 1, maxFrequency should be 2 instead of 3.
Keep in mind that the way we validate the window is by comparing windowLength - maxFrequency with k. When the situation described earlier happens, notice that the most frequent char is removed from both the maxFrequency count as well as the windowLength count. In other words "the number of chars that need to be replaced" becomes (windowLength - 1) - (maxFrequency - 1). The two -1s cancel out
Also, if the maxFrequency of the current window is smaller than that of the previous, we do not wish to examine this window because it will not produce longest substring.
if the maxFrequency increases it will be updated automatically.
having stale maxFrequency does not affect the sliding of the window since the right point can increase when left point increases.
            */
            while(left <= i && remainingCharsToReplace > k) {
                freq.put(s.charAt(left), freq.get(s.charAt(left)) - 1);
                left++;
            }
            maxLen = Math.max(maxLen, (i - left + 1));
        }
        return maxLen;
    }
    public static void main(String[] args) {
    }
}
