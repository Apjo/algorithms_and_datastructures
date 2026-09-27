#link:
from TreeNode import *
from typing import Optional

class Solution:
    #visit every node, calc. ht, and then the dia time:O(n^2),space: O(n)
    def diameterOfBinaryTree(self, root: Optional[TreeNode]) -> int:
        
        def get_ht(n) -> int:
            if not n:
                return 0
            return 1 + max(get_ht(n.left), get_ht(n.right))
        
        left_ht = get_ht(root.left)
        right_ht = get_ht(root.right)
        #calculate diameter through this node
        curr_dia = left_ht + right_ht
        #recursively calculate diameter through left, and right subtrees
        sub_tree_dia = max(self.diameterOfBinaryTree(root.left), self.diameterOfBinaryTree(root.right))
        
        #final diameter for this node = max(diameter through this node, diameter in left subtree, diameter in right subtree)
        return max(curr_dia, sub_tree_dia)
    
    #time: O(n), space: O(n)
    def diameterOfBinaryTree(self, root: Optional[TreeNode]) -> int:
        #we calculate diameter at the same time we are calculating ht of the tree
        global_dia = 0
        
        #for each node during dfs
        def dfs(n):
            nonlocal global_dia
            if not n:
                return 0
            #get left ht
            left_ht = dfs(n.left)
            #get right ht
            right_ht = dfs(n.right)
            #calc. dia through this node = left ht + right ht
            curr_dia = left_ht + right_ht
            #update the global answer = max(curr_dia, global_dia)
            global_dia = max(global_dia, curr_dia)
            
            #return the ht to the parent node
            return 1 + max(left_ht, right_ht)
        
        dfs(root)
        return global_dia
        
