# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/19/26
link: https://leetcode.com/problems/evaluate-boolean-binary-tree/
"""

# Definition for a binary tree node.
from typing import Optional

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def evaluateTree(self, root: Optional[TreeNode]) -> bool:
        def solve(node):
            if not node:
                return False
            if not node.left and not node.right:
                if node.val == 0:
                    return False
                else:
                    return True
            left_eval = solve(node.left)
            right_eval = solve(node.right)
            if node.val == 2:
                return left_eval or right_eval
            else:
                return left_eval and right_eval
        return solve(root)

def main():
    return Solution().evaluateTree([2,1,3,null,null,0,1])


if __name__ == '__main__':
    main()
