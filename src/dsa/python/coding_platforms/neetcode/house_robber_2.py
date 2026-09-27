"""
Filename: house_robber_2.py
Date: 2026-08-18
"""

from typing import List

class Solution:
    #using simple recursion
    def rob_recur(self, nums: List[int]) -> int:
        n = len(nums)
        if n == 0:
            return 0
        if n == 1:
            return nums[0]
        if n == 2:
            return max(nums[0], nums[1])
        def solve(idx, robbed_house_0):
            if idx == len(nums) or (robbed_house_0 and idx >= len(nums) - 1):
                return 0
            return max(
                solve(idx + 1, robbed_house_0),
                nums[idx] + solve(idx + 2, (robbed_house_0 or idx==0)),
            )
        #if we pick 0, then rob houses from 0 to n-2(excludes last house)
        # else we start from house 1, and then rob houses from 1 to n-1(skipping house 0) 
        return max(solve(0, True), solve(1, False))
    #using memo
    def rob_memo(self, nums: List[int]) -> int:
        # if you are robbing the 0th house, cant rob n-1th
        # if you are robbing the n-1th house, cant rob 0th
        # if you are robbing the ith house, cant rob i + 1th
        # the call to recursion should happen twice once from 0 to n-2, and the second one from 1 to n-1, since we determine what happens to the max answer if we were to start/skip from 0, and n-1 respectively
        def solve(idx, flag):
            # base case when idx is at end of array, or idx==n-1 and flag is True i.e. we have picked up 0th house,last house isn't allowed!
            if idx >= len(nums) or (flag and idx == len(nums) - 1):
                # memo[idx] = 0
                return 0
            if idx in memo:
                return memo[(idx, flag)]
            # not pick
            a = solve(idx + 1, flag)
            # picked, skip to next house
            b = solve(idx + 2, (flag or idx == 0)) + nums[idx]

            memo[(idx, flag)] = max(a, b)

            return memo[(idx, flag)]

        n = len(nums)

        if n == 0:
            return 0
        if n == 1:
            return nums[0]
        if n == 2:
            return max(nums[0], nums[1])
        
        memo = {}

        return max(solve(0, True), solve(1, False))

    
    #bottom up DP
    def rob_dp(self, nums: List[int]) -> int:
        n = len(nums)

        if n == 0:
            return 0
        if n == 1:
            return nums[0]
        if n == 2:
            return max(nums[0], nums[1])

        def solve(arr: List[int]) -> int:
            dp = [0] * (len(arr))
            dp[0] = arr[0]
            dp[1] = max(arr[0], arr[1])
            for i in range(2, len(arr)):
                dp[i] = max(dp[i - 1], arr[i] + dp[i - 2])

            return dp[-1]
        
        return max(solve(nums[:-1]), solve(nums[1:]))
        


if __name__ == '__main__':
    Solution().solve()