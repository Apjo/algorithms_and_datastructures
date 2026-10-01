"""
Filename: min_window_substring.py
Date: 2026-09-28
"""


class Solution:
    def minWindow(self, s: str, t: str) -> str:
        # basically we need to determine how many distinct characters of t are present in s
        if t == "":
            return ""
        if s == t:
            return s
        freq_s, freq_t = {}, {}
        for c in t:
            freq_t[c] = freq_t.get(c, 0) + 1
        expected = len(freq_t)
        seen = 0
        left = 0
        ans = float("inf")
        M = len(s)
        start, end = -1, -1

        for ch in range(M):
            freq_s[s[ch]] = freq_s[s[ch]].get(s[ch], 0) + 1
            if s[ch] in freq_t and freq_s[s[ch]] == freq_t[s[ch]]:
                seen += 1
            while seen == expected:
                if ch - left + 1 < ans:
                    ans = ch - left + 1
                    start = left
                    end = ch
                if s[left] in freq_t and freq_s[s[left]] < freq_t[s[left]]:
                    seen -= 1
                left += 1

        return s[start : end + 1] if ans != float("inf") else ""


if __name__ == "__main__":
    Solution().solve()
