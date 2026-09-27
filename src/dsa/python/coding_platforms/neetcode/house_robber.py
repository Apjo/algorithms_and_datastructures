"""
Filename: house_robber.py
Date: 2026-08-17
"""

from typing import List


class Solution:
    # recursion only
    def rob(self, nums: List[int]) -> int:
        def solve(idx):
            # At idx == 0: You have one house. Should you rob it or not? You rob it. So return nums[0].
            if idx == 0:
                return nums[0]
            # At idx == 1: You have two houses (indices 0 and 1). You can't rob both (adjacent). So return the maximum of the two—max(nums[0], nums[1]).
            if idx == 1:
                return max(nums[0], nums[1])
            # pick house at idx, so now only grab house at idx-2
            b = solve(idx - 2) + nums[idx]
            # dont pick house at idx, so now get what you have from idx-1
            a = solve(idx - 1)

            return max(a, b)

        return solve(len(nums) - 1)
    
    # memo only
    def rob_memo(self, nums: List[int]):
        # time: N unique subproblems (indices 0 through len(nums)-1). Each one does O(1) work (lookup, arithmetic, memo assignment). So total time = N × O(1) = O(N).
        # Space is O(N): memo dict stores N entries + recursion stack depth up to N
        def solve(idx):
            if idx in memo:
                return memo[idx]
            # base cases
            if idx == 0:
                memo[0] = nums[0]
                return nums[0]
            if idx == 1:
                memo[1] = max(nums[0], nums[1])
                return max(nums[0], nums[1])

            # recursive case
            b = solve(idx - 2) + nums[idx]

            a = solve(idx - 1)

            memo[idx] = max(a, b)

            return max(a, b)

        memo = {}
        return solve(len(nums) - 1)

    # bottom up DP
    def solve2(self, nums: List[int]):
        n = len(nums)
        if n == 0:
            return 0
        if n == 1:
            return nums[0]

        ans = [0] * n  # ans[i]=max amount of money you can rob from houses 0...i - 1
        ans[0] = nums[0]
        ans[1] = max(nums[0], nums[1])
        if n == 2:
            return ans[1]

        ans[2] = ans[0] + nums[2]
        for i in range(3, n):
            # we cannot take the house just before i, so we can either take i - 2, or i - 3 house
            ans[i] = max(nums[i] + ans[i - 2], nums[i] + ans[i - 3])
        return max(ans[n - 1], ans[n - 2])

    # even shorter code for bottom up DP
    def solve3(self, nums: List[int]):
        n = len(nums)
        if n == 0:
            return 0
        if n == 1:
            return nums[0]

        ans = [0] * n  # ans[i]=max amount of money you can rob from houses 0...i - 1
        ans[0] = nums[0]
        ans[1] = max(nums[0], nums[1])

        for i in range(2, n):
            # the last house say i could either be robbed or not robbed.
            # if it is robbed, then previous house cannot be robbed, so look for solutions from 0 to i -2
            # if it is NOT robbed, then look for solutions from 0 to i - 1
            ans[i] = max(ans[i - 1], nums[i] + ans[i - 2])

        return ans[n - 1]


if __name__ == "__main__":
    Solution().solve()
