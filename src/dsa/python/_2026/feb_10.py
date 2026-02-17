# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/17/26
link https://leetcode.com/problems/longest-balanced-subarray-i/description/?envType=daily-question&envId=2026-02-17
"""
from typing import List

class Solution:
    def longestBalanced(self, nums: List[int]) -> int:
        ans=0
        mp={}
        for i in range(len(nums)):
            all_even, all_odd = set(), set()
            for j in range(i, len(nums)):
                #if nums[i] not in mp and nums[j] not in mp:
                #both even
                if nums[j] % 2 == 0:
                    all_even.add(nums[j])
                else:
                    all_odd.add(nums[j])
                if len(all_even) == len(all_odd):
                    ans=max(ans, j - i + 1)
        return ans

def main():
    return Solution().longestBalanced([2,5,4,3])


if __name__ == '__main__':
    main()
