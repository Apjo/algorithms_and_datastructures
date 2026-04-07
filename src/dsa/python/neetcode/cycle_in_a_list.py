#link: https://neetcode.io/problems/linked-list-cycle-detection/question
from ListNode import *
from typing import Optional

class Solution:
    def hasCycle(self, head: Optional[ListNode]) -> bool:
        if not head:
            return False
        slow, fast  = head, head
        while fast.next is not None:
            fast=fast.next.next
            if fast is None:
                return False
            slow=slow.next
            if fast == slow:
                return True
        return False