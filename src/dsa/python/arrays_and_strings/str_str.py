"""
Filename: str_str.py
Date: 2026-09-27
"""


class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        M, N = len(haystack), len(needle)
        for i in range(M):
            curr = i
            j = 0
            while curr < M and j < N and haystack[curr] == needle[j]:
                curr += 1
                j += 1
            if j == N:
                return i
        return -1


if __name__ == "__main__":
    Solution().solve()
