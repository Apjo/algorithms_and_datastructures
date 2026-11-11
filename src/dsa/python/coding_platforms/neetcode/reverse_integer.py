"""
Filename: reverse_integer.py
Date: 2026-11-08
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
    def reverse(self, x: int) -> int:
        def is_32_bit_signed(n):
            return -(2**31) <= n <= 2**31 - 1

        if not is_32_bit_signed(x):
            return 0

        is_neg = False
        if x < 0:
            x *= -1
            is_neg = True

        d = 0

        while x:
            rem = x % 10
            d = d * 10 + rem
            x //= 10

        if not is_32_bit_signed(d):
            return 0

        if is_neg:
            d *= -1

        return d
        


if __name__ == '__main__':
    Solution().solve()