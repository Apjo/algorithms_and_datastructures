#link:
from ListNode import *
from typing import List, Optional
import heapq

class NodeWrap:
    def __init__(self, n):
        self.node = n

    def __lt__(self, other):
        return self.node.val < other.node.val

class Solution:
    #time O(nlogK), space: O(k)
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        #dump all into a priority queue
        min_h = []
        #add all items to min heap
        if not lists:
            return []
        for item in lists:
            if item is not None:
                heapq.heappush(min_h, NodeWrap(item))
        
        dummy=ListNode(-999)
        curr=dummy
        #pop from the min heap, and start appending to a new list
        while min_h:
            popped = heapq.heappop(min_h)
            #append this node to the next of curr
            curr.next = popped.node
            curr=curr.next
            #if the popped node has a next, add to the min heap
            if popped.node.next:
                heapq.heappush(min_h, NodeWrap(popped.node.next))

        return dummy.next

#or use mergesort logic to merge 2 at a time
    
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        if not lists:
            return None
        
        def merge_all(l1, l2):
            dummy=ListNode(-999)
            curr=dummy
            while l1 and l2:
                if l1.val <= l2.val:
                    curr.next = l1
                    l1=l1.next
                else:
                    curr.next = l2
                    l2=l2.next
                curr=curr.next
            while l1:
                curr.next = l1
                l1=l1.next
                curr=curr.next
            while l2:
                curr.next = l2
                l2=l2.next
                curr=curr.next
            return dummy.next
        #NOTE:
        # while the code below will work just fine, if one of the lists from the input is long think >= 1000 nodes
        #the implementation is not safe for large inputs because it creates a recursion depth proportional to the total number of nodes. In Python, this exceeds the recursion limit (~1000), leading to a RecursionError.
            # if l1 and not l2:
            #     return l1
            # if l2 and not l1:
            #     return l2
            # if not l1 and not l2:
            #     return None
            # if l1.val <= l2.val:
            #     l1.next = merge_all(l1.next, l2)
            #     return l1
            # else:
            #     l2.next = merge_all(l1, l2.next)
            #     return l2
            
        def do_merge(start, end):
            if start > end:
                return None
            if start==end:
                return lists[start]
            if start < end:
                mid = start + end // 2
                le = do_merge(start, mid)
                ri = do_merge(mid + 1, end)
                return merge_all(le, ri)
        
        return do_merge(0, len(lists) - 1)
