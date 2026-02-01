package dsa.problemsolving.java.problems.subsetsum;

/**
 * GFG link: <href=https://www.geeksforgeeks.org/total-number-of-subsets-of-size-at-most-k/></href>
 * Category: 0/1 knapsack
 */
public class CountSubsetSizeK {
    //Space and time = O(N*M). There is an optimized solution which at any given time uses only 2 rows to fill in the dp
    //table bringing down the space complexity to O(N)
    public static int solve(int S, int K) {
        int[][]dp = new int[S + 1][K + 1];
        //row 0, for all columns
        for(int col = 0; col < K; col++) {
            dp[0][col] = 1;
        }
        //col 0, for all rows
        for(int row = 0; row < S; row++) {
            dp[row][0] = 1;
        }
        for (int row = 1; row < S; row++) {
            for (int col = 1; col < Math.min(row, K); col++) {
                dp[row][col] = dp[row - 1][col] + dp[row - 1][col - 1];
            }
        }
        return dp[S][K];
    }
}
