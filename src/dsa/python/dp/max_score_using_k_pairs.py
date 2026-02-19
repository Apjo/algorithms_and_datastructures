# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/17/26
"""
'''
Why DP works here
DP works because the problem has:

Optimal substructure
The optimal solution for (i, j, t) is built from optimal solutions of:

(i-1, j, t)
(i, j-1, t)
(i-1, j-1, t-1)
Overlapping subproblems
The same (i, j, t) appears many times via different paths.
'''
from typing import List

class Solution:
    def maxScore(self, nums1: List[int], nums2: List[int], k: int) -> int:
        n, m = len(nums1), len(nums2)
        '''
        here our dp table or the recurrence relation is max score possible for choosing 
        first i elements from nums1
        first j elements from nums2
        chosing exactly t pairs
        O(N*m*k) number of states 
        '''
        NEG = -10**18
        ans=[[[NEG] * (k + 1) for _ in range(m + 1)] for _ in range(n + 1)]
        ans[0][0][0]=0
        for i in range(n + 1):
            for j in range(m + 1):
                for t in range(k + 1):
                    if i > 0: #skip from nums1
                        ans[i][j][t] = max(ans[i][j][t], ans[i-1][j][t])
                    if j > 0 :#skip from nums2
                        ans[i][j][t] = max(ans[i][j][t], ans[i][j - 1][t])
                    #else take this pair (nums1[i-1], nums2[j-1])
                    if i > 0 and j > 0 and t > 0 and ans[i-1][j-1][t-1] != NEG:
                        ans[i][j][t] = max(ans[i][j][t], ans[i - 1][j - 1][t - 1] + nums1[i - 1] * nums2[j - 1])
        return ans[n][m][k]

def main():
    return solve()


if __name__ == '__main__':
    main()
