"""
Filename: split_string.py
Date: 2026-09-13
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
   def split(self, s, c):
       if not s:
           return []
       res, curr = [], []
       i, N = 0, len(s)
       while i < N:
           if s[i] == c:
               res.append("".join(curr))
               curr = []
            else:
               curr.append(s[i])
            i+=1

       res.append("".join(curr))
       return res
        


if __name__ == '__main__':
    Solution().solve()