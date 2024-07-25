package dsa.problems;
//LC# 322

/**
 * What is f(n) = minimum no.of coins required to make up amount N
 * What was the last coin added, could be any one of the denominations given to us
 * f(n) = min{f(n-c1), f(n-c2),..., f(n- ck)} + 1
 * whichever amount from (f(n-c1)...f(n-ck))took the fewest no.of coins, adding 1 more coin to that would be f(n)
 * How many subproblems? roughly N, since parameter value for N could be anything from 0 to whatever amount we have to
 * optimize for
 */
public class CoinChange {
    //Time: O(target*size of coin array), space= O(target)
    public static int solve(int[]coins, int S) {
        int N = coins.length;
        int []dp = new int[S + 1];
        //base case
        dp[0] = 0; //min coins to have S=0, will be 0
        // init the table with bad/infinity
        for(int i = 1; i <= N; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        //generic traversal
        for (int i = 1; i <= S; i++) {
            int min = Integer.MAX_VALUE;
            for (int c = 0; c < N; c++) {
                //look at previous denomination. If the subproblem size is < 0 we ignore.
                // Meaning, say we have denominations(c) [3,5,7] greater target value (i) say 1, we cannot have a < 0 size problems
                if (i - c >= 0) {
                    min = Math.min(min, dp[i - c]);
                }
            }
            dp[i] = min + 1;
        }
        if (dp[S - 1] == Integer.MAX_VALUE) { return -1; }
        return dp[S - 1];
    }
    // this will be a combo of unbounded knapsack + countSubset sum
    public static int maxNumWaysToFormSum(int[]coins, int S) {
        int N = coins.length;
        int[][]dp = new int[N + 1][S + 1];
        //for row 0
        for (int col = 0; col < S; col++) {
            dp[0][col] = 0;
        }
        //for col 0
        for (int row = 0; row < N; row++) {
            dp[row][0] = 1;
        }
        //generic traversal
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= S; col++) {
                if(coins[row - 1] <= col) {
                    dp[row][col] = dp[row - 1][col] + dp[row - 1][col - coins[row - 1]];
                } else {
                    dp[row][col] = dp[row - 1][col];
                }
            }
        }
        return dp[N][S];
    }
}
