"""
Filename: june_18.py
Date: 2026-06-27
link: https://leetcode.com/problems/angle-between-hands-of-a-clock/description/?envType=daily-question&envId=2026-06-18
"""


class Solution:
    def angleClock(self, hour: int, minutes: int) -> float:
        # degree covered by hour hand = hour area + minutes area
        hour_angle = (hour % 12 * 30) + (
            minutes / 60 * 30
        )  # we could use (minutes * 0.5)
        # degree covered by minute hand
        min_angle = 6 * minutes
        ans_angle = abs(hour_angle - min_angle)
        # If the angle is obtuse (>180), convert it to acute (0<=x<=180)
        return min(ans_angle, 360.0 - ans_angle)
        


if __name__ == '__main__':
    Solution().solve()