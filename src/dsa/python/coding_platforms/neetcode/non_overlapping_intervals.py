"""
Filename: non_overlapping_intervals.py
Date: 2026-10-25
"""


class Solution:
    def eraseOverlapIntervals(self, intervals: list[list[int]]) -> int:
        ans, N = 0, len(intervals)
        sorted_int = sorted(intervals, key=lambda x: x[0])
        prev_end = sorted_int[0][1]

        for i in range(1, N):
            if sorted_int[i][0] >= prev_end:
                prev_end = sorted_int[i][1]
            else:
                ans += 1
                prev_end = min(prev_end, sorted_int[i][1])

        return ans


if __name__ == "__main__":
    Solution().solve()
