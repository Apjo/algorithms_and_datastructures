"""
Filename: num_islands.py
Date: 2026-07-22
"""

from typing import List

class Solution:
    class DSU:
        def __init__(self, size):
            self.size = [1]*(size+1)
            self.parent = [0]*(size + 1)
            #initiall each node is a parent of itself!
            for i in range(size + 1):
                self.parent[i] = i
        #quikc find
        def find(self, node):
            if node == self.parent[node]:
                return node
            
            node_to_ret = self.find(self.parent[node])

            return node_to_ret
        
        def union(self, n1, n2):
            p1 = self.find(n1)
            p2 = self.find(n2)
            if p1 == p2:
                return False
            # if parents are not same, think of merging
            if p1 != p2:
                # if the sizes of p1 >= p2, make p1 parent of p2, and increment size of p1 by size of p2
                if self.size[p1] >= self.size[p2]:
                    self.parent[p2] = p1
                    self.size[p1] += self.size[p2]

                else:
                    # elif the sizes of p2 > p1, or if sizes of p1 and p2 are the same then:
                    # 1. make p2 parent of p1
                    # 2. increment size of p2 by size of p1
                    self.parent[p1] = p2
                    self.size[p2] += self.size[p1]
            return True

    # using DS(Union Find)
    def numIslands_dsu(self, grid: List[List[str]]) -> int:
        '''
        Every land cell is initially an island. When we find 2 land cells adj to each other(left/right/top/bottom), they actually belong to the same island, and hence we merge them.
        DSU helps in identifying connected adj. cells, and helps avoid counting the same island multiple times
        '''
        #treat each cell as a node, and map (rowi, colj) to a unique index
        num_islands, M, N = 0, len(grid), len(grid[0])
        directions = [[0,1], [0,-1], [1,0], [-1, 0]]
        
        dsu = Solution.DSU(M*N)
        
        def calc_index(row_num, col_num):
            return row_num * N + col_num
        
        for i in range(M):
            for j in range(N):
                if grid[i][j]=="1":
                    num_islands+=1
                    for dir in directions:
                        new_i = dir[0] + i
                        new_c = dir[1] + j
                        if new_i < 0 or new_c < 0 or new_i >= M or new_c >= N or grid[new_i][new_c] == "0":
                            continue
                        if dsu.union(calc_index(i, j), calc_index(new_i, new_c)):
                            num_islands-=1

        return num_islands

    #using DFS time and space: O(m*n)
    def numIslands(self, grid: List[List[str]]) -> int:
        # iterate over the grid, and if you see a 1, perform a dfs to count the islands
        # after finishing the search then only increment the count
        ans, M, N = 0, len(grid), len(grid[0])

        def solve(r, c):
            if (
                r < 0
                or c < 0
                or r >= M
                or c >= N
                or grid[r][c] == "#" #should not be visited
                or grid[r][c] == "0" #shouldnt be a 0(water)
            ):
                return

            # Basically, here is where ideally we could have just marked the land cell as water
            # since Whenever we find a land cell that hasn’t been visited, we start a DFS to sink the entire island by marking all its connected land as water.
            # in our case a '#' means this cell is visited
            grid[r][c] = "#"
            solve(r + 1, c)
            solve(r - 1, c)
            solve(r, c + 1)
            solve(r, c - 1)

        for r in range(M):
            for c in range(N):
                if grid[r][c] == "1":
                    solve(r, c)
                    ans += 1
        return ans        


if __name__ == '__main__':
    Solution().solve()