# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/28/26
link: https://leetcode.com/problems/coin-change-ii/description/
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
        #in how many ways can we make amount k using coins from index index
        #Both k AND index define a unique subproblem
        #Same k with different index = different available choices = different answer
        memo[(k, index)] = picked + not_picked
        return memo[(k, index)]

    return solve(amount,0)

def change(amount: int, coins: List[int]):
    #ways[i,j] is the number of coins required(i) to make amount j
    ways=[[0]*(amount + 1) for _ in range(len(coins) + 1)]
    #init, it takes 1 way to make an amount=0 from i coins
    for coin in range(len(coins) + 1):
        ways[coin][0]=1
    for coin in range(1, len(coins) + 1):
        for amt in range(1, amount + 1):
            if amt - ways[coin - 1][amt] >=0:
                #if a coin is selected, deduct it from amount col, else if it is not selected reuse prev rows value
                ways[coin][amt] = ways[coin - 1][amt] + ways[coin][amt - coins[coin - 1]]
            else:
                ways[coin][amt] = ways[coin - 1][amt]

    return ways[-1][-1]


def main():
    return solve(amount = 5, coins = [1,2,5])


if __name__ == '__main__':
    main()
