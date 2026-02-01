# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/28/26
link: https://leetcode.com/problems/combination-sum-ii/description/
"""
from typing import List

#time: O(2^N), space: O(N)

def combinationSum2(candidates: List[int], target: int) -> List[List[int]]:
    #sort the input so that duplicates are together
    arr = sorted(candidates)
    #pick and not pick strategy, but if you don't pick arr[i] do not pick all the adjacent arr[i+1] too!
    res, buff=set(), []
    def solve(arr, k, index, buff, res):
        if k == 0 :
            res.add(tuple(buff[:]))
            return
        if k < 0 or index >= len(arr):
            return
        #pick
        buff.append(arr[index])
        solve(arr, k - arr[index], index + 1, buff, res)
        buff.pop()
        # do not pick arr[index] and all other indexes where it matches
        j = index + 1
        while j < len(arr) and arr[index]== arr[j]:
            j+=1
        solve(arr, k, j, buff, res)
    solve(arr, target, 0, buff, res)

    return [list(item) for item in res]

def main():
    return combinationSum2([10,1,2,6,7,1,5], 8)


if __name__ == '__main__':
    main()
