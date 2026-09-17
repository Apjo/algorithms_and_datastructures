"""
Filename: array_intersection.py
Date: 2026-09-17
"""


class Solution:
    def common_elements(self, arr1, arr2):
        c = []
        M, N = len(arr1), len(arr2)
        i, j = 0, 0
        while i < M:
            j = 0
            while j < N:
                print(f"current i={i}, j={j}")
                if i < M and arr1[i] == arr2[j]:
                    print(f"found common element at i={i}, j={j}")
                    c.append(arr1[i])
                    i += 1
                j += 1
            i += 1
        return c


if __name__ == "__main__":
    Solution().solve()
