from typing import List
import deque
import heapq

class Solution:
    #time: O(nlogn) using max heap, space:O(n)
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        #maintain a max heap with (index, element)
        max_heap, res = [], []
        for i in range(len(nums)):
            #as we slide right our window, we keep on adding elements to the heap sorted based on values in descending order
            heapq.heappush(max_heap, [-nums[i], i])
            #once we get past the window of size k
            if i >= k - 1:
                #pop from the heap the element on the left side of the window which is outside
                while max_heap[0][1] <= k - 1:
                    heapq.heappop(max_heap)
                res.append(-max_heap[0])
        return res
    
    def maxSlidingWindow_2(self, nums: List[int], k: int) -> List[int]:
        #in this we use a deque whose pop, and push operations run in O(1) time, this will help us get the time in O(n)
        buff = deque()
        right, left = 0, 0
        res=[]
        #keep expanding the window to the right, if the element at the right end of the queue is < nums[i], pop from the queue, else add this index to the queue
        while right < len(nums):
            while buff and nums[buff[-1]] < nums[right]:
                buff.pop()
            buff.append(right)
            if left > buff[0]:
                buff.popleft()
            if right + 1 >= k :
                res.append(nums[buff[0]])
                left+=1
            right+=1
        #if the index of the left pointer is way beyond the index in the queue, pop from the queue from the LEFT!
        #once the window reaches a size == k, the front of the queue is the max element, add this to the result
        #return result
        return res

      