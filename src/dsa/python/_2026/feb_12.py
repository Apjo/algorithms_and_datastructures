# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/17/26
link: https://leetcode.com/problems/longest-balanced-substring-i/?envType=daily-question&envId=2026-02-17
"""

class Solution:
    def longestBalanced(self, s: str) -> int:
        ans = float("-inf")
        def is_same_freq(mp):
            val = 0
            for v in mp.values():
                if val == 0:
                    val = v
                elif val != v:
                    return False
            return True

        for i in range(len(s)):
            freq={}
            for j in range(i, len(s)):
                if s[j] in freq:
                    freq[s[j]]+=1
                else:
                    freq[s[j]]=1
                if is_same_freq(freq):
                    ans = max(ans, j - i + 1)
        return ans

def main():
    return solve()


if __name__ == '__main__':
    main()
