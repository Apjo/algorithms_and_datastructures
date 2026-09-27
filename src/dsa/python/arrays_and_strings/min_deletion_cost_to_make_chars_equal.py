#link: https://leetcode.com/problems/minimum-deletion-cost-to-make-all-characters-equal/

from typing import List
class Solution:
    def minCost(self, s: str, cost: List[int]) -> int:
        #sum up all costs
        ss = sum(cost)
        freq=[0]*26
        max_cost=0
        #we keep the max cost, and get rid of all other
        for i in range(len(s)):
            int_char = ord(s[i]) - ord('a')
            freq[int_char]+=cost[i]
            max_cost = max(max_cost, freq[int_char])
        #total deletion cost = sum of all cost - maximum total cost of 1 character kept
        return ss - max_cost