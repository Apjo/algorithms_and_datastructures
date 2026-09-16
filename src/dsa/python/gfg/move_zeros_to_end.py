"""
Filename: move_zeros_to_end.py
Date: 2026-09-13
link: https://www.geeksforgeeks.org/batch/gfg-160-problems/track/arrays-gfg-160/problem/move-all-zeroes-to-end-of-array0751
"""


class Solution:
    #approach 2,move only the non zero elements to the front, and then copy the leftover indices by 0
    def pushZerosToEnd_2(self, arr):
        last_zero_loc = 0
        for i in range(len(arr)):
            if arr[i] != 0:
                arr[last_zero_loc] = arr[i]
                last_zero_loc+=1
            for i in range(last_zero_loc, len(arr)):
                arr[i] = 0

    #approach 1 go back to the leftmost zero and swap with this non zero element
    def pushZerosToEnd(self, arr):
        x = 0
        for i in range(len(arr)):
            if arr[i] == 0:
                x += 1
            elif x > 0:
                t = arr[i]
                arr[i] = 0
                arr[i - x] = t
        


if __name__ == '__main__':
    Solution().solve()