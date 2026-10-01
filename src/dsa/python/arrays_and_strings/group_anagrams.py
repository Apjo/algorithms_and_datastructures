"""
Filename: group_anagrams.py
Date: 2026-09-28
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
    #uses an array of fixed size of 26 if all we are processing is a string of lowercase alphabets, time: O(M*N)
    def groupAnagrams2(self, strs: list[str]) -> list[list[str]]:
        final_map = defaultdict(list)
        # prepare a frquency map of each string
        for s in strs:
            freq = [0]*26
            for cc in s:
                freq[ord(cc) - ord('a')]+= 1
            final_map[tuple(freq)].append(s)
        # create a string of this freq_map
        # this str of the freq_mapi will be the key of a new map whose value will be a list of the strings
        # return the values of this final map
        return [v for v in final_map.values()]

    #uses sorted freq map as a key, time: O(M*NlogN)
    def groupAnagrams(self, strs: list[str]) -> list[list[str]]:
        final_map = defaultdict(list)
        # prepare a frquency map of each string
        for s in strs:
            freq = {}
            for cc in s:
                freq[cc] = freq.get(cc, 0) + 1
            freq_map_str = tuple(sorted(freq.items()))
            final_map[freq_map_str].append(s)
        # create a string of this freq_map
        # this str of the freq_mapi will be the key of a new map whose value will be a list of the strings
        # return the values of this final map
        return [v for v in final_map.values()]

        


if __name__ == '__main__':
    Solution().solve()