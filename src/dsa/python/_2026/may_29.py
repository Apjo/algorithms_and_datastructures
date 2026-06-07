"""
Filename: june_2.py
Date: 2026-06-05
"""

from typing import List

class Solution:
    def solve(self, nums: List[int]):
        min_elem = min(nums)
        
        sums = []
        for n in nums:
            min_elem = n
            inner_sum = 0
            while min_elem:
                dd = min_elem % 10
                inner_sum += dd
                min_elem //= 10
            sums.append(inner_sum)
        
        return min(sums)


if __name__ == '__main__':
    Solution().solve()