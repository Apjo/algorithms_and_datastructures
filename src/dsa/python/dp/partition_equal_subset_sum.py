# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/15/26
link: https://leetcode.com/problems/partition-equal-subset-sum/
Notes:
- we cannot divide a set into 2 equal subsets if the sum of elements in the set are odd
- so if the sum of all elements of a set is even then we may or may not have subsets having equal subset sum
-
"""
from typing import List
#time: O(2^N), space: O(N)
def solve_recur(arr: List[int]) -> bool:
    def solve(arr: List[int], idx: int, init_sum: int, expected_sum: int):
        if idx == len(arr):
            if init_sum == expected_sum:
                return True
            return False
        if init_sum > expected_sum:
            return False
        #pick
        #init_sum+=arr[idx]
        if init_sum == expected_sum:
            return True
        picked = solve(arr, idx+1, init_sum + arr[idx], expected_sum)
        #init_sum-=arr[idx]
        #not pick
        not_picked = solve(arr, idx+1, init_sum, expected_sum)
        return picked or not_picked

    total_sum = sum(arr)
    if total_sum % 2 == 1:
        return False
    k = total_sum // 2
    return solve(arr, 0, 0, k)

'''
is there a subset that sums to k? and we want to answer this just by looking at the last input, and we can either include/exclude the last number
if we EXCLUDE the last number, we are asking the question whether we found a subset from the previous n-1 numbers adding upto k
if we INCLUDE the last number, we are asking the question whether we found a subset from previous n-1 numbers adding up to k - the last number
if either of those answers are TRUE, we return TRUE
f(n,k)  = True if there exists a subset among the first n numbers adding upto k, False otherwise
        = f(n-1, k) OR f(n-1,k - nth number) for inclusion
        = f(0,0) is True
        = f(i,0) is True as there is only 1 way to exclude all i elements to get a subset sum of 0
        = f(0,i) is False as we cannot get a non-zero subset by including 0 elements 
'''
#input size cN + log target value(k) if we are given k explicitly
def canPartition(nums: List[int]) -> bool:
    N = len(nums)
    if N == 0:
        return False
    total_sum = sum(nums)
    if total_sum % 2 == 1:
        return False
    k = int(total_sum / 2)

    ans = [[False]*(k+1) for _ in range(N + 1)]
    ans[0][0] = True

    for i in range(1, k + 1):
        ans[0][i] = False

    for i in range(1, N + 1):
        ans[i][0] = True

    for i in range(1, N + 1):
        for j in range(1, k + 1):
            #ans[numindex][targetsum] is True if the first numindex numbers can form a subset adding upto the targetsum
            #ans[numindex][targetsum] = ans[numindex - 1][targetsum] or ans[numindex - 1][targetsum - nums[numindex]]
            ans[i][j] = ans[i - 1][j]
            if j >= nums[i - 1]:
                ans[i][j] = ans[i][j] or ans[i - 1][j - nums[i - 1]]
    return ans[-1][-1]

def solve_memo(nums:List[int]):
    N = len(nums)
    if N == 0:
        return False
    total_sum = sum(nums)
    if total_sum % 2 == 1:
        return False
    k = int(total_sum / 2)
    memo = {}
    # ans = [[False]*(k+1) for _ in range(N + 1)]
    # ans[0][0] = True
    memo[(0,0)] = True
    for i in range(1, k + 1):
        # ans[0][i] = False
        memo[(0,i)] = False
    for i in range(1, N + 1):
        # ans[i][0] = True
        memo[(i, 0)] = True

    # for i in range(1, N + 1):
    #     for j in range(1, k + 1):
            #ans[numindex][targetsum] is True if the first numindex numbers can form a subset adding upto the targetsum
            #ans[numindex][targetsum] = ans[numindex - 1][targetsum] or ans[numindex - 1][targetsum - nums[numindex]]
    def helper(numindex, target):
            if (numindex, target) in memo:
                return memo[(numindex, target)]
            # ans[i][j] = ans[i - 1][j]
            # if j >= nums[i - 1]:
            #     ans[i][j] = ans[i][j] or ans[i - 1][j - nums[i - 1]]
            if target >= nums[numindex - 1]:
                memo[(numindex, target)] = helper(numindex - 1, target - nums[numindex - 1]) or helper(numindex - 1, target)
            else:
                memo[(numindex, target)] = helper(numindex - 1, target)
            return memo[(numindex, target)]
    return helper(len(nums), k)

def main():
    assert solve_recur([1,2,3,4]) == True
    assert solve_recur([1,2,3,5]) == False
    assert canPartition([1,2,3,5]) == False
    assert canPartition([1,2,3,4]) == True


if __name__ == '__main__':
    main()
