#link: https://leetcode.com/problems/display-table-of-food-orders-in-a-restaurant/description/?envType=company&envId=jpmorgan&favoriteSlug=jpmorgan-all

from typing import List

class Solution:
    def displayTable(self, orders: List[List[str]]) -> List[List[str]]:
        res=[]
        unique_foods = set()
        hm = {}
        for order in orders:
            n,t,food = order[0],order[1], order[2]
            unique_foods.add(food)
            if t in hm:
                curr = hm[t]
                curr[food] = curr.get(food, 0) + 1
                hm[t] = curr
            else:
                temp={}
                temp[food] = 1
                hm[t] = temp
        te = sorted(unique_foods)
        res=list(te)
        res=[["Table"] + [f for f in te]]
        for k, v in sorted(hm.items(), key=lambda item: int(item[0])):
            row = [k]
            for item in te:
                row.append(str(v[item] if item in v else "0"))
            res.append(row)
        return res
