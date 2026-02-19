# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/19/26
"""

from typing import List
from collections import defaultdict


class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        op=defaultdict(list)
        #iterate over the input strs
        for str1 in strs:
            #maintain a freq count for each char in str1
            #NOTES:
            # 1. The frequency array approach with size 26 only works for lowercase English letters.
            # 2. If the input could contain uppercase letters or other characters, the solution would fail or produce incorrect groupings.
            hm=[0]*26
            for cc in str1:
                hm[ord(cc) - ord('a')]+=1
            #the key for our map would be the tuple version of our freq map
            '''
            Gotchas:
            When using character frequency arrays as keys, you must convert them to an immutable type (like a tuple in Python or a string in other languages).
            Lists and arrays are mutable and cannot be used as dictionary keys directly.
            why tuple?
            1. Every word with the same letters (like "cat" and "tac") will produce the exact same (1, 0, 1, ..., 1, ...) tuple.
            2. Unlike lists or dictionaries, tuples are immutable, so they can be used as keys in a dictionary.
            3. This avoids O(N log N) sorting and runs in linear O(N) time based on the length of your strings
            '''
            key = tuple(hm)
            print(f"stringified hm={key} for s={str1}")
            op[key].append(str1)
        return list(op.values())


def main():
    return Solution().groupAnagrams()


if __name__ == '__main__':
    main()
