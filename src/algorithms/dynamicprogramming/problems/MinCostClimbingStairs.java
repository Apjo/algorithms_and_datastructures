package algorithms.dynamicprogramming.problems;
//LC#746

/**
 * F(n) = minimum cost of climbing the stairs
 * f(n) = cost[n] + Math.min(f(n - 1), f(n - 2))
 * How many different subproblems are there in the whole space? N
 *
 */
public class MinCostClimbingStairs {
    public static int solve(int[] costs) {
        int N = costs.length;
        int[]dp = new int[N];
        dp[0] = 0; //we can start at 0, so just pick the cost as is
        dp[1] = costs[1]; //we can start at 1, so just pick the cost as is
        for (int i = 2; i < N; i++) {
            dp[i] = costs[i] + Math.min(dp[i - 1], dp[i - 2]);
        }
        //You look at the highest step, and you could have climbed from there to the floor above, or from the penultimate step
        //you could have jumped by 2 steps to reach the floor above. Whichever of those is minimum is the answer
        //also, the top of the floor just lies beyond the highest/last step, we could get away with this and return dp[N-1] by appending a 0 to the costs array i.e. to reach
        //the floor above i dont have to expense extra cost
        return Math.min(dp[N - 1], dp[N - 2]);
    }
}
