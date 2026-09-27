"""
Filename: max_area_island.py
Date: 2026-07-27
"""

from typing import List


class Solution:
    # dfs time: O(m*n), space: O(m*n)
    def maxAreaOfIsland(self, grid: List[List[int]]) -> int:

        def solve(r, c, aa):
            if r < 0 or c < 0 or r >= M or c >= N or grid[r][c] == 0:
                return
            # aa+=1
            aa[0] += 1
            grid[r][c] = 0
            solve(r + 1, c, aa)
            solve(r - 1, c, aa)
            solve(r, c + 1, aa)
            solve(r, c - 1, aa)

        global_ans = float("-inf")
        M, N = len(grid), len(grid[0])

        for i in range(M):
            for j in range(N):
                if grid[i][j] == 1:
                    # aa=0
                    aa = [0]
                    solve(i, j, aa)
                    # global_ans=max(global_ans, aa)
                    global_ans = max(global_ans, aa[0])
                    # aa=0
                    aa = [0]

        return int(global_ans) if global_ans != float("-inf") else 0


if __name__ == "__main__":
    Solution().solve()
