"""
Filename: merge_intervals.py
Date: 2026-10-24
"""


class Solution:
    def merge(self, intervals: list[list[int]]) -> list[list[int]]:
        res = []
        N = len(intervals)
        sorted_intervals = sorted(intervals, key=lambda x: x[0])
        for i in range(N):
            if not res or res[-1][1] < sorted_intervals[i][0]:
                res.append(sorted_intervals[i])
            else:
                res[-1][1] = max(res[-1][1], sorted_intervals[i][1])
        return res


if __name__ == "__main__":
    Solution().solve()
