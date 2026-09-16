"""
Filename: interleaving_strings.py
Date: 2026-09-12
"""


class Solution:
    def isInterleave_dp(self, s1: str, s2: str, s3: str) -> bool:
        if len(s1) + len(s2) != len(s3):
            return False

        L, M, N = len(s3), len(s1), len(s2)

        dp = [[False for _ in range(N + 1)] for _ in range(M + 1)]
        dp[0][0] = True
        # case1: we have non empty s1, and an empty s2, we check if first i characters from s1 match first i chars in s3
        for i in range(1, M + 1):
            dp[i][0] = dp[i - 1][0] and s3[i - 1] == s1[i - 1]
        # case2: we have non empty s2, and an empty s1, we check if first j characters from s2 match first j chars in s3
        for j in range(1, N + 1):
            dp[0][j] = dp[0][j - 1] and s3[j - 1] == s2[j - 1]
        for i in range(1, M + 1):
            for j in range(1, N + 1):
                dp[i][j] = (
                    dp[i][j - 1]
                    and s3[i + j - 1] == s2[j - 1]
                    or dp[i - 1][j]
                    and s3[i + j - 1] == s1[i - 1]
                )
        return dp[M][N]

    def isInterleave_memo(self, s1: str, s2: str, s3: str) -> bool:
        if len(s1) + len(s2) != len(s3):
            return False

        L, M, N = len(s3), len(s1), len(s2)

        ans: bool = False
        memo = {}

        def solve(idx1, idx2):
            nonlocal ans
            if idx1 == M and idx2 == N:
                return True
            if (idx1, idx2) in memo:
                return memo[(idx1, idx2)]

            if idx1 + idx2 < L and idx1 < M and s1[idx1] == s3[idx1 + idx2]:
                r1 = solve(idx1 + 1, idx2)
                ans |= r1

            if idx1 + idx2 < L and idx2 < N and s1[idx2] == s3[idx1 + idx2]:
                r2 = solve(idx1, idx2 + 1)
                ans |= r2

            memo[(idx1, idx2)] = ans

            return ans

        return solve(0, 0)

    def isInterleave_recur(self, s1: str, s2: str, s3: str) -> bool:
        L, M, N = len(s3), len(s1), len(s2)

        ans: bool = False

        def solve(idx1, idx2):
            nonlocal ans
            if idx1 == M and idx2 == N:
                return True

            if idx1 + idx2 < L and idx1 < M and s1[idx1] == s3[idx1 + idx2]:
                r1 = solve(idx1 + 1, idx2)
                ans |= r1
            if idx1 + idx2 < L and idx2 < N and s1[idx2] == s3[idx1 + idx2]:
                r2 = solve(idx1, idx2 + 1)
                ans |= r2

            return ans

        return solve(0, 0)


if __name__ == "__main__":
    Solution().solve()
