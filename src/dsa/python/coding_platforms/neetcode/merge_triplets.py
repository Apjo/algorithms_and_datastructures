"""
Filename: merge_triplets.py
Date: 2026-10-21
"""


class Solution:
    # O(n) time, and O(1) space
    def mergeTriplets_2(self, triplets: list[list[int]], target: list[int]) -> bool:
        x, y, z = False, False, False
        for t in triplets:
            x |= t[0] == target[0] and t[1] <= target[1] and t[2] <= target[2]
            y |= t[1] == target[1] and t[0] <= target[0] and t[2] <= target[2]
            z |= t[2] == target[2] and t[0] <= target[0] and t[1] <= target[1]
            if x and y and z:
                return True
        return False

    # O(n) time and space
    def mergeTriplets(self, triplets: list[list[int]], target: list[int]) -> bool:
        good = set()
        for t in triplets:
            if t[0] > target[0] or t[1] > target[1] or t[2] > target[2]:
                continue
            else:
                for k, v in enumerate(t):
                    if v == target[k]:
                        good.add(k)
        return len(good) == 3


if __name__ == "__main__":
    Solution().solve()
