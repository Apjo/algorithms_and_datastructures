"""
Filename: most_common_word.py
Date: 2026-10-06
"""


class Solution:
    def mostCommonWord(self, paragraph: str, banned: list[str]) -> str:
        # para_small = paragraph.lower()
        # para_t = para_small.strip()
        for c in paragraph:
            if c in "!?',;.":
                paragraph = paragraph.replace(c, " ")

        ws = paragraph.lower().split()
        print(ws)
        
        freq_mp = {}
        curr_ma = 0
        ans = ""
        
        for w in ws:
            if w not in banned:
                freq_mp[w] = freq_mp.get(w, 0) + 1
        print(freq_mp)
        max_freq = max(freq_mp.values())
        for k, v in freq_mp.items():
            if v == max_freq:
                return k
        


if __name__ == '__main__':
    Solution().solve()