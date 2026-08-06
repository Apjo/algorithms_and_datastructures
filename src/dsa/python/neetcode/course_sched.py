"""
Filename: course_sched.py
Date: 2026-08-02
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
    #using DFS algo
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        def solve(vertex):

            visiting[vertex] = True

            for neighbor in G[vertex]:
                if visiting[neighbor]:
                    return True
                if not visited[neighbor]:
                    ans = solve(neighbor)
                    if ans:
                        return True

            visiting[vertex] = False
            visited[vertex] = True
            return False

        # build graph of prequisite course num -> course
        G = [[] for _ in range(numCourses)]
        for preq in prerequisites:
            G[preq[1]].append(preq[0])

        visited = [False] * numCourses
        visiting = [False] * numCourses

        for i in range(numCourses):
            if not visited[i]:
                ans = solve(i)
                if ans:
                    return False
        return True
    #using kahns


if __name__ == '__main__':
    Solution().solve()