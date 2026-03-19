#link: https://leetcode.com/problems/rotate-image/description/?envType=company&envId=jpmorgan&favoriteSlug=jpmorgan-all
from typing import List

class Solution:
    def rotate(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        #create transpose of the input matrix i.e. make rows as cols, and cols as rows
        rows = len(matrix)
        # print(f"before={matrix}")
        for i in range(rows):
            for j in range(i):
                matrix[i][j], matrix[j][i] = matrix[j][i], matrix[i][j]

        #reverse each row
        for i in range(rows):
            top, bottom = 0, len(matrix[i]) - 1
            while top < bottom:
                matrix[i][top], matrix[i][bottom] = matrix[i][bottom], matrix[i][top]
                top+=1
                bottom-=1

