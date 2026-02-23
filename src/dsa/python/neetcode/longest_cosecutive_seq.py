from typing import List

class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        #add all elements to hashset, remove duplicates
        # iterate over the hashset
            #if the element - 1 is not present in the hashset
            # then this num is the start of the seq
            #init the curr len=1,and continue moving ahead until this num + curr len is in the hashset
            #set ans=max(ans, curr len)
        hs = set(nums)
        ans=0
        for num in hs:
            if num - 1 not in hs:
                curr_len=1
                while (num + curr_len) in hs:
                    curr_len+=1
                ans=max(ans, curr_len)
        return ans