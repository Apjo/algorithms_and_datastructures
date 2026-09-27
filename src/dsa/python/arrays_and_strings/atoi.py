"""
Filename: atoi.py
Date: 2026-09-26
"""


class Solution:
    def myAtoi(self, s: str) -> int:
        ans = 0
        neg = 1
        N = len(s)
        s = s.strip()
        s = s.lower()
        i = 0
        symbols = {"+": 1, "-": -1}
        while i < N and s[i] == " ":
            i += 1
        if i < N and s[i] in symbols:
            neg = symbols[s[i]]
            i += 1
        while i < N and s[i].isdigit():
            ans = ans * 10 + int(s[i])
        ans = ans * neg
        NEG_INF = -(2**31)
        POS_INF = (2**31) - 1
        if NEG_INF < ans < POS_INF:
            return ans
        if ans <= NEG_INF:
            print(f"current final ans < {-(2**31)}")
            ans = NEG_INF
        if ans >= POS_INF:
            print(f"current final ans > {(2**31) - 1}")
            ans = POS_INF
        return ans


if __name__ == "__main__":
    Solution().solve()
