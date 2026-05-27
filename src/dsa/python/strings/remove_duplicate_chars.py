#link: https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/description/?envType=company&envId=geico&favoriteSlug=geico-all
from collections import deque

class Solution:
    def removeDuplicates(self, s: str) -> str:
        stk = deque()
        for cc in s:
            if len(stk) == 0 or stk[-1] != cc:
                stk.append(cc)
            elif len(stk) > 0 and stk[-1] == cc:
                stk.pop()

        res=""

        while len(stk) > 0:
            item = stk.pop()
            res+=item

        return res[::-1]

