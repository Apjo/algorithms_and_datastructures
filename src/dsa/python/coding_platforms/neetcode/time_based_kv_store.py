#link: https://neetcode.io/problems/time-based-key-value-store/question
from typing import List
from collections import defaultdict

class TimeMap:
    
    def __init__(self):
        self.adj= defaultdict(list)

    def set(self, key: str, value: str, timestamp: int) -> None:
        self.adj[key].append((value, timestamp))

    def get(self, key: str, timestamp: int) -> str:
        if key not in self.adj:
            return ""
        all_ts = self.adj[key]
        def search(lo, hi) -> str:
            ans = -1
            curr_loc = 0
            while lo <= hi:
                mid = (lo + hi) // 2
                if all_ts[mid][1] <= timestamp:
                    ans = all_ts[mid][1]
                    curr_loc = mid
                    lo = mid + 1
                else:
                    hi = mid - 1

            return "" if ans == -1 else all_ts[curr_loc][0]
        return search(0, len(all_ts) - 1)
    