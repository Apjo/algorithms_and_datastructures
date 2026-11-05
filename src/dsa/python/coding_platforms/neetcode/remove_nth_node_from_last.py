#link: https://neetcode.io/problems/remove-node-from-end-of-linked-list/question
from ListNode import *
from typing import Optional

class Solution:
    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        if head is None:
            return head
        
        temp,slow, fast = ListNode(-999, head), head,head
        
        slow=temp
        
        if head.next is None and n==1:
            return None
        #get the fast pointer one node before the nth node
        for i in range(n):
            fast=fast.next
        #now continue moving fast to the end of list, so that when slow moves it will be n nodes into the list, and the next node slow points to would be the one we have to delete. This is what helps in removing the node in 1-pass without having us to count the len of list etc.
        while fast:
            fast=fast.next
            slow=slow.next
        
        slow.next=slow.next.next
        
        return temp.next
