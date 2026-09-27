"""
Filename: buy_stock_sell.py
Date: 2026-09-21
"""


class Solution:
    def maxProfit(self, prices):
        curr, pro = prices[0], 0

        for i in range(1, len(prices)):
            if prices[i] > curr:
                pro += prices[i] - curr
            curr = prices[i]
        return pro


if __name__ == '__main__':
    Solution().solve()