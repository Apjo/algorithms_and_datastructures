#link:
class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random

from typing import Optional

class Solution:
    #using 2 passes, time, space is O(n) where n is the number of nodes in the list
    def copyRandomList(self, head: 'Optional[Node]') -> 'Optional[Node]':
        if not head:
            return head
        mp={}
        curr=head
        while curr is not None:
            mp[curr] = Node(curr.val)
            curr=curr.next
        curr=head
        while curr is not None:
            mp[curr].next = mp[curr.next]
            mp[curr].random = mp[curr.random]
            curr=curr.next
        return mp[head]

    #using 1 pass
    def copyRandomList(self, head: 'Optional[Node]') -> 'Optional[Node]':
        if not head:
            return head
        
        from collections import defaultdict
         # returns a new empty node whenever we access a key that doesn't exist yet.
        mp = defaultdict(lambda: Node(0))
        mp[None] = None
        ptr=head
        
        while ptr is not None:
            #get copied node
            copied_node = mp[ptr]
            #set its val
            copied_node.val = ptr.val
            #link its next pointer using the map
            copied_node.next = mp[ptr.next]
            #link its random pointer using the map
            copied_node.random = mp[ptr.random]
            ptr=ptr.next
        return mp[head]


    #space optimized 1