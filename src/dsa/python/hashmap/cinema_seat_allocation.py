#link: https://leetcode.com/problems/cinema-seat-allocation/description/
from typing import List

class Solution:
    def maxNumberOfFamilies(self, n: int, reservedSeats: List[List[int]]) -> int:
        allocated = 0
        mp = {}
        for seat in reservedSeats:
                if seat[0] not in mp:
                    mp[seat[0]] = [seat[1]]
                else:
                    mp[seat[0]].append(seat[1])
        
        #count families in all rows with reservations
        # we could have 3 lists showing 3 partitions of seating a 4 person group 
        #LEFT = [2,3,4,5], MIDDLE=[4,5,6,7], RIGHT=[6,7,8,9]
        for row_num in mp:
            families=0
            if all (seat not in mp[row_num] for seat in [2,3,4,5]):
                families+=1
            if all (seat not in mp[row_num] for seat in [6,7,8,9]):
                families+=1
            if families == 0 and all (seat not in mp[row_num] for seat in [4,5,6,7]):
                families+=1

            allocated+=families #Now, add all the above families from reserved rows
        #allocated = add 2 families with no reservations - families with reservations i.e. more specifically,
        '''
        We try to add families for rows WITHOUT any reservations, so we know in that case len(mp)==n
        (n - len(mp)) = number of completely empty rows
        Each empty row can fit exactly 2 families
        come to think of it as
        allocated = (families in reserved rows) + (families in empty rows)
        or consider we are adding 2 families for each row with NO reservations (completely empty rows)

        '''
        allocated += 2 * (n - len(mp))

        return allocated
    
    #using set to hold all the above 3 partitions
    '''
    We use three numbers to record whether the left(0), the middle(1) or the right(2) is occupied or not.
    First, we record whether the left, middle or right is occupied or not using a set as the value in the dictionary.
    For n rows, the maximum number of families that can sit together are 2*n.
    Then we iterate through the dictionary, if all three positions in the row was blocked, the total cnt should -2.
    If less than 3 positions was blocked, the total cnt should -1.
    '''
    def maxNumberOfFamilies(self, n: int, reservedSeats: List[List[int]]) -> int:
        from collections import defaultdict

        seats = defaultdict(set)
        
        LEFT = [2,3,4,5]
        MID = [4,5,6,7]
        RIGHT = [6,7,8,9]

        for row, col in reservedSeats:
            if col in LEFT:
                seats[row].add(0)
            if col in MID:
                seats[row].add(1)
            if col in RIGHT:
                seats[row].add(2)
        
        res = 2 * n
        for seat in seats:
            if len(seats[seat]) == 3:
                res-=2
            else:
                res-=1
        
        return res

        