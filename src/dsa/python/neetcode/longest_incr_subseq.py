"""
Filename: longest_incr_subseq.py
Date: 2026-08-26
"""

from typing import List


class Solution:
    # time: O(N), space:O(N)
    def lengthOfLIS(self, nums: List[int]) -> int:
        N = len(nums)
        ans = [1] * (N)
        for i in range(N):
            for k in range(i):
                if nums[i] > nums[k]:
                    ans[i] = max(ans[i], ans[k] + 1)
        # print(ans)
        return max(ans)

    # time: O(2^N)
    def lengthOfLIS_recur(self, nums: List[int]) -> int:
        N = len(nums)
        res, buff = [], []
        resl = float("-inf")

        def solve(idx, buff, res):
            nonlocal resl
            if idx >= N:
                if resl < len(buff):
                    resl = len(buff)
                    # res.append(buff[:])
                return
            # if picking
            # if buff is empty add the element at index = idx
            # else, only add if current num > buff[-1]
            # not pick
            # pick only if prev is < than current
            if not buff or nums[idx] > buff[-1]:
                buff.append(nums[idx])
                solve(idx + 1, buff, res)
                buff.pop()

            solve(idx + 1, buff, res)

        solve(0, buff, res)

        return int(resl)


if __name__ == "__main__":
    Solution().solve()
