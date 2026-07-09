"""
Filename: subset_with_duplicates.py
Date: 2026-07-09
"""

from typing import List

class Solution:
    # time: O(n*2^N), space: O(2^N) using a hashset
    """
    The below func. generates every possible subset by making a binary choice at each index:

    Include the current number, or
    Skip the current number.
    Since duplicates exist, many generated subsets may look identical.
    To avoid returning duplicates, we:
    - Sort the array first, so duplicates are next to each other.
    - Store each subset as a tuple inside a set, because sets automatically remove duplicates ,and we know that tuples are hashable (lists are not).
    In the end, we convert the set of tuples back to a list of lists.
    """
    def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
        def solve(idx, res, buff):
            if idx == len(nums):
                # res.append(buff[:])
                res.add(tuple(buff[:]))
                return
            # pick
            buff.append(nums[idx])
            solve(idx + 1, res, buff)
            buff.pop()
            # not pick
            solve(idx + 1, res, buff)

        res, buff, idx = set(), [], 0
        
        nums.sort()
        solve(idx, res, buff)

        return [list(t) for t in res]

    # time: O(n*2^N), space: O(N) extra space, O(2^N) for output list
    """
    In order to avoid picking the same value in the same decision level more than once.
        At each index i, we make two choices:
        Include nums[i]
        Exclude nums[i]
        But when excluding, if the next number is the same (nums[i] == nums[i+1]), then skipping it now and skipping it later produce the same subset.
        So after exploring the "exclude" branch, we skip over all duplicate values to avoid generating duplicate subsets.

    We also sort the array first, so duplicates become consecutive and easy to skip.
    """
    def subsetsWithDup_noset(self, nums: List[int]) -> List[List[int]]:
        def solve(idx, res, buff):
            if idx == len(nums):
                # res.append(buff[:])
                res.append(buff[:])
                return
            # pick
            buff.append(nums[idx])
            solve(idx + 1, res, buff)
            buff.pop()
            
            # skip duplicates for this nums[idx]
            while idx + 1 < len(nums) and nums[idx] == nums[idx + 1]:
                idx += 1
            # not pick
            solve(idx + 1, res, buff)

        res, buff, idx = [], [], 0

        nums.sort()

        solve(idx, res, buff)

        return res


if __name__ == '__main__':
    Solution().solve()