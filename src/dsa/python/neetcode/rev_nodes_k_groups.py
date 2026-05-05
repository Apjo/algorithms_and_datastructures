#link: https://neetcode.io/problems/reverse-nodes-in-k-group/question
from ListNode import *

from typing import Optional

class Solution:
    #recursive sol, time:O(n)
    def reverseKGroup(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        #basically in this function we are doing 3 things
        #Checks if there are k nodes, if there are fewere than k nodes, return as is
        #Reverses them if possible
        #Returns the new head of that processed part
        if not head:
            return None
        cnt=0
        curr=head
        while cnt < k :
            if curr is None:
                return head
            cnt+=1
            curr=curr.next
        cnt=0
        #curr always points to the start of remaining list in THIS call, not global resets every recursion frame
        prev,next,curr=None, None, head
        while cnt < k:
            next=curr.next
            curr.next=prev
            prev=curr
            curr=next
            cnt+=1
        #My current block is done. Now link its end to whatever comes next.
        head.next = self.reverseKGroup(next, k)
        #prev points to the start of the reversed group, while head points to the end of the reversed group
        #curr does not “end up” anywhere globally it only exists within each recursive call and always points to "what’s left to process from that point."
        return prev