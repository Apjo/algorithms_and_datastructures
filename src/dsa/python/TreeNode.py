from typing import Optional


class TreeNode:
    def __init__(
        self, val, left: Optional["TreeNode"] = None, right: Optional["TreeNode"] = None
    ):
        self.val = val
        self.left = None
        self.right = None
