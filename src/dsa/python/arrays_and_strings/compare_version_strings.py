"""
Filename: compare_version_strings.py
Date: 2026-09-29
"""


class Solution:
    # use 2 pointers, and without having to split strings to iterate over both versions, skipping '.', and creating an int whenever we encounter a number.
    def compareVersion(self, version1: str, version2: str) -> int:
        i, j, n1, n2 = 0, 0, 0, 0
        M, N = len(version1), len(version2)
        while i < M or j < N:
            n1, n2 = 0, 0
            while i < M and version1[i] != ".":
                n1 = n1 * 10 + int(version1[i])
                i += 1
            while j < N and version2[j] != ".":
                n2 = n2 * 10 + int(version2[j])
                j += 1
            if n1 > n2:
                return 1
            elif n1 < n2:
                return -1
            else:
                i += 1
                j += 1
        return 0


if __name__ == "__main__":
    Solution().solve()
