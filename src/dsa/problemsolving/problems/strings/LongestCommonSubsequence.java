package dsa.problemsolving.problems.strings;

/**
 * Contains problems of LCS, and its patterns
 * A subsequence maintains the order of characters, irrespective of how many characters appear in between them
 * LC#1143
 *More similar LCS problems:
 * 1092. Shortest Common Supersequence and Solution(TRICK: LEN(StrA + StrB) - LEN(LCS))
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
    //Just returns the length of the LCS
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

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                //if there is a match, add 1 cost to the operation
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    //else, get the max cost from the neighbor to left(i, j - 1) or top(i, j - 1)
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[M][N]; //final answer stored here
    }
    //pattern: LCS, TRICK: We only add a cost for a matching case
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
        int[][]dp = buildLcsCostMatrix(A, B);

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
    //This is a utility function to just return the cost matrix (or DP table) for LCS operation
    private static int[][] buildLcsCostMatrix(String A, String B) {
        int M = A.length();
        int N = B.length();
        int[][]dp = new int[M+1][N+1];
        for (int i = 0; i < M + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 0;
            }
        }
        for(int i = 1; i <= M + 1; i++) {
            for (int j = 1; j <= N + 1; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp;
    }
    //TRICK: Len(A) + Len(B) - Len(LCS(A,B))
    //Problem: Given two strings X and Y, print the length of the shortest string that has both X and Y as subsequences.
    public static int shortestCommonSuperSequenceLen(String A, String B) {
        return A.length() + B.length() - printLCS(A, B).length();
    }

    //Return the shortest supersequence
    //Given two strings X and Y, print the shortest string that has both X and Y as subsequences.
    // If multiple shortest supersequence exists, print any one of them.
    public static String shortestCommonSuperSequence(String A, String B) {
        int[][]dp = buildLcsCostMatrix(A, B);
        StringBuilder sb = new StringBuilder();
        int i = dp.length;
        int j = dp[0].length;
        while(i > 0 && j > 0) {
                if(A.charAt(i) == B.charAt(j)) {
                    //we found a match, add the character at this location to builder and move diagonally up(towards left)
                    sb.append(A.charAt(i));
                    i --;
                    j --;
                } else {
                    //we move to adjacent blocks where the cost of LCS is greater than its neighboring block, and append to result
                    if (dp[i - 1][j] > dp[i][j - 1]) {
                        sb.append(A.charAt(i - 1));
                        i--;
                    } else {
                        //if your neighbor to your left has a higher cost
                        sb.append(B.charAt(j - 1));
                        j--;
                    }
                }
        }

        //we could have either String A or String B still remaining to be processed. So, we append the remaining characters as is
        while(i > 0) {
            sb.append(A.charAt(i - 1));
            i--;
        }

        while(j > 0) {
            sb.append(B.charAt(j - 1));
            j--;
        }
        return sb.reverse().toString();
    }

    public static int longestRepeatingSubsequenceLen(String A) {
        String B = A;//we start with LCS to have the second string the same as A.
        int M = A.length();
        int N = A.length();

        int [][]dp = new int[M + 1][N + 1];

        for (int i = 0; i< M+1; i++) {
            for (int j = 0; j < N + 1; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0 ;
                }
            }
        }
        for (int i = 1; i<= M + 1; i++) {
            for (int j = 1; j <= N + 1; j++) {
                //If the characters say 'c' at appears at locations i and j of the String A, and String B,
                // we would not want to build the duplicate subsequence from the same location i, we would like to have the character c at location starting at j.
                if (A.charAt(i - 1) == B.charAt(j - 1) && i != j) {
                    dp[i][j] = dp[i - 1][j] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[M][N];
    }

    public static void main(String[] args) {
        String A = "AGGTAB";
        String B = "GXTXAYB";
        System.out.println(lcs(A, B));
    }
}
