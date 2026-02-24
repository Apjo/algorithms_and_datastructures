#link:https://leetcode.com/problems/rotate-non-negative-elements/

from typing import List

class Solution:
    def rotateElements(self, nums: List[int], k: int) -> List[int]:
        n = len(nums)
        if not nums or k < 0:
            return nums
        if all(x < 0 for x in nums):
            return nums
        temp=[]
        for i in range(n):
            if nums[i] >= 0:
                temp.append(nums[i])
        #left rotate by k places the temp list
        k = k % len(temp)
        j=0
        temp2 = temp[k:] + temp[:k]
        for i in range(n):
            if nums[i] < 0:
                continue
            else:
                nums[i] = temp2[j]
                j+=1
        return nums