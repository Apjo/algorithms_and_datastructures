"""
Filename: min_number_of_stops.py
Date: 2026-08-14
"""

import os
import time
import pandas as pd
import numpy as np
import heapq
import math
import collections
from typing import Optional, List
import random
from collections import deque, defaultdict, Counter

# version 1
class Solution:
    def findCheapestPrice(
        self, n: int, flights: List[List[int]], src: int, dst: int, k: int
    ) -> int:
        # build graph of {from : (to, cost)}
        G = defaultdict(list)
        for flight in flights:
            G[flight[0]].append((flight[1], flight[2]))
        # set distance matrix for source to find destination
        dist = [float("inf")] * (n)
        dist[src] = 0
        bfs_q = deque()
        # q will hold (curr cost, curr node, curr stop num)
        bfs_q.append((0, src, k + 1))
        """
        WHY THIS WORKS?
        At each iteration, you process all nodes currently in the queue. When a node's stops are exhausted (curr_stop_num <= 0), you skip its neighbors but don't poison the search—other nodes at the same level continue processing. The curr_cost in the tuple keeps each path's cost isolated to its stop depth, preventing the corruption you had before.
        """
        # apply dijkstra!
        while bfs_q:
            N = len(bfs_q)
            for _ in range(N):
                curr_cost, curr_node, curr_stop_num = bfs_q.popleft()
                if curr_stop_num <= 0:
                    # Skip this node's neighbors, but keep processing other nodes in the queue.
                    continue
                # why not check if we have already seen this node?
                """
                Ans:there might be multiple paths to the same node at the same stop level, but by the time you process it (or even if you process it multiple times), dist[neighbor] already reflects the best cost found so far, so redundant enqueues get filtered.
                """
                for neighbor, neighbor_cost in G[curr_node]:
                    # print(f"looking at neighbor={neighbor}, with cost={neighbor_cost}")
                    new_cost = curr_cost + neighbor_cost
                    # print(f"new cost={new_cost}")
                    if new_cost < dist[neighbor]:
                        # print("updating neighbor's cost, and reducing stop by 1")
                        dist[neighbor] = new_cost
                        bfs_q.append((new_cost, neighbor, curr_stop_num - 1))

        return dist[dst] if dist[dst] != float("inf") else -1


if __name__ == '__main__':
    Solution().solve()