# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/8/26
link:https://leetcode.com/problems/minimum-removals-to-balance-array/?envType=daily-question&envId=2026-02-06
"""
from typing import List

class Solution:
    def minRemoval(self, nums: List[int], k: int) -> int:

        nums.sort()
        N = len(nums)
        ans,i=1,0
        for j in range(N):
            while nums[j] > k * nums[i]:
                i+=1

            ans=max(ans, (j - i + 1))
            j+=1
        return N - ans

def main():
    return Solution().minRemoval([2,1,5], k=2)


if __name__ == '__main__':
    main()
