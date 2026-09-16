"""
Filename: buy_sell_stock_with_cooldown.py
Date: 2026-09-07
"""

from typing import List


class Solution:
    #bottom up DP
    def maxProfit_bottom_updp(self, prices: List[int]) -> int:
        if not prices:
            return 0
        N = len(prices)
        dp = [[0] * 2 for _ in range(N + 1)]
        for i in range(N - 1, -1, -1):
            for buying in [True, False]:
                if buying:
                    buy_profit = dp[i + 1][not buying] - prices[i] if i + 1 < N else -prices[i]
                    skip_the_day = dp[i+1][buying] if i + 1 < N else 0
                    dp[i][buying] = max(buy_profit, skip_the_day)
                else:
                    sell_profit = (
                        dp[i + 2][True] + prices[i] if i + 1 < N else -prices[i]
                    )
                    skip_the_day = dp[i + 1][False] if i + 1 < N else 0
                    dp[i][False] = max(sell_profit, skip_the_day)
        return dp[0][True]

    # memoization, time: O(N), space: O(N)
    def maxProfit_memo(self, prices: List[int]) -> int:
        if not prices:
            return 0
        N = len(prices)
        memo = {}

        def solve(idx, buying):
            if idx >= N:
                return 0

            if (idx, buying) in memo:
                return memo[(idx, buying)]

            do_nothing = solve(idx + 1, buying)

            if buying:
                curr_profit = solve(idx + 1, not buying) - prices[idx]
                memo[(idx, buying)] = max(curr_profit, do_nothing)
                return max(curr_profit, do_nothing)
            else:
                curr_profit_after_sell = solve(idx + 2, not buying) + prices[idx]
                memo[(idx, buying)] = max(curr_profit_after_sell, do_nothing)
                return max(curr_profit_after_sell, do_nothing)

        return solve(0, True)

    # pure recursion
    def maxProfit(self, prices: List[int]) -> int:
        if not prices:
            return 0
        N = len(prices)

        def solve(idx, buying):
            if idx >= N:
                return 0
            do_nothing = solve(idx + 1, buying)
            # if we are allowed to buy
            if buying:
                # buy stock today, substract the price and move to selling state, or option 2 skip the day, and we finally keep the max of the two
                curr_profit = solve(idx + 1, not buying) - prices[idx]
                return max(curr_profit, do_nothing)
            # or we are allowed to sell a stock today
            else:
                # sell the stock today(add the price and skip the next day due to cooldown), option 2 is to skip the day,
                # finally storing the max of these 2 options
                curr_profit_after_sell = solve(idx + 2, not buying) + prices[idx]
                return max(curr_profit_after_sell, do_nothing)

        return solve(0, True)


if __name__ == "__main__":
    Solution().solve()
