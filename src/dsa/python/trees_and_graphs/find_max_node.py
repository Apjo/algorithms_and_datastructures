"""
Filename: find_max_node.py
Date: 2026-10-06
"""

from TreeNode import *


class Solution:
    def findMax(self, root: TreeNode | None):
        def solve(n):
            if not n:
                return float("-inf")
            if not n.left and not n.right:
                return n.val
            left_max = solve(n.left)
            right_max = solve(n.right)

            return max(left_max, right_max, n.val)

        return solve(root)


if __name__ == "__main__":
    Solution().solve()
