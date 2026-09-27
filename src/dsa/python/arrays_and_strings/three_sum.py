"""
Filename: three_sum.py
Date: 2026-09-27
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
    def threeSum(self, nums: list[int]) -> list[list[int]]:
        if not nums:
            return []
        N = len(nums)
        nums.sort()
        res = []
        for i in range(N):
            lo, hi = i + 1, N - 1
            if i > 0 and nums[i] == nums[i - 1]:
                continue
            while lo < hi:
                curr_sum = nums[i] + nums[lo] + nums[hi]
                if curr_sum == 0:
                    res.append((nums[i], nums[lo], nums[hi]))
                    lo+=1
                    hi-=1
                    while lo < hi and nums[lo] == nums[lo - 1]:
                        lo+=1
                elif curr_sum < 0 :
                    lo+=1
                else:
                    hi-=1
        return res

        


if __name__ == '__main__':
    Solution().solve()