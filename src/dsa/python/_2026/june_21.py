"""
Filename: june_21.py
Date: 2026-06-22
link https://leetcode.com/problems/maximum-ice-cream-bars/description/?envType=daily-question&envId=2026-06-21
"""

from typing import List
from collections import Counter

class Solution:
    def maxIceCream(self, costs: List[int], coins: int) -> int:
        cnt = Counter(costs)
        ans = 0
        for i in range(1, max(cnt) + 1):
            # determine how many ice creams for cost i can be purchased
            # where cnt[i]=how many with cost i are available
            # coins//i = how many max one can purchase with available coins
            buy = min(cnt[i], coins // i)
            # add number of ice creams purchases to the ans
            ans += buy
            # subtract from coins the amount spent
            coins -= buy * i
        return ans


if __name__ == '__main__':
    Solution().solve()