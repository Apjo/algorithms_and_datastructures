# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/17/26
link: https://leetcode.com/problems/reverse-bits/?envType=daily-question&envId=2026-02-17

"""
class Solution:
    def reverseBits(self, n: int) -> int:
        res=0
        for i in range(31, -1, -1):
            #extract bit
            extracted = (n >> i) & 1
            #leftshift in reversed position
            res = res | (extracted << (31 - i))
        return res

def main():
    return solve()


if __name__ == '__main__':
    main()
