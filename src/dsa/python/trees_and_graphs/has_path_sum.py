"""
Filename: has_path_sum.py
Date: 2026-10-06
"""

from TreeNode import *


class Solution:
    # time: O(N) N=number of nodes, space: O(N) aux call stack
    def hasPathSum(self, root: TreeNode | None, target: int):
        if not root:
            return False
        if not root.left and not root.right:
            return target == root.val
        target -= root.val
        return self.hasPathSum(root.left, target) or self.hasPathSum(root.right, target)


if __name__ == "__main__":
    Solution().solve()
