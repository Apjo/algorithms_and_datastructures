"""
Filename: min_cost_clim_stairs.py
Date: 2026-08-15
"""


class Solution:
    # pure recursion with memo or top down DP, time: O(N), space:O(N)
    def climbStairs_memo(self, n: int) -> int:
        if n <= 2:
            return n
        memo=[-1]*(n+1)
        memo[0]=0
        memo[1]=1
        def solve(idx):
            if memo[idx] != -1:
                return memo[idx]
            
            memo[idx] = solve(idx - 1) + solve(idx - 2)
            return memo[idx]
            
        return memo[n]
    
    #pure recursion, TLE on n=38, time: O(2^n)
    def climbStairs_rec(self, n: int) -> int:
        if n <= 2:
            return n

        return self.climbStairs_rec(n - 1) + self.climbStairs_rec(n - 2)


    #without using a DP table, time: O(n), space: O(1)
    def climbStairs(self, n: int) -> int:
        if n <= 2:
            return n
        f3, f1, f2 = 0, 1, 1
        for _ in range(3, n + 1):
            f3 = f1 + f2
            f1 = f2
            f2 = f3

        return f3
        


if __name__ == '__main__':
    Solution().solve()