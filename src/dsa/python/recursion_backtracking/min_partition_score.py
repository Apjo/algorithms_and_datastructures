# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/4/26
link: https://leetcode.com/problems/minimum-partition-score/

"""

from typing import List
class Solution:
    #time: O(n^k), if k approx == n, then O(n^3), space: O(k)
    def minPartitionScore(self, nums: List[int], k: int) -> int:
        #In this recursion ftn at every step, choose where to end the next subarray.
        def solve(index, num_partitions_left):
            #we ran out of array but still need partitions OR ran out of partitions but still have array left
            if index == len(nums) and num_partitions_left > 0 or num_partitions_left == 0 and index < len(nums):
                return float("inf")
            if index == len(nums) and num_partitions_left == 0:
                return 0
            min_score = float("inf")
            #recurse on nums[index: len(nums) - num_partitions_left] for exactly num_partitions_left
            #split the array starting at position index into exactly num_partitions_left contiguous subarrays such that the total score is minimized.
            '''
            Each recursive call answers one very specific question:
                “What is the minimum score I can get if I start partitioning at index and still need to form p subarrays?”
            Cost of each recursive call:
            we loop i from index to N
            we also compute the sum from index to i + 1 in linear time
            so per call we are costing O(n^2)
            '''
            for i in range(index, len(nums) - num_partitions_left + 1):
                #calculate the score from partition nums[index : i+1]
                curr_partition = nums[index: i + 1]
                curr_sum = sum(curr_partition)
                curr_score = ((curr_sum * (curr_sum + 1)) // 2)
                next_score = solve(i+1, num_partitions_left - 1)
                total_score = curr_score + next_score
                min_score = min(min_score, total_score)
            return min_score

        return solve(0, k)

    def minPartitionScore_memoized(self, nums: List[int], k: int) -> int:
        memo={}
        #The score of a partition depends only on the sum of its elements, which allows us to precompute prefix sums and calculate partition costs efficiently
        prefix_sum_arr=[0]*(len(nums) + 1)
        prefix_sum_arr[0] = 0
        for i in range(len(nums)):
            prefix_sum_arr[i + 1] = prefix_sum_arr[i] + nums[i]

        def cost(l, r):
            curr_sum = prefix_sum_arr[r] - prefix_sum_arr[l]
            return ((curr_sum * (curr_sum + 1)) // 2)

        def solve(index, num_partitions_left):
            if index == len(nums) and num_partitions_left > 0 or num_partitions_left == 0 and index < len(nums):
                return float("inf")
            if index == len(nums) and num_partitions_left == 0:
                return 0
            min_score = float("inf")
            if (index, num_partitions_left) in memo:
                return memo[(index, num_partitions_left)]
            #recurse on nums[index: len(nums) - num_partitions_left] for exactly num_partitions_left
            for i in range(index, len(nums) - num_partitions_left + 1):
                #calculate the score from partition nums[index : i+1]
                curr_score = cost(index, i + 1)
                next_score = solve(i+1, num_partitions_left - 1)
                total_score = curr_score + next_score
                min_score = min(min_score, total_score)
            memo[(index, num_partitions_left)] = min_score
            return min_score
        return solve(0, k)


def main():
    assert Solution().minPartitionScore([5,1,2,1], 2) == 25
    assert Solution().minPartitionScore([5,1,2,1], 1) == 45
    assert Solution().minPartitionScore([1,2,3,4], 1) == 55
    assert Solution().minPartitionScore_memoized([1,2,3,4], 1) == 55
    assert Solution().minPartitionScore([1,1,1], 3) == 3
    assert Solution().minPartitionScore_memoized([1,1,1], 3) == 3



if __name__ == '__main__':
    main()
