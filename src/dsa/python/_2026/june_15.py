#link: https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/description/?envType=daily-question&envId=2026-06-15

from typing import Optional
from ListNode import ListNode

class Solution:
    def deleteMiddle(self, head: Optional[ListNode]) -> Optional[ListNode]:
        def find_mid(ll: ListNode[int]):
            f, s, temp = ll, ll, ll
            while f and f.next:
                f = f.next.next
                temp = s
                s = s.next
            return temp, s

        if not head:
            return head
        
        if head.next is None:
            return None
        
        p, m = find_mid(head)
        p.next = m.next
        
        return head


if __name__ == '__main__':
    Solution().solve()