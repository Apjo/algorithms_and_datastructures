# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/4/26
"""
from typing import List


def lengthOfLIS(nums: List[int]) -> int:
    ans=[1]*(len(nums)) #will store the longest increasing subsequence ending at index i
    for i in range(len(nums)):
        #why this loop? look back at all previous numbers and see if I can attach this number at index i to any LIS ending there at j
        for j in range(i):
            if nums[i] > nums[j]:
                ans[i] = max(ans[i], ans[j] + 1)
    print(ans)
    return max(ans)#the LIS could end anywhere, not necessarily at the last element of the array.

def main():
    return lengthOfLIS([5,1,2,1])


if __name__ == '__main__':
    main()
