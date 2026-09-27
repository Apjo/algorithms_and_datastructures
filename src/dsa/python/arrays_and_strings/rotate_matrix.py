"""
Filename: rotate_matrix.py
Date: 2026-09-27
"""


class Solution:
    def rotate(self, matrix: list[list[int]]) -> None:
        M, N = len(matrix), len(matrix[0])
        for r in range(M):
            for c in range(r + 1, N):
                matrix[r][c], matrix[c][r] = matrix[c][r], matrix[r][c]
        for r in range(M):
            lo, hi = 0, N - 1
            while lo < hi:
                matrix[r][lo], matrix[r][hi] = matrix[r][hi], matrix[r][lo]
                lo += 1
                hi -= 1
    #follow up could be a non square matrix! tip is to use the zip function or, creat a temporary array
    


if __name__ == "__main__":
    Solution().solve()
