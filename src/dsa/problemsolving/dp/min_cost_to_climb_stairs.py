#link https://leetcode.com/problems/min-cost-climbing-stairs/description/
from typing import List

def solve(cost: List[int]):
    if len(cost) < 2:
        return 0
    #if the len of cost is 2, we can directly return min(cost[0], cost[1])
    if len(cost) == 2:
        return min(cost[0], cost[1])
    #init dp table of size n
    n = len(cost)
    ans=[0]*(n)
    #we can start with either the 0th step, or 1st step
    ans[0]=cost[0]
    ans[1]=cost[1]
    #the answer to each step i will be equal to = adding current step cost + min of previous 1 step, or previous 2 steps
    for i in range(2, n):
        ans[i] = cost[i] + min(ans[i - 1], ans[i - 2])
    # since we are the last, we still need to determine whats the cheapest cost from previous 1 step, or 2 steps
    return min(ans[n - 1], ans[n - 2])


def main():
    assert solve([10,15,20]) == 15
    assert solve([1,100,1,1,1,100,1,1,100,1]) == 6


if __name__ == '__main__':
    main()
