# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/10/26
"""
from typing import List
class Solution:
    def maximalRectangle(self, matrix: List[List[str]]) -> int:
        if not matrix or not matrix[0]:
            return 0
        maxArea = 0
        C = len(matrix[0])
        all_heights=[0]*(C+1)

        # st.push(-1);
        for row in matrix:
            for c in range(C):
                all_heights[c] = all_heights[c] + 1 if row[c]=='1' else 0
            st = [-1]
            for itr in range(len(all_heights)):
                while st and all_heights[st[-1]] >= all_heights[itr]:
                    #previousIndex    = st.pop()
                    previousHeight   = all_heights[st.pop()]
                    #leftLimit        = st[-1]
                    currentWidth     = itr if not st else itr - 1 - st[-1]
                    maxArea = max(maxArea, previousHeight * currentWidth)
                st.append(itr)
        return maxArea


def main():
    return solve()


if __name__ == '__main__':
    main()
