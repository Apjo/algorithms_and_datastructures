"""
Filename: max_subarray_product.py
Date: 2026-08-24
"""

from typing import List

class Solution:
    #using prefix, suffix
    def maxProduct_pre_suff(self, nums: List[int]) -> int:
        N, temp, res = len(nums), 1, float("-inf")
        for i in range(N):
            temp = temp * nums[i]
            res = max(res, temp)
            temp = temp if temp != 0 else 1
        
        temp=1
        for i in range(N-1, -1, -1):
            temp = temp * nums[i]
            res = max(res, temp)
            temp = temp if temp != 0 else 1

        return int(res)

    #kadane's algo time:O(N), space:O(1)
    def maxProduct_kd(self, nums: List[int]) -> int:
        '''
        If nums[i] is a negative number (e.g., -2), you want to multiply it by the smallest (most negative) possible previous product (e.g., -10) to get the biggest positive result (20).
        If nums[i] is a positive number (e.g., 3), you want to multiply it by the largest previous product (e.g., 10) to get 30.
        A highly negative number (like -10) is technically a "minimum" value. But the moment it hits another negative number, it instantly flips into a massive maximum value.If you don't track the current min, your code becomes "blind" to these negative flips, and it will miss the jackpot combination.
        '''
        curr_max, curr_min, res = 1,1,nums[0]
        for elem in nums:
            #since curr_max will be updated, we temporarily save it
            temp = elem * curr_max
            curr_max = max(elem, curr_max*elem, curr_min * elem)
            curr_min = min(elem, temp, curr_min * elem)
            res = max(res, curr_max)
        
        return res


    #time: O(N^3)
    def maxProduct(self, nums: List[int]) -> int:
        ans = float("-inf")
        #O(N) for every call
        def get_prod(ll):
            result = []
            running_product = 1

            for num in ll:
                running_product *= num
                result.append(running_product)

            return result

        def solve(idx):
            nonlocal ans

            if idx >= len(nums):
                return
            # The loop's job is to expand the subarray from a fixed starting point (idx). Once that loop finishes, it has successfully checked every single subarray that could possibly start at idx.
            for i in range(idx, len(nums)):
                curr_subarr = nums[idx : i + 1]
                ans = max(ans, max(get_prod(curr_subarr)))
            solve(idx + 1)

        solve(0)
        return ans
        


if __name__ == '__main__':
    Solution().solve()