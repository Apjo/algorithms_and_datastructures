#link: https://leetcode.com/problems/rotate-list/description/?envType=daily-question&envId=2026-05-05
from ListNode import *
from typing import Optional

class Solution:
    def rotateRight(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        #get len of list
        #make the last node point to head node, this is useful later on 
        #make sure to have k within the len bounds, so k=k%len
        #then iterate again len - k - 1 in the list by incrementing in the list starting from the head
        #once at the node where you want to break off, make the next node saved in another pointer(say answer), and then mark it as none
        #return the final answer
        if not head or not k:
            return head

        L, curr = 1, head
        while curr.next:
            curr=curr.next
            L+=1
        curr.next = head
        k%=L
        temp = head
        for _ in range(L - k - 1):
            temp=temp.next
        ans=temp.next
        temp.next=None
        
        return ans

