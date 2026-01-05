from typing import List

#space and time:O(m*n)
def solve(grid:List[List[int]]):
    m, n = len(grid), len(grid[0])
    dp=[[0] * n for _ in range(m)]
    dp[0][0]=1 if grid[0][0] == 0 else 0
    #row 0
    for col in range(1, n):
        dp[0][col] = dp[0][col - 1] if grid[0][col] != 1 else 0
    #col 0
    for row in range(1, m):
        dp[row][0] = dp[row - 1][0] if grid[row][0] != 1 else 0

    for i in range(1, m):
        for j in range(1, n):
            if grid[i][j] != 1:
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
            else:
                dp[i][j]=0
    return dp[-1][- 1]


def main():
    assert solve([[0,0,0],[0,1,0],[0,0,0]]) == 2


if __name__ == '__main__':
    main()
