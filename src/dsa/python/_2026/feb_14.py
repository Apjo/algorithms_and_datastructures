# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/17/26
link: https://leetcode.com/problems/champagne-tower/?envType=daily-question&envId=2026-02-17
"""

class Solution:
    def champagneTower(self, poured: int, query_row: int, query_glass: int) -> float:
        '''
        Every glass except the top one, will get contribution from the upper left and upper right. The left/right will contribute only if it's already filled. For a glass, both the upper left and right will contribute surplus/2 to that glass. Now, this glass will again store 1 from this input of water, and donate surplus to its bottom left and right.
        Now, all the game is of this surplus.
        We have been given "poured" in the beginning , so recursively move backwards/upwards and for every cup, use this relation.
        left_contri = max(0, [func(poured,row-1,glass-1)-1]/2).
        right_contri=max(0,[func(poured,row-1,glass)-1]/2). [for upper right, glass number remains same]
        For the base case, return poured'''
        memo={}
        def solve(curr_row, glass_num):
            if curr_row < 0 or glass_num < 0 or glass_num > curr_row:
                return 0.0
            if curr_row == 0 and glass_num == 0:
                return poured

            if (curr_row, glass_num) in memo:
                return memo[(curr_row, glass_num)]
            # the reason we decrement by 1 is because we keep 1 for this glass/row, and then divide by 2
            contribution_of_glasses_from_left = max(0.0, (solve(curr_row - 1, glass_num - 1) - 1) / 2)
            contribution_of_glasses_from_right = max(0.0, (solve(curr_row - 1, glass_num) - 1) / 2)
            memo[(curr_row, glass_num)] = contribution_of_glasses_from_left + contribution_of_glasses_from_right
            return contribution_of_glasses_from_left + contribution_of_glasses_from_right
            # return contribution_of_glasses_from_left + contribution_of_glasses_from_right
        return min(1.0, solve(query_row, query_glass))

def main():
    return solve()


if __name__ == '__main__':
    main()
