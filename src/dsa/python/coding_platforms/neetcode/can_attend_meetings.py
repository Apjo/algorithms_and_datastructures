"""
Filename: can_attend_meetings.py
Date: 2026-10-25
"""


class Solution:
    class Interval:
        def __init__(self, start, end):
            self.start = start
            self.end = end
    def canAttendMeetings(self, intervals: list[Interval]) -> bool:
        #just sort the meetings by start time. then iterate over the sorted list to determine whether there is any overlap between 2 consecutive meetings. if there is one, return False. Else at the end we return True
        s_i = sorted(intervals, key=lambda x: x.start)
        N = len(s_i)
        for i in range(N):
            if i + 1 < N and s_i[i].end > s_i[i + 1].end:
                return False
        return True
        


if __name__ == '__main__':
    Solution().solve()