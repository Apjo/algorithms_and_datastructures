# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/28/26
"""

from typing import List

#recursion but TLE similar to Combination Sum 1 problem!
def change_recur(amount: int, coins: List[int]) -> int:
    res=[]
    def solve(k, index, buff, res):
        if k == 0:
            res.append(buff[:])

            return
        if k < 0 or index >= len(coins):
            return
        #pick and keep on picking for unlimited times! OBSERVE: not moving index!
        buff.append(coins[index])
        k-=coins[index]
        solve(k, index, buff, res)
        k+=coins[index]
        buff.pop()
        #not pick, so move to the next one, so avoids duplicates
        solve(k, index + 1, buff, res)

    solve(amount, 0, [], res)
    return len(res)

#time: O(amount * len(coins)), space:O(amount)
def change_recur_memo(amount: int, coins: List[int]) -> int:
    memo = {}

    def solve(k, index):
        if k == 0:
            return 1
        if k < 0 or index >= len(coins):
            return 0
        if (k, index) in memo:
            return memo[(k, index)]

        #pick and keep on picking for unlimited times! OBSERVE: not moving index!
        picked = solve(k - coins[index], index)

        #not pick, so move to the next one, so avoids duplicates
        not_picked = solve(k, index + 1)

        memo[(k, index)] = picked + not_picked
        return memo[(k, index)]

    return solve(amount,0)


if __name__ == '__main__':
    main()
