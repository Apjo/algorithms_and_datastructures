# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/19/26
"""
from typing import List
class Solution:
    #time: O(n), space: O(n)
    def hasDuplicate(self, nums: List[int]) -> bool:
        hs = set()
        for i in range(len(nums)):
            if nums[i] in hs:
                return True
            else:
                hs.add(nums[i])
        return False
def main():
    return Solution().hasDuplicate([1,2,3,1])


if __name__ == '__main__':
    main()
