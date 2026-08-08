"""
Filename: network_delay_time.py
Date: 2026-08-08
"""

import heapq
from typing import List


class Solution:
    # time: O(ElogV) space: O(V+E)
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        # build graph
        G = [[] for _ in range(n)]
        for time in times:
            # print(f"Current source node={time[0]}, dest node={time[1]}")
            # print(f"will be adding to graph source={time[0] - 1}, dest={time[1] - 1}")
            G[time[0] - 1].append((time[1] - 1, time[2]))

        captured = [False] * (n)
        captured[k - 1] = True

        min_heap = []
        heapq.heappush(min_heap, (0, k - 1))

        distance = [-1] * (n)
        distance[k - 1] = 0

        while min_heap:
            priority, node = heapq.heappop(min_heap)
            if distance[node] < priority:
                continue
            for neighbor, edge_wt in G[node]:
                updated_dist = edge_wt + distance[node]
                if distance[neighbor] == -1 or updated_dist < distance[neighbor]:
                    distance[neighbor] = updated_dist
                    heapq.heappush(min_heap, (updated_dist, neighbor))
        # print(distance)
        ans = float("-inf")
        for d in distance:
            if d == -1:
                return d
            ans = max(ans, d)
        # print(ans)
        return ans


if __name__ == "__main__":
    Solution().solve()
