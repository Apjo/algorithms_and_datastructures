"""
Filename: num_ops_to_make_ntwk_connected.py
Date: 2026-11-20
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
    class MyDSU:
        def __init__(self, n):
            self.rank = [1] * (n)
            self.parent = [i for i in range(n)]
            self.components = n

        def find(self, x):
            if self.parent[x] != x:
                self.parent[x] = self.find(self.parent[x])
            return self.parent[x]

        def get_num_components(self):
            return self.components

        def union(self, x, y):
            pa_x = self.find(x)
            pa_y = self.find(y)
            if pa_x != pa_y:
                if self.rank[pa_x] > self.rank[pa_y]:
                    self.parent[pa_y] = pa_x
                    self.rank[pa_x] += self.rank[pa_y]
                else:
                    self.parent[pa_x] = pa_y
                    self.rank[pa_y] += self.rank[pa_x]
                self.components -= 1
                return True
            return False

    def makeConnected(self, n: int, connections: list[list[int]]) -> int:
        # cannot have more nodes and fewer edges
        if n - 1 > len(connections):
            return -1
        N = n
        dsu = self.MyDSU(N)
        for u, v in connections:
            dsu.union(u, v)
        # determine number of connected components
        return dsu.get_num_components() - 1
        


if __name__ == '__main__':
    Solution().solve()