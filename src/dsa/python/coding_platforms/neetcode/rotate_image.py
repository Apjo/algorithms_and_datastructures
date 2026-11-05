"""
Filename: rotate_image.py
Date: 2026-10-29
"""


class Solution:
    def rotate(self, matrix: list[list[int]]) -> None:
        M, N = len(matrix), len(matrix[0])
        # transpose
        for i in range(M):
            # take a special note here, the col moves with same index as the row!
            for j in range(i, N):
                matrix[i][j], matrix[j][i] = matrix[j][i], matrix[i][j]
        print(f"after transpose={matrix}")

        # reverse each row
        def rev(row):
            lo, hi = 0, N - 1
            while lo < hi:
                matrix[row][lo], matrix[row][hi] = matrix[row][hi], matrix[row][lo]
                lo += 1
                hi -= 1

        for i in range(M):
            rev(i)


if __name__ == "__main__":
    Solution().solve()
