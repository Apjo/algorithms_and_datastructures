"""
Filename: median_from_a_datastream.py
Date: 2026-06-06
link: https://neetcode.io/problems/find-median-in-a-data-stream/question
"""

import heapq

class MedianFinder:
    def __init__(self):
        self.lo = []
        self.hi = []
    #time: O(5 logN) space:O(N), finding median is done in O(1) time
    def addNum(self, num: int) -> None:
        """
        use 2 heaps, one heap to indicate the lower 1/2 of the input,
        and the other heap to indicate the upper 1/2 of the input
        The lower half will be stored in the max heap,
        the upper half will be stored in the min heap.
        The reason we do this is that when we calculate median, the lower half stores the largest element from the lower 1/2 of the input,
        and the upper 1/2 stores the minimum element from the "larger" side of the sorted input
        and depending on whether the size of lower, and upper are equal or not we return the median in O(1)
        - we start by adding num to maxh
        - to balance, we add to minh by popping from maxh
        - if size of maxh < size of minh:
            -add to maxh polling from minh
        Then for finding the median:
            - if sizes of the heaps are equal:
                -return maxh[0] + minh[0] / 2
            else return from maxh[0]
        """
        heapq.heappush(self.lo, -num)
        heapq.heappush(self.hi, -heapq.heappop(self.lo))
        if len(self.lo) < len(self.hi):
            heapq.heappush(self.lo, -heapq.heappop(self.hi))

    def findMedian(self) -> float:
        if len(self.lo) == len(self.hi):
            return (-self.lo[0] + self.hi[0]) / 2
        else:
            return -self.lo[0]


if __name__ == '__main__':
    Solution().solve()