# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/8/26
link: https://leetcode.com/problems/max-dot-product-of-two-subsequences/?source=submission-ac
"""

from typing import List
def maxDotProduct(nums1: List[int], nums2: List[int]) -> int:
    m, n = len(nums1), len(nums2)
    ans=[[0]*(n) for _ in range(m)]
    curr_max = float('-inf')
    for i in range(m):
        curr_max = max(curr_max, nums1[i]*nums2[0])
        ans[i][0] = curr_max
    curr_max = float('-inf')
    for i in range(n):
        curr_max = max(curr_max, nums1[0]*nums2[i])
        ans[0][i] = curr_max
    for i in range(m):
        for j in range(n):
            #lcs
            ans[i][j] = max(ans[i - 1][j], ans[i][j - 1], ans[i-1][j-1]+nums1[i]*nums2[j], nums1[i]*nums2[j])
    return ans[-1][-1]


def main():
    return maxDotProduct([2,1,-2,5], [3,0,-6])


if __name__ == '__main__':
    main()
