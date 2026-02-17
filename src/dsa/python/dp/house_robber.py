#link: https://leetcode.com/problems/house-robber/description/
from typing import List


def solve_2(nums: List[int]):
    N = len(nums)
    if N == 0:
        return 0
    if N == 1:
        return nums[0]
    dp=[0]*N
    dp[0] = nums[0]
    dp[1] = max(nums[0], nums[1])

    for i in range(2, N):
        #the last house say i could either be robbed or not robbed.
        # if it is robbed, then previous house cannot be robbed, so look for solutions from 0 to i -2
        # if it is NOT robbed, then look for solutions from 0 to i - 1
        dp[i] = max(dp[i - 1], nums[i] + dp[i - 2])
    return dp[N - 1]


def solve(nums: List[int]):
    n = len(nums)
    if n == 0:
        return 0
    if n == 1:
        return nums[0]

    ans = [0]*n #ans[i]=max amount of money you can rob from houses 0...i - 1
    ans[0] = nums[0]
    ans[1] = max(nums[0], nums[1])
    if n == 2:
        return ans[1]

    ans[2] = ans[0] + nums[2]
    for i in range(3, n):
        # we cannot take the house just before i, so we can either take i - 2, or i - 3 house
        ans[i] = max(nums[i] + ans[i - 2], nums[i] + ans[i - 3])
    return max(ans[n - 1], ans[n - 2])


def main():
    assert solve([1, 2, 3, 1]) == 4
    assert solve_2([1, 2, 3, 1]) == 4
    assert solve([2, 7, 9, 3, 1]) == 12
    assert solve([1]) == 1
    assert solve([0,0]) == 0
    assert solve_2([0,0]) == 0


if __name__ == '__main__':
    main()
