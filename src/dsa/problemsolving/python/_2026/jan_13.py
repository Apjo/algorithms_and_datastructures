# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/14/26
"""

from typing import List
class Solution:
    def separateSquares(self, squares: List[List[int]]) -> float:
        #         '''
        #         pre work:
        #         for square in squares:
        #             - calculate are
        #             - calculate total area
        #             - calculate miny=min(miny, sqaure[1])
        #             - calculate maxy=max(maxy+square[2], maxy)
        #         -init a variable to return
        #         while maxy - miny > 1e-5:
        #             -calculate midy = max - miny / 2.0
        #             - calculate
        #             - if are_areas_equal(squares, midy) or calculated_area == half_area:
        #                 -answer = miny
        #             -elif calculated_area < half_total_area:
        #                 - increment miny
        #             -else:
        #                 -decrement maxy
        #         inside the function are_areas_equal, it takes parameters(sqaures, midy, total_area_of_squares), this function calculates total areas of all the squares below the midy
        #         -init 2 variables to keep track of lower and upper square areas
        #         - iterate over all the squares:
        #             - 1.completely below the midy
        #                 -area below+=len*len
        #             - 2.completely above the midy: we ignore this
        #             - 3.midy intersecting the square:
        #                 -area below += (midy - y)*len
        #         - finally calculate area upper = total area - area lower
        #         - return the final ans by comparing area upper with area lower
        #         '''
        all_min_y = min(squares, key=lambda x:x[1])[1]
        all_max_y = max(y + len for _,y,len in squares)
        total_area = sum(sq[2]**2 for sq in squares)
        eps = 10**-5
        def find(low, high):
            while(high - low > eps):
                midy = (low + high) / 2
                calc_area = are_areas_equal(midy)
                if calc_area == 0 or calc_area == -1:
                    high=midy
                else:
                    low=midy
            return low
        def are_areas_equal(midy):
            area_of_below_squares=0
            for _,y,len in squares:
                if y >= midy:
                    continue
                elif y + len <= midy:
                    #total area
                    area_of_below_squares+=len*len
                else:
                    #line intersects
                    area_of_below_squares+=len*(midy - y)
            area_of_upper_squares = total_area - area_of_below_squares
            if area_of_upper_squares == area_of_below_squares:
                return 0
            elif area_of_upper_squares > area_of_below_squares:
                return 1
            else:
                return -1
        return find(all_min_y, all_max_y)

# class Solution:
#     def separateSquares(self, squares: List[List[int]]) -> float:

#         min_y = min(squares, key=lambda x: x[1])[1]
#         max_y = max(y + s for _, y, s in squares)

#         ta = sum(s[2] ** 2 for s in squares)
#         half = ta / 2
#         T = 10**-5

#         def _area_under_y(y):
#             a = 0
#             for _, y0, s in squares:
#                 if y0 >= y:
#                     continue
#                 elif y0 + s <= y:
#                     a += s*s
#                 else:
#                     a += s * (y - y0)
#             return a

#         def search(bottom, top):
#             if top - bottom <= T:
#                 return top

#             mid = (top + bottom) / 2

#             auy = _area_under_y(mid)

#             if auy >= half:
#                 top = mid
#             elif half > auy:
#                 bottom = mid

#             return search(bottom, top)

#         return search(min_y, max_y)

def main():
    return solve()


if __name__ == '__main__':
    main()
