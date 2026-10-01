"""
Filename: first_unique_char_in_string.py
Date: 2026-10-01
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
    def firstUniqChar(self, s: str) -> int:
        mp = {}#note dict as of py 3.7 are ordered
        for c in s:
            mp[c] = mp.get(c, 0) + 1
        for i in range(len(s)):
            if mp[s[i]] == 1:
                return i
        return -1

        


if __name__ == '__main__':
    Solution().solve()