"""
Filename: distinct_subseq.py
Date: 2026-09-23
"""


class Solution:
    #memoized
    def numDistinct(self, s: str, t: str) -> int:
        M, N = len(s), len(t)
        if N > M:
            return 0
        dp = {}

        def solve(idx1, idx2):
            if idx2 >= N:
                return 1
            if idx1 >= M:
                return 0
            if (idx1, idx2) in dp:
                return dp[(idx1, idx2)]

            a = solve(idx1 + 1, idx2)
            if s[idx1] == t[idx2]:
                a += solve(idx1 + 1, idx2 + 1)
            dp[(idx1, idx2)] = a
            return a

        return solve(0, 0)

    #pure recursion
    def numDistinct_rec(self, s: str, t: str) -> int:
        M, N = len(s), len(t)
        if N > M:
            return 0

        def solve(idx1, idx2):
            if idx2 >= N:
                return 1
            if idx1 >= M:
                return 0

            a = solve(idx1 + 1, idx2)
            if s[idx1] == t[idx2]:
                a += solve(idx1 + 1, idx2 + 1)

            return a

        return solve(0, 0)



if __name__ == '__main__':
    Solution().solve()