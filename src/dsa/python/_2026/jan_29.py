# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/30/26
"""
#link https://leetcode.com/problems/minimum-cost-to-convert-string-i/description/?envType=daily-question&envId=2026-01-30

from typing import List
class Solution:
    def minimumCost(self, source: str, target: str, original: List[str], changed: List[str], cost: List[int]) -> int:
        #build a adj list of all the 26 letters of the alphabets
        #use the source and target/cost arrays to build relationships aka edges associated with the respective costs
        #run dijkstra over the letters to find ssp from each letter to every other letter, store in a buffer
        #iterate over the source str
        #if it source[i] != target[i]
        #use the min dist from dijkstra stored in the buffer, and add to total cost if cost is !=-1 else return -1
        #finally return total cost

        def build_graph():
            adj_list=[[] for _ in range(26)]
            N = len(original)
            for i in range(N):
                adj_list[ord(original[i]) - ord("a")].append((ord(changed[i]) - ord("a"), cost[i]))
            return adj_list
        def run_dijk_sssp(start, adj_list):
            import heapq
            all_costs=[float("inf")] * 26
            pq=[(0, start)]
            while len(pq) > 0:
                curr_cost, curr_node = heapq.heappop(pq)
                #if we have already found the current mis cost for curr_node, continue
                if all_costs[curr_node] != float("inf"):
                    continue
                all_costs[curr_node] = curr_cost
                for neighbor, conversion_cost in adj_list[curr_node]:
                    new_cost = curr_cost + conversion_cost
                    if all_costs[neighbor] == float("inf"):
                        heapq.heappush(pq, (new_cost, neighbor))
            return all_costs
        graph = build_graph()
        # for i in range(26):
        all_costs = [run_dijk_sssp(i, graph) for i in range(26)]
        ans=0
        for s, t in zip(source, target):
            if s != t:
                cost_to_transform = all_costs[ord(s) - ord("a")][ord(t) - ord("a")]
                if cost_to_transform == float("inf"):
                    return -1
                ans+=cost_to_transform
        return ans


def main():
    sol = Solution()
    return sol.minimumCost(source = "abcd", target = "acbe", original = ["a","b","c","c","e","d"], changed = ["b","c","b","e","b","e"], cost = [2,5,5,1,2,20])


if __name__ == '__main__':
    main()
