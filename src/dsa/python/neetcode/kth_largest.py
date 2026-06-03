"""
Filename: kth_largest.py
Date: 2026-06-02
"""

import heapq
from typing import List


class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        min_h = []
        for num in nums:
            heapq.heappush(min_h, num)
            if len(min_h) > k:
                heapq.heappop(min_h)

        return min_h[0]


if __name__ == "__main__":
    Solution().findKthLargest(nums=[2, 3, 1, 5, 4], k=2)
