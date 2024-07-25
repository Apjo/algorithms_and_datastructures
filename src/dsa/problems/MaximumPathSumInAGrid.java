package dsa.problems;

/**
 * Problem:
 * https://www.geeksforgeeks.org/maximum-sum-path-in-a-matrix-from-top-left-to-bottom-right/
 * medium:
 * https://www.geeksforgeeks.org/maximum-sum-path-matrix-top-bottom/
 * harder variant:
   https://www.geeksforgeeks.org/maximum-sum-path-in-a-matrix/
 * Category:
 * Optimization problem
 */
public class MaximumPathSumInAGrid {
    //Time = O(R*C) ==> space
    //To return the path itself? we would need to not only update the value, but will need to keep a record of which neighbor
    // we got that optimal value from.
    //TODO: print path
    //Followup:could be asked for min path
    public static int solveMaxPathSumDp(int[][] G) {
        int R = G.length;
        int C = G[0].length;
        //will store the max path from (0,0) to location(i,j)
        int[][]dp = new int[R][C];

        dp[0][0] = G[0][0];

        //for row=1 & for all columns. The value will be derived from its neighbor lying to its left + cost at that location
        for(int col = 1; col <= C - 1; col++) {
            dp[0][col] = dp[0][col - 1] + G[0][col];
        }
        //for col=1 & for all rows. The value will be derived from its neighbor lying to above + cost at that location
        for(int row = 1; row <= R - 1; row++) {
            dp[row][0] = dp[row - 1][0] + G[row][0];
        }

        for(int row = 1; row <= R - 1; row++) {
            for(int col = 1; col <= C - 1; col++) {
                dp[row][col] = G[row][col] + Math.max(
                        dp[row - 1][col]/*neighbor above me*/,
                        dp[row][col - 1]/*neighbor to my left*/);
            }
        }
        return dp[R - 1][C - 1];
    }
    public static void main(String[] args) {
        int[][]G = new int[][]{{1,3,5}, {1,5,1}, {4,2,1}};
        System.out.println(solveMaxPathSumDp(G));//12
        //TODO: add a function to print the path
    }
}
