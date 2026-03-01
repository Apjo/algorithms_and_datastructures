#link: https://leetcode.com/problems/continuous-subarray-sum/description/
from typing import List
class Solution:
    '''
    Notes on how the % works here: https://leetcode.com/problems/continuous-subarray-sum/description/comments/1764484/
    
    '''
    def checkSubarraySum(self, nums: List[int], k: int) -> bool:
        freq={0:-1}
        ps=0
        for i in range(len(nums)):
            ps+=nums[i]
            ps%=k
            if ps in freq:
                if i - freq[ps] > 1:
                    return True
            else:
                freq[ps]=i
        return False