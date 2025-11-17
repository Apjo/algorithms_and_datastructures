#Count the number of shortest paths a rook can take to go from one corner of a chessboard to the other if it can only move horizontally or vertically
#link https://leetcode.com/problems/unique-paths/description/

#t:O(m*n), s:O(m*n)
def uniquePaths(m: int, n: int) -> int:
    rows, cols = m, n
    grid = [[0] * cols] * rows
    for i in range(rows):
        grid[i][0] = 1
    for j in range(cols):
        grid[0][j] = 1
    for i in range(1, rows):
        for j in range(1, cols):
            # If f(m,n)= num ways to reach (m,n)from top left corner
            #           = f(m - 1, n) + f(m, n - 1)
            grid[i][j] = grid[i - 1][j] + grid[i][j - 1]
    return grid[rows - 1][cols - 1]


def main():
    assert uniquePaths(m=3, n=7) == 28


if __name__ == '__main__':
    main()
