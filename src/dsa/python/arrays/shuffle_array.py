"""
Filename: shuffle_array.py
Date: 2026-08-30
"""

from typing import List

class Solution:
    #similar to using 2 pointers, but we simply iterate over n
    def shuffle_2(self, nums: List[int], n: int) -> List[int]:
        b = []
        for i in range(n):
            b.append(nums[i])
            b.append(nums[i + n])

        return b

    #use 2 pointers one from 0 to n, and the other moving from n + 1 to 2*n
    def shuffle(self, nums: List[int], n: int) -> List[int]:
        L = 2 * n
        p1, p2 = 0, n
        b = []
        while p1 < n and p2 < L:
            b.append(nums[p1])
            b.append(nums[p2])
            p1 += 1
            p2 += 1
        return b
        


if __name__ == '__main__':
    Solution().solve()