"""
Filename: bit_manip_missing_number.py
Date: 2026-11-06
"""


class Solution:
    #using bit manipulation, XOR trick
    def missingNumber_xor(self, nums: list[int]) -> int:
        N = len(nums)
        ans = N
        """
        If we XOR all numbers from 0 to n, and all numbers present in the array then every number that appears in both places will cancel out, leaving only the missing number.
        """
        for i in range(N):
            ans ^= i ^ nums[i]

        return ans

    # using cyclic sort
    def missingNumber(self, nums: list[int]) -> int:
        for i in range(len(nums)):
            while nums[i] != i:
                dest_idx = nums[i]
                if dest_idx < len(nums) and nums[i] != nums[dest_idx]:
                    nums[i], nums[dest_idx] = nums[dest_idx], nums[i]
                else:
                    break
        for i in range(len(nums)):
            if nums[i] != i:
                return i
        return len(nums)


if __name__ == "__main__":
    Solution().solve()
