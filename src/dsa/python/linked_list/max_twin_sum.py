"""
Filename: max_twin_sum.py
Date: 2026-06-14
link: https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/description/?envType=daily-question&envId=2026-06-14
"""

from typing import Optional
from ListNode import ListNode

class Solution:
    def pairSum(self, head: Optional[ListNode]) -> int:
        # find mid of list
        # reverse everything from the mid
        # apply logic
        def rev(l):
            curr, nxt, prev = l, None, None

            while curr:
                nxt = curr.next
                curr.next = prev
                prev = curr
                curr = nxt
            l = prev
            return prev

        def find_mind_rev(ll):
            f, s = ll, ll
            while f and f.next:
                f = f.next.next
                s = s.next
            s = rev(s)
            return s

        if not head:
            return head

        rev_l = find_mind_rev(head)
        # print(rev_l)

        temp2 = head
        ans = float("-inf")

        while rev_l:
            # print(f"original={temp2.val}, rev={rev_l.val}")
            ans = max(ans, temp2.val + rev_l.val)
            # print(f"current ans={ans}")
            temp2 = temp2.next
            rev_l = rev_l.next

        return ans


if __name__ == '__main__':
    Solution().solve()