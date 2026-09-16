"""
Filename: reverse_array.py
Date: 2026-09-14
link https://www.geeksforgeeks.org/batch/gfg-160-problems/track/arrays-gfg-160/problem/reverse-an-array
"""


class Solution:
    def reverseArray(self, arr):
        lo, hi = 0, len(arr) - 1
        while lo <= hi:
            arr[lo], arr[hi] = arr[hi], arr[lo]
            lo += 1
            hi -= 1


if __name__ == "__main__":
    Solution().solve()
