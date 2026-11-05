#link: https://neetcode.io/problems/level-order-traversal-of-binary-tree/question

from TreeNode import *
from typing import Optional, List
from collections import deque

class Solution:
    #using BFS, time: O(N), space:O(N)
    def levelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        res=[]
        if not root:
            return res
        bfs_q = deque()
        bfs_q.append(root)

        while bfs_q:
            N = len(bfs_q)
            temp=[]

            for i in range(N):
                curr = bfs_q.popleft()
                temp.append(curr.val)
                if curr.left:
                    bfs_q.append(curr.left)
                if curr.right:
                    bfs_q.append(curr.right)
            if temp:
                res.append(temp)
        
        return res