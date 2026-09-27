"""
Filename: peak_in_grid.py
Date: 2026-09-25
"""


class Solution:
    def findPeakGrid(self, mat):
        M, N = len(mat), len(mat[0])
        lo, hi = 0, M - 1
        while lo <= hi:
            mid = lo + ((hi - lo) // 2)
            max_col = 0
            # iterate over the mid column to find a max element
            for c in range(1, N):
                if mat[mid][c] > mat[mid][max_col]:
                    max_col = c
            # once found determine if this max is actual max by comparing it withs neighbors from prev row(mid - 1), and the next row(mid + 1)
            left = mat[mid - 1][max_col] if mid > 0 else -1
            right = mat[mid + 1][max_col] if mid + 1 < M else -1
            # if it is, we found the global max
            if left < mat[mid][max_col] and right < mat[mid][max_col]:
                return [mid, max_col]
            # else we look into left
            if left > mat[mid][max_col]:
                hi = mid - 1
            else:
                # or right
                lo = mid + 1
        return [-1, -1]


if __name__ == "__main__":
    Solution().solve()
