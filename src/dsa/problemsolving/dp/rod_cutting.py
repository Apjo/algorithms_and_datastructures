# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/5/26
link https://www.geeksforgeeks.org/dsa/cutting-a-rod-dp-13/
Problem:
Given a rod of length n inches and an array price[].
price[i] denotes the value of a piece of length i.
The task is to determine the maximum value obtainable by cutting up the rod and selling the pieces.

Note: price[] is 1-indexed array.

Observations:
For a given rod of len N, there are 2^N-1 ways of cutting it, so clearly an exponential number in N.
In general for a rod of len=i, we focus on the last piece of it.
let f(i) = max amount of money we can get by cutting a rod of len=i
What could be the len of the last piece?
It is no longer has a constant number of choices.
It could vary from 1 to i i.e. since we dont know the len of the last bit as it could be 1,2,.. or i itself, so assume the len of last bit was k
Let's treat the case where we don't cut the rod itself to be a default case,and see if we can do better,
so we will vary the len of the last piece from 1 to i - 1
so our recurrence now becomes
f(i) = max(f(i - k) + prices[k], f(i)) where k varies from 1 to i

"""

from typing import List

def solve_recur(n: int, prices: List[int])->int:
    if n == 0:
        return 0
    ans=0
    for j in range(1, n + 1):
        print(f"Cutting a rod of len={n} with start cut of len={j} => price={prices[j - 1]}, and remaining len={n - j}")
        ans = max(ans, prices[j - 1] + solve_recur(n - j, prices))
    return ans

#time: O(N*N) as our last piece can go upto N
def solve(n: int, prices: List[int]) -> int:
    if n <= 0 or len(prices) == 0:
        return 0
    table = [0]*(n + 1)
    #base cases
    table[0]=0
    table[1] = prices[1]
    for i in range(2, n + 1):
        #fill in table[i]
        for k in range(1, i + 1):
            #note going back to our recurrence above we need another loop to evaluate all the cases when k=1,2,..i i.e. when
            #our last piece was of len=1,2,3,..i
            table[i] = max(table[i], table[i - k] + prices[k - 1])
    return table[n - 1]


def main():
    prices = [1, 5, 8, 9, 10, 17, 17, 20]
    assert solve_recur(n=len(prices), prices=prices) == 22
    assert solve(n=len(prices), prices=prices) == 22


if __name__ == '__main__':
    main()
