"""
Filename: word_break.py
Date: 2026-08-25
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

    #bottom up DP
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        dp = [False] * (len(s) + 1)
        dp[0] = True
        """
        i: where we're currently ending the string we're trying to build.
        k: where the last word starts.
        s[k:i] = the last word we're considering
        and, dp[k] = can everything before that last word be broken into valid words?
        So when you're calculating dp[i], you're basically asking:
"Can I find a place k where the last word starts, such that everything before k works AND s[k:i] is a word?"
    leet | code
         ↑     ↑
         k     i
         4     8
         k = 4 → "code" starts here
         i = 8 → "code" ends here
         s[4:8] → "code"
         dp[4] → tells you whether "leet" can be broken successfully
        """
        for i in range(len(s) + 1):
            "k asks: Can I find a valid previous boundary such that the piece from k to i is a dictionary word?"
            for k in range(i):
                if s[k:i] in wordDict and dp[k]:
                    dp[i] = True
        return dp[len(s)]

    #with memo, O(N) space, and time
    def wordBreak_memo(self, s: str, wordDict: List[str]) -> bool:
        def solve(index):
            """
            The base case is a single comparison and immediate return. It's trivial cost. And since you check the base case before the cache lookup, you'd return True before ever using a cached value anyway.
            Caching is valuable when you're saving expensive computation or preventing redundant recursion. The base case does neither—it's just if index >= len(s): return True.
            """
            if index >= len(s):
                return True
            if index in memo:
                return memo[index]
            for k in range(index, len(s)):
                if s[index : k + 1] in wordDict and solve(k + 1):
                    memo[index] = True
                    return True
            memo[index] = False
            return False

        memo = {}
        return solve(0)
    #time:O(2^N)
    def wordBreak_recur(self, s: str, wordDict: List[str]) -> bool:
        def solve(index):
            if index >= len(s):
                return True
            # if index in memo:
            #     return memo[index]
            for k in range(index, len(s)):
                #the word s[index:k+1] is in the wordDict, and we can partition from k onwards, so we return True
                if s[index : k + 1] in wordDict and solve(k + 1):
                    # memo[index] = True
                    return True
                #else we continue partitioning from the next index.
            # memo[index] = False
            return False

        # memo = {}
        return solve(0)


if __name__ == '__main__':
    Solution().solve()