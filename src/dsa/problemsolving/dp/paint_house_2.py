# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/6/26
link: https://leetcode.com/problems/paint-house-ii/description/
The question we ask is: what i need from my neighbors?
f(i, C) = min. cost to paint houses from 0 to i WITH the last house colored with color C
        = min over all colors K which are different from C(k != C) given by
        = f(i - 1, k) + costs[i][C]
time it takes is O(k) for each cells, we have N*K cells, so total time= O(N*k*k)
how to bring it to O(nk)?
- we cannot ignore the complexity of iterating over the grid, so we need to bring down the cost associated with finding the min over all the k colors which is making O(k) time to constant time
- in order to do that an approach would be to not only keep track of a min variable across the k colors say min1, but also another min2 variable
since if the min cost happen to be corresponding to the same color that im planning to use for my cell i, then I will rely on min2 since that will be definitely different
"""

from typing import List
#time: O(n*k*k)
def minCostII(costs: List[List[int]]) -> int:
    N = len(costs)
    k = len(costs[0])

    ans = [[0] * (k) for _ in range (N)]

    for color in range(k):
        ans[0][color] = costs[0][color]

    for i in range(1, N):
        #for each house i compute the best way to color the houses upto that house ending with each specific color
        for j in range(k):
            #set ans[i][k] which depends on ans[i-1][c] where c != k
            ans[i][j] = costs[i][j] + min([ans[i - 1][curr_color] for curr_color in range(k) if curr_color !=j])

    return min(ans[-1])

#optimized to get a O(n*k)
def minCostII_opt(costs: List[List[int]]) -> int:
    N = len(costs)
    k = len(costs[0])
    if N == 1:
        if k == 1:
            return costs[0][0]
        else:
            return -1
    #Why 0? → No cost before first house. Starting from scratch.
    prevMin, prevMinIndex, prevSecondMin = 0, -1, 0 #NOTE:prevSecMin always >= prevMin
    for i in range(N):
        '''
        We want the first real cost we encounter to become the new minimum so
If the below mins are init to = 0: Will a cost of 10 replace it? No (10 > 0)
If placeholder value = inf: Will cost 10 replace it? Yes (10 < inf)
Logically: We need a starting value that any real cost will beat. That's why inf works it's a "worst possible" starting point that guarantees the first real value will replace it.
Hence, the natural flow for us will be:
        For each house, start with curr_min = inf
        Loop through colors, find actual minimum costs
        After loop: prev_min = curr_min (pass to next house)
        Repeat
        '''
        curr_min, curr_min_index, curr_second_min = float('inf'), -1,float('inf')
        for color in range(k):
            #basically our recurrence becomes
            # dp[currentHouse][currentColor] = (currentColor == prevMinColor? prevSecondMin: prevMin) + costs[currentHouse][currentColor].

            curr_cost = costs[i][color] + (prevSecondMin if color == prevMinIndex else prevMin)
            if curr_cost < curr_min:
                curr_second_min = curr_min
                curr_min = curr_cost
                curr_min_index = color
            elif curr_cost < curr_second_min:
                curr_second_min = curr_cost
        prevMin = curr_min
        prevMinIndex = curr_min_index
        prevSecondMin = curr_second_min
    return prevMin


def main():
    assert minCostII([[1,5,3],[2,9,4]]) == 5
    assert minCostII_opt([[1,5,3],[2,9,4]]) == 5


if __name__ == '__main__':
    main()
