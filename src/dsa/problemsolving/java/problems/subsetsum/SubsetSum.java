package dsa.problemsolving.java.problems.subsetsum;
/**
 * Given an array = [1,5,11,5] determine if the input arr. contains subsets whose sum is equal to the given target S(say 11)
 * All numbers will be positive
 * category: 0/1 knapsack
 */
public class SubsetSum {
    public static boolean hasSubsetSum(int[]a, int S) {
        int N = a.length;
        boolean[][]dp=new boolean[N + 1][S+1];
        //base case when we have target S=0
        for(int row = 0; row < N; row++) {
            dp[row][0] = true;
        }
        //base case for when we have an empty array for S >= 0
        for(int col = 0; col < N; col++) {
            dp[0][col] = false;
        }
        //remaining traversal
        for (int i = 1; i <= N; i++) {
            for(int wt = 1; wt <= S; wt++) {
                //if element in the array is > target sum we ignore else use it
                if (a[i - 1] <= wt) {
                    dp[i][wt] = dp[i - 1][wt - a[i - 1]] || dp[i - 1][wt];
                } else {
                    dp[i][wt] = dp[i - 1][wt];
                }
            }
        }
        return dp[N][S];
    }
}
