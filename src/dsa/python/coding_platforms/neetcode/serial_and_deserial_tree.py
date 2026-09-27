#link:
from TreeNode import *
from typing import Optional
from collections import deque

class Codec:
    
    # Encodes a tree to a single string.
    def serialize(self, root: Optional[TreeNode]) -> str:

        if not root:
            return ""
        bfs_q = deque()
        res=""
        bfs_q.append(root)
        while bfs_q:
            curr = bfs_q.popleft()
            if not curr:
                res+="#,"
                continue
            res+=str(curr.val)+","
            bfs_q.append(curr.left)
            bfs_q.append(curr.right)

        return res

    def deserialize(self, data):
        """Decodes your encoded data to tree.
        
        :type data: str
        :rtype: TreeNode
        """
        if not data:
            return None

        formatted_data = data.split(",")

        root = TreeNode(int(formatted_data[0]))
        bfs_q = deque()
        bfs_q.append(root)

        i=1

        while bfs_q:
            curr = bfs_q.popleft()
            if formatted_data[i] != "#":
                left_child = TreeNode(formatted_data[i])
                curr.left = left_child
                bfs_q.append(left_child)
            i+=1
            if formatted_data[i] != "#":
                right_child = TreeNode(formatted_data[i])
                curr.right = right_child
                bfs_q.append(right_child)
            i+=1

        return root
