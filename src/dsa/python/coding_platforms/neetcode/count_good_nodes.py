#link:https://neetcode.io/problems/count-good-nodes-in-binary-tree/question
from TreeNode import *

class Solution:
    #dfs, time:O(n), space:O(n)
    def goodNodes(self, root: TreeNode) -> int:
        #while traversing the tree we keep track of max val seen so far on the current path
        def solve(node, currMax):
            if not node:
                return 0
            #if we see that current node's val > current max, we have seen a good node, so then we update currmax
            res = 1 if node.val >= currMax else 0
            currMax = max(currMax, node.val)
            #recursively explore left and right child with new currmax
            res+=solve(node.left, currMax)
            res+=solve(node.right, currMax)

        return solve(root, root.val)
    
    #using BFS, time:O(n), space:O(n)
    def goodNodes(self, root: TreeNode) -> int:
        from collections import deque
        
        if not root:
            return 0
        
        ans=0
        
        bfs_q = deque()
        bfs_q.append((root, float("-inf")))

        while bfs_q:
            
            curr_node, curr_max = bfs_q.popleft()
            
            if curr_node.val >= curr_max:
                curr_max = curr_node.val
                ans+=1
            
            if curr_node.left:
                bfs_q.append((curr_node.left, curr_max))
            
            if curr_node.right:
                bfs_q.append((curr_node.right, curr_max))

        return ans