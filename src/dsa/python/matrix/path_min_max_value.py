#link: https://leetcode.com/problems/path-with-maximum-minimum-value/?envType=company&envId=geico&favoriteSlug=geico-all

import heapq
from typing import List

class Solution:
    def maximumMinimumPath(self, grid: List[List[int]]) -> int:
        #iterate over the grid as you normally would while keeping track of the visited cells.
        #But, most importantly, you want the max of min values so we start with keeping a max heap
        M,N = len(grid), len(grid[0])
        max_heap = []
        #add the first element at location (0,0)
        heapq.heappush(max_heap, (-grid[0][0], 0,0))
        visited = set()
        visited.add((0,0))
        moves = [(0, 1), (0, -1), (1, 0), (-1, 0)]

        while max_heap:
            elem, row, col = heapq.heappop(max_heap)
            if row == M - 1 and col == N - 1:
                return -elem
            for r, c in moves:
                new_row = row + r
                new_col = col + c       
                if 0 <= new_row < M and 0 <= new_col < N and (new_row, new_col) not in visited:
                    visited.add((new_row, new_col))
                    to_consider_next = max(-grid[new_row][new_col], elem)
                    heapq.heappush(max_heap, (to_consider_next, new_row, new_col))
        return -1



