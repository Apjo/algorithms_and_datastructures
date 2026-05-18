#link: https://neetcode.io/problems/valid-binary-search-tree/question
from TreeNode import *
from typing import Optional

class Solution:
    #time: O(N), space:O(N)
    def isValidBST(self, root: Optional[TreeNode]) -> bool:
        '''
        We build on the fact that every node must fit within a valid value range decided by all its ancestors.
        
        '''
        def solve(node, min_v, max_v) -> bool:
            if not node:
                return True
            if not(min_v < node.val < max_v):
                return False
            return solve(node.left, min_v, node.val) and solve(node.right, node.val, max_v)
        
        return solve(root, float("-inf"), float("inf"))