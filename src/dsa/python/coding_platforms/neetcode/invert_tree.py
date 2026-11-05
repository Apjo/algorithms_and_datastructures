#link: https://neetcode.io/problems/invert-a-binary-tree/question
from TreeNode import *
from typing import Optional
from collections import deque

class Solution:
    #recursive dfs time: O(n), space:O(n)
    def invertTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        def solve(n):
            if not n:
                return
            #save old left and right of this current node
            oldLe = n.left
            oldRi = n.right
            #swap
            n.left = oldRi
            n.right = oldLe
            if n.left:
                solve(n.left)
            if n.right:
                solve(n.right)
        solve(root)

        return root

    #using BFS, time: O(n)
    def invertTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        if not root:
            return None
        
        #start by adding root node to the queue
        q = deque([root])

        #while queue is not empty:
        while q:
            #poll the current node from the left end
            curr = q.popleft()
            #swap the left and right children of the current node
            curr.left, curr.right = curr.right ,curr.left
            #if the left and right children exists for this current node add to queue
            if curr.left:
                q.append(curr.left)
            if curr.right:
                q.append(curr.right)
        
        return root

    