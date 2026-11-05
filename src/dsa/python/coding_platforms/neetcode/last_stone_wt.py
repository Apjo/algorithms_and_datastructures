#link: https://neetcode.io/problems/last-stone-weight/question
from typing import List
import heapq

class Solution:
    #time:O(nlogn), space:O(n)
    def lastStoneWeight(self, stones: List[int]) -> int:
        
        if len(stones) == 1:
            return stones[0]
        #create a max heap
        max_h = [-x for x in stones]
        
        #O(N) time
        heapq.heapify(max_h)
        curr = 0
        
        while len(max_h) >= 1:
            x,y=0,0
            #if len of heap is > 1 that means we can pop twice
            #else, if len is exactly 1 just pop once, and set curr = curr - value  if curr > value else value - curr
            x = -1 * heapq.heappop(max_h) #a single call takes O(logN)
            y = -1 * heapq.heappop(max_h) #a single call takes O(logN)
            
            curr = x - y if x > y else y - x
            heapq.heappush(max_h, -curr)

            if len(max_h) == 1:
                curr = -1 * heapq.heappop(max_h)
                break
        
        return curr
