"""
Filename: min_cost_climb_stairs.py
Date: 2026-08-16
"""

from typing import List
class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        # dp[i] = min(dp[i - 1], dp[i-2]) + cost[i]
        n = len(cost)
        if n == 1:
            return cost[n]
        if n == 2:
            return min(cost[0], cost[1])

        dp = [0] * (n + 1)
        dp[0] = cost[0]
        dp[1] = cost[1]

        for i in range(2, n):
            dp[i] = min(dp[i - 1], dp[i - 2]) + cost[i]
        # since we are beyond the last step we still need to find the min from last 2 steps
        return min(dp[n - 1], dp[n - 2])


if __name__ == '__main__':
    Solution().solve()