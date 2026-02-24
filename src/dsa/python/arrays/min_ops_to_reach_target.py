#link:
from typing import List
#time:O(N), space:O(N)
class Solution:
    def minOperations(self, nums: List[int], target: List[int]) -> int:
        if not nums or len(nums) != len(target):
            return 0
        N = len(nums)
        ans=0
        freq={}
        for num in nums:
            if num in freq:
                freq[num]+=1
            else:
                freq[num]=1
        seen=set()
        for i in range(N):
            if freq[nums[i]]==1 and nums[i]!=target[i] or freq[nums[i]] > 1 and nums[i] not in seen and nums[i]!=target[i]:
                seen.add(nums[i])
                ans+=1
            # else:
            #     ans=1
        return ans
        #maintain a freq count of nums
        #iterate over nums, if freq of nums[i]==1 and nums[i]!=taget[i],increment count
        #else if nums[i]>1, count for all the dupes will be 1
        #rturn cnt
    
    #time O(n), space:O(n)
    def minOperations2(self, nums: List[int], target: List[int]) -> int:
        if not nums or len(nums) != len(target):
            return 0
        N = len(nums)
        ans=0
        seen=set()
        for i in range(N):
            if nums[i] != target[i]:
                seen.add(nums[i])
        return len(seen)
