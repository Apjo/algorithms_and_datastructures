"""
Filename: buy_sell_only_one_transact.py
Date: 2026-09-21
"""


class Solution:
    def maxProfit(self, prices):
        curr = prices[0]
        pro = 0
        for i in range(1, len(prices)):
            if prices[i] > curr:
                pro = max(pro, prices[i] - curr)
            else:
                curr = prices[i]

        return pro


if __name__ == '__main__':
    Solution().solve()