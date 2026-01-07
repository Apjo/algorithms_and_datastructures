# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/6/26
"""

from typing import Optional
from collections import deque

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def maxLevelSum(self, root: Optional[TreeNode]) -> int:
        if not root:
            return 0
        level, q = 1, deque([root])
        ans = float('-inf')
        max_sum = float('-inf')
        print(max_sum)
        while q:
            q_size = len(q)
            curr_sum=0
            for i in range(q_size):
                curr_node = q.popleft()

                curr_sum+=curr_node.val
                #print(f"node={str(curr_node.val)}, curr sum={curr_sum}")
                if curr_node.left:
                    q.append(curr_node.left)
                    #curr_sum+=curr_node.left.val
                if curr_node.right:
                    q.append(curr_node.right)
                    #curr_sum+=curr_node.right.val
            if curr_sum > max_sum:
                max_sum = curr_sum
                ans=level
                #print(f"max sum={max_sum}, level={level}")
            level+=1
        #do a level order traversal, maintaining the curr level, and at each level calculate the sum
        #if we get a new min,update our tracking min, and update the level
        return ans



def main():
    return solve()


if __name__ == '__main__':
    main()
