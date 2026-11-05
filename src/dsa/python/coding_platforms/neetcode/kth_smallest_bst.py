#link: https://neetcode.io/problems/kth-smallest-integer-in-bst/question

from TreeNode import *
from typing import Optional

class Solution:
    def kthSmallest(self, root: Optional[TreeNode], k: int) -> int:

        ans = k
        ret_val = 0

        def solve(node):
            nonlocal ans, ret_val
            #first go left
            solve(node.left)
            #if ans=0 that means we have already gotten an answer, simply return
            if ans==0:
                return
            #else, decrement ans by 1
            ans-=1
            #if we get ans=0 that means we have found the kth smallest node, save it
            if ans==0:
                ret_val=node.val
                return
            #if node is still not found on the left side, then only go right
            solve(node.right)

        solve(root)
        
        return ret_val
