# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/28/26
link https://leetcode.com/problems/combination-sum/description/

"""
from typing import List

def combinationSum1(candidates: List[int], target: int) -> List[List[int]]:
    #pick and not pick strategy
    res, buff=[], []

    def solve(k, index, buff, res):
        if k == 0 :
            res.append(buff[:])
            return
        if k < 0 or index >= len(candidates):
            return
        #pick and keep on picking for unlimited times! OBSERVE: not moving index!
        buff.append(candidates[index])
        k-=candidates[index]
        solve(k, index, buff, res)
        k+=candidates[index]
        buff.pop()
        #not pick, so move to the next one, so avoids duplicates
        solve(k, index + 1, buff, res)

    solve(target, 0, buff, res)
    return res

def main():
    return combinationSum1([10,1,2,6,7,1,5], 8)


if __name__ == '__main__':
    main()
