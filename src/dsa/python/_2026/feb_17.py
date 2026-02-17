# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/17/26
"""
from typing import List

class Solution:
    def readBinaryWatch(self, turnedOn: int) -> List[str]:
        hours = [1,2,4,8]
        minutes=[1,2,4,8,16,32]
        res=[]

        def build(allowed_digits, num):
            def solve(arr, curr_level, curr_index, curr_sum, buff):
                if curr_level == 0:
                    buff.append(curr_sum)
                    return

                for i in range(curr_index, len(arr)):
                    curr_sum += arr[i]
                    solve(arr, curr_level - 1, i + 1, curr_sum, buff)
                    curr_sum -= arr[i]

            buff=[]
            solve(allowed_digits, num, 0, 0, buff)
            return buff
        #build all digits from hours only
        get_hrs, get_mins=[],[]
        for i in range(turnedOn + 1):
            #when hh=1, then remaining digits = turnedon - hh=2, and so on
            all_hours = build(allowed_digits=hours, num=i)
            all_mins = build(allowed_digits=minutes, num=turnedOn - i)
            for h in all_hours:
                if h >= 12:
                    continue
                for m in all_mins:
                    if m >= 60:
                        continue
                    if m < 10:
                        m_str = "0" + str(m)
                    else:
                        m_str = str(m)
                    res.append(str(str(h) + ":" + m_str))
        #build all digits from minutes only
        #build final time from each hour, and each minute built in previous 2 steps
        #add to our result list
        return res

def main():
    return Solution().readBinaryWatch(1)


if __name__ == '__main__':
    main()
