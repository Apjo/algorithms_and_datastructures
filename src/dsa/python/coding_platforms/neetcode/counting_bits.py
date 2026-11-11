"""
Filename: counting_bits.py
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
    """
    Let n be represented by the binary string consisting of some number of 0s and 1s, the rightmost 1s and then some number of terminating 0s.
    Then, n-1 can be represented by the binary string which is exactly the same before the rightmost 1, and everything from the rightmost 1 is toggled.
    In other words, n can be written as a regular expression - (0 + 1)^x (1) (0)^y,  where x and y are non-negative numbers.
    n-1 is (0 + 1)^x (0) (1)^y
    AND-ing n and n-1 gives us (0 + 1)^x 0^{y+1}.
    Applying n&(n-1), removes the rightmost set bit. We keep applying this till we get 0 i.e. till all the set bits are reset.*/

    """
    def countBits(self, n: int) -> List[int]:
        def countSetBits(n) -> int:
            ctr = 0
            while n > 0:
                # if n has a set bit at the ith position the result is positive, else its a 0. We increment ctr if result is positive
                if n & 1:
                    ctr += 1
                # shifting n by 1 to right moves us to next bit
                n >>= 1
                # ctr+=1
            return ctr

        res = []
        for i in range(n + 1):
            res.append(countSetBits(i))

        return res


if __name__ == '__main__':
    Solution().solve()