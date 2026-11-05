#link: https://neetcode.io/problems/find-duplicate-integer/question
from typing import List

class Solution:
    def findDuplicate(self, nums: List[int]) -> int:
        slow,fast=0,0
        while True:
            slow=nums[slow]
            fast=nums[nums[fast]]
            if slow == fast:
                slow=0
                while slow != fast:
                    slow=nums[slow]
                    fast=nums[fast]
                return slow
    def findDuplicate_2(self, nums: List[int]) -> int:
        slow,fast=nums[0],nums[nums[0]]
        #try to find a cycle
        while slow != fast:
            slow=nums[slow]
            fast=nums[nums[fast]]
        slow=0 #found a cycle, restart from head
        while slow != fast:
            slow=nums[slow]
            fast=nums[fast]
        return slow
