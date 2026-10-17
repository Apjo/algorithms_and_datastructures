"""
Filename: burst_balloons.py
Date: 2026-10-11
"""


class Solution:

    def maxCoins_dp(self, nums: list[int]) -> int:

        N = len(nums)
        # build a new nums arr where we drop 0s and prepend, and append a 1.
        # This padding of nums makes the balloon boundaries available at left - 1 and right + 1.
        nums = [1] + nums + [1]
        dp = [[0] * (N + 2) for _ in range(N + 2)]
        """
        dp[left][right] is the most coins from bursting every balloon in [left, right].
        For each possible last balloon i:
          - dp[left][i - 1] bursts the balloons to its left first.
          - dp[i + 1][right] bursts the balloons to its right first.
          - Then nums[i] is the only balloon left in this interval, so its neighbors are nums[left - 1] and nums[right + 1].
        k is the interval length; solving shorter intervals first lets us use them
        to calculate longer intervals.
        NOTE:The key idea is that i is the balloon burst last within [left, right]. k does not identify a balloon in this code; it controls how long the interval is.
        """
        for k in range(1, N + 1):
            # We define the sub-problem size by the length k, we solve the all the sub-problems M[i][left + k - 1] where left varies with fixed length k, left + k can not larger than N, i.e. left + k - 1 <= N, thus we have left <= N - k + 1
            for left in range(N - k + 2):
                right = left + k - 1

                for i in range(left, right + 1):
                    left_num = nums[left - 1] if left - 1 >= 0 else 1
                    right_num = nums[right + 1] if right + 1 <= N else 1
                    coins_earned = left_num * nums[i] * right_num
                    # burst ballons from index left to i - 1, i.e. newNums[left: i - 1], and we get the max coins for this part, which is M[left][i - 1]
                    left_sum = dp[left][i - 1]
                    # burst ballons from index i + 1 to right, i.e. newNums[right: i - 1], and we get the max coins for this part, which is M[i + 1][right]
                    right_sum = dp[i + 1][right]
                    # at last, only newNums[i] is left, the coins we can get is determined by its left newNums[left - 1] and its right newNums[right + 1], which is newNums[left - 1] * newNums[i] * newNums[right + 1]

                    # dp[left][right] = max(dp[left][right], dp[left][i-1] + nums[left - 1] * nums[i] * nums[right + 1] + dp[i + 1][right])
                    dp[left][right] = max(
                        dp[left][right], coins_earned + left_sum + right_sum
                    )

        return dp[1][N]

    def maxCoins_memo(self, nums: list[int]) -> int:
        memo = {}
        nums = [1] + nums + [1]

        def solve(le, ri):
            if le > ri:
                return 0
            if (le, ri) in memo:
                return memo[(le, ri)]
            ans = 0
            for i in range(le + 1, ri):
                ans = max(
                    ans, nums[le] * nums[i] * nums[ri] + solve(le, i) + solve(i, ri)
                )
            memo[(le, ri)] = ans
            return ans

        return solve(0, len(nums) - 1)

    def maxCoins_recur(self, nums: list[int]) -> int:
        if not nums:
            return 0
        nums = [1] + nums + [1]

        def solve(nums):
            if len(nums) == 2:
                return 0
            ans = 0
            for i in range(1, len(nums) - 1):
                coins = nums[i - 1] * nums[i] * nums[i + 1]
                coins += solve(nums[:i] + nums[i + 1 :])
                return max(ans, coins)

        return solve(nums)


if __name__ == "__main__":
    Solution().solve()
