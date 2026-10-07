"""
Filename: good_nodes_count.py
Date: 2026-10-06
"""

from TreeNode import *


class Solution:
    # time: O(N)
    def countGoodNodes(self, root: TreeNode | None):
        def solve(curr_node, curr_max: int):
            if not curr_node:
                return 0
            cnt = 0
            if curr_node.val >= curr_max:
                cnt += 1
                curr_max = curr_node.val
            le = solve(curr_node.left, curr_max)
            ri = solve(curr_node.right, curr_max)
            return le + ri + cnt

        return solve(root, root.val)


if __name__ == "__main__":
    Solution().solve()
