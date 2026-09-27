#link:https://neetcode.io/problems/add-two-numbers/question
from ListNode import *
from typing import Optional

class Solution:
    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        p1,p2=l1, l2
        carry=0
        dummy=ListNode(-999)
        temp=dummy
        while p1 is not None or p2 is not None or carry !=0:
            curr_sum=0
            if p1 is not None:
                curr_sum+=p1.val
                p1=p1.next
            if p2 is not None:
                curr_sum+=p2.val
                p2=p2.next
            curr_sum+=carry
            carry = 1 if curr_sum >=10 else 0
            nn=ListNode(curr_sum % 10)
            temp.next=nn
            temp=temp.next
        return dummy.next