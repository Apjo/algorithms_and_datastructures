#link: https://leetcode.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/description/
from typing import List
class Solution:
    def minimumDifference(self, nums: List[int], k: int) -> int:
        nums.sort()
        ans=float("inf")
        for i in range(len(nums)):
            if i + k - 1 >= len(nums):
                break
            ans = min(ans, nums[i + k - 1] - nums[i])
        return ans