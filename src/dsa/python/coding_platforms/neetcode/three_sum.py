from typing import List

class Solution:
    #using extra space to create a set of lists to avoid duplicates
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        nums.sort()
        res=[]
        for i in range(len(nums)):
            lo, hi = i + 1, len(nums) - 1
            while lo < hi:
                ss = nums[i] + nums[lo] + nums[hi]
                if ss == 0:
                    res.append([nums[i], nums[lo], nums[hi]])
                    lo+=1
                    hi-=1
                elif ss < 0:
                    lo +=1
                else:
                    hi-=1
        #we dont need duplicates so create a set of lists
        unique_tuples = set(tuple(item) for item in res) 
        #next create a list as the final output
        return list(unique_tuples)
    def threeSum2(self, nums: List[int]) -> List[List[int]]:
        nums.sort()
        res=[]
        for i in range(len(nums)):
            if nums[i] > 0:
                break
            if i > 0 and nums[i - 1] == nums[i]:
                continue
            lo, hi = i + 1, len(nums) - 1
            while lo < hi:
                ss = nums[i] + nums[lo] + nums[hi]
                if ss == 0:
                    res.append([nums[i], nums[lo], nums[hi]])
                    lo+=1
                    hi-=1
                    while nums[lo] == nums[lo - 1] and lo < hi:
                        lo+=1
                elif ss < 0:
                    lo +=1
                else:
                    hi-=1
        return res
