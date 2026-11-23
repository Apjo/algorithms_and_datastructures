"""
Filename: prims_impl.py
Date: 2026-08-08

Given a weighted,undirected graph representing n cities, and the direct road connection costs between them our goal is to
Find a way to connect all cities such that:
- the total connection cost is minimized
- resulting structure is connected, and acyclic

Key Minimum spanning tree properties:
- must span all vertices
- no cycles
- total edge cost is minimized

the way Prims works is by using a greedy strategy of growing by a vertex.

At the heart of MST algorithms is the greedy strategy:
- At each step, add the next cheapest edge that doesn't form a cycle.
- Maintain connectivity without cycles ⇒ ensures tree structure.

Key principles:
Optimal substructure: Any subtree of an MST is itself an MST for its vertices.
Greedy choice property => globally optimal solution built from locally optimal steps.

Detailed insight:
● If adding an edge creates a cycle ⇒ skip it.
● Edge exchange argument: If a cheaper edge could replace an edge in a spanning tree, it would reduce the total cost => Kruskal's correctness.

"""

import heapq
from typing import List


class Solution:
    # time: O(E+VlogE), space: O(v+e)
    def solve(self, edges: List[List[int]], n: int) -> int:
        """
        We will be starting at any random vertex say k, and lets init k=0
        will also maintain a visited/captured array, starting with all initial values for all V vertices being False,then we will set for captured[k]=True
        build the graph given V number of vertices
        Each edge E will have the source vertex u, connected to a destination vertex v with a cost of the edge being c, so if we had a adj list
        adjlist[u].append((v, cost)) would be the way to represent our Graph.
        """
        # build graph
        G = [[] for _ in range(n)]
        for edge in edges:
            G[edge[0]].append((edge[1], edge[2]))
            G[edge[1]].append((edge[0], edge[2]))
        # build a captured array
        captured = [False] * (n)
        start_vertex = 0
        captured[start_vertex] = True
        min_heap = []
        total_cost = 0

        # Add all neighbord for start vertex 0 to a min heap sorted based on weight of the edge
        for neighbor, edge_cost in G[start_vertex]:
            heapq.heappush(min_heap, (edge_cost, neighbor))
        """
        overall complexity
        m=edges, n=vertices
        initialization would be linear O(m+n)
        
        each node is added multiple times, heap size will be O(m), so m insertons, every time we insert, we are also going to pop out m times
        each insert, and delete is logm, so total would be O(mlogm)
        """
        while min_heap:
            edge_cost, curr_node = heapq.heappop(min_heap)
            # if this node is already captured, ignore
            if captured[curr_node]:
                continue
            captured[curr_node] = True
            total_cost += edge_cost
            for neighbor, cost in G[curr_node]:
                # neighbor is not captured
                if not captured[neighbor]:
                    heapq.heappush(min_heap, (cost, neighbor))

        return total_cost

    # adjacency matrix with a PQ, but this time print out the MST output for every node
    """
    Notes on time and space complexities:
    For a dense graph (E ≈ V²):
        Adjacency matrix is reasonable: O(V²) time and space
        Adjacency list would be O(V²) anyway (storing all those edges)
    
    For a sparse graph (E ≈ V or E ≈ V log V):
        Adjacency matrix wastes space and time: O(V²) is overkill
        Adjacency list is much better: O((V + E) log E) ≈ O(V log V)
    space:
        O(V) for captured
        O(M*N) for mst_output
        O(E) for the min heap
    """

    def prims_adj_mat(self, G):
        start_vertex = 0
        M, N = len(G), len(G[0])
        mst_output = [[0 for _ in range(N)] for _ in range(M)]

        min_heap = []

        captured = [False] * (M)
        captured[start_vertex] = True

        def print_mst():
            print("Edge \tWeight")
            for u in range(M):
                for v in range(u + 1, N):
                    if mst_output[u][v] > 0:
                        print(u, "-", v, "\t", mst_output[u][v])

        # push the neighbors of the start vertex with their costs onto the heap!
        for i in range(len(G[start_vertex])):
            if i != start_vertex and G[start_vertex][i] > 0:
                # print(
                #     f"neighbor={i}, value={G[start_vertex][i]} of starting node={start_vertex}"
                # )
                heapq.heappush(min_heap, (G[start_vertex][i], start_vertex, i))
        """
        We use a min-heap. In the worst case with an adjacency matrix:
        We have V vertices
            Each vertex can have up to V neighbors
            So you could push up to O(E) edges onto the heap (where E is the number of edges)
            Each heap operation (push/pop) is O(log E)
        So: O(E log E) for heap operations.
        You also iterate through each vertex's neighbors once (when exploring): O(V²) in the worst case for an adjacency matrix (you look at every cell).
        Total: O(V² + E log E)
        """
        while min_heap:
            cost, curr_parent, curr_node = heapq.heappop(min_heap)
            if captured[curr_node]:
                continue

            captured[curr_node] = True
            mst_output[curr_parent][curr_node] = cost
            mst_output[curr_node][curr_parent] = cost

            for neighbor_index in range(len(G[curr_node])):
                if not captured[neighbor_index] and G[curr_node][neighbor_index] > 0:
                    heapq.heappush(
                        min_heap,
                        ((G[curr_node][neighbor_index], curr_node, neighbor_index)),
                    )

        print_mst()
        return mst_output


if __name__ == "__main__":
    min_cost = Solution().solve(edges=[[0, 1, 5], [1, 2, 3], [0, 2, 1]], n=3)
    # print(f"min cost using prims with edge list={min_cost}")
    assert min_cost == 4
    G = [
        [0, 9, 75, 0, 0],
        [9, 0, 95, 19, 42],
        [75, 95, 0, 51, 66],
        [0, 19, 51, 0, 31],
        [0, 42, 66, 31, 0],
    ]
    # print(G[0])
    print(f"Prims algo with input as an adjacency matrix, input graph={G}\n")
    Solution().prims_adj_mat(G)
    # print(f"MST from input grid of len={len(G)} is={op}")
