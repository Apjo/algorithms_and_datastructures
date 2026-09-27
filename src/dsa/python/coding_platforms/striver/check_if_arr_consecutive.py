"""
Filename: check_if_arr_consecutive.py
Date: 2026-09-23
"""


class Solution:
    def isConsecutive(self, nums):
        if not nums:
            return 0
        min_x = min(nums)
        sum_x = sum(nums)
        N = len(nums)
        range_end = min_x + N - 1
        new_sum = 0
        for i in range(min_x, range_end + 1):
            new_sum += i
        return new_sum == sum_x


if __name__ == "__main__":
    Solution().solve()
