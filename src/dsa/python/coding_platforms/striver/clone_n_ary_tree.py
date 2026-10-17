"""
Filename: clone_n_ary_tree.py
Date: 2026-10-09
"""

from collections import deque


# Definition for an n-ary tree node.
class TreeNode:
    def __init__(self, val=0):
        self.val = val
        self.children = []



class Solution:
    def cloneTree(self, root: TreeNode) -> TreeNode:
        if not root:
            return None

        root_clone = TreeNode(root.val)
        clone_map = {}

        clone_map[root] = root_clone
        bfs_q = deque([root])

        while bfs_q:
            curr = bfs_q.popleft()
            for neighbor in curr.children:
                if neighbor not in clone_map:
                    clone_map[neighbor] = TreeNode(neighbor.val)
                    bfs_q.append(neighbor)
                clone_map[curr].children.append(clone_map[neighbor])

        return clone_map[root]


if __name__ == '__main__':
    Solution().solve()