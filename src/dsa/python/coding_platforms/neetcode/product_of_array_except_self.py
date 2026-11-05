from typing import List

class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        #use the logic of prefix sum, execept we keep the product of the numbers instead of sum
        N = len(nums)
        ps=[0]*(N)
        ps[0]=1
        for i in range(1, N):
            ps[i] = ps[i - 1] * nums[i - 1]
        mul=1
        for i in range(N - 1, -1, -1):
            ps[i]*=mul
            mul*=nums[i]
        return ps