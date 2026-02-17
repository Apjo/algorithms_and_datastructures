# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/28/26
link: https://leetcode.com/problems/combination-sum-iv/description/
Notes:
- order matters so this is a permutation version of the combination sum
- approach 1: brute force i.e. exhaustively try all subsets this will take exponential time atleat 2^n
- focussing on the rightmost location what is the last number going to be picked by me which makes up the target amount?
- we define the subproblem in english as F(T) is the number of ways to reach a target T using nums, more formally the subproblem solved by ith subordinate
F(T) = sum of ( F(t - nums[i])) i ranging from 0 to len(nums)
or ways(target) = sum of ways(target - nums[i]) for all i from 0 to len(nums)-1
However if our Target is >= 2^N, then the DP will still be exponential then our DP table will be 2^N!


"""

from typing import List
#time: O(len of  nums * Target), space O(Target)
def combinationSum4(nums: List[int], target: int) -> int:
    ways=[0]*(target + 1)
    ways[0]=1
    for i in range(1, target + 1):
        #compute F(i) and store in ways[i]
        for num in nums:
            if i - num >= 0:
                #if reaching target, add to the existing number of ways i.e.
                # ways(3) = ways(3-1) + ways(3-2) if target=3 for input nums=[1,2]
                # ways(2) = ways(2-1) + ways(2-2)
                # ways(1) = ways(1) + ways(1-1)
                # ways[0] = 1
                ways[i] = ways[i] + ways[i - num]
    return ways[target]

def main():
    return combinationSum4(nums=[1,2], target=3)


if __name__ == '__main__':
    main()
