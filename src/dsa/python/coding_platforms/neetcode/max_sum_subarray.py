"""
Filename: max_sum_subarray.py
Date: 2026-10-17
"""


class Solution:
    def maxSubArray_1ddp(self, nums: list[int]) -> int:
        N = len(nums)
        # make copy of nums as the dp table as smallest subarray ending at each index is the element itself
        dp = [*nums]
        for i in range(1, N):
            # If the sum up to the previous index is negative, extending it would only make things worse, so we start fresh at the current element.
            dp[i] = max(nums[i] + dp[i - 1], nums[i])

        return max(dp)

    # using kadane's O(n) algo, very difficult to come up with
    def maxSubArray(self, nums: list[int]) -> int:
        curr_sum, ans = 0, float("-inf")
        for i in range(len(nums)):
            if curr_sum < 0:
                curr_sum = nums[i]
            else:
                curr_sum += nums[i]
            ans = max(curr_sum, ans)

        return ans


if __name__ == "__main__":
    Solution().solve()
