#link: https://neetcode.io/problems/balanced-binary-tree/question

from TreeNode import *
from typing import Optional

class Solution:
    #brute force, time: O(n^2)
    '''
    The brute-force approach directly follows the definition:
        For every node, compute the height of its left subtree.
        Compute the height of its right subtree.
        Check if their difference is ≤ 1.
        Recursively repeat this check for all nodes.
    '''
    def isBalanced(self, root: Optional[TreeNode]) -> bool:
        def ht(n):
            if not n:
                return 0
            return 1 + max(ht(n.left), ht(n.right))
        
        if not root:
            return True
        
        left_ht = ht(root.left)
        right_ht = ht(root.right)
        diff = left_ht - right_ht
        
        if diff > 1:
            return False
        
        return self.isBalanced(root.left) and self.isBalanced(root.right)
    
    #time:O(n)
    '''
    The brute-force solution wastes time by repeatedly recomputing subtree heights.
    We fix this by doing one DFS that returns two things at once for every node:
        Is the subtree balanced? (True/False)
        What is its height?
        This way, each subtree is processed only once.
        If at any node the height difference > 1, we mark it as unbalanced and stop worrying about deeper levels.
    '''
    #time and space for a balanced tree: O(n), O(h); for unbalanced, space: O(n), n=no.of nodes in tree, and h is the ht of the tree
    def isBalanced(self, root: Optional[TreeNode]) -> bool:
        def dfs(n):
            if not n:
                return [True, 0]
            
            lhs = dfs(n.left)
            rhs = dfs(n.right)
            is_curr_node_balanced = lhs[0] and rhs[0] and abs(lhs[1] - rhs[1]) <= 1
            
            return [is_curr_node_balanced, 1 + max(lhs[1], rhs[1])]

        return dfs(root)[0]