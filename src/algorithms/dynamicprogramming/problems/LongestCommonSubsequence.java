package algorithms.dynamicprogramming.problems;

/**
 * Contains problems of LCS, and its patterns
 * LC#1143
 *More similar LCS problems:
 * 1092. Shortest Common Supersequence and Solution
 * 1062. Longest Repeating Substring (Premium).
 * 516. Longest Palindromic Subsequence
 * 1312. Minimum Insertion Steps to Make a String Palindrome
 */
public class LongestCommonSubsequence {
    //Time: O(2^N)
    //just returns the length of longest common subsequence
    public static int lcs(String A, String B) {
        return solveLcsRec(A, B, A.length() , B.length());
    }
    private static int solveLcsRec(String A, String B, int endOfA, int endOfB) {
        //base case when both the strings are empty
        if (endOfA == 0 || endOfB == 0) {
            return 0;
        }
        //Look at the last characters of both the strings
        // if characters match, then increment the cost, and move 1 character to the left for both the strings
        if (A.charAt(endOfA - 1) == B.charAt(endOfB - 1)) {
           return 1 + solveLcsRec(A, B, endOfA - 1, endOfB - 1);
        } else {
            //else either decreasing A's length one index at a time or B's lenght, and finally pick the one with max cost
           int x = solveLcsRec(A, B, endOfA - 1, endOfB);
           int y = solveLcsRec(A, B, endOfA, endOfB - 1);
            return Math.max(x, y);
        }
    }

    public static int solveLcsDP(String A, String B) {
        int M = A.length();
        int N = B.length();
        int[][]dp = new int[M+1][N+1];
        for (int i = 0; i < M + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                if (i == 0 || j == 0)
                dp[i][j] = 0;
            }
        }
        for(int i = 1; i < M+1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (A.charAt(i) == B.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[M][N];
    }

    public static int longestCommonSubstring(String A, String B) {
        int M = A.length();
        int N = B.length();
        int[][]dp = new int[M+1][N+1];
        for (int i = 0; i < M + 1; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < N + 1; i++) {
            dp[0][i] = 0;
        }
        for(int i = 1; i <= M+1; i++) {
            for (int j = 1; j <= N + 1; j++) {
                //we only add the cost for a match, and ignore the costs of mismatches
                if (A.charAt(i) == B.charAt(j)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return dp[M][N];
    }

    public static String printLCS(String A, String B) {
        //first carryout the usual LCS
        int M = A.length();
        int N = B.length();
        int[][]dp = new int[M+1][N+1];
        for (int i = 0; i < M + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 0;
            }
        }
        for(int i = 1; i < M+1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (A.charAt(i) == B.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if(A.charAt(i) == B.charAt(j)) {
                    //we found a match, add the character at this location to builder and move diagonally up(towards left)
                    sb.append(A.charAt(i));
                    i --;
                    j --;
                } else {
                    //we move to adjacent blocks where the cost of LCS is greater than its neighboring block
                    if (dp[i - 1][j] > dp[i][j - 1]) {
                        //if the top neighbor has a bigger cost
                        i--;
                    } else {
                        //if your neighbor to your left has a higher cost
                        j--;
                    }
                }
            }
        }
        return sb.reverse().toString();
    }
    public static void main(String[] args) {
        String A = "AGGTAB";
        String B = "GXTXAYB";
        System.out.println(lcs(A, B));
    }
}
