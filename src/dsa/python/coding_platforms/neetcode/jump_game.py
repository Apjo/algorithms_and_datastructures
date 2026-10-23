"""
Filename: jump_game.py
Date: 2026-10-18
"""


class Solution:
    def jump_kadane_reverse(self, nums: list[int]) -> bool:
        # ask which positions can reach the end
        # as we move backwards we check to see which earlier positions can reach those positions
        goal = len(nums) - 1
        for i in range(len(nums) - 2, -1, -1):
            if i + nums[i] >= goal:
                goal = i
        # if the index 0 becomes the goal it means we can reach the end!
        return goal == 0

    # time O(N)
    def jump_kadane(self, nums: list[int]) -> bool:
        furthest = 0
        for i in range(len(nums)):
            if furthest < i:
                return False
            furthest = max(furthest, i + nums[i])
        return True

    # time: O(N^2)
    def jump_memo(self, nums: list[int]) -> bool:
        memo = {}
        N = len(nums)

        def solve(idx):
            if idx in memo:
                return memo[idx]

            if idx == N - 1:
                return True

            if nums[idx] == 0:
                return False

            furthest = min(N - 1, idx + nums[idx])

            for i in range(idx + 1, furthest + 1):
                if solve(i):
                    memo[i] = True
                    return True
                memo[i] = False

            return False

        return solve(0)

    # time:O(N!)
    def jump_rec(self, nums: list[int]) -> bool:
        N = len(nums)

        def solve(idx):
            # if we are already at the last index, return True
            if idx >= N - 1:
                return True
            # furthest index we can jump to from index idx
            furthest = min(N - 1, idx + nums[idx])
            # from index idx we can jump between idx + 1 to idx + nums[idx]
            for reach in range(idx + 1, furthest + 1):
                # if any call returns True, we return True
                if solve(reach):
                    return True
            # none of the jumps lead to an end, we return False
            return False

        return solve(0)


if __name__ == "__main__":
    Solution().solve()
