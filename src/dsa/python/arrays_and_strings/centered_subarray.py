#link:https://leetcode.com/problems/number-of-centered-subarrays/description/

from typing import List
class Solution:
    def centeredSubarrays(self, nums: List[int]) -> int:
        if not nums:
            return 0
        ans, N = 0, len(nums)
        for i in range(N):
            seen = set()
            curr_sum=0
            for j in range(i, N):
                seen.add(nums[j])
                curr_sum+=nums[j]
                if curr_sum in seen:
                    ans+=1
        return ans