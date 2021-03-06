package algorithms.dynamicprogramming.problems;

/**
 * Problem statement:
 * Given a 2D grid with M rows, and N columns, count the number of unique paths starting at top-left corner, and getting
 * to the bottom-right corner. All moves either go right or down
 *
 * Possible variations:
 * https://www.geeksforgeeks.org/count-possible-paths-top-left-bottom-right-nxm-matrix/
 * https://www.geeksforgeeks.org/print-all-possible-paths-from-top-left-to-bottom-right-of-a-mxn-matrix/
 *
 * A variation and a follow up:
 * https://www.geeksforgeeks.org/unique-paths-in-a-grid-with-obstacles/
 * https://www.geeksforgeeks.org/count-number-ways-reach-destination-maze/
 * Category:
 * Counting problem ==> 0/1 knapsack
 */

public class CountUniquePathsInAGrid {
    //Time=O(M*N) == space
    public static int solveUniquePathsDp(int[][]g) {
        int R = g.length;
        int C = g[0].length;
        int[][]dp = new int[R + 1][C + 1];
        //col=0, all rows
        for (int row = 0; row < R; row++) {
            dp[row][0] = 0;
        }
        //row=0, all columns
        for (int col = 0; col < C; col++) {
            dp[0][col] = 0;
        }
        for (int row = 1; row < R; row++) {
            for (int col = 1; col < C; col++) {
                dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
            }
        }
        return dp[R][C];
    }
}
