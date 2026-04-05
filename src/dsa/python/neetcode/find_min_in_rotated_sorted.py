#link: https://neetcode.io/problems/find-minimum-in-rotated-sorted-array/question
from typing import List 

class Solution:
    def findMin(self, nums: List[int]) -> int:
        lo, hi = 0, len(nums) - 1
        ans = float("infinity")
        while lo <= hi:
            mid = (lo + hi) // 2
            if nums[lo] <= nums[mid]:
                ans = min(ans, nums[lo])
                lo = mid + 1
            else:
                ans = min(ans, nums[mid])
                hi = mid - 1
        return ans

