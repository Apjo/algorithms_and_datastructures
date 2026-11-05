"""
Filename: word_search.py
Date: 2026-07-10
"""

from typing import List


class Solution:
    # time: O(M*4^N), space:O(N) m=len of board, N = len of word
    """
    for every cell, we try to start the word there:
    - If the current cell matches the current character, we move to its 4 neighbors for the next character.
    - While exploring, we mark the cell as visited (in a hash set) so we don't reuse it in the same path.
    - If a path fails, we undo (backtrack) the visit and try other directions.
    -If we ever match all characters, we return true (found the word).
    """

    def exist(self, board: List[List[str]], word: str) -> bool:
        M, N = len(board), len(board[0])
        visited = [[False for _ in range(N)] for _ in range(M)]
        idx = 0

        def solve(row, col, idx, visited):
            if idx == len(word):
                return True
            if (
                row < 0
                or col < 0
                or row >= M
                or col >= N
                or board[row][col] != word[idx]
                or visited[row][col]
            ):
                return False
            visited[row][col] = True
            idx += 1
            found = (
                solve(row + 1, col, idx, visited)
                or solve(row - 1, col, idx, visited)
                or solve(row, col + 1, idx, visited)
                or solve(row, col - 1, idx, visited)
            )
            visited[row][col] = False

            return found

        for row in range(M):
            for col in range(N):
                if solve(row, col, idx, visited):
                    return True
        return False


if __name__ == "__main__":
    Solution().exist(
        board=[["A", "B", "C", "D"], ["S", "A", "A", "T"], ["A", "C", "A", "E"]],
        word="BAT",
    )
