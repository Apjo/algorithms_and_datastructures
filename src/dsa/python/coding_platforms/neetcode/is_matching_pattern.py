"""
Filename: is_matching_pattern.py
Date: 2026-10-16
"""


class Solution:
    def is_match_memo(self, s: str, p: str):
        memo = {}
        M, N = len(s), len(p)

        def solve(i, j):
            if i >= M and j >= N:
                return True
            if j >= N:
                return False
            
            if (i, j) in memo:
                return memo[(i, j)]
            
            is_match = False
            
            if i < M:
                is_match = s[i] == p[j] or p[j] == "."
            else:
                is_match = False

            if j + 1 < N and p[j + 1] == "*":
                memo[(i, j)] = solve(i, j + 2) or (is_match and solve(i + 1, j))
                return memo[(i, j)]

            if is_match:
                memo[(i, j)] = solve(i + 1, j + 1)
                return memo[(i, j)]

            return False


if __name__ == "__main__":
    Solution().solve()
