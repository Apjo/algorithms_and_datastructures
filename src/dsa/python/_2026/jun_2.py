"""
Filename: jun_2.py
Date: 2026-06-02
link: https://leetcode.com/problems/earliest-finish-time-for-land-and-water-rides-i/?envType=daily-question&envId=2026-06-02
"""

from typing import List

class Solution:
    def earliestFinishTime(self, landStartTime: List[int], landDuration: List[int], waterStartTime: List[int], waterDuration: List[int]) -> int:
        #only need earliest first finish time
        #for land to water, and water -> land
        def solve(lst, ldur, wst, wdur):
            min_first_finish_time = float("inf")
            for i in range(len(lst)):
                min_first_finish_time = min(min_first_finish_time, lst[i] + ldur[i])
            ans = float("inf")
            #cal. start time for water ride = max(earliest finish time for land, start time[i] for water ride)
            for i in range(len(wst)):
                water_st_time = max(min_first_finish_time, wst[i])
                water_finish_time = water_st_time + wdur[i]
                ans = min(ans, water_finish_time)
            
            return ans

        land_to_water= solve(landStartTime, landDuration, waterStartTime, waterDuration)
        water_to_land = solve(waterStartTime, waterDuration, landStartTime, landDuration)

        return min(land_to_water, water_to_land)


if __name__ == '__main__':
    solve()