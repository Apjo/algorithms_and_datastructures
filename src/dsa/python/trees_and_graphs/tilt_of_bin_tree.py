"""
Filename: tilt_of_bin_tree.py
Date: 2026-10-06
"""

from TreeNode import *


class Solution:
    # time: O(N)
    def tilt(self, root: TreeNode | None):
        ans = 0

        def solve(n):
            nonlocal ans
            if not n:
                return 0
            if not n.left and not n.right:
                return n.val
            le = solve(n.left)
            ri = solve(n.right)
            ans += abs(le - ri)
            return le + ri + n.val

        solve(root)
        return ans


if __name__ == "__main__":
    Solution().solve()
