# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/19/26
"""
from typing import List

class Solution:
    #time:O(nlogn), space: O(n)
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        freq={}
        res=[]
        sorted(nums)
        for i in range(len(nums)):
            if target - nums[i] in freq:
                res.append(freq[target-nums[i]])
                res.append(i)
            else:
                freq[nums[i]]=i
        return res


def main():
    return Solution().twoSum([1,2,3], 5)


if __name__ == '__main__':
    main()
