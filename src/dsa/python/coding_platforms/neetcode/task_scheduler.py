"""
Filename: task_scheduler.py
Date: 2026-06-03
link https://neetcode.io/problems/task-scheduling/question?list=neetcode150
"""

import heapq
from typing import List
from collections import deque, Counter

class Solution:
    def solve(self, tasks: List[str], n: int) -> int:
        #max heap to store tasks with highest frquency first
        counts = Counter(tasks)
        max_h = [-cnt for cnt in counts.values()]
        heapq.heapify(max_h)
        ans = 0
        remain_q = deque()
        #at each time unit, we take the most frequent available task and run it.
        while max_h:
            # n+1 is the CPU cycle length, if n is the cooldown period then after a task A there will be n more tasks. Hence n+1 will be the cycle len.
            cycle_len = n + 1
            while cycle_len and max_h:
                #the task at the top should be first assigned the CPU as it has highest frequency
                curr_max_freq = heapq.heappop(max_h)
                #task with more than one occurrence, the next occurrence will be done in the next cycle
                if curr_max_freq > 1:
                    # add it to remaining task list
                    remain_q.append(curr_max_freq - 1)
                ans += 1
                cycle_len -=1
            # When a task’s cooldown finishes, we push it back into the heap so it can be scheduled again.
            while remain_q:
                heapq.heappush(max_h, -remain_q.popleft())
            # if the priority queue is empty than all tasks are completed and we don't need to include the idle time
            if not max_h:
                break
            # this counts the idle time
            ans += cycle_len
        
        return ans
        


if __name__ == '__main__':
    Solution().solve()