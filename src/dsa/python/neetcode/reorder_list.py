#link: https://neetcode.io/problems/reorder-linked-list/question
from ListNode import *
from typing import Optional

class Solution:
    def reorderList(self, head: Optional[ListNode]) -> None:
        if not head:
            return None
        slow,fast = head, head
        #find mid
        while fast is not None and fast.next is not None:
            fast=fast.next.next
            slow=slow.next
        def reverse(n):
            curr = n
            prev, nxt = None, None
            while curr is not None:
                nxt = curr.next
                curr.next = prev
                prev = curr
                curr = nxt
            return prev
        #reverse the list after the mid
        head2 = reverse(slow.next)
        #make the mid.next to be None to denote 2 linked lists, one having head as head, and the new one having head as head2 which is the reversed list
        slow.next = None
        while head is not None and head2 is not None:
            n1 = head.next
            n2 = head2.next
            head.next = head2
            head2.next = n1
            head=n1
            head2=n2