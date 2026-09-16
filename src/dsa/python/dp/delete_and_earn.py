"""
Filename: delete_and_earn.py
Date: 2026-08-30
"""

from typing import List


class Solution:
    # basically boild down to using house robber approach sum[i] = max(sum[i-1], sum[i-2] + sum[i])
    # You also notice that if you have duplicate values in nums array, if you earn one of them, you end up earning all of them. This is because you have deleted its neighbors and therefore make its remaining duplicates "undeletable".
    # uses 2 separate dp, and sum arrays
    def deleteAndEarn_2(self, nums: List[int]) -> int:
        L = 10001
        N = len(nums)
        sum_arr = [0] * (L)
        dp = [0] * (L)
        for i in range(N):
            sum_arr[nums[i]] += nums[i]

        dp[0] = 0
        dp[1] = sum_arr[1]

        for i in range(2, L):
            dp[i] = max(sum_arr[i] + dp[i - 2], dp[i - 1])

        return dp[L - 1]

    # use the sum array as the dp array itself
    def deleteAndEarn(self, nums: List[int]) -> int:
        curr_max = max(nums)
        dp = [0] * (curr_max + 2)
        for i in range(len(nums) + 1):
            dp[nums[i]] += nums[i]
        for i in range(2, curr_max + 1):
            dp[i] = max(dp[i - 1], dp[i - 2] + dp[i])

        return dp[curr_max]


if __name__ == "__main__":
    Solution().solve()
