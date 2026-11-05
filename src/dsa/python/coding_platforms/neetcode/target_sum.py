"""
Filename: target_sum.py
Date: 2026-09-10
"""

from typing import List

class Solution:
    # def findTargetSumWays_dp(self, nums: List[int], target: int) -> int:
        
    #     if not nums:
    #         return 0
        
    #     N = len(nums)
        
    #     ways = [[0 * (target + 1)] for _ in range(N + 1)]

    #     for T in range(1, target + 1):
    #         for i in range(1, N + 1):
    #             if T - nums[i] >=0:


    
    #memoization, time: O(N), space: O(N)
    def findTargetSumWays_memo(self, nums: List[int], target: int) -> int:
        if not nums:
            return 0
        N = len(nums)
        memo={}
        def solve(idx, T):
            if idx >= N :
                if T == target:
                    return 1
                return 0
            
            if (idx, T) in memo:
                return memo[(idx, T)]
            
            a = solve(idx + 1, T + nums[idx])
            b = solve(idx + 1, T + -1 * nums[idx])
            memo[(idx, T)] = a+b

            return a + b

        return solve(0, 0)

    #pure recursion that gives a TLE, time: O(2^N)
    '''
    Iterate over each index of the input by keeping track of the current sum, we either add the current num to the sum, or substract it. And, when we finally reach where current sum == target we return 1 or add 1 to the possible ways to make the target sum
    '''
    def findTargetSumWays_recur(self, nums: List[int], target: int) -> int:
        
        if not nums:
            return 0
        
        N = len(nums)

        def solve(idx, T):
            if idx >= N:
                if T == target:
                    return 1
                return 0
            a = solve(idx + 1, T + nums[idx])
            b = solve(idx + 1, T + -1 * nums[idx])

            return a + b

        return solve(0, 0)
        


if __name__ == '__main__':
    Solution().solve()