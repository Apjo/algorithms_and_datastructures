"""
Filename: valid_parenthesis.py
Date: 2026-10-01
"""

from typing import List


class Solution:
    def isValid(self, s: str) -> bool:
        if not s:
            return False

        stack: List[str] = []
        pairs = {")": "(", "]": "[", "}": "{"}

        for ch in s:
            if ch in "([{":
                stack.append(ch)
            elif not stack or stack.pop() != pairs[ch]:
                return False

        return not stack


if __name__ == "__main__":
    tests = ["()", "()[]{}", "(]", "([)]", "{[]}", ""]
    sol = Solution()
    for test in tests:
        print(test, sol.isValid(test))