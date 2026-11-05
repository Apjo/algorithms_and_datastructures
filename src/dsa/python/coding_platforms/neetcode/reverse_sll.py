#link: https://neetcode.io/problems/reverse-a-linked-list/question?list=neetcode150
from typing import Optional
from ListNode import *

class Solution:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        curr = head
        prev, next = None
        while curr is not None:
            next = curr.next
            curr.next = prev
            prev = curr
            curr = next
        return prev