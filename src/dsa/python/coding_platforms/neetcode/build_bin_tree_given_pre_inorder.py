#link: https://neetcode.io/problems/binary-tree-from-preorder-and-inorder-traversal/question?list=neetcode150
from TreeNode import *
from typing import List, Optional

class Solution:
    #time:O(n^2),space:O(n)
    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        #get root index which will always be pre[0], and this will be the root of the tree
        #num Nodes on left of root index in the inorder will be responsible for left subtree
        # everything to the right of the root index in the inorder traversal will be responsible for the right subtree
        #recurse
        '''
        To recap the steps which are based on the facts that Preorder gives us the root first, and inorder tells us how to split left and right subtrees.
        - Take the first value from preorder → this is the root.
        - Find that value in inorder
        - Everything to the left in inorder → left subtree.
        - Everything to the right → right subtree.
        - Recursively build left and right using sliced arrays.
        '''
        def solve(pre_st, pre_end, ino_st, ino_end):
            if pre_st > pre_end or ino_st > ino_end:
                return None
            #create root node from preorder
            root_node = TreeNode(preorder[pre_st])
            #determine index of root in inorder traversal
            i, root_idx_ino = ino_st, ino_st
            
            while i <= ino_end:
                if inorder[i] == root_node.val:
                    root_idx_ino = i
                    break
                i+=1
            #calculate number of nodes in the left subtree
            num_nodes_on_left = root_idx_ino - ino_st
            
            root_node.left = solve(pre_st + 1, pre_st + num_nodes_on_left, ino_st, ino_end)
            root_node.right = solve(pre_st+num_nodes_on_left + 1, pre_end, root_idx_ino + 1, ino_end)

            return root_node

        return solve(0, len(preorder) - 1, 0, len(inorder) - 1)