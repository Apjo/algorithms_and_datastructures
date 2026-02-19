# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/17/26
link:
"""
from typing import List
from functools import lru_cache

#time: O(3^(m+n))
class Solution:
    '''
    Very basic DP concept test. We analyze what choices to make.
    In this problem we have 3 choices, so each call branches into 3 calls:
        skip the nums1 element
        skip the nums2 element
        use the current nums1 and nums2 element, add their product to score

    '''
    def maxScore(self, nums1: List[int], nums2: List[int], k: int) -> int:
        res=0
        def solve(i, j, k):
            res=float("-inf")
            if i >= len(nums1) or j >= len(nums2):
                if k == 0:
                    return 0
                return float("-inf")
            '''
            1.pick at i, pick at j
            2.pick at i, not pick at j
            4.not pick at i, pick at j
            '''
            if i < len(nums1) and j < len(nums2):
                if k > 0:
                    #pick both at i and j
                    res = max(res, nums1[i] * nums2[j] + solve(i + 1, j + 1, k - 1))

            #skip at i
            if i < len(nums1):
                res=max(res, solve(i+1,j, k))

            #skip only at j
            if j < len(nums2):
                res=max(res, solve(i, j+1, k))
            return res
        return solve(0,0, k)

    def maxScore_memoized(self, nums1: List[int], nums2: List[int], k: int) -> int:
        memo={}
        def solve(i, j, k):
            res=float("-inf")
            if i >= len(nums1) or j >= len(nums2):
                if k == 0:
                    return 0
                return float("-inf")
            '''
            1.pick at i, pick at j
            2.pick at i, not pick at j
            4.not pick at i, pick at j
            '''
            if (i,j,k) in memo:
                return memo[(i,j,k)]
            if i < len(nums1) and j < len(nums2):
                if k > 0:
                    #pick both at i and j
                    res = max(res, nums1[i] * nums2[j] + solve(i + 1, j + 1, k - 1))

            #skip at i
            if i < len(nums1):
                res=max(res, solve(i+1,j, k))

            #skip only at j
            if j < len(nums2):
                res=max(res, solve(i, j+1, k))
            memo[(i,j,k)] = res
            return res
        return solve(0,0, k)

    def maxScore_memoized_lru(self, nums1: List[int], nums2: List[int], k: int) -> int:
        memo={}
        @lru_cache(None)
        def solve(i, j, k):
            res=float("-inf")
            if i >= len(nums1) or j >= len(nums2):
                if k == 0:
                    return 0
                return float("-inf")

            if i < len(nums1) and j < len(nums2):
                if k > 0:
                    #pick both at i and j
                    res = max(res, nums1[i] * nums2[j] + solve(i + 1, j + 1, k - 1))

            #skip at i
            if i < len(nums1):
                res=max(res, solve(i+1,j, k))

            #skip only at j
            if j < len(nums2):
                res=max(res, solve(i, j+1, k))
            return res
        return solve(0,0, k)


def solve():
    return None


def main():
    return solve()


if __name__ == '__main__':
    main()
