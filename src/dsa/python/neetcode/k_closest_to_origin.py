"""
Filename: k_closest_to_origin.py
Date: 2026-06-02
link: https://neetcode.io/problems/k-closest-points-to-origin/question
"""

import heapq
from typing import List


class Solution:
    # time: O(NlogK) space: O(K)
    def solve(self, k, points: List[List[int]]):
        min_heap = []
        for point in points:
            dd = point[0] ** 2 + point[1] ** 2
            rec = (-dd, point[0], point[1])
            heapq.heappush(min_heap, rec)
            if len(min_heap) > k:
                heapq.heappop(min_heap)
        res = []
        while min_heap:
            _, second, third = heapq.heappop(min_heap)
            res.append([second, third])
        return res


if __name__ == "__main__":
    Solution().solve()
