# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/7/26
"""
from typing import Optional
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    ans, total_sum=0, 0
    #time: for each node x in the tree with N nodes we are doing a DFS of time O(Y) O(Y*N)
    def maxProduct(self, root: Optional[TreeNode]) -> int:
        '''
        Calculate the entire sum of the tree, and then consider each node to be a potential candidate from where an edge can be broken,
        so that leaves us woith 2 subtrees, we calculate the subtree starting from this node,
        then the remaining sum = total sum of tree - this sum, then what remains is maximizing the product of these 2 sums,
        so we get ans=max(ans, left_tree_sum*(total sum - left_tree_sum), right_tree_sum*(total sum - right_tree_sum))
        '''
        def dfs(node):
            if not node:
                return 0
            left_sum = dfs(node.left)
            right_sum = dfs(node.right)
            self.ans = max(self.ans, left_sum*(self.total_sum - left_sum), right_sum*(self.total_sum - right_sum))
            total = left_sum + right_sum + node.val
            return total
        self.total_sum = dfs(root)
        dfs(root)
        return int(self.ans%(10**9+7))
