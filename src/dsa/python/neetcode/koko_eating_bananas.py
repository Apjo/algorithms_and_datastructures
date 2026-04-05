#link: https://neetcode.io/problems/eating-bananas/question

from typing import List

class Solution:
    def minEatingSpeed(self, piles: List[int], h: int) -> int:
        N = len(piles)
        if N > h:
            return 0
        def solve(mid_val):
            ans=0
            for i in piles:
                if i % mid_val != 0:
                    ans+=1
                ans+=(i // mid_val)
            return ans <= h
        ans,lo, hi = 0, 1, max(piles)
        while lo <= hi:
            mid = (lo + hi) // 2
            if solve(mid):
                ans=mid
                hi = mid-1
            else:
                lo = mid + 1
        return ans