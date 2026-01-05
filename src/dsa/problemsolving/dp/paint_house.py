# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/2/26
"""
from typing import List

def solve(costs: List[List[int]]) -> int:
    #look at house i, and to paint i with color red, the previous houses should not have been painted in red so that means
    #the previous houses could be painted in GREEN, or BLUE and we need to get the min cost of to paint all such houses from 1 to i- 1 to paint house i in red
    N = len(costs)

    ans=[[0] * 3 for _ in range(N) ]
    #base case
    ans[0] = costs[0]
    for i in range(1, N):
        ans[i][0] = min(ans[i - 1][1], ans[i - 1][2])+costs[i][0]
        ans[i][1] = min(ans[i - 1][0], ans[i - 1][2])+costs[i][1]
        ans[i][2] = min(ans[i - 1][1], ans[i - 1][0])+costs[i][2]
    return min(ans[-1])


def main():
    assert solve([[17,2,17],[16,16,5],[14,3,19]]) == 10


if __name__ == '__main__':
    main()
