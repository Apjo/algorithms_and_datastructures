# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/8/26
link: https://leetcode.com/problems/minimum-deletions-to-make-string-balanced/submissions/1911776958/?envType=daily-question&envId=2026-02-08
"""

from collections import deque
class Solution:
    def minimumDeletions(self, s: str) -> int:
        #iterate from reverse, and also maintain a stack
        #if we encounter a b and the stack is not empty, and the top of the stack ==a, pop from stk, and increment count
        #else keep on pushing to stk
        cnt, N =0, len(s)
        stk=deque()
        for i in range(N - 1, -1, -1):
            if s[i] == 'b' and stk:
                if stk[-1] == 'a':
                    stk.pop()
                    cnt+=1
            else:
                stk.append(s[i])
        return cnt

def main():
    return Solution().minimumDeletions("aababbab")


if __name__ == '__main__':
    main()
