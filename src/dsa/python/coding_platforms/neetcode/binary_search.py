#link: https://neetcode.io/problems/binary-search/question
from typing import List
class Solution:
    def search(self, nums: List[int], target: int) -> int:
        lo,hi = 0, len(nums) - 1
        while lo <= hi:
            mid = lo + ((hi  - lo) // 2)
            if nums[mid] == target:
                return mid
            elif nums[mid] < target:
                lo=mid + 1
            else:
                hi = mid - 1
        return -1
    
    def search_rec(self, nums: List[int], target: int) -> int:
        lo,hi = 0, len(nums) - 1
        def solve(lo, hi):
            if lo > hi:
                return -1
            mid = lo + ((hi  - lo) // 2)
            if nums[mid] == target:
                return mid
            elif nums[mid] < target:
                return solve(mid + 1, hi)
            else:
                return solve(lo, mid - 1)
        return solve(lo, hi)
