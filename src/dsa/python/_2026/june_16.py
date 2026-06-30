"""
Filename: june_16.py
Date: 2026-06-27
link: https://leetcode.com/problems/process-string-with-special-operations-i/?envType=daily-question&envId=2026-06-16
"""


class Solution:
    def processStr(self, s: str) -> str:
        res = []
        for ch in s:
            if ch == "*":
                if res:
                    res.pop()
            elif ch == "#":
                res.extend(res)
            elif ch.isalpha():
                res.append(ch)
            elif ch == "%":
                res.reverse()
        
        return "".join(res)

        


if __name__ == '__main__':
    Solution().processStr("ff#f")