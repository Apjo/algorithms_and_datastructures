# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/19/26
link: https://leetcode.com/contest/biweekly-contest-176/problems/weighted-word-mapping/
"""
from typing import List
class Solution:
    def mapWordWeights(self, words: List[str], weights: List[int]) -> str:
        ll=""
        rev_map={25 : 'a', 24 : 'b', 23 : 'c', 22 : 'd', 21:'e',20 : 'f', 19 : 'g', 18: 'h', 17:'i', 16 :'j', 15 : 'k', 14 : 'l', 13 : 'm', 12 : 'n', 11 : 'o', 10 : 'p', 9 : 'q', 8 : 'r', 7: 's', 6 : 't', 5 :'u', 4:'v', 3: 'w', 0:'z', 1:'y', 2 : 'x'}
        for word in words:
            ss=0
            for w in range(len(word)):
                ss+=weights[ord(word[w]) - ord('a')]
            ss%=26
            ll=ll+rev_map[ss]
        return ll

def main():
    return solve()


if __name__ == '__main__':
    main()
