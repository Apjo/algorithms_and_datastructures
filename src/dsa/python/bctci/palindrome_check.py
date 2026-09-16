"""
Filename: smaller_prefixes.py
Date: 2026-09-15
"""
class Solution:
    def palindrome(self, s):
        s = s.strip()
        lo, hi = 0, len(s) - 1
        while lo <= hi:
            if s[lo] != s[hi]:
                return False
            lo += 1
            hi -= 1
        return True

