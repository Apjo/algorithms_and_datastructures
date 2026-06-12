"""
Filename: first_missing_positive.py
Date: 2026-06-12
"""

import os
import time
import pandas as pd
import numpy as np
import heapq
import math
import collections
from typing import Optional, List
import random
from collections import deque, defaultdict, Counter

class Solution:
    def firstMissingPositive(self, nums: List[int]) -> int:
        #use cyclic sort
        #first determine the index where the element should ideally be i.e in a sorted array at index i we will have element of value a[i+1]. If such is not the case keep on "finding" that index, and if you do, swap, else if you reach the end of the array, of if the element at destination, and current is the same(i.e. duplicates) ignore
        #a = [1,2,0]
        #IDEALLY, a=[0,1,2] output: 3
        N = len(nums)
        for i in range(N):
            #if the number a[i+1] is not equal to index i, continue finding that destination index
            while nums[i] != i + 1: 
                destination_index = nums[i] - 1
                if destination_index >= 0 and destination_index < N and nums[destination_index] != nums[i]:
                    t = nums[i]
                    nums[i] = nums[destination_index]
                    nums[destination_index] = t
                else:
                    break
        #at the end, you reiterate over the array to find a case where a[i] != i + 1, here since we are finding the number we need to return i + 1. 
        for i in range(N):
            if nums[i] != i + 1:
                return i + 1
        return N + 1
        


if __name__ == '__main__':
    Solution().solve()