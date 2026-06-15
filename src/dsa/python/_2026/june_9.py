"""
Filename: june_9.py
Date: 2026-06-13
link https://leetcode.com/problems/maximum-total-subarray-value-i/?envType=daily-question&envId=2026-06-10
"""

from typing import List

class Solution:
    def maxTotalValue(self, nums: List[int], k: int) -> int:
        return k *(max(nums) - min(nums))
        


if __name__ == '__main__':
    Solution().solve()