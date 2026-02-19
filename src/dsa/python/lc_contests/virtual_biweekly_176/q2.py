# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/19/26
link: https://leetcode.com/contest/biweekly-contest-176/problems/number-of-prefix-connected-groups/
"""
from typing import List

class Solution:
    def prefixConnected(self, words: List[str], k: int) -> int:
        mp = {}
        if not words:
            return 0
        if k < 0 :
            return 0
        for word in words:
            if len(word) < k :
                continue
            pref_w = word[:k]
            if pref_w in mp:
                mp[pref_w]+=1
            else:
                mp[pref_w] = 1
        ans=0
        for k, v in mp.items():
            if v >= 2:
                ans+=1
        return ans

def main():
    return solve()


if __name__ == '__main__':
    main()
