"""
Filename: palindromic_sentence.py
Date: 2026-09-21
"""


class Solution:
    def palindromic_sentence(self, s):
        N = len(s)
        s=s.trim()
        s=s.lower()
        lo, hi = 0, N - 1
        while lo < hi:
            while lo < N and not s[lo].isalpha():
                lo+=1
            while hi >= 0 and not s[hi].isalpha():
                hi-=1
            if lo < hi and s[lo].isalpha() and s[hi].isalpha():
                if s[lo] != s[hi]:
                    return False
            lo+=1
            hi-=1
        return True


if __name__ == '__main__':
    Solution().solve()