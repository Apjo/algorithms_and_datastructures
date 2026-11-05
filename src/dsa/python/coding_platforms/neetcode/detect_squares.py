"""
Filename: detect_squares.py
Date: 2026-11-05
"""


class CountSquares:
    def __init__(self):
        self.freq = {}
        self.all_points = []

    def add(self, point: list[int]) -> None:
        self.freq[tuple(point)] = self.freq.get(tuple(point), 0) + 1
        self.all_points.append(point)

    def count(self, point: list[int]) -> int:
        res = 0
        # get the query coordinates
        qx, qy = point
        for px, py in self.all_points:
            if (abs(qx - px) != abs(qy - py)) or qx == px or py == qy:
                continue
            other_point1_count = self.freq.get((px, qy), 0)
            other_point2_count = self.freq.get((qx, py), 0)
            res += other_point1_count * other_point2_count
        return res
        # get the diagonal for the query point, this will cover 2 points
        # now get the other 2 points as P1(x, qy) with Q(qx, qy) and P2(qx, y) with Q(qx, qy).
        # return res+=total p1 points * total p2 points


if __name__ == "__main__":
    Solution().solve()
