"""
Filename: min_ops_to_write_lettery.py
Date: 2026-11-12
"""


class Solution:
    def minimumOperationsToWriteY(self, grid: list[list[int]]) -> int:
        ans = float("inf")
        if not grid:
            return 0

        N = len(grid)
        y_regions = set()
        nony_regions = set()
        center_loc_r, center_loc_c = N // 2, N // 2

        # figure out Y regions
        def get_all_y_regions():
            r, c = center_loc_r, center_loc_c
            while r >= 0 and c >= 0:
                y_regions.add((r, c))
                r -= 1
                c -= 1
            r, c = center_loc_r, center_loc_c
            while r >= 0 and c < N:
                y_regions.add((r, c))
                r -= 1
                c += 1
            r, c = center_loc_r, center_loc_c
            while r < N:
                y_regions.add((r, c))
                r += 1
            return y_regions

        # figure out non-Y regions
        def get_all_non_y_regions():
            for r in range(N):
                for c in range(N):
                    if (r, c) not in y_regions:
                        nony_regions.add((r, c))

            return nony_regions

        y_regions = get_all_y_regions()
        nony_regions = get_all_non_y_regions()

        # get counts of 0/1/2 in the non-y, a y regions
        freq_nony, freq_y = {0: 0, 1: 0, 2: 0}, {0: 0, 1: 0, 2: 0}
        size_n, size_y = len(nony_regions), len(y_regions)
        for r in range(N):
            for c in range(N):
                if (r, c) in y_regions:
                    freq_y[grid[r][c]] += 1
                else:
                    freq_nony[grid[r][c]] += 1
        """
        The loop tries every way to color the grid and keeps the cheapest one.
        Each pair (i, j) means:

        i: the value every non-Y cell should become
        j: the value every Y cell should become
        i != j, because the problem requires the two groups to differ
        And cost calc becomes
        Cost of one pair:
            Non-Y cells that are already i stay as they are. The rest must change. Number of changes: size_n - cnt_n[i].
            Y cells that are already j stay as they are. The rest must change. Number of changes: size_y - cnt_y[j].
            Total cost = the sum of those two.
        """
        for i in range(3):
            for j in range(3):
                if i != j:
                    cost = size_n - freq_nony[i] + size_y - freq_y[j]
                    ans = min(ans, cost)

        return ans


if __name__ == "__main__":
    Solution().solve()
