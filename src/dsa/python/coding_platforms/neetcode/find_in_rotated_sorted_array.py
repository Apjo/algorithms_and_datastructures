#link: https://neetcode.io/problems/find-target-in-rotated-sorted-array/question
from typing import List

class Solution:
    def search(self, nums: List[int], target: int) -> int:
        lo, hi = 0, len(nums) - 1
        ans = -1
        if nums[lo] == target:
            return lo
        elif nums[hi] == target:
            return hi
        while lo <= hi:
            mid = (lo + hi) // 2
            if nums[mid] == target:
                ans = mid
                return ans
            if nums[lo] <= nums[mid]:
                #lhs is sorted
                #if target between lo and mid, search between lo and mid i.e. hi = mid - 1
                if target >= nums[lo] and target < nums[mid]:
                    hi = mid - 1
                else:
                    #else target could be between mid + 1 to hi i.e. lo = mid + 1
                    lo = mid + 1
            else:
                #rhs is sorted
                #if target between mid + 1 and hi, then search in this range i.e. lo = mid + 1
                if target >= nums[mid] and target < nums[hi]:
                    lo = mid + 1
                else:
                    # else, search between hi = mid - 1 i.e. between lo to mid
                    hi = mid - 1
        return ans