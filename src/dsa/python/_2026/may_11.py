#link: https://leetcode.com/problems/separate-the-digits-in-an-array/description/?envType=daily-question&envId=2026-05-11
from typing import List

class Solution:
    #time: O(N log M)
    def separateDigits(self, nums: List[int]) -> List[int]:
        if not nums:
            return nums
        b=[]
        N = len(nums)
        for i in range(N - 1, -1, -1):
            if nums[i] < 10:
                b.append(nums[i])
            else:
                x = nums[i]
                while x != 0:
                    n = int(x % 10)
                    b.append(n)
                    x = x // 10
        
        #note: this returns None
        b.reverse()
        return b