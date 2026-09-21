"""
Filename: max_path_in_grid.py
Date: 2026-09-21
"""


class Solution:
    #memo
    def longestIncreasingPath_memo(self, matrix: list[list[int]]) -> int:
        M, N = len(matrix), len(matrix[0])
        
        def solve(r, c, prev_val):
            if r < 0 or r >= M or c < 0 or c >= N or matrix[r][c] <= prev_val:
                return 0

            if (r, c) in dp:
                return dp[(r, c)]
            
            res = 1
            res = max(res, 1 + solve(r + 1, c, matrix[r][c]))
            res = max(res, 1 + solve(r - 1, c, matrix[r][c]))
            res = max(res, 1 + solve(r, c + 1, matrix[r][c]))
            res = max(res, 1 + solve(r, c - 1, matrix[r][c]))
            dp[(r, c)] = res
            return res

        dp={}
        for i in range(M):
            for j in range(N):
                solve(i, j, float("-inf"))
        return max(dp.values())

    #pure recursion, dfs at each cell.time: O(R*C*4^(R*C))
    def longestIncreasingPath(self, matrix: list[list[int]]) -> int:
        M, N = len(matrix), len(matrix[0])

        def solve(r, c, prev_val):
            if r < 0 or r >= M or c < 0 or c >= N or matrix[r][c] <= prev_val:
                return 0
            res = 1
            res = max(res, 1 + solve(r + 1, c, matrix[r][c]))
            res = max(res, 1 + solve(r - 1, c, matrix[r][c]))
            res = max(res, 1 + solve(r, c + 1, matrix[r][c]))
            res = max(res, 1 + solve(r, c - 1, matrix[r][c]))
            return res

        ans = 0
        for i in range(M):
            for j in range(N):
                ans = max(ans, solve(i, j, float("-inf")))
        return ans
        


if __name__ == '__main__':
    Solution().solve()