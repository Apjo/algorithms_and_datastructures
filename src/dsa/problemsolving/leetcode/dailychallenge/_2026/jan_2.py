# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/5/26
"""
from typing import List

#O(N) time and space
def repeatedNTimes(nums: List[int]) -> int:
    freq={}
    for i in nums:
        if i in freq:
            return i
            # freq[i]+=1
        # else:
        freq[i]=1
        # for k, v in freq.items():
        #     if v >= 2:
        #         return k
        # return None


def main():
    return repeatedNTimes([])


if __name__ == '__main__':
    main()
