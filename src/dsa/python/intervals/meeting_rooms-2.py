#link: https://leetcode.com/problems/meeting-rooms-ii/description/?envType=company&envId=jpmorgan&favoriteSlug=jpmorgan-all
import heapq
from typing import List

class Solution:
    #time: O(nlogn)
    def minMeetingRooms(self, intervals: List[List[int]]) -> int:
        minh = []
        ans=0
        sorted_intervals = sorted(intervals, key=lambda x: x[0])
        for i in range(len(sorted_intervals)):
            heapq.heappush(minh, sorted_intervals[i][1])
            ans = max(ans, len(minh))
            while i+1 < len(sorted_intervals) and sorted_intervals and sorted_intervals[0] <= intervals[i + 1][1]:
                heapq.heappop(minh)
        return ans
            
            