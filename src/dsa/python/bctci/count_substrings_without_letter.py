"""
Filename: count_substrings_without_letter.py
Date: 2026-09-12
link: https://beyondctci.dev/editor?problem=count-substrings-without-letter
"""


class Solution:
    # remember count of substrings in a string s of len N= N*(N + 1) // 2
    def count_substrings_without_letter(self, s):
        if not s:
            return 0
        new_str = s.split("a")
        k = 0
        for ss in new_str:
            N = len(ss)
            m = (N * (N + 1)) // 2
            k += m
        return k


if __name__ == "__main__":
    Solution().solve()
