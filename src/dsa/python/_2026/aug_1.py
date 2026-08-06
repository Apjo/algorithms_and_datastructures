"""
Filename: aug_1.py
Date: 2026-08-02
link: https://leetcode.com/problems/predict-the-winner/description/
"""

from typing import List

class Solution:
    def predictTheWinner(self, nums: List[int]) -> bool:
        def solve(player_playing, p1, p2, st, end):
            if st > end:
                return p1 >= p2
            #player 1 turn
            if player_playing == 1:
                #pick a number from left
                first_turn = solve(2, p1+nums[st], p2, st + 1, end)
                #or picks a number from right
                second_turn = solve(2, p1 + nums[end], p2, st, end - 1)
            else:
                #Now player 2 is playing
                # player2 also pick a number from left, and update score p2
                first_turn = solve(1, p1, p2 + nums[st], st + 1, end)
                # or picks a number from right
                second_turn = solve(1, p1, p2 + nums[end], st, end - 1)
            return (
                first_turn or second_turn
                if player_playing == 1
                else (first_turn and second_turn)
            )
        return solve(1, 0, 0, 0, len(nums) - 1)
        
    #another way to solve it
    """
    say the input array is nums=[1,5,2]
    [1, 5, 2]
        1/\2     <--- 1st player's decision
    [5,2]  [1,5]
     5/\2    1/\5    <---- 2nd player's decision
    [2] [5]  [5] [1]
    1.if player 1 is playing:
    1. player1 picks nums[st], player2 can pick either ends of the array nums[st + 1, end]
       1. player2 picks nums[st+1], then player1 can pick either ends of the array nums[st + 2, end]
       2. player2 picks nums[end], then player1 can pick either ends of the array nums[st + 1, end-1]
    Since, player2 will try to maximize his score, so player1 gets nums[st] + min(1.1, 1.2)
    2. player1 picks nums[end], player2 can pick either ends of the array nums[st, end - 1]
       1. player2 picks nums[st], then player1 can pick either ends of the array nums[st + 1, end - 1]
       2. player2 picks nums[end-1], then player1 can pick either ends of the array nums[st, end-2]
    Since, player2 will try to maximize his score, so player1 gets nums[end] + min(2.1, 2.2)

    since the player1 is playing to maximize their scores player1 can get
    max(
    nums[st] + min(1.1, 1.2),
    nums[end] + min(2.1, 2.2)
    )
    """
    def predictTheWinner_2(self, nums: List[int]) -> bool:
        def solve(st, end):
            if st > end:
                return 0
            return max(nums[st] + min(solve(st+2, end), solve(st + 1, end - 1)),
                       nums[end] + min(solve(st+1, end - 1), solve(st, end - 2)))
        
        total_score = sum(nums)
        player1_score = solve(0, len(nums) - 1)
        return player1_score >= total_score - player1_score


if __name__ == '__main__':
    Solution().predictTheWinner([1,5,2])