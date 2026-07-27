"""
Filename: july_23.py
Date: 2026-07-24
link: https://leetcode.com/problems/number-of-unique-xor-triplets-i/
"""

from typing import List


class Solution:
    def uniqueXorTriplets(self, nums: List[int]) -> int:
        ans = 0
        if len(nums) <= 2:
            return len(nums)
        for i in range(len(nums)):
            ans |= nums[i]
        return ans + 1


if __name__ == "__main__":
    Solution().solve()
