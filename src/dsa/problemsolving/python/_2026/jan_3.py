# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/5/26
"""

def numOfWays(n: int) -> int:
    #heavily inspired from: https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/description/comments/3288820/
    num_same_combo, num_diff_combo, modul = 6, 6, 10**9+7
    for i in range(1, n):
        total_same_combo = num_same_combo * 3 + num_diff_combo * 2
        total_diff_combo = num_same_combo * 2 + num_diff_combo * 2
        num_same_combo = total_same_combo % modul
        num_diff_combo = total_diff_combo % modul
    return (num_same_combo + num_diff_combo)%modul


def main():
    return numOfWays(1)


if __name__ == '__main__':
    main()
