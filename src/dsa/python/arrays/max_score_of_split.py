#link: https://leetcode.com/problems/maximum-score-of-a-split/description/
from typing import List
class Solution:
    def maximumScore(self, nums: List[int]) -> int:
        if not nums:
            return 0
        N = len(nums)
        ps=[0]*(N)
        ps[0] = nums[0]
        for i in range(1, N):
            ps[i] = ps[i - 1] + nums[i]
        # print(ps)
        suff_min = [0]*(N)
        suff_min[N - 1] = nums[N - 1]
        for i in range(N-2, -1,-1):
            suff_min[i] = min(suff_min[i + 1], nums[i])
        # print(suff_min)
        ans = float("-inf")
        for i in range(N-1):
            ans = max(ps[i] - suff_min[i+1], ans)
        return ans