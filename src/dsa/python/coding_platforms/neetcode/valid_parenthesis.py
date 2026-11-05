"""
Filename: valid_parenthesis.py
Date: 2026-10-23
"""


class Solution:
    #using 2 stacks 1 to keep track of open, and 1 to keep track fo star
    def checkValidString(self, s: str) -> bool:
        open, star = [], []
        for i in range(len(s)):
            if s[i] == '(':
                open.append(i)
            elif s[i] == '*':
                star.append(i)
            else: #we see a )
                if open:
                    open.pop()
                elif star:
                    star.pop()
                else:
                    return False #seen ) as the begin of the string
        while open and star:
            if open[-1] < star[-1]:
                open.pop()
                star.pop()
            else:
                #seeing a star before a open is not valid
                return False
        return not open #cannot have more open


if __name__ == '__main__':
    Solution().solve()