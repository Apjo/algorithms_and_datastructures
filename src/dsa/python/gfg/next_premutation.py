"""
Filename: next_premutation.py
Date: 2026-09-16
"""


class Solution:
    def nextPermutation(self, arr):
        """
        1.scan left to right
        2.if arr[i] < arr[i+1] keep updating pivot=i
        3. pivot is your first righmost valid pivot
        4.start scanning from the end to find first element > arr[pivot]
        5.swap(arr, pivot, i)
        6.reverse arr from i+1
        7.return arr
        """

        N = len(arr)
        pivot = -1
        for i in range(N - 1):
            if arr[i] < arr[i + 1]:
                pivot = i

        if pivot == -1:
            return arr.reverse()

        for i in range(N - 1, -1, -1):
            if arr[i] > arr[pivot]:
                arr[i], arr[pivot] = arr[pivot], arr[i]
                break
        le, ri = pivot + 1, N - 1
        while le < ri:
            arr[le], arr[ri] = arr[ri], arr[le]
            le += 1
            ri -= 1

        return arr        


if __name__ == '__main__':
    Solution().solve()