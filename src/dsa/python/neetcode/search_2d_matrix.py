#link: https://neetcode.io/problems/search-2d-matrix/question

from typing import List
class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        rows, cols = len(matrix), len(matrix[0])
        low, hi = 0, cols - 1
        while low <= rows - 1 and hi >= 0:
            if matrix[low][hi] == target:
                return True
            elif matrix[low][hi] < target:
                low +=1
            else:
                hi-=1
        return False
    def search_2(self, matrix: List[List[int]], target: int):
        rows, cols = len(matrix), len(matrix[0])
        matrix_len = rows * cols - 1
        low, hi = 0, matrix_len
        while low <= hi:
            mid = (low + hi) // 2
            row_loc = mid // cols
            col_loc = mid % cols
            if matrix[row_loc][col_loc] == target:
                return True
            elif matrix[row_loc][col_loc] <= target:
                low = mid + 1
            else:
                hi = mid - 1
        return False

