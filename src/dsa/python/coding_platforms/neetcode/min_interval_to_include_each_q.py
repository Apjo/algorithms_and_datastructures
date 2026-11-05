"""
Filename: min_interval_to_include_each_q.py
Date: 2026-10-27
"""

import heapq


class Solution:
    def minInterval(self, intervals: list[list[int]], queries: list[int]) -> list[int]:
        mp = {}
        N = len(intervals)
        intervals.sort()
        i = 0
        min_h = []
        for q in sorted(queries):
            while i < N and intervals[i][0] <= q:
                st, end = intervals[i]
                heapq.heappush(min_h, (end - st + 1, end))
                i += 1
            while min_h and min_h[0][0] < q:
                heapq.heappop(min_h)
            mp[q] = min_h[0][1] if min_h else -1

        return [mp[q] for q in queries]


if __name__ == "__main__":
    Solution().solve()
