"""
Filename: sum_all_nodes.py
Date: 2026-10-06
"""

from TreeNode import *


class Solution:
    def findSum(self, root: TreeNode | None):
        def solve(n):
            if not n:
                return 0
            if not n.left and not n.right:
                return n.val
            left_sum = solve(n.left)
            right_sum = solve(n.right)

            return left_sum + right_sum + n.val
        
        return solve(root)
        


if __name__ == '__main__':
    Solution().solve()