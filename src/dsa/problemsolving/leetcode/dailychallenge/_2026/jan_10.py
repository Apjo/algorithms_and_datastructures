# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/10/26
link: https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/description/?envType=daily-question&envId=2026-01-10
"""


def minimumDeleteSum(s1: str, s2: str) -> int:
    def get_lcs(s1, s2):
        if s1 == s2:
            return len(s1)
        M, N=len(s1), len(s2)
        ans = [[0]*(N + 1) for _ in range(M+1)]
        for i in range(1, M + 1):
            ans[i][0] = 0
        for i in range(1, N + 1):
            ans[0][i] = 0
        for i in range(1, M+1):
            for j in range(1, N + 1):
                if s1[i - 1] == s2[j - 1]:
                    ans[i][j] = ans[i - 1][j - 1] + ord(s1[i-1])
                else:
                    ans[i][j] = max(ans[i - 1][j], ans[i][j-1])
        return ans[-1][-1]

    lcs=get_lcs(s1, s2)

    return sum(ord(i) for i in s1) + sum(ord(i) for i in s2) - (2 * lcs)


def main():
    return minimumDeleteSum("delete", "leet")


if __name__ == '__main__':
    main()
