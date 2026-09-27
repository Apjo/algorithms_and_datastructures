#link: https://leetcode.com/problems/divide-players-into-teams-of-equal-skill/?envType=company&envId=jpmorgan&favoriteSlug=jpmorgan-all

from collections import defaultdict
from typing import List

class Solution:
    def dividePlayers(self, skill: List[int]) -> int:
        freq=defaultdict(list)
        temp = sorted(skill)
        lo, hi = 0, len(temp) - 1
        while lo < hi:
            ss = temp[lo] + temp[hi]
            freq[ss].append((temp[lo], temp[hi]))
            lo+=1
            hi-=1
        if len(freq) > 1:
            return -1
        else:
            return sum(l * r for l, r in freq[ss])
