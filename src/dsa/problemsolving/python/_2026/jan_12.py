# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/14/26
"""

from typing import List
class Solution:
    def minTimeToVisitAllPoints(self, points: List[List[int]]) -> int:
        if not points:
            return 0
        ans,M, N = 0,len(points), len(points[0])
        for i in range(M-1):
            p1 = points[i]
            p2 = points[i+1]
            dx = abs(p1[0] - p2[0])
            dy = abs(p1[1] - p2[1])
            if dx==dy:
                ans+=dx
            else:
                ans+=max(dx,dy)
        return ans


def main():
    return solve()


if __name__ == '__main__':
    main()
