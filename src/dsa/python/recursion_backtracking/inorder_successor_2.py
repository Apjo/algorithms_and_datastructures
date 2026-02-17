# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/14/26
link:https://leetcode.com/problems/inorder-successor-in-bst-ii/description/
"""

from typing import Optional
class Node:
    def __init__(self, val):
        self.val = val
        self.parent=None
        self.left = None
        self.right=None

#this looks the node using its value
def inorderSuccessor(node: 'Node') -> 'Optional[Node]':
    if node.right is not None:
        curr = node.right
        while curr.left is not None:
            curr=curr.left
        return curr
    prev=node.parent
    while prev is not None and prev.val < node.val:
        prev=prev.parent
    return prev

#followup: without using node value look up
def inorderSuccessor_2(node: 'Node') -> 'Optional[Node]':
    if node.right is None:
        prev = node
        while prev.parent is not None and prev.parent.right is prev:
            prev=prev.parent
        return prev.parent

    curr = node.right
    while curr.left is not None:
        curr=curr.left
    return curr


def main():
    return solve()


if __name__ == '__main__':
    main()
