package dsa.problemsolving.neetcode;

import java.util.Arrays;
/*
You are given an array of strings strs. Return the longest common prefix of all the strings.

If there is no longest common prefix, return an empty string "".

Example 1:

Input: strs = ["bat","bag","bank","band"]

Output: "ba"
Example 2:

Input: strs = ["dance","dag","danger","damage"]

Output: "da"
Example 3:

Input: strs = ["neet","feet"]

Output: ""
 */
public class Problem5 {
    public static String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        String s1 = strs[0];
        String s2 = strs[strs.length - 1];
        int i=0, j = 0, ans = 0;
        while(i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) != s2.charAt(j)) {
                break;
            }
            i++;
            j++;
            ans++;
        }
        return s1.substring(0, ans);
    }
    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"dance","dag","danger","damage"}));
        System.out.println(longestCommonPrefix(new String[]{"colorado", "color", "cold"}));
    }
}
