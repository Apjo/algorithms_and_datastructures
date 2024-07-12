package problemsolving.leetcode.dailychallenge;

import java.util.Stack;

/*
You are given a string s and two integers x and y. You can perform two types of operations any number of times.

Remove substring "ab" and gain x points.
For example, when removing "ab" from "cabxbae" it becomes "cxbae".
Remove substring "ba" and gain y points.
For example, when removing "ba" from "cabxbae" it becomes "cabxe".
Return the maximum points you can gain after applying the above operations on s.



Example 1:

Input: s = "cdbcbbaaabab", x = 4, y = 5
Output: 19
Explanation:
- Remove the "ba" underlined in "cdbcbbaaabab". Now, s = "cdbcbbaaab" and 5 points are added to the score.
- Remove the "ab" underlined in "cdbcbbaaab". Now, s = "cdbcbbaa" and 4 points are added to the score.
- Remove the "ba" underlined in "cdbcbbaa". Now, s = "cdbcba" and 5 points are added to the score.
- Remove the "ba" underlined in "cdbcba". Now, s = "cdbc" and 5 points are added to the score.
Total score = 5 + 4 + 5 + 5 = 19.
 */
public class Solution_2024_07_11 {
    public int maximumGain(String s, int x, int y) {
        StringBuilder sb = new StringBuilder(s);
        if (x > y) {
            return solve(sb, "ab", x) + solve(sb, "ba", y);
        } else {
            return solve(sb, "ba", y) + solve(sb, "ab", x);
        }
    }
    private static int solve(StringBuilder sb, String pat, int points) {
        int k=0, ans=0;
        for(int i=0; i < sb.length(); i++) {
            sb.setCharAt(k++, sb.charAt(i));
            //compare if substring contains ab or ba
            if (k > 1 && sb.charAt(k - 2)==pat.charAt(0) && sb.charAt(k - 1) == pat.charAt(1)) {
                k-=2;
                ans+=points;
            }
        }
        sb.setLength(k);
        return ans;
    }
}
