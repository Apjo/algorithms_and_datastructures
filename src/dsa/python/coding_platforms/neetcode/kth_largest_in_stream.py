#link: https://neetcode.io/problems/kth-largest-integer-in-a-stream/question

from typing import List
import heapq

class KthLargest:
    '''
    here we aren't leveraging heapify which has a time complexity of O(N), instead we add all N elements to the heap using `heappush` which takes O(Log M) M = current heap size, so worst case time complexity of our `add()` function is O(NlogN)
    space: O(N)
    '''
    def __init__(self, k: int, nums: List[int]): #O(NlogN)
        self.pq = []
        self.k = k
        for i in nums:
            heapq.heappush(self.pq, i) #O(NlogN)

    '''
    the __init__ function above as we can see add all N elements into the heap without trimming.
    So when add is called the first time, the heap has N+1 elements after the push.
    The while loop then pops until len of heap == k. Worst case is K=1, meaning it pops N times, each pop costing O(log N).
    That's O(N log N) for total time complexity
    '''
    def add(self, val: int) -> int:
        heapq.heappush(self.pq, val) #O(logM) M=current heap size
        while len(self.pq) > self.k:
            heapq.heappop(self.pq) #O(LogM) per pop
        
        return self.pq[0] #O(1)

#Next approach is trimming the heap in init

class KthLargest:
    #time: O(MlogK) where M=no.of calls made to add(), space: O(K)
    def __init__(self, k: int, nums: List[int]): #O(NlogN)
        self.pq = nums
        self.k = k
        heapq.heapify(self.pq) #This takes O(N) time
        while len(self.pq) > k:
            heapq.heappop(self.pq) #This takes O(N-K log N) time
    '''
    Whenever a new number arrives:
        If we add it and the heap grows beyond k, we remove the smallest element - because it cannot be in the top k anymore.
        This way, the heap always holds exactly the top k elements, and retrieving the k-th largest is O(1).
    '''
    def add(self, val: int) -> int:
        heapq.heappush(self.pq, val) #O(logM) M=current heap size
        if len(self.pq) > self.k:
            heapq.heappop(self.pq) #O(LogM) per pop
        
        return self.pq[0] #O(1)