# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/18/26
link: https://leetcode.com/contest/biweekly-contest-176/problems/house-robber-v/description/
"""
from typing import List

class Solution:
    def rob(self, nums: List[int], colors: List[int]) -> int:
        N = len(nums)
        if N == 0:
            return 0
        if N == 1:
            return nums[0]
        dp=[0]*N
        dp[0] = nums[0]
        if colors[0] != colors[1]:
            dp[1]=nums[0] + nums[1]
        else:
            dp[1]=max(nums[0], nums[1])

        for i in range(2, N):
            if colors[i] == colors[i - 1]:
                dp[i] = max(dp[i-1], nums[i] + dp[i - 2])
            else:
                dp[i] = max(dp[i-1], nums[i] + dp[i - 1])
        #print(dp)
        return dp[-1]

def main():
    return solve()


if __name__ == '__main__':
    main()
