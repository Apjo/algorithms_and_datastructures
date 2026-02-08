# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/4/26
link: https://leetcode.com/problems/longest-increasing-subsequence/description/
"""
from typing import List
#time: O(2^N)
def lengthOfLIS(nums: List[int]) -> int:
    def solve(index, prev):
        if index >= len(nums):
            return 0
        #dont pick element at index index
        dont_pick = solve(index + 1, prev)
        pick=0
        if nums[index] > prev:
            #pick this element
            pick = 1 + solve(index+1, nums[index])
        return max(dont_pick, pick)
    return solve(0, float("-inf"))

#time: O(n^2)
def lengthOfLIS_memo(nums: List[int]) -> int:
    memo = {}
    def solve(index, prev_index):
        if index >= len(nums):
            return 0
        if prev_index!=-1 and (index, prev_index) in memo:
            return memo[(index, prev_index)]
        #dont pick element at index index
        dont_pick = solve(index + 1, prev_index)
        pick=0
        if prev_index == -1 or nums[index] > nums[prev_index]:
            #pick this element
            prev_index = index
            pick = 1 + solve(index+1, prev_index)

        memo[(index, prev_index)] = max(dont_pick, pick)
        return max(dont_pick, pick)
    return solve(0, -1)

def main():
    assert lengthOfLIS([0,1,0,3,2,3]) == 4
    assert lengthOfLIS([7,7,7,7,7]) == 1
    assert lengthOfLIS_memo([0,1,0,3,2,3]) == 4
    assert lengthOfLIS_memo([10,9,2,5,3,7,101,18]) == 4
    assert lengthOfLIS_memo([7,7,7,7,7]) == 1


if __name__ == '__main__':
    main()
