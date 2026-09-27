#link: https://leetcode.com/problems/button-with-longest-push-time/description/?envType=company&envId=jpmorgan&favoriteSlug=jpmorgan-all

from typing import List

class Solution:
    def buttonWithLongestTime(self, events: List[List[int]]) -> int:
        curr_max = events[0][1]
        ans=events[0][0]
        for i in range(1, len(events)):
            idx, time_val = events[i][0], events[i][1]
            time_taken = time_val - events[i-1][1]
            if time_taken > curr_max:
                curr_max = time_taken
                ans = idx
            elif time_taken == curr_max:
                curr_max = time_taken
                ans = min(ans, idx)
        return ans