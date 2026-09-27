#link: https://neetcode.io/problems/binary-tree-maximum-path-sum/question

from TreeNode import TreeNode
from typing import Optional

class Solution:
    #time: O(N^2) this is because maxPathSum recurses into every node, and at each node calls `get_max_sum` which is `O(N)`.So, to optimize we need to ask "What work is being repeated at every level that I could compute just once on the way up?"
    def maxPathSum(self, root: Optional[TreeNode]) -> int:

        def get_max_sum(n):
            if not n:
                return 0
            #leaf node, only choice left is returning the node's value
            if not n.left and not n.right:
                return n.val
            
            le, ri = float("-inf"), float("-inf")
            # Get the best single-direction arm going down each side
            if n.left:
                le = get_max_sum(n.left)
            
            if n.right:
                ri = get_max_sum(n.right)
            '''
            Remember we only get 3 choices for the best arm starting at node n:
        1. Just n.val alone (don't extend downward)
        2. n.val + left arm (extend left)
        3. n.val + right arm (extend right)
        Note: we never return both arms: that would create a fork which a parent node cannot extend further
            '''
            return max(n.val, n.val + le, n.val + ri)
        # non-existent subtree loses any max() comparison
        if not root:
            return float("-inf")
        
        # single node — answer is itself
        if not root.left and not root.right:
            return root.val
        
        #get max path sum for left subtree        
        left_s = get_max_sum(root.left)
        
        #get max path sum for right subtree        
        right_s = get_max_sum(root.right)
        
        #now find max path sum going through this node. i.e.consider this to be a Path THROUGH current node this node connects both arms Clamp to 0 means: if an arm is negative, just don't include it
        curr_s = root.val + max(0, left_s) + max(0, right_s)
        
        #recursively call max path sum for both left and right subtrees so that the best path that lives entirely inside left or right subtree (i.e., root is NOT part of the answer)
        sub_sum = max(self.maxPathSum(root.left), self.maxPathSum(root.right))
        
        # Answer is whichever is bigger the path through current root, or the best path in a subtree
        return max(curr_s, sub_sum)
   
    '''
    To have one single traversal, one helper function, that does two things simultaneously at each node:
    1. Updates a global max: "is the path through me the best I've seen so far?" this is where you use node.val + left + right
    2. Returns the best single arm upward: max(node.val, node.val + left, node.val + right) so the parent can extend it
    '''
     #time: O(N),space:O(H)
    def maxPathSum(self, root: Optional[TreeNode]) -> int:
        global_ans = -float("inf")
        def get_max_sum(node):
            nonlocal global_ans
            
            if not node:
                return 0
            
            left_max = get_max_sum(node.left)
            left_max = max(0, left_max)
            
            right_max = get_max_sum(node.right)
            right_max = max(0, right_max)
            
            curr_max_sum = left_max + right_max + node.val
            
            global_ans = max(global_ans, curr_max_sum)
            
            #return max(node.val, node.val + left_max, node.val + right_max)
            '''
            Since left_max and right_max are already clamped to ≥ 0, node.val + left_max and node.val + right_max are always ≥ node.val.
            So we can simplify to have so that node.val alone can never win that max().
            '''
            return node.val + max(left_max, right_max)
        
        get_max_sum(root)
        
        return global_ans
