"""
Filename: best_team_score.py
Date: 2026-08-31
"""

from typing import List


class Solution:
    def bestTeamScore_1dp(self, scores: List[int], ages: List[int]) -> int:
        if not scores or not ages:
            return 0

        N, temp = len(scores), sorted(zip(ages, scores))
        dp = [temp[i][1] for i in range(N)]

        # follows LIS approach
        for i in range(N):
            for j in range(i):
                if temp[j][1] <= temp[i][1]:
                    dp[i] = max(dp[i], dp[j] + temp[i][1])

        return max(dp)

    def bestTeamScore_memo(self, scores: List[int], ages: List[int]) -> int:
        if not scores or not ages:
            return 0

        N, temp = len(scores), []

        for i in range(N):
            temp.append((ages[i], scores[i]))

        # sorted by age in a non-decreasing order
        temp.sort()
        memo = {}

        def solve(idx, prev_player_score):

            if (idx, prev_player_score) in memo:
                return memo[(idx, prev_player_score)]

            if idx >= N:
                return 0  # no more players, no more scores, ret 0

            # pick only if current player's score is better than the last one
            pick_score = 0
            if prev_player_score <= temp[idx][1]:
                pick_score = temp[idx][1] + solve(idx + 1, temp[idx][1])

            # dont pick
            dont_pick_score = solve(idx + 1, prev_player_score)

            memo[(idx, prev_player_score)] = max(pick_score, dont_pick_score)

            return max(pick_score, dont_pick_score)

        return solve(0, 0)

    # using simple recursion, pick and not pick.
    def bestTeamScore(self, scores: List[int], ages: List[int]) -> int:
        if not scores or not ages:
            return 0

        N, ans, temp = len(scores), 0, []

        for i in range(N):
            temp.append((ages[i], scores[i]))

        # sorted by age in a non-decreasing order
        temp.sort()
        curr_score = 0  # highest score so far in this path

        def solve(idx, prev_player_score):
            nonlocal ans, curr_score
            if idx >= N:
                ans = max(ans, curr_score)
                return

            # pick only if current player's score is better than the last one
            if prev_player_score <= temp[idx][1]:
                curr_score += temp[idx][1]
                solve(idx + 1, temp[idx][1])
                # backtrack
                curr_score -= temp[idx][1]

            # dont pick
            solve(idx + 1, prev_player_score)

        solve(0, 0)

        return ans


if __name__ == "__main__":
    Solution().solve()
