"""
Filename: june_19.py
Date: 2026-07-01
link: https://leetcode.com/problems/find-the-highest-altitude/description/?envType=daily-question&envId=2026-06-19
"""

from typing import List


class Solution:
    def largestAltitude(self, gain: List[int]) -> int:
        ps = [0] * (len(gain) + 1)
        print(ps)
        for i in range(len(gain)):
            prev = ps[i - 1]
            ss = prev + gain[i]
            ps.insert(i, ss)
        # print(ps)
        return max(ps)


if __name__ == "__main__":
    Solution().largestAltitude(gain=[-5, 1, 5, 0, -7])
