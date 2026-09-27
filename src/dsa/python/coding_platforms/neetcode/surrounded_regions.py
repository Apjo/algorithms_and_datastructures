"""
Filename: surrounded_regions.py
Date: 2026-08-01
"""

from typing import List

class Solution:
    """
        Only the 'O' regions that touch the border can never be surrounded, because they have a path to the outside of the board.So instead of trying to find surrounded regions directly, we do the opposite:
        -mark all border-connected O cells as "safe" to "T"
        -any remaining O cells is truly surrounded so flip to X
        -convert any remaining T cells to O
    """
    #time: O(M*N)
    def solve(self, board: List[List[str]]) -> None:
        def solve(r, c):
            if r < 0 or c < 0 or c >= N or r >= M or board[r][c] != "O":
                return
            # Mark all border-connected 'O' cells as “safe” (temporary mark 'T').
            board[r][c] = "T"
            solve(r + 1, c)
            solve(r - 1, c)
            solve(r, c + 1)
            solve(r, c - 1)

        M, N = len(board), len(board[0])
        # run dfs on all borders of the board
        # run for all rows first and last cols
        for i in range(M):
            if board[i][0] == "O":
                solve(i, 0)
            if board[i][N - 1] == "O":
                solve(i, N - 1)
        # run for all cols first and last row
        for j in range(N):
            if board[0][j] == "O":
                solve(0, j)
            if board[M - 1][j] == "O":
                solve(M - 1, j)
        # capture on all grid
        for i in range(M):
            for j in range(N):
                if board[i][j] == "O":
                    board[i][j] = "X"
                if board[i][j] == "T":
                    board[i][j] = "O"


if __name__ == '__main__':
    Solution().solve(
        board=[
            ["X", "X", "X", "X"],
            ["X", "O", "O", "X"],
            ["X", "X", "O", "X"],
            ["X", "O", "X", "X"],
        ]
    )