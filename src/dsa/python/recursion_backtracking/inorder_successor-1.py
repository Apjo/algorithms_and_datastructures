# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/14/26
"""

from typing import Optional
class TreeNode:
    def __init__(self, val):
        self.val=val
        self.left=None
        self.right=None

def inorderSuccessor(root: TreeNode, p: TreeNode) -> Optional[TreeNode]:
    #p.right exists, then look for the smallest value in p's right subtree which is > p
    #else start from root, and find the first node which is > p
    if p.right is not None:
        curr = p.right
        while curr.left is not None:
            curr=curr.left
        return curr
    succ=None
    curr=root
    while curr is not None:
        if p.val > curr.val:
            curr=curr.right
        elif p.val < curr.val:
            succ=curr
            curr=curr.left
        else:
            break
    return succ

def main():
    return solve()


if __name__ == '__main__':
    main()
