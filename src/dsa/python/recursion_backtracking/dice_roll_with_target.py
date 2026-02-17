# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/1/26
link: https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/description/
"""

def numRollsToTarget(n: int, k: int, target: int) -> int:

    def solve(remaining_target, remaining_dice):
        if remaining_target == 0:
            return 1 if remaining_dice == 0 else 0
        if remaining_target <= 0:
            return 0

        curr_ans=0
        for i in range(1, k + 1):
            curr_ans += solve(remaining_target - i, remaining_dice - 1)
        return curr_ans
    return int((solve(target, n)) % 1000000007)

#time: O(target * n * k), space: O(n * target)
def numRollsToTarget_memoized(n: int, k: int, target: int) -> int:
    memo={}

    def solve_memo(remaining_target, remaining_dice):
        if remaining_target == 0:
            return 1 if remaining_dice==0 else 0
        if remaining_target <= 0:
            return 0
        if (remaining_target, remaining_dice) in memo:
            return memo[(remaining_target, remaining_dice)]
        curr_ans = 0
        for face_value in range(1, k + 1):
            curr_ans=(curr_ans + solve_memo(remaining_target - face_value, remaining_dice - 1)) % 1000000007
        memo[(remaining_target, remaining_dice)] = curr_ans
        return curr_ans
    return int((solve_memo(remaining_target=target, remaining_dice=n)) % 1000000007)


def main():
    assert numRollsToTarget(n=1,k=6,target=3)==1
    assert numRollsToTarget_memoized(n=1,k=6,target=3)==1
    assert numRollsToTarget(n=2,k=6,target=7)==6
    assert numRollsToTarget_memoized(n=2,k=6,target=7)==6
    assert numRollsToTarget_memoized(n=30,k=30,target=500)==222616187


if __name__ == '__main__':
    main()
