"""
Filename: aug_2.py
Date: 2026-08-02
"""

from typing import List

class Solution:
    def stoneGame(self, piles: List[int]) -> bool:
        N, sum_even, sum_odd = len(piles), 0, 0
        for i in range(N):
            if i % 2 == 0:
                sum_even += piles[i]
            else:
                sum_odd += piles[i]
        # if sum_even > sum_odd then Alice only picks numbers at even indices, else
        # alice only picks numbers at odd since Alice always goes first, and it doesn't matter what Bob
        # ends up choosing
        sum_alice, sum_bob = 0, 0
        for i in range(N):
            if sum_even > sum_odd:
                if i % 2 == 0:
                    sum_alice += piles[i]
                else:
                    sum_bob += piles[i]
            if sum_odd > sum_even:
                if i % 2 == 1:
                    sum_alice += piles[i]
                else:
                    sum_bob += piles[i]

        return sum_alice > sum_bob


if __name__ == '__main__':
    Solution().solve()