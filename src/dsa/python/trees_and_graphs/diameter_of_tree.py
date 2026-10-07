"""
Filename: diameter_of_tree.py
Date: 2026-10-06
"""

from TreeNode import *


class Solution:
    # time: O(N)
    def maxDiameter(self, root: TreeNode | None) -> int:
        ans = 0

        def solve(n):
            # we calculate the height, and at the same time calculate the diameter
            nonlocal ans
            if not n:
                return 0
            le = solve(n.left)
            ri = solve(n.right)
            # at each node we calculate the len of longest path that passes through this node. Wehre the longest path that passes through the node == max depth of the node's left subtree + max depth of node's right subtree.
            # Where we know depth of a subtree == len of longest path from the root of that subtree to a leaf node.
            # So at each node, we want to find the max depth of our left and right subtrees, and use that to calculate the length of the longest path that passes through that node.
            curr_dia = le + ri

            ans = max(curr_dia, ans)

            return 1 + max(le, ri)

        solve(root)

        return ans


if __name__ == "__main__":
    Solution().solve()
