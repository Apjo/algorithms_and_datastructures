#link:https://neetcode.io/problems/car-fleet/question
from typing import List
class Solution:
    #using stack
    def carFleet(self, target: int, position: List[int], speed: List[int]) -> int:
        sorted_arrival_times = sorted(zip([(target - position[i] / speed[i]) for i in range(len(position))]), reverse=True)
        stk=[]
        for rec in sorted_arrival_times:
            if not stk:
                stk.append(rec)
            elif rec[1] > stk[-1][1]:
                stk.append(rec)

        return len(stk)
    #not using any stack
    def carFleet_no_stack(self, target: int, position: List[int], speed: List[int]) -> int:
        sorted_arrival_times = sorted(zip([(target - position[i] / speed[i]) for i in range(len(position))]), reverse=True)
        curr_max, ans=-1, 0
        for rec in sorted_arrival_times:
            if rec[1] > curr_max:
                curr_max = rec[1]
                ans+=1
        return ans