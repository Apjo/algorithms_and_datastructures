"""
Filename: longest_unival_path.py
Date: 2026-10-06
"""

from TreeNode import *


class Solution:
    #time: O(N)
    def longestUnivaluePath(self, root: TreeNode | None) -> int:
        ans = 0

        def solve(curr_node, curr_val):
            nonlocal ans

            if not curr_node:
                return 0

            le = solve(curr_node.left, curr_node.val)
            ri = solve(curr_node.right, curr_node.val)

            ans = max(ans, le + ri)

            if curr_node.val == curr_val:
                return 1 + max(le, ri)

            return 0

        solve(root, root.val)

        return ans


if __name__ == "__main__":
    Solution().solve()
