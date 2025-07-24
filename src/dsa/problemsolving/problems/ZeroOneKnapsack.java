package dsa.problemsolving.problems;

/**
 * A bounded knapsack version
 *  * A note on 0/1 knapsack:
 *  *  We are given 2 collections denoting the properties of an item, say the weight array and the value array.
 *  *  Look at the last item, and then we either select that item or not. Once a decision is taken on an item, we never look
 *  *  at it again. So, we do not allow multiple occurrences of the same item
 *  remember pattern:
 *  Zeroth row, All cols = 0
 *  Zeroth row, Zeroth col = 0
 *  FOR item in 1 to N LOOP
 *      FOR wt in 1 to WEIGHT_ARRAY_LEN LOOP
 *          if (A[item - 1] <= wt) {
 *              dp[item - 1][wt] = max(dp[item - 1][wt] , val[item - 1] + dp[item - 1][wt - wt[item - 1]])
 *          } else {
              dp[item - 1][wt] = dp[item - 1][wt]
 *          }
 *      END LOOP
 *  END LOOP
 *  Time: O(W*V)
 */
public class ZeroOneKnapsack {
    private static void printMatrix(int[][]m) {
        for (int row = 0; row < m.length; row ++) {
            for (int col = 0; col < m[0].length; col ++) {
                System.out.print(" " + m[row][col]);
            }
            System.out.println();
        }
    }
    public static int solveBoundedKnapsack(int[]weights, int[]costs, int W) {
        int N = weights.length;
        int[][]dp = new int[N + 1][W + 1];
        //initialize
        for (int row = 0; row < N + 1; row++) {
            for (int col = 0; col < W + 1; col++) {
                if (row == 0 || col == 0) {
                    dp[row][col] = 0;
                }
            }
        }

        //general traversal
        for (int row = 1; row < N + 1 ; row++) {
            for (int col = 1; col < W + 1; col++) {
                if(weights[row - 1] <= col) {
                    //we found a weight capable to be added to the knapsack
                    dp[row][col] = Math.max(
                            dp[row - 1][col],//don't select the item of weight
                            dp[row - 1][col - weights[row - 1]] + costs[row - 1] //use the item's weight -> select the item -> and add its cost
                    );
                } else {
                    //we did not find a weight < knapsack's weight,don't select the item
                    dp[row][col] = dp[row - 1][col];
                }
            }
        }
        printMatrix(dp);
        return dp[N][W];
    }
    public static int solveUnboundedKnapsack(int[]weights, int[] costs, int W) {
        if (weights.length == 0 || W == 0) {return 0;}
        int N = weights.length;
        int[][]dp = new int[N+1][W+1];
        //initialize
        for (int row = 1; row < N; row++) {
            dp[row][0] = 0; //for col 0, costs at i -> 0
        }
        for (int col = 1; col < dp[0].length; col++) {
            dp[0][col] = 0; //for row 0, cost at i -> 0
        }
        //general traversal
        for (int item = 1; item < N; item++) {
            for (int col = 1; col < dp[0].length; col++) {
                if(weights[item - 1] <= col) {
                    //we found a weight capable to be added to the knapsack
                    dp[item][col] = Math.max(
                            //don't select the item of weight, we have processed it, we will never come back to it.
                            dp[item - 1][col],
                            //use the item's weight. But, we need to reuse the item, so we don't perform item - 1.
                            // We might come back to using it. End up adding the cost of using the item if we select it
                            dp[item][col - weights[item - 1]] + costs[item - 1]
                    );
                } else {
                    //we did not find a weight < knapsack's weight,dont select the item
                    dp[item][col] = dp[item - 1][col];
                }
            }
        }
        return dp[N][W];
    }
    public static void main(String [] args) {
        int[]weights = new int[]{1, 2, 3};
        int[] costs = new int[]{10, 15, 40};
        int K = 6;
        System.out.println("Bounded knapsack= " + solveBoundedKnapsack(weights, costs, K));
    }
}
