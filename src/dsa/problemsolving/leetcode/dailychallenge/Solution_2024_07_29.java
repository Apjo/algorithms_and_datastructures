package dsa.problemsolving.leetcode.dailychallenge;

/*
There are n soldiers standing in a line. Each soldier is assigned a unique rating value.

You have to form a team of 3 soldiers amongst them under the following rules:

Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
A team is valid if: (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).
 */

import java.util.*;

public class Solution_2024_07_29 {
    //stack based solution
    public int minimumDeletions(String s) {
        Stack<Character> st = new Stack<>();
        int ans=0;
        for(char cc : s.toCharArray()) {
            if(!st.isEmpty() && cc == 'a' && st.peek() == 'b') {
                st.pop();
                ans++;
            } else {
                st.push(cc);
            }
        }
        return ans;
    }
    //DP solution
    //dp[i]= is the min. no.of deletions required at index i to keep the string s[0...i-1] as balanced
    //dp[i] = min(cost required to delete a, cost required to keep a)
    //dp[i] = min(dp[i-1]+1, countofBs)
    public int minimumDeletions2(String s) {
        int[]dp = new int[s.length() + 1];
        dp[0]=0;
        int allBs = 0;
        for(int i=1; i <= s.length(); i++) {
            if (s.charAt(i - 1) == 'b') {
                dp[i] = dp[i - 1];
                allBs++;
            } else {
                dp[i] = Math.min(dp[i - 1] + 1, allBs);
            }
        }
        return dp[s.length()];
    }
}
