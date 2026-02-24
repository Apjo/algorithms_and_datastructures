#link:https://leetcode.com/problems/best-reachable-tower/description/
#using max heap, time:O(nlogn)
from typing import List
import heapq

class Solution:
    # class Point:
    #     def __init__(self, x, y, q, manhattdist):
    #         self.x=x
    #         self.y=y
    #         self.q=q
    #         self.mhd=manhattdist
    #     def __lt__(self, other):
    #     return self.q > other.q

    def bestTower(self, towers: List[List[int]], center: List[int], radius: int) -> List[int]:
        #for all the towers calculate their manhattan distances wrt to center, and only dump those into a max heap that satisfy the [condition ]
        if not towers:
            return [-1, -1]
        hp = []
        for tower in towers:
            x,y,q = tower[0],tower[1],tower[2]
            # print(f"looking at x={x}, y={y} center radius={radius}")
            mhd = abs(x - center[0]) + abs(y - center[1])
            # print(f"Calculated mahattan distace={mhd}")
            if mhd <= radius:
                # print(f"Adding to heap point (x, y)=({x}, {y})")
                heapq.heappush(hp, (-tower[2], (tower[0], tower[1])))
        if hp:
            _, best_coords = heapq.heappop(hp)
            # print(f"Coordinates: {list(best_coords)}")
            return best_coords
        else:
            return[-1,-1]


        #The max heap of size 1 is built by sorting based on quality factor(i.e. sort descending)
        #if there is a tie pick the one having lexicographically smallest coordinate i.e.a [xi, yi] coordinate is lexicographically smaller than [xj, yj] if xi < xj, or xi == xj and yi < yj
        #return maxheap.peek
    #using simple sorting of the input
