#link: https://leetcode.com/problems/jump-game-ix/description/?envType=daily-question&envId=2026-05-07
from typing import List

class Solution:
    def maxValue(self, nums: List[int]) -> List[int]:
        #find prefix max, and suffix min by using 2 individual arrays, and then find the answer
        N = len(nums)
        pref_max, suff_min = [0] * N, [0] * N
        pref_max[0] = nums[0]
        suff_min[N - 1] = nums[N - 1]
        for i in range(1, N):
            pref_max[i] = max(nums[i], pref_max[i - 1])
        for i in range(N - 2, -1, -1):
            suff_min[i] = min(nums[i], suff_min[i + 1])

        for i in range(N - 2, -1, -1):
            if pref_max[i] > suff_min[i + 1]:
                pref_max[i] = pref_max[i + 1]
        return pref_max
