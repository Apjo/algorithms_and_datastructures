# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/30/26
"""

from collections import defaultdict
from typing import List

class Solution:
    def minCost(self, grid: List[List[int]], k: int) -> int:
        '''
        we know the usual recurrence
        dp[i][j] = min(dp[i-1][j], dp[i][j-1], ) + grid[i][j]

        '''
        m,n=len(grid), len(grid[0])
        #print(f"num rows={m}, num cols={n}")
        my_inf = float("inf")
        ans=[[my_inf] * n for _ in range(m)]

        # ans[0][0]=grid[0][0]
        ans[0][0]=0
        def solve():
            #row 0
            # for i in range(1, m):
            #     ans[0][i] = grid[0][i] + ans[0][i - 1]
            # #col 0
            # for i in range(1, n):
            #     ans[i][0] = grid[i][0] + ans[i - 1][0]
            #general traversal
            for i in range(m):
                for j in range(n):
                    #add to my neighbors my cost, and pick the minimum
                    curr_ans = grid[i][j] + min(
                        ans[i-1][j] if i else my_inf,
                        ans[i][j - 1] if j else my_inf)
                    if curr_ans < ans[i][j]:
                        ans[i][j]=curr_ans
            #print(ans)

        solve()
        dd = defaultdict(list)
        for i in range(m):
            for j in range(n):
                dd[grid[i][j]].append((i, j))
        sorted_keys = sorted(dd, reverse=True)
        for i in range(k):
            curr_dist=float("inf")
            for curr_key in sorted_keys:
                for r, c in dd[curr_key]:
                    if ans[r][c] < curr_dist:
                        curr_dist = ans[r][c]
                for r, c in dd[curr_key]:
                    ans[r][c] = curr_dist
            solve()
        return ans[-1][-1]


def main():
    sol = Solution()
    return sol.minCost(grid = [[1,3,3],[2,5,4],[4,3,5]], k = 2)


if __name__ == '__main__':
    main()
