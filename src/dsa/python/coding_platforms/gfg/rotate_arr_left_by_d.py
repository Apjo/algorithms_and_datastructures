"""
Filename: rotate_arr_left_by_d.py
Date: 2026-09-15
"""
class Solution:
    def rotateArr(self, arr, d):
        # OBSERVERr: after d left rotations last n-d elements appear first, and first d elements appear last

        # reverse first N - d elements of the array
        # reverse remaining d elements of the array
        # reverse entire array

        N = len(arr)
        d %= N
        # reverse the subarray containing first d elements
        arr[:d] = reversed(arr[:d])
        # rotote last N-d elements
        arr[d:] = reversed(arr[d:])
        arr.reverse()
        # print(f"final arr={arr}")
