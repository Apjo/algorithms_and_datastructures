#link:https://leetcode.com/problems/number-of-spaces-cleaning-robot-cleaned/description/
from typing import List

class Solution:
    def numberOfCleanRooms(self, room: List[List[int]]) -> int:
        moves=0
        R,C=len(room), len(room[0])
        r,c=0,0
        while True:
            found_ans = False
            #go right 1 col at a time
            while c < C and room[r][c] != 1:
                if room[r][c] == 0:
                    moves+=1
                    found_ans=True
                room[r][c]=42
                c+=1
            r+=1
            c-=1
            #go down 1 row at a time
            while r < R and room[r][c] != 1:
                if room[r][c] == 0:
                    moves+=1
                    found_ans=True
                room[r][c]=42
                r+=1
            #go left 1 col at a time
            r-=1
            c-=1
            while c >=0 and room[r][c] != 1:
                if room[r][c] == 0:
                    moves+=1
                    found_ans=True
                room[r][c]=42
                c-=1
            c+=1
            r-=1
            #go up 1 row at a time
            while r >=0 and room[r][c] != 1:
                if room[r][c] == 0:
                    moves+=1
                    found_ans=True
                room[r][c]=42
                r-=1
            #go next row, next col
            r+=1
            c+=1
            if not found_ans:
                break

        return moves
