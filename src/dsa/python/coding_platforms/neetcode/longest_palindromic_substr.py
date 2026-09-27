"""
Filename: longest_palindromic_substr.py
Date: 2026-08-19
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
    #pure recursion O(N^3) time, TLEs
    def longestPalindrome(self, s: str) -> str:
        N = len(s)
        longest_str_ans = ""

        def solve(idx):
            nonlocal longest_str_ans
            if idx >= N:
                return

            for i in range(idx, N):
                curr_substr = s[idx : i + 1]
                # print(f"working with current substr={curr_substr}")
                if curr_substr[0] == curr_substr[len(curr_substr) - 1]:
                    if len(curr_substr) > len(longest_str_ans):
                        longest_str_ans = curr_substr
                    solve(i + 1)
        solve(0)
        return longest_str_ans

    def longestPalindrome_memo(self, s: str) -> str:
        N = len(s)
        longest_str_ans = ""
        memo = {}

        def is_palindrome(ss, l, h):
            if ss in memo:
                return memo[ss]
            while l < h:
                if ss[l] != ss[h]:
                    memo[ss] = False
                    return memo[ss]
                l += 1
                h -= 1
            memo[ss] = True
            return memo[ss]

        def solve(idx):
            nonlocal longest_str_ans
            if idx >= N:
                return

            for i in range(idx, N):
                curr_substr = s[idx : i + 1]
                if is_palindrome(curr_substr, 0, len(curr_substr) - 1):
                    if len(curr_substr) > len(longest_str_ans):
                        longest_str_ans = curr_substr
                solve(i + 1)

        solve(0)

        print(longest_str_ans)

        return longest_str_ans
    
    """
    The idea is to recursively search for the longest palindromic substring by reducing the current string from both sides.
    If the current string is already a palindrome, we return it immediately. Otherwise, we compare the best result from removing the first character and the best result from removing the last character.
    strip one char from left, one char from right, and find max, but for every string we still check if its a palindrome.
    Memoization is used to avoid recomputing the same substrings multiple times. This makes the approach a top-down dynamic programming solution.
    """
    #time: O(N^3), space:O(n^2)
    def longestPalindrome_stripchars(self, s: str) -> str:
        
        def is_palindrome(ss):
            n = len(ss)
            if n == 0:
                return False
            if n == 1:
                return True
            else:
                lo, hi = 0, n - 1
                while lo < hi:
                    if ss[lo] != ss[hi]:
                        return False
                    lo+=1
                    hi-=1
                return True
        
        def solve(input_str: str):
            if is_palindrome(input_str):
                return (len(input_str), input_str)
            if input_str not in memo:
                memo[input_str] = max(solve(input_str[1:]), solve(input_str[:-1]))
            return memo[input_str]

        memo={}
        
        return solve(s)[1]

        
    def longestPalindrome_expand_from_center(self, s: str) -> str:
        N = len(s)
        if N <=1:
            return ""
        longest_str_ans = ""

        def expand_str(l:int, ri:int):
            while l >=0 and ri < len(s) and s[l] == s[ri]:
                l-=1
                ri+=1
            return s[l:ri]

        for i in range(N - 1):
            #expand from current char for all odd len strings
            odd_len = expand_str(i, i)
            #expand from current, and current + 1 character for all even len strings
            even_len = expand_str(i, i + 1)
            if len(odd_len) > len(longest_str_ans):
                longest_str_ans = odd_len
            if len(even_len) > len(longest_str_ans):
                longest_str_ans = even_len
        
        return longest_str_ans



if __name__ == '__main__':
    Solution().solve()