"""
Filename: set_matrix_zeros.py
Date: 2026-10-30
"""


class Solution:
    """
    This can be done using M+N space, to get to O(1) space, we leverage row=0 and col=0 as markers
    1. determine if there is a 0 in row=0
    2. determine if there is a 0 in col=0
    3. Now, iterate over row/col=1 and check to see if there is a 0, if there is one, set the corresponding zeroth row/col for that row/col index to 0 (these are the markers)
    4. Now, iterate again row/col=1, and check which col for row=0, and which row for col=0 there is a 0, and accordingly set row/col to 0
    5. finally, if step 1 has a 0 on row=0 set all cols for row=0 to be 0
    6. And, if step 2 has a 0 in col=0, set all rows for col=0 to be 0
    """
    def setZeroes(self, matrix: list[list[int]]) -> None:
        M, N = len(matrix), len(matrix[0])
        row_zero, col_zero = False, False
        # determine if a 0 exists on row 0
        for i in range(N):
            if matrix[0][i] == 0:
                row_zero = True
                break

        # determine if a 0 exists on col 0
        for i in range(M):
            if matrix[i][0] == 0:
                col_zero = True
                break
        # iterate from row/col=1 and determine if there is a 0 anywhere, if there is, then mark the row 0 col=j, and row=i col=0 as 0
        for i in range(1, M):
            for j in range(1, N):
                if matrix[i][j] == 0:
                    matrix[0][j] = 0
                    matrix[i][0] = 0
        # again iterate from row/col=1 and for all matrix[i][0]=0 or matrix[0][j]=0 set those locations matrix[i][j] to 0
        for i in range(1, M):
            for j in range(1, N):
                if matrix[i][0] == 0 or matrix[0][j] == 0:
                    matrix[i][j] = 0
        # if there was a 0 seen on row=0 set all cols for row=0 0
        if row_zero:
            for i in range(N):
                matrix[0][i] = 0
        # if there was a 0 seen on col=0 set all rows for col=0 0
        if col_zero:
            for i in range(M):
                matrix[i][0] = 0


if __name__ == "__main__":
    Solution().solve()
