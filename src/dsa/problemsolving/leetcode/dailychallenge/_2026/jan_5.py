# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/5/26
problem:
You are given an n x n integer matrix. You can do the following operation any number of times:

Choose any two adjacent elements of matrix and multiply each of them by -1.
Two elements are considered adjacent if and only if they share a border.

Your goal is to maximize the summation of the matrix's elements. Return the maximum sum of the matrix's elements using the operation mentioned above.
"""

from typing import List

def maxMatrixSum(matrix: List[List[int]]) -> int:
    M = len(matrix)
    N = len(matrix[0])

    def get_matrix_sum(grid) -> int:
        curr_sum = 0
        for row in range(len(grid)):
            for col in range(len(grid[0])):
                elem = grid[row][col]
                curr_sum += elem
        return curr_sum

    #count total number of negative elements present in the input matrix
    num_negs=0
    for row in range(M):
        for col in range(N):
            if matrix[row][col] < 0:
                num_negs+=1
    #print(f"found {num_negs} negative numbers")
    # print(f"Finding curr min")
    curr_min = float('inf')
    #make the matrix absolute positive, and get the matrix sum, and current absolute min value
    for row in range(M):
        for col in range(N):
            matrix[row][col] = abs(matrix[row][col])
            curr_min = min(curr_min, matrix[row][col])
    #print(f"found curr absolute min={curr_min}")

    if num_negs % 2 == 0:
        return get_matrix_sum(matrix)
    else:
        flag=False
        for row in range(M):
            for col in range(N):
                if matrix[row][col] == curr_min and not flag:
                    matrix[row][col] = -1 * matrix[row][col]
                    flag=True

        curr_sum = get_matrix_sum(matrix)
        #print(f"curr sum={curr_sum}, curr min={curr_min}")
        return curr_sum

def main():
    assert maxMatrixSum([[1,1],[1,1]]) == 4
    assert maxMatrixSum([[1,-1],[-1,1]]) == 4
    assert maxMatrixSum([[1,2,3],[-1,-2,-3],[1,2,3]]) == 16


if __name__ == '__main__':
    main()
