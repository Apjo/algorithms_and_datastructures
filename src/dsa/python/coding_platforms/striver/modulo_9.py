"""
Filename: modulo_9.py
Date: 2026-10-08
https://takeuforward.org/practice/dsa/check-if-a-large-number-is-divisible-by-9?tab=problem&discussion_id=6128
"""


class Solution:
    # time:O(N), space:O(1)
    def isDivisibleBy9(self, s: str) -> bool:
        remainder = 0

        for start in range(0, len(s), 3):
            group = int(s[start : start + 3])

            remainder = (group * 1000 + remainder) % 9

        return remainder == 0

    def isDivisibleBy9_brute(self, s: str) -> bool:
        cnt = 0
        for cc in s:
            x = ord(cc) - ord("0")
            # print(x)
            cnt += x
        # print()
        return cnt % 9 == 0


if __name__ == "__main__":
    Solution().solve()
