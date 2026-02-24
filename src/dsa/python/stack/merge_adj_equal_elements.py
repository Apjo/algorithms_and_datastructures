#link: https://leetcode.com/problems/merge-adjacent-equal-elements/description/
from typing import List

class Solution:
    def mergeAdjacent(self, nums: List[int]) -> List[int]:
        if not nums:
            return None
        stk=[]
        n = len(nums)
        
        for i in range(n):
            curr=nums[i]
            while stk and stk[-1] == curr:
                curr+=stk.pop()
            stk.append(curr)
        return stk