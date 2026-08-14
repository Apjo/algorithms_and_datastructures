"""
Filename: p16_remove_value_list.py
Date: 2026-08-14
"""

from typing import Optional
import sys, os
# Make parent directory importable so sibling modules like ListNode can be imported
_this_dir = os.path.dirname(__file__)
_parent = os.path.dirname(_this_dir)
if _parent not in sys.path:
    sys.path.insert(0, _parent)
from ListNode import ListNode
'''
Given a linked list and a value, remove all nodes containing the provided value, and return the resulting list.

Ex: Given the following linked lists and values...

1->2->3->null, value = 3, return 1->2->null
8->1->1->4->12->null, value = 1, return 8->4->12->null
7->12->2->9->null, value = 7, return 12->2->9->null
'''
class Solution:
    def solve(self, to_del: int, input: Optional[ListNode]) ->  Optional[ListNode]:
        if not input:
            return input
        dummy = ListNode(-99999)
        temp = dummy
        curr = input
        
        while curr is not None:
            if curr.val != to_del:
                temp.next = curr
                temp=temp.next
            curr=curr.next
        
        return dummy.next        

def printL(l: Optional[ListNode]):
    temp = l
    while temp:
        print(temp.val)
        temp = temp.next
    print("\n")

if __name__ == '__main__':
    list1 = ListNode(1)
    list1.next = ListNode(2)
    list1.next.next = ListNode(3)
    printL(Solution().solve(1, list1))  # 2->3->NULL

    list2 = ListNode(1)
    list2.next = ListNode(2)
    list2.next.next = ListNode(3)
    list2.next.next.next = ListNode(3)
    list2.next.next.next.next = ListNode(433)
    printL(Solution().solve(3, list2))  # 1->3->NULL
