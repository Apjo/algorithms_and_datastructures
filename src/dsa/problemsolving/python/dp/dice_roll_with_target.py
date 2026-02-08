# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/1/26

"""

def numRollsToTarget_memo(n: int, k: int, target: int) -> int:
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
            curr_ans+=solve_memo(remaining_target - face_value, remaining_dice - 1)
        memo[(remaining_target, remaining_dice)] = curr_ans
        return curr_ans
    return int(solve_memo(remaining_target=target, remaining_dice=n) % 1000000007)

#time: O(n * target * k)
def numRollsToTarget(n: int, k: int, target: int) -> int:
    dp=[[0]*(target + 1) for _ in range(n + 1)] #number of ways to reach target j using i dice.
    dp[0][0]=1
    #number of ways to get a non-zero target from 0 dice is 0
    for i in range(1, target + 1):
        dp[0][i]=0
    #number of ways to get a target=0 from non-zero dice=0
    for i in range(1, n + 1):
        dp[i][0]=0

    for remaining_dice in range(1, n+1):
        for remaining_target in range(1, target + 1):
            for face_value in range(1, k + 1):
                if remaining_target - face_value >=0:
                    dp[remaining_dice][remaining_target] += dp[remaining_dice - 1][remaining_target - face_value]
    #print(dp)
    return int(dp[n][target] % 1000000007)


def main():
    assert numRollsToTarget(n=1,k=6,target=6)==1
    assert numRollsToTarget(n=2,k=6,target=7)==6
    assert numRollsToTarget(n=30,k=30,target=500)==222616187


if __name__ == '__main__':
    main()
