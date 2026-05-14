#link: https://leetcode.com/problems/rotating-the-box/description/?envType=daily-question&envId=2026-05-06
from typing import List

class Solution:

    def rev_mat(self, mat):
        for row in mat:
            st, end=0, len(row) -1 
            while st <= end:
                temp = row[st]
                row[st] = row[end]
                row[end] = temp
                st+=1 
                end-=1

    def rotateTheBox(self, boxGrid: List[List[str]]) -> List[List[str]]:
        N = len(boxGrid[0])
        
        # Outer loop iterates over columns, inner over rows
        transpose = [[row[i] for row in boxGrid] for i in range(N)]
        
        self.rev_mat(transpose)
        
        def mark(board, M, N):
            for col in range(N):
                index=M-1
                for row in range(M-1, -1, -1):
                    if board[row][col] == '#':
                        if board[index][col] == '.':
                            board[index][col]=board[row][col]
                            board[row][col] = '.'
                            index-=1
                        else:
                            index=row-1
                    elif board[row][col] == '*':
                        index=row - 1
        
        mark(transpose, len(transpose), len(transpose[0]))
        
        return transpose
