"""
Filename: reverse_bits.py
Date: 2026-11-06
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
    def reverse_bits_bit_manip(self, n:int):
        #init res to 0
        res=0
        #for each bit position i from 0 to 31
        for i in range(32):
            #extract the ith bit in n
            extracted_bit = (n >> i) & 1
            #shift this bit to position 31 - i
            shifted_bit = extracted_bit << (31 - i)
            #add it to final result res
            res+=shifted_bit
        #finally return re after processing all 32 bits
        return res

    def reverseBits(self, n: int) -> int:
        # convert the n into a binary string by padding 0s
        bstr = format(n, "032b")
        # reverse the string
        rev_bstr = bstr[::-1]
        # convert back to int
        return int(rev_bstr, 2)
        


if __name__ == '__main__':
    Solution().solve()