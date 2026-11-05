"""
Filename: palindrome_partitions.py
Date: 2026-07-11
"""

from typing import List

class Solution:
    def partition(self, s: str) -> List[List[str]]:
        def is_palindrome(s):
            lo, hi = 0, len(s) - 1
            while lo < hi:
                if s[lo] != s[hi]:
                    return False
                lo+=1
                hi-=1
            return True

        def solve(curr_str, buff, res):
            if not curr_str:
                res.append(buff[:])
                return
            for i in range(len(curr_str)):
                curr_substr = curr_str[0: i]
                if not is_palindrome(curr_substr):
                    continue
                buff.append(curr_substr)
                solve(curr_str[i : len(curr_str)], buff, res)
                buff.pop()
        res, buff = [], []
        solve(s, buff, res)

        return res
    # time: O(n*2^n), space:  O(n*2^n) for output list, and O(n)
    def partition_2(self, s: str) -> List[List[str]]:

        def is_palindrome(s) -> bool:
            lo, hi = 0, len(s) - 1
            while lo < hi:
                if s[lo] != s[hi]:
                    return False
                lo += 1
                hi -= 1
            return True

        def solve(idx, input_s, buff, res):
            if idx >= len(input_s):
                res.append(buff[:])
                return
            for j in range(idx, len(input_s)):
                # we start from a starting index=idx, and at each step we determine where to cut the string
                # Hence, we try every possible end index=j
                # And if s[idx..j+1] is a palindrome, it can be the next piece.
                curr_substr = input_s[idx : j + 1]
                if not is_palindrome(curr_substr):
                    continue
                # Choose this substr i.e. add to buff, then recursively solve the rest starting at j + 1.
                buff.append(curr_substr)
                solve(j + 1, input_s, buff, res)
                # After coming back, undo the choice (pop) and try a different j.
                buff.pop()

        res, buff = [], []
        solve(0, s, buff, res)
        return res

    


if __name__ == '__main__':
    Solution().partition("aab")