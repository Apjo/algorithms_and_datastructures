"""
Filename: first_missing_number.py
Date: 2026-09-30
"""


class Solution:
    #using cyclic sort
    def missingNumber(self, nums: list[int]) -> int:
        N = len(nums)
        for i in range(N):
            while nums[i] != i:
                destn_idx = nums[i]
                if nums[destn_idx] != nums[i]:
                    nums[destn_idx], nums[i] = nums[i], nums[destn_idx]
                else:
                    break
        for i in range(N):
            if i != nums[i]:
                return i

        return N


if __name__ == '__main__':
    Solution().solve()