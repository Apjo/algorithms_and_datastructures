package dsa.java.problems.subsetsum;

/**
 * Sum of subset differences
 * Given a set of integers, the task is to divide it into two sets S1 and S2 such that the absolute difference between their sums is minimum.
 * If there is a set S with n elements, then if we assume Subset1 has m elements, Subset2 must have n-m elements and the value of
 * abs(sum(Subset1) – sum(Subset2)) should be minimum
 * Example:
 * Input:  arr[] = {1, 6, 11, 5}
 * Output: 1
 * Explanation:
 * Subset1 = {1, 5, 6}, sum of Subset1 = 12
 * Subset2 = {11}, sum of Subset2 = 11
 * problem link: https://www.geeksforgeeks.org/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum/
 * category: 0/1 knapsack
 */
public class SubsetSumDifference {
    public static int solve(int []A) {
        //first count the total sum of all elements in the array
        int s = 0;
        for (int i: A) {
            s+=A[i];
        }
        //next find the subset sum for the given array A whether there exists a subset with sum from 0 to s + 1
        int N = A.length;
        boolean[][]dp = new boolean[N + 1][s + 1];
        //For the 0th row except for dp[0][0], when the size of the subset is 0 and sum ranges from 0 to s + 1
        for (int col = 1; col <= s; col++) {
            dp[0][col] = false;
        }
        //For the 0th col, when the size of the subsets range from 0 to N + 1 for a taget sum=0
        for (int row = 0; row < N; row++) {
            dp[row][0] = true;
        }
        //generic traversal
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j<= s; j++) {
                dp[i][j] = dp[i - 1][j];
                if(A[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - A[i - 1]];
                }
            }
        }
        int mn = Integer.MAX_VALUE;
        //iterate over 1/2 of the table of dp, to find an index where dp[row][col] is true and is largest till that index
        for (int j = s / 2; j >=0; j--) {
            if (dp[N][j]) {
                mn = s - 2 * j;
                break;
            }
        }
        return mn;
    }

}
