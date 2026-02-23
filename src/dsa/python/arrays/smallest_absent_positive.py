#link: https://leetcode.com/problems/smallest-absent-positive-greater-than-average/

from typing import List
class Solution:
    def smallestAbsent(self, nums: List[int]) -> int:
        N = len(nums)
        ss =sum(nums)
        avg = ss / N
        result = int(avg + 1)
        hs = set(nums)
        while result in hs or result <= 0:
            result+=1
        return result