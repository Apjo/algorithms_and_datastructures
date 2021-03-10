package algorithms.dynamicprogramming.problems.subsetsum;
/**
 * Given an array = [1,5,11,5] return the count of no.of subsets whose sum is equal to the given target S(say 11)
 * All numbers will be positive
 * time: O(sum * N) == space
 * category: 0/1knapsack
 */
public class CountSubsetSum {
    public static int countSubsetSum(int[]a, int S) {
        int N = a.length;
        int[][]dp=new int[N + 1][S+1];
        dp[0][0] = 1;
        //base case when we have target S=0
        for(int row = 1; row <= N; row++) {
            dp[row][0] = 1;
        }
        //base case for when we have an empty array for S >= 0
        for(int col = 1; col <= S; col++) {
            dp[0][col] = 0;
        }
        //remaining traversal
        for (int i = 1; i <= N; i++) {
            for(int wt = 1; wt <= S; wt++) {

                //if element in the array is > target sum we ignore else use it
                if (a[i - 1] <= wt) {
                    dp[i][wt] = dp[i - 1][wt - a[i - 1]] + dp[i - 1][wt];
                } else {
                    dp[i][wt] = dp[i - 1][wt];
                }
            }
        }
        return dp[N][S];
    }
}
