"""
Filename: meeting_rooms.py
Date: 2026-10-25
"""

import heapq


class Solution:
    class Interval:
        def __init__(self, start, end):
            self.start = start
            self.end = end

    def minMeetingRooms(self, intervals: list[Interval]) -> int:
        ans, N = 0, len(intervals)
        sorted_i = sorted(intervals, key=lambda x: x.start)
        min_h = []
        for i in range(N):
            heapq.heappush(min_h, sorted_i[i].end)
            while i + 1 < N and min_h and min_h[0] <= sorted_i[i].start:
                heapq.heappop(min_h)

        return ans


if __name__ == "__main__":
    Solution().solve()
