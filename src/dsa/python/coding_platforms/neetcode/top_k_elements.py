# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/19/26
"""


import heapq

from typing import List

from collections import Counter

class Solution:
    #time: O(nlogk), space:O(n+k)
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        #maintain a freq map
        freq=Counter(nums) #space:O(n) in the worst case if input contains all unique elements we end up storing all of them in this frequency map
        '''
        A min-heap is perfect for keeping track of most frequent elements efficiently because it always keeps the smallest element at the top.
        By pushing (frequency, value) pairs into the heap and removing the smallest whenever the heap grows beyond size k,
        we ensure that the heap always contains the top k most frequent elements.
        In the end, the heap holds exactly the k values with the highest frequencies.
        '''
        min_heap=[]
        for val, frequency in freq.items():
            heapq.heappush(min_heap, [frequency, val])
            if len(min_heap) > k:
                heapq.heappop(min_heap)
        ans = []
        while min_heap:
            frequency, value = heapq.heappop(min_heap)
            ans.append(value)
        return ans


def main():
    return Solution().topKFrequent([7,7],1)


if __name__ == '__main__':
    main()
