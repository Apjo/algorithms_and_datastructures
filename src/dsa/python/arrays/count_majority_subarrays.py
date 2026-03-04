#link:https://leetcode.com/problems/count-subarrays-with-majority-element-i/description/

from typing import List
class Solution:
    def countMajoritySubarrays(self, nums: List[int], target: int) -> int:
        ans=0
        N = len(nums)
        for i in range(N):
            count=0
            for j in range(i, N):
                curr_len = j - i + 1
                # print(f"i={i}, j={j} so current sub_arr={sub_arr} with Len={L}")
                if target == nums[j]:
                    count+=1
                if 2 * count > curr_len:
                    # print(f"i={i}, j={j} count of target={t} is={count} in sub_array={nums[i:j]} with LEN={curr_len}")
                    ans+=1
        return ans