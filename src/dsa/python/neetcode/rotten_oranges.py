"""
Filename: rotten_oranges.py
Date: 2026-07-30
"""

from typing import List
from collections import deque

class Solution:
    def orangesRotting(self, grid: List[List[int]]) -> int:
        """
        -iterate over the grid, and first determine if there are any rotten fruits,
        if there aren't any rotten fruits return -1
        - else collect rotten fruits locations into a q
        - start a bfs from all the locations where the rotten fruits are located
        -
        """
        if not grid:
            return 0

        M, N = len(grid), len(grid[0])

        ans = -1
        bfs_q = deque()
        directions = [[0, 1], [0, -1], [1, 0], [-1, 0]]
        captured = [[False for _ in range(N)] for _ in range(M)]
        fresh = 0
        for i in range(M):
            for j in range(N):
                if grid[i][j] == 1:
                    fresh += 1
                if grid[i][j] == 2:
                    bfs_q.append((i, j))  # O(1)

        if fresh == 0:
            return 0

        while bfs_q:
            L = len(bfs_q)
            for i in range(L):
                x, y = bfs_q.popleft()  # O(1)
                for dire in directions:
                    new_r = dire[0] + x
                    new_c = dire[1] + y
                    if (
                        new_r < 0
                        or new_c < 0
                        or new_r >= M
                        or new_c >= N
                        or grid[new_r][new_c] == 0
                        or grid[new_r][new_c] == 3
                        or captured[new_r][new_c]
                    ):
                        continue
                    if grid[new_r][new_c] == 1:
                        # will get rotten soon, add it to q
                        # what to do with "ans"? icnrement here?
                        grid[new_r][new_c] = 3
                        bfs_q.append((new_r, new_c))
                        captured[new_r][new_c] = True
            ans += 1
        # check if all fresh == 0
        for i in range(M):
            for j in range(N):
                if grid[i][j] == 3:
                    fresh -= 1
        return ans if fresh == 0 else -1
        


if __name__ == '__main__':
    Solution().solve()