"""
Filename: subarray_sum_equals_k.py
link: https://leetcode.com/problems/subarray-sum-equals-k/
Date: 2026-06-12
"""

from typing import List

class Solution:
    def subarraySum(self, nums: List[int], k: int) -> int:
        ans = 0
        #create a prefix sum
        ps=[]
        N = len(nums)

        for i in range(1, N):
            prev = ps[i - 1]
            ps.append(prev + nums[i])

        subarray_sum_freq = {}
        # maintain a frequency map of prefix sum seen so far which will be used in calculating if we have seen alredy a prefixsum[i] - k, increment our ans by its frquency, else add a new enrty of this ps[i] to the subarray_sum_freq with a frequency == 1
        for i in range(N):
            if ps[i] == k:
                ans+=1
            if ps[i - k] in subarray_sum_freq:
                ans += subarray_sum_freq[ps[i] - k]
            
            subarray_sum_freq[ps[i]] = subarray_sum_freq.get(ps[i], 0) + 1

        return ans


if __name__ == '__main__':
    Solution().subarraySum()