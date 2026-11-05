"""
Filename: coin_change_2.py
Date: 2026-09-08
"""

from typing import List


class Solution:
    # bottom up version
    def change_dp(self, amount: int, coins: List[int]) -> int:
        N = len(coins)

        ways = [[0 * (amount + 1)] for _ in range(N + 1)]
        # there is only 1 ways to make 0 amount with i coins
        for i in range(N + 1):
            ways[i][0] = 1

        for coin in range(1, N + 1):
            for amt in range(1, amount + 1):
                if amt - coins[coin] >= 0:
                    # same row, smaller amount → use current coin
                    ways[coin][amt] = (
                        ways[coin - 1][amt] + ways[coin][amt - coins[coin - 1]]
                    )

                else:
                    # previous row, same amount → don't use current coin
                    ways[coin][amt] = ways[coin - 1][amt]

        return ways[N][amount]

    # memoized version, time: O(N)
    def change_memo(self, amount: int, coins: List[int]) -> int:
        if not coins:
            return 0
        memo = {}
        N = len(coins)

        def solve(T, idx):
            # we found 1 combination
            if T == 0:
                memo[(T, idx)] = 1
                return memo[(T, idx)]

            if T < 0 or idx >= N:
                return 0

            if (T, idx) in memo:
                return memo[(T, idx)]
            # stay in the same row, but with a smaller amount
            a = solve(T - coins[idx], idx)
            # go to next coin with the same amount or next row but same col
            b = solve(T, idx + 1)
            memo[(T, idx)] = a + b

            return memo[(T, idx)]

        return solve(amount, 0)

    # pure recursion time: O(2^N)
    def change(self, amount: int, coins: List[int]) -> int:
        if not coins:
            return 0
        res, buff = [], []
        N = len(coins)

        def solve(T, idx):
            if T == 0:
                res.append(buff[:])
                return
            if T < 0 or idx >= N:
                return
            # pick
            buff.append(coins[idx])
            T -= coins[idx]
            solve(T, idx)
            buff.pop()
            # not pick
            solve(T, idx + 1)

        solve(amount, 0)
        return len(res)


if __name__ == "__main__":
    Solution().solve()
