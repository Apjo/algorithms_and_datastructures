"""
Filename: permutations.py
Date: 2026-07-08
"""

from typing import List

class Solution:
    # time: O(n!*n), space for output only: O(n!*n)
    def permute(self, nums: List[int]) -> List[List[int]]:
        def solve(idx, buff, res):
            if idx == len(nums):
                print(f"reach eol at nums, adding buff={buff} to res!")
                res.append(nums[:])
                return
            # add element at idx
            # then for each of the remaining elements, perform swap amongst them
            # each swap will generate a new seq of elements of nums
            # add this finally to res upon reaching the end
            # repeat for each element in nums
            """
            Swap-based permutation generation (what you're actually writing) doesn't need any visited-tracking for distinct elements. The whole point of swapping index i into position idx, recursing, then swapping back, is that it naturally cycles every remaining element through position idx exactly once. No buff, no boolean array — the swap mechanism is the "pick" mechanism.
            - Because the swap-back always happens, sibling iterations of the for loop are guaranteed to see the same starting array each time.
            - Since all elements are distinct, there's no possibility of generating the same permutation twice — every distinct choice of "what goes in position idx" leads to a distinct final arrangement. That's why no dedup structure is needed at all for 46.


            """
            for i in range(idx, len(nums)):
                print(f"perform swap at i={i}, idx={idx}")
                nums[i], nums[idx] = nums[idx], nums[i]
                print(f"recurse with idx={idx + 1}")
                solve(idx + 1, buff, res)
                nums[i], nums[idx] = nums[idx], nums[i]

        res, buff, idx = [], [], 0
        solve(idx, buff, res)
        return res
        


if __name__ == '__main__':
    Solution().solve()