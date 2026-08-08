"""
Filename: redundant_connection.py
Date: 2026-08-08
"""

from typing import List
from collections import deque

class Solution:
    """
    approach 1, detect the start of the cycle, and all nodes contributing to it.
    time: O(V + E), space: O(V + E)
    approach 2, determine indegrees of the vertices, and apply Kahns algo
    time: O(V + E), space: O(V + E)
    approach 3, simple DFS to detect cyles in a graph
    time: O(V*(V + E)), space: O(V + E)
    approach 4, using DSU, first edge for which union doesn't happen will be our output edge
    time: O(V + E), space: O(V + E)
    """
    #approach1
    def findRedundantConnection(self, edges: List[List[int]]) -> List[int]:
        cycle_start = -1
        all_nodes_in_cycle = []
        # build graph
        n = len(edges)
        G = [[] for _ in range(n)]
        visited = [False] * (n)

        for edge in edges:
            G[edge[0]].append(edge[1])
            G[edge[1]].append(edge[0])

        def dfs(vertex, parent=-1):
            nonlocal cycle_start
            # we found a cycle, set this vertex to be the start of it
            if visited[vertex]:
                cycle_start = vertex
                return
            visited[vertex] = True
            for neighbor in G[vertex]:
                # if this neighbor == parent, continue
                if neighbor == parent:
                    continue
                # if the vertex == cycle_start we are back to start of cycle, reset cycle_start to -1, and return
                if vertex == cycle_start:
                    cycle_start = -1
                    return
                # if cycle_start != -1 we know there exists a cycle, keep on adding this vertex to the all_nodes_in_cycle list
                if cycle_start != -1:
                    all_nodes_in_cycle.append(vertex)
                if len(all_nodes_in_cycle) == 0:
                    # all_nodes_in_cycle is empty, keep on exploring, as we haven't found a cycle
                    dfs(neighbor, vertex)

        # apply dfs, and build the cycle when detected
        # iterate over the edges in reverse since we want the last matching edge, and if both the nodes in the all_nodes_in_cycle are present in the input edges list, we immediately return them
        for i in range(n, -1, -1):
            if edges[i - 1][0] in all_nodes_in_cycle and edges[i - 1][1] in all_nodes_in_cycle:
                return edges[i - 1]
        return []
    
    #approach 2 using kahns algo
    def findRedundantConnection2(self, edges: List[List[int]]) -> List[int]:
        n = len(edges)
        G = [[] for _ in range(n + 1)]
        indeg = [0]*(n+1)
        # outdeg = [0]*(n)
        for edge in edges:
            G[edge[0]].append(edge[1])
            G[edge[1]].append(edge[0])
            indeg[edge[0]]+=1
            indeg[edge[1]] += 1
        q = deque()
        #add to q all those nodes whose indeg ==1
        for i in range(1, n + 1):
            if indeg[i] == 1:
                q.append(i)
        while q:
            curr = q.popleft()
            #decrement indegree of this node by 1
            indeg[curr]-=1
            for neighbor in G[curr]:
                #decrement indegree of neighbor node by 1
                indeg[neighbor]-=1
                if indeg[neighbor]==1:
                    q.append(neighbor)
        #nodes with degree > 0 are forming a cycle, we traverse our input edge list in reverse, pick the first set of nodes where indeg[u] > 0 and indeg[v] > 0
        for u, v in reversed(edges):
            if indeg[u] > 0 and indeg[v] > 0:
                return [u, v]
    
    #approach 3 using simple DFS at CREATION TIME!!!! time: O(E*(V+E))
    def findRedundantConnection3(self, edges: List[List[int]]) -> List[int]:
        n = len(edges)
        G = [[] for _ in range(n + 1)]
        visited = [False] * (n + 1)
        
        def dfs(vertex, parent):
            if visited[vertex]:
                return True
            visited[vertex] = True
            for neighbor in G[vertex]:
                if neighbor == parent:
                    continue
                if dfs(neighbor, vertex):
                    return True
            return False
        #Everytime we add an edge to the Graph we check to see if this edge was responsible for a cyle, if yes, return this edge, else create the graph, and return an empty list
        for edge in edges:
            G[edge[0]].append(edge[1])
            G[edge[1]].append(edge[0])
            if dfs(edge[0], -1):
                return [edge[0], edge[1]]
        return []

    #approach 4 using DSU
    def findRedundantConnection4(self, edges: List[List[int]]) -> List[int]:
        n = len(edges)
        #each node first is the parent of itself
        parent = [i for i in range(n + 1)]
        #initiall rank of all nodes is 1
        rank = [1]*(n + 1)
        
        def find(node):
            if node == parent[node]:
                return node
            node_to_ret = find(parent[node])
            return node_to_ret
        def union(n1, n2):
            p1, p2 = find(n1), find(n2)
            if p1 == p2:
                return False
            if rank[p1] > rank[p2]:
                parent[p2] = p1
                rank[p1]+=rank[p2]
            else:
                parent[p1] = p2
                rank[p2] += rank[p1]
            return True
        
        for u, v in edges:
            if not union(u, v):
                return [u, v]
        return []

if __name__ == "__main__":
    Solution().solve()
