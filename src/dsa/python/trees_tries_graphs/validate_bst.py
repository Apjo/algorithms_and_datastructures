"""
Filename: validate_bst.py
Date: 2026-10-06
"""

from TreeNode import *


class Solution:
    # time: O(n) space: O(n)
    def validateBST(self, root: TreeNode | None):
        def solve(curr, curr_min, curr_max):
            # and empty bst is a BST!
            if not curr:
                return True
            # The work that we need to do at each node is to check if the current node's value falls within the valid range. If it doesn't we can return False immediately.
            if curr.val <= curr_min or curr.val >= curr_max:
                return False
            return solve(curr.left, curr_min, curr.val) and solve(
                curr.right, curr.val, curr_max
            )

        return solve(root, float("-inf"), float("inf"))


if __name__ == "__main__":
    Solution().solve()
