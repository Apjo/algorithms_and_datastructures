"""
Filename: clone_graph.py
Date: 2026-07-29
"""

from typing import Optional
from collections import deque


# Definition for a Node.
class Node:
    def __init__(self, val=0, neighbors=None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []

    def __repr__(self) -> str:
        neighbor_vals = [str(n.val) for n in self.neighbors]
        return f"Node(val={self.val}, neighbors=[{', '.join(neighbor_vals)}])"




class Solution:
    def cloneGraph(self, node: Optional["Node"]) -> Optional["Node"]:
        # for the input node we create a new node N
        # we maintain a map of int value of node to its cloned value of type Node
        # then we iterate over the graph using either DFS/BFS, and repeat for each of the nodes in the input graph
        # if the MAP already contains a node we get the current but cloned neighbors of this node, and to that list we add a cloned version of the neighbor
        if not node:
            return None
        clone_map = {}
        # G = Node(node.val) #why are we not adding neighbors of node here?
        # clone_map[node] = G
        # bfs_q = deque()
        # bfs_q.append(node)
        clone_map[node] = Node(node.val)
        bfs_q = deque([node])
        while bfs_q:
            curr_node = bfs_q.popleft()
            for neighbor in curr_node.neighbors:
                if neighbor not in clone_map:
                    clone_map[neighbor] = Node(neighbor.val)
                    bfs_q.append(neighbor)
                # else:
                # print(f"neighbor={neighbor.val} for currrent node={curr_node.val} already exists in cloned map")
                # #get current node's clone
                # cloned_node = clone_map[curr_node]
                # print(f"Got cloned node={cloned_node.val} of current node={curr_node.val}")
                # #get cloned node's neighbors
                # cloned_neighbors = cloned_node.neighbors
                # print(f"cloned node's neighbors={cloned_neighbors}")
                # #get the clone of the existing neighbor
                # cloned_neighbor = clone_map[neighbor]
                # print(f"Found cloned neighbor={cloned_neighbor.val} for the neighbor={neighbor.val}")
                # print(f"adding cloned neighbor={cloned_neighbor.val} to neighbors of cloned node={cloned_node.val}")
                # #now append this cloned_neighbor to
                # cloned_neighbors.append(cloned_neighbor)
                # print(f"updated neighbors for node={cloned_node.val} is={cloned_neighbors}")
                clone_map[curr_node].neighbors.append(clone_map[neighbor])

        # return G
        return clone_map[node]


if __name__ == '__main__':
    Solution().solve()