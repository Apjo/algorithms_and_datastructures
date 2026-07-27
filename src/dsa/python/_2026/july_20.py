"""
Filename: july_20.py
Date: 2026-07-21
"""

from typing import List

class Solution:
    def shiftGrid(self, grid: List[List[int]], k: int) -> List[List[int]]:
        # flatten the entire grid into a 1D list
        def flatten() -> List[int]:
            return [elem for inner_list in grid for elem in inner_list]

        def rotate(k):
            cols = len(grid[0])
            #flatten a 2d grid to a single list of size N
            A = flatten()
            ret = []
            N = len(A)
            k = k % N
            ret = []
            if N == 0:
                return ret
            
            #grab the tail of size k, and bring to front, and then append rest of the list to tail
            ret = A[-k:] + A[:-k]
            ret2 = []

            for i in range(0, N, cols):
                ret2.append(ret[i : i + cols])

            return ret2

        return rotate(k)
        


if __name__ == '__main__':
    Solution().solve()