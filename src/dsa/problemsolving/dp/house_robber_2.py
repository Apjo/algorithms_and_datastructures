#link https://leetcode.com/problems/house-robber-ii/description/

from typing import List


def solve(nums: List[int]):
    N = len(nums)
    if N == 0:
        return 0
    if N == 1:
        return nums[0]
    if N == 2:
        return max(nums[0], nums[1])

    def rob(nums):
        ans=[0]*len(nums)
        ans[0] = nums[0]
        ans[1] = max(nums[0], nums[1])
        for i in range(2, len(nums)):
            ans[i] = max(ans[i - 1], ans[i - 2] + nums[i])
        return ans[-1]
    #case1: first house is robbed, since house 0 was robbed, so we can't rob house=1 , so now we have houses from 2 to n-2 to rob
    #case2: first house is not robbed then we have houses from 1 to n-1
    return max(rob(nums[:-1]), rob(nums[1:]))


def main():
    assert solve([2, 3, 2]) == 3
    assert solve([1, 2, 3, 1]) == 4
    assert solve([1,2,3]) == 3
    assert solve([1]) == 1
    assert solve([0,0]) == 0


if __name__ == '__main__':
    main()
