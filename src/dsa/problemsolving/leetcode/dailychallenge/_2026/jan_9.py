# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/09/26
link:https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/description/?envType=daily-question&envId=2026-01-09
"""
from typing import Optional
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val=val
        self.left=left
        self.right = right
class Solution:

def subtreeWithAllDeepest(root: Optional[TreeNode]) -> Optional[TreeNode]:
    def lca(node, max_depth, curr_len):
        if max_depth == curr_len + 1:
            return node
        left = lca(node.left, max_depth, curr_len+1)
        right = lca(node.right, max_depth, curr_len + 1)
        if left and right:
            return node
        return left if left and not right else right
    #find the height of the tree
    def ht(node):
        if not node:
            return 0
        return 1 + max(ht(node.left), ht(node.right))
    #find lca by passing in a curr len variable
    if not root:
        return root
    max_depth = ht(root)
    return lca(root, max_depth, 0)
        #when currlen == depth -1 we have found the node
        #else iterate on left side
        #iterate on right side
        #if both left, and right exists: return root
        #if le, but right is none, return le
        #else return right
def main():
    return subtreeWithAllDeepest([3,5,1,6,2,0,8,None,None,7,4])


if __name__ == '__main__':
    main()
