"""
Filename: june_29.py
Date: 2026-07-01
link: https://leetcode.com/problems/number-of-strings-that-appear-as-substrings-in-word/?envType=daily-question&envId=2026-06-29
"""

from typing import List

class Solution:
    def numOfStrings(self, patterns: List[str], word: str) -> int:
        ans = 0
        for pat in patterns:
            if pat in word:
                ans += 1
        return ans
        


if __name__ == '__main__':
    Solution().solve()