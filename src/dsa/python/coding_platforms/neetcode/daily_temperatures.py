#link: https://neetcode.io/problems/daily-temperatures/question
from typing import List

class Solution:
    def dailyTemperatures(self, temperatures: List[int]) -> List[int]:
        stk=[]
        st=[0]*(len(temperatures))
        for i in range(len(temperatures)):
            # print(f"looking at index={i}, current temp={temperatures[i]}")
            while stk and temperatures[stk[-1]] < temperatures[i]:
                # print("popping from stk")
                idx = stk.pop()
                # print(f"popped index from stk={idx}, current res={st}")
                st[idx]=i - idx

            stk.append(i)
        return st
