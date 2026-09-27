"""
Filename: majority_element.py
Date: 2026-09-17
"""

import math



class Solution:
    def findMajority(self, arr):
        freq = {}
        N = len(arr)
        res = []
        for n in arr:
            if n in freq:
                freq[n] += 1
            else:
                freq[n] = 1
        ans = int(math.floor(N / 3))
        for k, v in freq.items():
            if ans < v:
                res.append(k)

        res.sort()
        return res


if __name__ == '__main__':
    Solution().solve()