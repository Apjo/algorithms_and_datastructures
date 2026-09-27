"""
Filename: graph_valid_tree.py
Date: 2026-08-04
"""

from typing import List

class Solution:
    def validTree(self, n: int, edges: List[List[int]]) -> bool:
        # basically cycle in an undirected graph
        # build the graph
        # use 3 sets, white: all current nodes all unvisited
        # grey: visiting set
        # black:to mark visited ones
        # while performing the search i.e. DFS/BFS we first add the current node to grey set, then search for neighbors
        # if the current neighbor is in grey, there is a cycle, else continue exploring.
        # if after doing a search we see that there is a cycle anyhwere else in the Graph, we return
        # else upon completion we remove from white, remove from grey, and add to black
        # at the end total number of nodes in input graph == total size of black set, if size matches, the graph is a valid tree, else not
        if not edges and n > 0:
            return True
        if len(edges) != n - 1:
            return False

        E, white, grey, black = len(edges), set(), set(), set()
        G = [[] for _ in range(n)]
        for edge in edges:
            white.add(edge[0])
            white.add(edge[1])
            G[edge[0]].append(edge[1])
            G[edge[1]].append(edge[0])
        print(f"input graph={G}")

        def solve(vertex):
            grey.add(vertex)
            for neighbor in G[vertex]:
                if neighbor in black:
                    # continue
                    return True
                if neighbor in grey:
                    # return True
                    continue

                # if neighbor not in black:
                ans = solve(neighbor)
                if ans:
                    return True
            grey.discard(vertex)
            black.add(vertex)
            return False

        for i in range(n):
            print(f"starting at vertex={i}, grey={grey}, black={black}, white={white}")
            if i not in black:
                white.discard(i)
                ans = solve(i)
                if ans:
                    return False
        # print(black)
        # return n == len(black)
        return True



if __name__ == '__main__':
    Solution().solve()