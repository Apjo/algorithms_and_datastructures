"""
Filename: max_len_substr_with_2_occurrences.py
Date: 2026-08-31
"""


class Solution:
    def maximumLengthSubstring(self, s: str) -> int:
        # maintain a freq map of each char
        # slide through the string, for each char keep track of how many we have seen, if we exceed 2, increment left, and keep on decrementing count of that char by 1 if we reach 0 for that char, delete from map.
        if not s:
            return 0
        ans, N, left = 0, len(s), 0
        freq = {}
        for i in range(N):
            if s[i] in freq:
                freq[s[i]] += 1
            else:
                freq[s[i]] = 1
            while freq[s[i]] > 2:
                freq[s[left]] -= 1
                left += 1
            ans = max(ans, i - left + 1)

        return ans

if __name__ == '__main__':
    Solution().solve()