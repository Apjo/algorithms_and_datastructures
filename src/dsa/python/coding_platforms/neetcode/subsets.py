"""
Filename: subsets.py
Date: 2026-07-01
link: https://neetcode.io/problems/subsets/question
"""

from typing import List

class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        def solve(res, buff, idx):
            if idx == len(nums):
                """
                What:
                buff[:] creates a shallow copy of your list (or a portion of it), which remains mutable. 
                Then tuple(...) freezes that data into an unchangeable sequence.
                Finally, res.add(...) successfully tracks it, automatically ignoring duplicates if you add the same sequence again later.
                """
                res.add(tuple(buff[:]))
                return
            # pick
            buff.append(nums[idx])
            solve(res, buff, idx + 1)
            buff.pop()
            # dont pick
            solve(res, buff, idx + 1)

        res = set()
        buff = []

        solve(res, buff, 0)

        return [list(t) for t in res]
        


if __name__ == '__main__':
    Solution().subsets(nums=[1, 2, 3])