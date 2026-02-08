# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/8/26
link:
https://leetcode.com/problems/balanced-binary-tree/description/?envType=daily-question&envId=2026-02-08
"""
#DFS O(N^2) solution

from typing import Optional

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val =val
        self.left = left
        self.right = right
def isBalanced(root: Optional[TreeNode]) -> bool:
    if not root:
        return True
    def get_ht(node) -> int:
        if not node:
            return 0
        return 1 + max(get_ht(node.left), get_ht(node.right))
    left_ht = get_ht(root.left)
    right_ht = get_ht(root.right)
    return abs(left_ht - right_ht) <= 1 and isBalanced(root.left) and isBalanced(root.right)

#O(N) solution
'''
Instead of calling get_ht() explicitly for each child node, we return the height of the current node in DFS recursion.
When the sub tree of the current node (inclusive) is balanced, the function get_ht() returns a non-negative value as the height.
Otherwise -1 is returned.
According to the leftHeight and rightHeight of the two children, the parent node could check if the sub tree
is balanced, and decides its return value
'''
def isBalanced_bottom_up(root: Optional[TreeNode]) -> bool:
    if not root:
        return True

    def get_ht(node) -> int:
        if not node:
            return 0
        left_ht = get_ht(node.left)
        right_ht = get_ht(node.right)
        if left_ht == -1 or right_ht == -1 or abs(left_ht - right_ht) == -1:
            return -1
        return 1 + max(get_ht(node.left), get_ht(node.right))
    left_ht = get_ht(root.left)
    right_ht = get_ht(root.right)
    return get_ht(root) != -1

def main():
    return solve()


if __name__ == '__main__':
    main()
