"""
Filename: pacific_atlantic_water_flow.py
Date: 2026-07-31
"""

from typing import List

class Solution:
    def pacificAtlantic(self, heights: List[List[int]]) -> List[List[int]]:
        # we maintain 2 sets, one for pacific, one for atlantic
        # iterate over the borders of the grid
        # we start by iterating over all row=0 for all COLS capturing pacific
        # then we iterate over all rows again for col=0 capturing pacific
        # we repeat the above 2 stes for atlantic starting with cols=COLS-1 for all rows,
        # and then for row=ROWS-1 for all cols
        # finally, we determine which all locations were common in pacific and atlantic i.e. their intersection
        # to lookup neighbors we could either use a DFS or a BFS approach
        """
                A cell can flow to an ocean if you can start from the ocean border and move backwards into the grid using the rule:

        from (r, c) you can go to neighbor (nr, nc) if heights[nr][nc] >= heights[r][c]
        (because in the real direction water would flow from higher/equal down to (r, c)).
        """
        if not heights:
            return []
        M, N = len(heights), len(heights[0])
        atlantic, pacific = set(), set()
        directions = [[0, 1], [0, -1], [1, 0], [-1, 0]]

        def dfs(r, c, st):
            if (r, c) in st:
                return
            st.add((r, c))
            for direction in directions:
                new_r, new_c = direction[0] + r, direction[1] + c
                # REMEMBER:
                # From cell (r, c), you may go to a neighbor (nr, nc) only if heights[nr][nc] >= heights[r][c] (uphill or same).
                if (
                    0 <= new_r < M
                    and 0 <= new_c < N
                    and heights[new_r][new_c] >= heights[r][c]
                ):
                    dfs(new_r, new_c, st)

        for c in range(N):
            # pacific row=0,all cols
            dfs(0, c, pacific)
            # atlantic row=M-1 bottom
            dfs(M - 1, c, atlantic)
        for r in range(M):
            # pacific all rows, col=0
            dfs(r, 0, pacific)
            # atlantic all rows, last col=N-1
            dfs(r, N - 1, atlantic)

        # temp = pacific & atlantic
        temp = []
        for r1, c1 in pacific:
            if (r1, c1) in atlantic:
                temp.append([r1, c1])
        # return [list(aa) for aa in temp]
        return temp
        


if __name__ == '__main__':
    Solution().solve()