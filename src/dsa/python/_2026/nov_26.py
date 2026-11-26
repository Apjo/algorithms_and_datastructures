"""
Filename: nov_26.py
Date: 2026-11-26
"""


class Solution:
    def canAliceWin(self, n: int) -> bool:
        A, B, st = 0, 0, 10
        if n < 10:
            return False
        while n > 0:
            A = st
            if n >= A:
                n -= A
            else:
                return False
            B = A - 1
            if n >= B:
                n -= B
            else:
                return True
            st = B - 1
        return False


if __name__ == "__main__":
    Solution().solve()
