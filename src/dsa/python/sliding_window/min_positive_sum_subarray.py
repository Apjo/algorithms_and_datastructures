"""
Filename: min_positive_sum_subarray.py
Date: 2026-07-19
"""

from typing import List

class Solution:
    def minimumSumSubarray(self, nums: List[int], l: int, r: int) -> int:
        ans = float("inf")

        def find_min_sub_sum(k):
            curr_ans = float("inf")
            curr_sum = 0
            for i in range(k):
                curr_sum += nums[i]
            if curr_sum > 0:
                curr_ans = min(curr_ans, curr_sum)

            for i in range(k, len(nums)):
                curr_sum += nums[i] - nums[i - k]
                if curr_sum > 0:
                    curr_ans = min(curr_ans, curr_sum)
            return curr_ans

        for i in range(l, r + 1):
            ans = min(ans, find_min_sub_sum(i))
        
        return int(ans) if (ans != float("inf") and ans > 0) else -1

        


if __name__ == '__main__':
    Solution().minimumSumSubarray(nums=[3, -2, 1, 4], l=2, r=3)