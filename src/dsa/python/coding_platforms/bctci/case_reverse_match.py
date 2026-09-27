"""
Filename: case_reverse_match.py
Date: 2026-09-25
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
    #constant space
    def reverse_case_match_2(self, s):
        lo, hi = 0, len(s) - 1
        while lo < hi:
            if not s[lo].islower():
                lo+=1
            if not s[hi].isupper():
                hi-=1
            else:
                if s[lo] != s[hi].upper():
                    return False
                lo+=1
                hi-=1
        return True

    #using extra space
    def reverse_case_match(self, s):
        if not s:
            return True
        s2 = []
        N = len(s)
        s1=""
        for i in range(N):
            if s[i].islower():
                s1+=s[i]
            else:
                s2.insert(0, s[i])
        ss2 = "".join(s2)
        return s1 == ss2.lower()
        


if __name__ == '__main__':
    Solution().solve()