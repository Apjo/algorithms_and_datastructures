"""
Filename: second_largest.py
Date: 2026-09-12
link: https://www.geeksforgeeks.org/batch/gfg-160-problems/track/arrays-gfg-160/problem/second-largest3735
"""


class Solution:
    def getSecondLargest(self, arr):
        if not arr:
            return -1
        N = len(arr)
        if N == 1:
            return arr[0]
        l, sl = float("-inf"), float("-inf")
        for i in range(N):
            if arr[i] > l:
                sl = l
                l = arr[i]
            if sl == float("-inf") and arr[i] < l:
                sl = arr[i]
            if sl != float("-inf") and arr[i] > sl and arr[i] < l:
                sl = arr[i]
        return sl if sl != float("-inf") else -1


if __name__ == "__main__":
    Solution().solve()
