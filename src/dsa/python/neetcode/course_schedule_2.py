"""
Filename: course_schedule_2.py
Date: 2026-08-03
"""

from typing import List

class Solution:
    def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        def solve(vertex):
            visiting[vertex] = True
            for neighbor in G[vertex]:
                if visiting[neighbor]:
                    return True
                if not visited[neighbor]:
                    ans = solve(neighbor)
                    if ans:
                        return True
            visiting[vertex] = False
            visited[vertex] = True
            st.append(vertex)
            return False

        G = [[] for _ in range(numCourses)]
        for preq in prerequisites:
            G[preq[1]].append(preq[0])
        visited, visiting, st = [False] * (numCourses), [False] * (numCourses), []
        for i in range(numCourses):
            if not visited[i]:
                ans = solve(i)
                if ans:
                    return []
        if not st:
            return []
        res = []
        while st:
            res.append(st.pop())
        return res


if __name__ == '__main__':
    Solution().solve()