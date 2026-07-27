"""
Filename: july_30.py
Date: 2026-07-31
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
    def minimumPushes(self, word: str) -> int:
        """
        - count frequency of each char
        - if all of same frequency:
            -return len(w)
        - higher the frequency, higher will be the priority of this char to be the first on the number
            - how to count in this case?
                -
        """

        freq_mp = dict(Counter(word))
        sorted_freq_mp = sorted(freq_mp, key=freq_mp.get, reverse=True)
        # print(sorted_freq_mp)
        ans = 0
        for i in range(len(freq_mp)):
            ans += (int(i / 8) + 1) * freq_mp[sorted_freq_mp[i]]
        return ans


if __name__ == '__main__':
    Solution().solve()