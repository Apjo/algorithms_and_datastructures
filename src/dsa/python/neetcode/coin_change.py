"""
Filename: coin_change.py
Date: 2026-08-23
"""

from typing import List

class Solution:
    #time: O(2^N)
    def coinChange(self, coins: List[int], amount: int) -> int:
        ans = float("inf")
        #returns the minimum number of coins needed to make amount T using coins from index idx onwards.
        def solve(T, idx, num_coins):
            nonlocal ans

            if idx >= len(coins):
                return
            if T < 0:
                return

            if T == 0:
                ans = min(ans, num_coins)
                return

            # if you pick this coin, since there are infinite denominations, we can just stay here, and continue down this path where we keep on picking this coin at idx itself, and decrementing T by coins[idx] value, till we either reach a 0 where we found the min number of coins required, or we reach a -1 where we would never be able to reach the target.
            num_coins += 1
            solve(T - coins[idx], idx, num_coins)
            # decrement the num coins by 1 when we explore the other coin
            num_coins -= 1
            solve(T, idx + 1, num_coins)

        solve(amount, 0, 0)
        return ans if ans != float("inf") else -1

    # time: O(N)
    def coinChange_memo(self, coins: List[int], amount: int) -> int:
        memo={}
        def solve(T, idx):
            if idx >= len(coins):
                if T != 0:
                    memo[(T, idx)] = float("inf")
                    return memo[(T, idx)]
                if T == 0:
                    memo[(T, idx)] = 0
                    return memo[(T, idx)]
            
            if T < 0:
                memo[(T, idx)] = float("inf")
                return memo[(T, idx)]

            if T == 0:
                #0 num coins required to reach from idx to T since we acheived the target
                memo[(T, idx)] = 0
                return memo[(T, idx)]
            
            if (T, idx) in memo:
                return memo[(T, idx)]
            else:
                memo[(T, idx)] = float("inf")
            # if you pick this coin, since there are infinite denominations, we can just stay here, and continue down this path where we keep on picking this coin at idx itself, and decrementing T by coins[idx] value, till we either reach a 0 where we found the min number of coins required, or we reach a -1 where we would never be able to reach the target.
            a = solve(T - coins[idx], idx) + 1
            b = solve(T, idx + 1)
            if a and b:
                #min number of coins required from idx to reach T
                memo[(T, idx)] = min(a, b)
                
            return memo[(T, idx)]
        
        val = solve(amount, 0)
        
        return val if val != float("inf") else -1

    def coinChange_dp(self, coins: List[int], amount: int) -> int:
        N = len(coins)
        dp=[[float("inf") for _ in range(N + 1)] for _ in range(N + 1)]
        #for making amount 0 from any coin we can only have 0 ways
        #for making an amount > 0 from 0 coins there is no way so set to INF
        for i in range(N + 1):
            dp[0][i] = 0
        for i in range(1, amount + 1):
            dp[0][i] = float("inf")

        for am in range(1, amount + 1):
            for coin in range(1, N + 1):
                    if am - coins[coin - 1] >= 0:
                        dp[am][coin] = min(dp[am][coins[coin - 1]], dp[am - coins[coin - 1]][coin] + 1)
                    else:
                        dp[am][coin] = dp[am][coin - 1]
        
        return int(dp[amount][N])



if __name__ == '__main__':
    Solution().solve()