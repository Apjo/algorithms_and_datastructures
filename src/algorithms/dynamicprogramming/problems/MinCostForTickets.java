package algorithms.dynamicprogramming.problems;
//LC# 983

import java.util.stream.IntStream;

/**
 * trying to use decrease and conquer strategy,
 * For travelling on last day we have 3 options, we could either use 1 day pass, or a 7 day pass or a 30 day pass
 * f(n) = min #dollars needed to travel from days[0...N]
 * f(n) = min {
 *              f(n - 1) + costs[1 day pass = 0] => if the last day is covered by a 1 day pass,
 *              f(n - x) + cost[1] => where x <= 6  for the last day being covered by a 7-day pass, and we find the location by a constant time lookup
 *                       for the last day before n - 6. i.e. n-6...n are covered by the 7=day pass,
 *              f(n - x) + cost[2] => where x <= 29 and we find the location by a constant time lookup
 *                        for the last day before n - 29. i.e. n-29...n are covered by the 30-day pass
 * }
 *
 */
public class MinCostForTickets {
    public int solve(int[]costs, int[]days) {
        int[]dp = new int[days.length + 1];
        dp[0] = 0;
        dp[1] = IntStream.of(costs).min().orElse(Integer.MAX_VALUE);
        //now fill the dp table with the cost to travel between days 0 to i
        for (int i = 2; i < costs.length; i++) {
            //day i is covered in the 1-day pass, then pick optimal solution for all the previous days
            int case1 = dp[i - 1] + costs[0];
            //day i is covered in the 7-day pass, then assume previous 6 days are covered by the 7-day pass ie. search for the
            //last day that appeared before the 6th day
            int j = i - 1;
            int case2 = 0;
            while(j>=0 && days[j] >= days[i] - 6) {
                j-=1;
            }
            if(j>=0) {
                case2 = dp[j] + costs[1]; //I reach a day which is not covered by my 7-day pass
            } else {
                case2 = costs[1]; //all of my previous days get covered by the 7-day pass, hence i am just paying for the 7-day pass
            }
            //30-day pass case
            int jj = i - 1;
            int case3 = 0;
            while(jj>=0 && days[jj] >= days[i] - 29) {
                jj-=1;
            }
            if(jj>=0) {
                case3 = dp[jj] + costs[2];
            } else {
                case3 = costs[2];
            }
            dp[i] = IntStream.of(case1,case2, case3).min().orElse(Integer.MAX_VALUE);
        }
        return dp[days.length - 1];
    }
}
