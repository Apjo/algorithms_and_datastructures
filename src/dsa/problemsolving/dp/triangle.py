#link: https://leetcode.com/problems/triangle/description/

from typing import List


def solve(triangle: List[List[int]]):
    #f(n,k): is the min cost path from top to row=n, col=k, and it relies on it neighbors from above i.e.
    #f(n,k) = min(f(n-1, k), f(n - 1, k - 1)) + grid[n][k]
    #init a 2d table of len(triangle) rows, and len(triangle)  + 1 cols
    N = len(triangle)
    ans = [[0 for cols in range(N)] for _ in range(N)]
    ans[0][0] = triangle[0][0]

    for row in range(1, N):
        #fill in the left wall
        ans[row][0] = ans[row - 1][0] + triangle[row][0]
        #fill in the right wall
        ans[row][row] = ans[row - 1][row - 1] + triangle[row][row]
    #general traversal start from row=2, within each row, num of col=row index+1, so col index will vary from 1 to row index - 1
    #having excluded the first and last col
    for row in range(2, N):
        for col in range(1, row):
            ans[row][col] = triangle[row][col] + min(ans[row - 1][col], ans[row - 1][col - 1])

    #the answer doesn't lie in the last N-1 cell this time, but instead the min value across all the elements in the last cell
    return min(ans[-1])


def main():
    assert solve([[2],[3,4],[6,5,7],[4,1,8,3]]) == 11


if __name__ == '__main__':
    main()
