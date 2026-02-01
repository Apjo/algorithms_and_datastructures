# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/30/26
"""
import heapq
from collections import defaultdict
from typing import List
#link: https://leetcode.com/problems/minimum-cost-path-with-edge-reversals/?envType=daily-question&envId=2026-01-29
class Solution:
    def minCost(self, n: int, edges: List[List[int]]) -> int:
        #build the graph using u -> v and w
        #also provide v -> and 2*w
        adj_list =defaultdict(list)
        for i in range(len(edges)):
            adj_list[edges[i][0]].append((edges[i][1], edges[i][2]))
            adj_list[edges[i][1]].append((edges[i][0], 2 * edges[i][2]))

        def solve_dijk_sssp(adj, src):

            cost_to_get_to = [float('inf')]*(n)
            cost_to_get_to[src] = 0
            pq=[]
            visited = set()

            for node in adj:
                heapq.heappush(pq, (cost_to_get_to[node], node))
            while len(pq) > 0:
                (cheapest_node, cost) = heapq.heappop(pq)
                for neighbor, edge_cost in adj[cheapest_node]:
                    new_distance = cost_to_get_to[cheapest_node] + edge_cost
                    if new_distance < cost_to_get_to[neighbor]:
                        cost_to_get_to[neighbor] = new_distance
                        heapq.heappush(pq, (neighbor, new_distance))

            return cost_to_get_to[n - 1] if cost_to_get_to[n - 1] != float('inf') else -1


        return solve_dijk_sssp(adj_list, 0)

        #run dijk from node 0 to node n - 1
        # while running dijk, when we come across a node we usually do
        #if cost_to_get_to[cheapest_node] + edge_cost < cost_to_get_to[neighbor]:
        #cost_to_get_to[neighbor] = cost_to_get_to[cheapest_node] + edge_cost
        #else:
        #we apply the 2*w flip, and check the same constraint, if it passes then we update the cost for this neighbor
        #finally return the cost_to_get_to


def main():
    return solve()


if __name__ == '__main__':
    main()
