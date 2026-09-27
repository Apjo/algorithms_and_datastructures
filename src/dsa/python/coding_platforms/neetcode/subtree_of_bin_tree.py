#link: https://neetcode.io/problems/subtree-of-a-binary-tree/question
from TreeNode import *
from typing import Optional

class Solution:
    #time O(M*N) where m=nodes in root, n=nodes in subroot
    def same_trees(self, r1, r2) -> bool:
        if not r1 and not r2:
            return True
        if r1 and r2 and r1.val == r2.val:
            return (self.same_trees(r1.left, r2.left) and self.same_trees(r1.right, r2.right))
    def isSubtree(self, root: Optional[TreeNode], subRoot: Optional[TreeNode]) -> bool:
        #1. start from root, if the root.val == subroot.val, apply same tree logic here i.e. we check structure and values, else
        #2. first navigate left, and repeat 1
        #3. if left returns false, redo step 1 but for root.right
        #4. return left or  right
        if not root:
            return False
        if not subRoot:
            return True
        if self.same_trees(root, subRoot):
            return True
        return (self.isSubtree(root.left, subRoot) or self.isSubtree(root.right, subRoot))