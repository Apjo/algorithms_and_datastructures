"""
Filename: combination_sum_2.py
Date: 2026-07-07
"""

from typing import List


class Solution:
    # time: O(N*2^N), space:O(N)
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        def solve(idx, target, res, buff):
            if target == 0:
                res.append(buff[:])
                return
            if target < 0 or idx >= len(candidates):
                return

            buff.append(candidates[idx])
            solve(idx + 1, target - candidates[idx], res, buff)
            buff.pop()

            # skip duplicates for this candidates[idx]
            while idx + 1 < len(candidates) and candidates[idx] == candidates[idx + 1]:
                idx += 1
            solve(idx + 1, target, res, buff)

        res, buff, idx = [], [], 0
        # why? Because as we traverse the array from left to right, we form combinations with the current element. By skipping duplicate elements, we ensure that the same combinations are not repeated for identical elements hence we avoid having to maintain a hashset! but, if we do end up using a hashset then the space too becomes O(N*2^N)
        candidates.sort()

        solve(idx, target, res, buff)

        return res


if __name__ == "__main__":
    Solution().solve()
