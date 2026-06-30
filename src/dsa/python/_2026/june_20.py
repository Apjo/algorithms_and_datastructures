"""
Filename: june_20.py
Date: 2026-06-30
link: https://leetcode.com/problems/maximum-building-height/description/?envType=daily-question&envId=2026-06-20
"""

from typing import List

class Solution:
    def maxBuilding(self, n: int, restrictions: List[List[int]]) -> int:
        # if restrictions is empty, return n - 1
        # else, apply brute force/simulate whats required
        # add restriction for building 0, and building n
        restrictions.extend([[1, 0], [n, n - 1]])
        # sort restrictions based on building index
        sorted_restrictions = sorted(restrictions, key=lambda x: x[0])

        N = len(sorted_restrictions)
        # iterate from L to R, and for each restriction ans[i] = min(ht[i], ht[i - 1] + dist)
        for i in range(1, N):
            curr = sorted_restrictions[i]  # form (i, hi)
            prev = sorted_restrictions[i - 1]  # form (j, hj)
            # The restriction (i,hi​) propagates to building j as (j, hi + (j − i)).
            # this propogates all restrictions coming from left
            curr[1] = min(curr[1], prev[1] + (curr[0] - prev[0]))
        # similarly iterate from R to L, and for each restriction ans[i] = min(ht[i], ht[i + 1] + dist)
        for i in range(N - 2, -1, -1):
            curr = sorted_restrictions[i]  # form (i, hi)
            next_r = sorted_restrictions[i + 1]  # form (j, hj)
            # The restriction (j,hj​) propagates to building i as (i, hj + (j−i)).
            # this propogates all restrictions coming from right
            curr[1] = min(curr[1], next_r[1] + (next_r[0] - curr[0]))
        """
        after processing the restrictions:
        We compute the peak between each pair of adjacent elements.
        Finally, determine the global maximum.
        read here for calculating ypeak, link https://leetcode.com/problems/maximum-building-height/solutions/8346225/maximum-building-height-greedy-linear-al-4sar/?envType=daily-question&envId=2026-06-20
        """
        ans = 0

        def y_peak(x1, y1, x2, y2) -> int:
            return (y1 + y2 + x2 - x1) // 2

        for i in range(1, N):
            x1, h1 = sorted_restrictions[i - 1]
            x2, h2 = sorted_restrictions[i]
            curr_peak = y_peak(x1, h1, x2, h2)
            ans = max(ans, curr_peak)

        return ans


if __name__ == '__main__':
    Solution().solve()