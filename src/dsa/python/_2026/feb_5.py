# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/8/26
link: https://leetcode.com/problems/transformed-array/description/?envType=daily-question&envId=2026-02-05
"""

from typing import List
class Solution:
    def constructTransformedArray(self, nums: List[int]) -> List[int]:

        N = len(nums)
        b=[0]*N
        for i in range(N):
            if nums[i] > 0:
                idx = ((i + nums[i]) % N)
                # print(f"i={i}, idx for +ve={idx}, num at that loc={nums[idx]}")

                b[i] = nums[idx]
            if nums[i] < 0 :
                val = (i + nums[i])%N
                if val < 0:
                    val+=N
                # print(f"i={i}, idx for -ve={val}, num at that loc={nums[val]}")
                b[i] = nums[val]
            if nums[i]==0:
                b[i] = nums[i]
        return b

def main():
    return Solution().constructTransformedArray([3,-2,1,1])


if __name__ == '__main__':
    main()
