"""
Filename: three_sum_closest.py
Date: 2026-09-27
"""


class Solution:
    def threeSumClosest(self, nums: list[int], target: int) -> int:
        if not nums:
            return 0
        N = len(nums)
        nums.sort()
        # calc. the initial result as sum of first 3 elements after sorting
        res = nums[0] + nums[1] + nums[2]
        for i in range(N):
            lo, hi = i + 1, N - 1
            while lo < hi:
                curr_sum = nums[i] + nums[lo] + nums[hi]
                if curr_sum == target:
                    return curr_sum
                # if this sum < pre calculated res sum, update our result
                elif abs(curr_sum - target) < abs(res - target):
                    res = curr_sum
                # else continue exploring
                elif curr_sum < target:
                    lo += 1
                else:
                    hi -= 1
        return res


if __name__ == "__main__":
    Solution().solve()
