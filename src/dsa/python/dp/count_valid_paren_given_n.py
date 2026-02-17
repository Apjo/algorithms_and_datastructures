# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/1/26
Problem:
Given a set of n '(' and ')' parentheses count all well formed parentheses and return the count

We define F(n) = number of well-formed parentheses that use n open and n closed parentheses
f(n) = sum (i goes from 0 to n-1 f(i) * f(n-i-1))
we want the recurrence to be held so f(0) = 1
f(1) = f(0)f(0) = 1*1 = 1
f(2) = f(0)f(1) + f(1)f(0) = 1+1 = 2
f(3) = f(0)f(2) + f(1)f(1) + f(2)f(0) = 1*2 + 1*1 + 2*1 = 2 + 1 + 2 = 5
...
f(n) = f(0)f(n-1) + f(1)f(n-2) + f(2)f(n-3) +....+ f(n-1)f(0)
"""
#time:O(N^2), space: O(n)
def generateParenthesisValidCount(n: int) -> int:
    ans =[0]*(n + 1)
    ans[0]=1
    for p in range(1, n+1):
        #compute f(p) and store in ans[p]
        for i in range(p):
            ans[p] += ans[i]*ans[p-i-1]
    return ans[n]

def solve():
    return None


def main():
    return solve()


if __name__ == '__main__':
    main()
