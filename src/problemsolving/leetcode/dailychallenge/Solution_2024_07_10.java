package problemsolving.leetcode.dailychallenge;

import java.util.Stack;

/*
You are given a string s that consists of lower case English letters and brackets.

Reverse the strings in each pair of matching parentheses, starting from the innermost one.

Your result should not contain any brackets.
 */
public class Solution_2024_07_10 {
    //O(N) time, O(N) space
    public String reverseParentheses(String s) {
        Stack<Integer> st = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(char ss : s.toCharArray()) {
            if(ss == '(') {
                st.push(sb.length());
            } else if(ss == ')') {
                int curr = st.pop();
                rev(sb, curr, sb.length() - 1);
            } else {
                sb.append(ss);
            }
        }
        return sb.toString();
    }
    private static void rev(StringBuilder sb, int st, int end) {
        while(st < end) {
            char temp = sb.charAt(st);
            sb.setCharAt(st++, sb.charAt(end));
            sb.setCharAt(end--, temp);
        }
    }
}
