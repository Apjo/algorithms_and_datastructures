# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/30/26
link https://leetcode.com/problems/find-the-largest-area-of-square-inside-two-rectangles/submissions/?envType=daily-question&envId=2026-01-30

"""
from typing import List
class Solution:
    def largestSquareArea(self, bottomLeft: List[List[int]], topRight: List[List[int]]) -> int:
        '''
        find overlap between rectangles
        rec1: bottomLeft= [x1, y1], topRight = [x2,y2]
                        = [1,1],[3,3]
        rec2: bottomLeft= [x3, y3], topRight = [x4,y4]
                        = [2,2], [4,4]
        width = min(x2,x4) - max(x1, x3)
        height = min(y2.y4) - max(y1, y3)
        if both positive:
            found
        else:
            ignore
        '''
        N = len(bottomLeft)
        ans=0
        for i in range(N):
            for j in range(i+1, N):
                stX = max(bottomLeft[i][0], bottomLeft[j][0])
                stY = max(bottomLeft[i][1], bottomLeft[j][1])
                endX = min(topRight[i][0], topRight[j][0])
                endY = min(topRight[i][1], topRight[j][1])
                if endX > stX and endY > stY:
                    width = endX - stX
                    height = endY - stY
                    curr_min = min(width, height)
                    ans=max(ans, curr_min)
        return ans*ans

def main():
    return solve()


if __name__ == '__main__':
    main()
