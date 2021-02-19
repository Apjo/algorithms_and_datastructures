package algorithms.dynamicprogramming.problems;
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
    public static int solve(int[]coins, int S) {
        int N = coins.length;
        int []dp = new int[S + 1];
        dp[0] = 0; //min coins to have S=0, will be 0
        for (int i = 1; i < S; i++) {
            int min = Integer.MAX_VALUE;
            for (int c = 0; c < N; c++) {
                //look at previous denomination
                if (i - c >= 0) {
                    min = Math.min(min, dp[i - c]);
                }
            }
            dp[i] = min + 1;
        }
        if (dp[S - 1] == Integer.MAX_VALUE) { return -1; }
        return dp[S - 1];
    }
}
