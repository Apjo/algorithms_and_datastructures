#link: https://leetcode.com/problems/merge-intervals/description/
from typing import List

class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        res = []
        sorted_intervals = sorted(intervals, key=lambda x: x[0])
        N = len(sorted_intervals)
        for i in range(N):
            if not res or res[-1][1] < sorted_intervals[i][0]:
                res.append(sorted_intervals[i])
            else:
                res[-1][1] = max(res[-1][1], sorted_intervals[i][1])
        return res