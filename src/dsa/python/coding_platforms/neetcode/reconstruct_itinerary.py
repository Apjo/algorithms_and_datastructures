"""
Filename: reconstruct_itinerary.py
Date: 2026-08-09
"""

import heapq
from typing import List
from collections import defaultdict

class Solution:
    def findItinerary(self, tickets: List[List[str]]) -> List[str]:
        # maintain a graph where the key of the map is the airport str, and the value is the priority queue containing the destination airports for the key
        # keep on applying DFS until you run out of nodes, and their neighbors
        G = defaultdict(list)
        for ticket in tickets:
            from_airport, to_airport = ticket[0], ticket[1]
            heapq.heappush(G[from_airport], to_airport)
        res = []

        def dfs(v):
            while v in G and G[v]:
                dfs(heapq.heappop(G[v]))
            res.append(v)

        dfs("JFK")
        temp = res[::-1]
        return temp
        


if __name__ == '__main__':
    Solution().solve()