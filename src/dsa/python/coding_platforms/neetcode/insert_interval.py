"""
Filename: insert_interval.py
Date: 2026-10-24
"""


class Solution:
    # using a bail index
    def insert_bail_index(
        self, intervals: list[list[int]], newInterval: list[int]
    ) -> list[list[int]]:
        res = []
        N = len(intervals)
        bail_index=0
        res=[]
        #iterate over the intervals, and find the location where newInterval and current intervals overlap up until that keep on adding the intervals to res
        while bail_index < N and intervals[bail_index][1] < newInterval[0]:
            res.append(intervals[i])
            bail_index+=1
        #from that index onwards merge the intervals and add to res
        while bail_index < N and intervals[bail_index][0] <= newInterval[1]:
            newInterval = [min(newInterval[0], intervals[bail_index][0]), max(newInterval[1], intervals[bail_index][1])]
            bail_index+=1
        
        res.append(newInterval)
        
        while bail_index < N:
            res.append(intervals[bail_index])
            bail_index+=1
        
        return res


    # O(n) time
    def insert(
        self, intervals: list[list[int]], newInterval: list[int]
    ) -> list[list[int]]:
        res = []
        N = len(intervals)
        for i in range(N):
            # if the new interval ends before the curent interval starts, add it to res, and add all elements of input starting from that index, as list is already sorted, then return the final res
            if newInterval[1] < intervals[i][0]:
                res.append(newInterval)
                return res + intervals[i:]
            # else if newinterval starts after current interval ends, add to res the current interval only
            elif newInterval[0] > intervals[i][1]:
                res.append(intervals[i])
            # else the newinterval overlaps with current interval, merge them basically find new start/end for new interval
            else:
                newInterval = [
                    min(newInterval[0], intervals[i][0]),
                    max(newInterval[1], intervals[i][1]),
                ]
        # finally add the newinterval to res
        res.append(newInterval)

        return res


if __name__ == "__main__":
    Solution().solve()
