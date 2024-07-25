package dsa.problems;
//LC# 198
/**
 * the last decision made by the robber could be either to rob a house or not
 * F(n) = max.no.of houses you can rob from first n houses =
 * max{
 *      f(n-1) ->(to not rob this house),
 *      f(n-2) + nums[n] -> rob this house and accrue the money
 * }
 */
public class HouseRobber {
    public int solve(int []a) {
        int[]dp = new int[a.length];
        //base case, if there were no houses, total value a robber would get be 0
        dp[0] = 0;
        dp[1] = a[1];
        dp[2] = Math.max(a[1], a[2]);
        for (int i = 3; i < a.length; i++) {
            dp[i] = Math.max(dp[i -1], dp[i -2] + a[i]);
        }
        return dp[a.length - 1];
    }
    //LC# 213
    /**
     * If N houses are arranged in a circular manner, we need to somehow break the circle, then we arrive at following cases
     * Case 1: House #0 is robbed
     * we need to find an optimal solution for houses between 1 to N - 2. We cannot rob house # N -1,
     * for this we can use the "straight line" soltn(prev problem)
     *
     * Case 2:house #0 is NOT robbed
     * we need to find an optimal solution for houses between 1 to N - 1, for this we can use the "straight line" soltn(prev problem)
     *Finally we return the max value from (case 1 soltn, case 2 soltn)
     */
}
