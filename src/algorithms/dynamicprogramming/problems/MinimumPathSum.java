package algorithms.dynamicprogramming.problems;

import java.util.stream.IntStream;

/**
 * LC#64:
 * Notes:
 * optimization problem
 */
public class MinimumPathSum {
    public int solve(int[][]g) {
        int M = g.length;
        int N = g[0].length;
        int[][]dp = new int[M][N];
        //save the start the top-left corner i.e. the shortest path from source to the source
        dp[0][0] = g[0][0];
        //Row 0, since there is a single path from source to these cells, we just accumulate the cost and add them
        for(int col = 1; col < N - 1; col++) {
            dp[0][col] = dp[0][col - 1] + g[0][col];
        }
        //Col 0
        for(int row = 1; row < M - 1; row++) {
            dp[row][0] = dp[row - 1][0] + g[row][0];
        }
        //general traversal
        for (int row = 1; row < M - 1; row++) {
            for (int col = 1; col < N - 1; col++) {
                dp[row][col] = g[row][col] + Math.min(dp[row - 1][col], dp[row][col - 1]);
            }
        }
        return dp[M - 1][N - 1];
    }
    //LC#120
    //remember pascal's triangle relation f(n,k) the min cost path from top to row n, col k
    // f(n, k) = min{f(n-1,k), f(n-1,k-1)} + cost[n][k]
    int solveTriangularInput(int[][] arr) {
        int[][]dp = new int[arr.length][arr.length];
        dp[0][0] = arr[0][0];
        //base cases
        for (int row = 1; row < arr.length - 1; row++) {
            //the left most side
            dp[row][0] = arr[row][0] + dp[row - 1][0];
            //the right most side
            dp[row][row] = arr[row][row] + dp[row - 1][row - 1];
        }
        //general traversal
        //within each row, no.of cols is row_index + 1.
        // Hence, col. index will vary from 1 to row_index - 1, having excluded first and last columns
        for (int row = 2; row < arr.length - 1; row++) {
            for (int col = 1; col < row - 1; col++) {
                dp[row][col] = arr[row][col] + Math.min(dp[row - 1][col - 1], dp[row - 1][col]);
            }
        }
        //we have all the minimum values available for each cell including each cell in the last row,we just pick whichever of them is minimum
        return IntStream.of(dp[arr.length - 1]).max().orElse(Integer.MIN_VALUE);
    }
}
