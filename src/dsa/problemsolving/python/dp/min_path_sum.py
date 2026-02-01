#link https://leetcode.com/problems/minimum-path-sum/
from typing import List

#time: O(m*n), space:O(m*n)
def solve(grid: List[List[int]]):
    m,n=len(grid), len(grid[0])
    ans=[[0] * n for _ in range(m)]
    ans[0][0]=grid[0][0]
    print(f"current ans={ans}, with rows={m}, cols={n}")
    #row 0
    for i in range(1, m):
        ans[0][i] = grid[0][i] + ans[0][i - 1]
    #col 0
    for i in range(1, n):
        ans[i][0] = grid[i][0] + ans[i - 1][0]
    #general traversal
    for i in range(1, m):
        for j in range(1, n):
            #add to my neighbors my cost, and pick the minimum
            ans[i][j] = grid[i][j] + min(ans[i-1][j], ans[i][j - 1])
    print(ans)
    return ans[-1][-1]


def main():
    # assert solve([[1,3,1],[1,5,1],[4,2,1]]) == 7
    # assert solve([[5,2],[3,1]]) == 8
    solve([[1,2],[2,3],[3,4]])
    # assert solve([[1]]) == 1


if __name__ == '__main__':
    main()
