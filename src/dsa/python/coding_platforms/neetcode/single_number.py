"""
Filename: single_number.py
Date: 2026-11-05
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
    def singleNumber(self, nums: List[int]) -> int:
        ans = 0
        """
        Because of 2 xor properties numbers that appear more than once will cancel out each other, and the number that appears exactly once will only remain
        a ^ a = 0
        a ^ 0 = a
        """
        for i in range(len(nums)):
            ans ^= nums[i]
        return ans       


if __name__ == '__main__':
    Solution().solve()