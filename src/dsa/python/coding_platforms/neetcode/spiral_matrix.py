"""
Filename: spiral_matrix.py
Date: 2026-10-30
"""


class Solution:
    """
    Basic idea is simply simulate what the problem is asking i.e.
    while output size < M*N loop:
    1. fill row 0 from left to right
    2. increment row by 1
    3. fill last col for all rows
    4. move to left by 1 col
    5. fill by moving up from end row to start row
    6. move to right by 1 col
    end loop
    """

    def spiralOrder(self, matrix: list[list[int]]) -> list[int]:
        res = []
        M, N = len(matrix), len(matrix[0])
        total = M * N
        st_col, end_col = 0, N - 1
        st_row, end_row = 0, M - 1
        # while the total elements in output != M*N
        while len(res) < total:
            # fill 0th row starting from 0th/st col going all the way up to the end col
            for i in range(st_col, end_col + 1):
                res.append(matrix[st_row][i])
            # goto next row since all elements of curr row from above are taken up
            st_row += 1
            # now fill all elements from st_row up until the last row for the last col!
            for i in range(st_row, end_row + 1):
                res.append(matrix[i][end_col])
            # move 1 col inside i.e. to the left, and fill all elements on the last row for all columns up until 0
            end_col -= 1
            if st_row > end_row or end_col < st_col:
                break

            for i in range(end_col, st_col - 1, -1):
                res.append(matrix[end_row][i])

            # Now, go up one row from the bottom, and fill all elements from end row up until the st row for that col st_col
            end_row -= 1
            for i in range(end_row, st_row - 1, -1):
                res.append(matrix[i][st_col])
            # move one col inside, and repeat
            st_col += 1
        return res


if __name__ == "__main__":
    Solution().solve()
