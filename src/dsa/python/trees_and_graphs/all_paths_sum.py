"""
Filename: all_paths_sum.py
Date: 2026-10-06
"""

from TreeNode import *


class Solution:
    # time: O(N^2) where N is the number of nodes in the binary tree. We visit each node exactly once, which is O(N). However, when we find a valid root-to-leaf path, we copy it into the result list, which costs up to O(H) where H is the tree height. With K valid paths, the total copy cost is O(K·H), giving us O(N + K·H) overall. In the worst case: a 'caterpillar' tree where a long spine of N/2 nodes each has a leaf child — both K and H are O(N), so this becomes O(N²). Note that a fully balanced tree gives O(N·log N) since H = log N, and a fully skewed tree (a straight chain) gives O(N) since K = 1. The O(N²) worst case comes from tree shapes in between these extremes.
    # space: O(N²) where N is the number of nodes in the binary tree in the worst case. The recursion stack uses O(H) space and the current path list uses O(H) space where H is the tree height. The result stores K valid paths of up to length H, which is O(K·H). In the worst case: the same 'caterpillar' tree shape would total to O(N²).
    def pathSum(self, root: TreeNode | None, target: int) -> list[list[int]]:
        res, buff = [], []

        def solve(node, t, buff, res):
            if not node:
                return

            buff.append(node.val)

            if not node.left and not node.right and t == node.val:
                # since we need to find all root-to-leaf paths with the target sum, not just the first one hence no return, hence, when the leaf matches, we save a copy of that path, then return from that leaf's recursion so DFS can explore the remaining branches.
                res.append(buff[:])

            t -= node.val
            solve(node.left, t, buff, res)

            solve(node.right, t, buff, res)
            buff.pop()

        solve(root, target, buff, res)
        return res


if __name__ == "__main__":
    Solution().solve()
