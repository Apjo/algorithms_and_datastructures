"""
Filename: june_22.py
Date: 2026-06-22
"""

from collections import Counter

class Solution:
    def maxNumberOfBalloons(self, text: str) -> int:
        # balloon_dict = dict(Counter("balloon"))
        text_dict = Counter(text)
        return min(
            text_dict["a"],
            text_dict["b"],
            text_dict["n"],
            text_dict["l"] // 2,
            text_dict["o"] // 2,
        )

        


if __name__ == '__main__':
    Solution().solve()