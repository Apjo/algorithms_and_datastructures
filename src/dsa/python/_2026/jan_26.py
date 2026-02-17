# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/30/26
"""
from typing import List
#link https://leetcode.com/problems/minimum-absolute-difference/?envType=daily-question&envId=2026-01-29

class Solution:
    def minimumAbsDifference(self, arr: List[int]) -> List[List[int]]:
        rs = sorted(arr)
        ans=float("inf")
        res=[]
        for i in range(len(rs) - 1):
            abs_diff = abs(rs[i + 1] - rs[i])
            ans = min(ans, abs_diff)
        for i in range(len(rs) - 1):
            abs_diff = abs(rs[i + 1] - rs[i])
            if abs_diff==ans:
                res.append([rs[i], rs[i+1]])
        return res


def main():
    sol = Solution
    return sol.minimumAbsDifference(arr = [4,2,1,3])


if __name__ == '__main__':
    main()
