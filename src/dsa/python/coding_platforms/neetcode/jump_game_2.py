"""
Filename: jump_game_2.py
Date: 2026-10-18
"""


class Solution:
    # using BFS idea
    def jump_bfs(self, nums: list[int]) -> int:
        jumps, curr_end, curr_level_farthest, N = 0, 0, 0, len(nums)
        # The for loop logic checks whether we can jump from a previous position in order to jump further when we are at position i . We don't need to check whether we need to jump further when we already at the last position
        # A.Length-1 or to put it simply we use i < length-1 is because it excludes the last value in nums. We don't need to care about furthestJump we can get from the last element.
        for curr_begin in range(N - 1):
            curr_level_farthest = max(
                curr_level_farthest, curr_begin + nums[curr_begin]
            )
            # we visited all items on current level
            if curr_begin == curr_end:
                # get the "queue" size i.e. len of next level we are going to iterate
                curr_end = curr_level_farthest
                # goto next level
                jumps += 1

        return jumps

    # bottom up dp
    def jump_dp(self, nums: list[int]) -> int:
        N = len(nums)
        dp = [1000000] * (N)
        dp[N - 1] = 0
        for i in range(N - 2, -1, -1):
            # from index i, we can jump to any index in the range [i + 1, i + nums[i]]
            for j in range(i + 1, min(i + 1, i + nums[i])):
                # dp[i] represents min no. of jumps needed to reach the last index from index i
                dp[i] = min(1 + dp[j], dp[i])
        return dp[0]

    def jump_memo(self, nums: list[int]) -> int:
        N = len(nums)
        memo = {}

        def solve(idx):
            if idx == N - 1:
                return 0
            if nums[idx] == 0:
                return 1000000
            if idx in memo:
                return memo[idx]
            end = min(N - 1, idx + nums[idx])
            res = 1000000
            for i in range(idx + 1, end + 1):
                res = min(res, 1 + solve(i))
            memo[idx] = res
            return res

        return solve(0)

    def jump_rec(self, nums: list[int]) -> int:
        N = len(nums)

        def solve(idx):
            if idx == N - 1:
                return 0
            if nums[idx] == 0:
                return float("-inf")
            end = min(N - 1, idx + nums[idx])
            res = float("inf")
            for i in range(idx + 1, end + 1):
                res = min(res, 1 + solve(i))
            return res

        return solve(0)


if __name__ == "__main__":
    Solution().solve()
