#link: https://neetcode.io/problems/lowest-common-ancestor-in-binary-search-tree/question
from TreeNode import *

class Solution:
    #what if both nodes dont exists
      #only one node exists
      #both node exists
      #both nodes are same
    def lowestCommonAncestor(self, root: TreeNode, p: TreeNode, q: TreeNode) -> TreeNode:
        
        if not root:
            return root
        
        if root.val == p.val or root.val == q.val:
            return root
        
        lca_le = self.lowestCommonAncestor(root.left, p, q)
        lca_ri = self.lowestCommonAncestor(root.right, p, q)

        if not lca_le and not lca_ri:
            return None
        
        if lca_le and lca_ri:
            return root
        
        if not lca_le and lca_ri:
            return lca_ri
        
        else:
            return lca_le
    
    #time: O(h)
    def lowestCommonAncestor(self, root: TreeNode, p: TreeNode, q: TreeNode) -> TreeNode:
        '''
        If both values are smaller than the current node -> both must lie in the left subtree.
        If both values are greater than the current node -> both must lie in the right subtree.
        Otherwise, the current node is the split point where one node is on the left and the other is on the right (or one is equal to the current node).
        That split point is the Lowest Common Ancestor (LCA).
        '''
        if max(p.val, q.val) < root.val:
            return self.lowestCommonAncestor(root.left, p, q)
        elif min(p.val, q.val) > root.val:
            return self.lowestCommonAncestor(root.right, p, q)
        else:
            return root
