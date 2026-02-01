#link https://leetcode.com/problems/knight-dialer/description/
#time: O(10*N)
def solve( n: int) -> int:
    '''
    if we were to lay out the number from left to right we would have n spaces to fill in. Looking at the last space on right
    we know we have  0-9 choices for the last space. After having made that choice then we ask "what would the preceeding digit be?"
    so, had the last digit been a 0, it must have come from a 4 or 6. Likewise we need to determine all such cases for each digit.
    But, there is no way to reach a 5!
    So, in order to count how many numbers  of len N give me all the phone numbers of a particular len of whatever the prefix is ending with a specific digit
    f(length=i, last digit=d)= no.of valid phone numbers of len=i ending with a digit=d
    Since i goes from 1 to N, and there could be a max of 10(0-9) digits, the total no.of unique subproblems are 10N
    #for every digit from 0 to 9, we would be calculating the cost from its neighbors which are present at the same distance a rook moves
    #so gather the cost for each digit into a dp table
    #finally return the sum of this dp table
    '''
    dp = [[0] * 10] * (n + 1)

    #for a number of len=1, we have 10 distinct numbers i.e. 1 each from 0-9
    for i in range(10):
        dp[1][i]=1

    for i in range(2, n + 1):
        dp[i][0] = dp[i-1][4] + dp[i-1][6]
        dp[i][1] = dp[i-1][6] + dp[i-1][8]
        dp[i][2] = dp[i-1][7] + dp[i-1][9]
        dp[i][3] = dp[i-1][4] + dp[i-1][8]
        dp[i][4] = dp[i-1][0] + dp[i-1][3] + dp[i-1][9]
        dp[i][5] = 0
        dp[i][6] = dp[i-1][0] + dp[i-1][1] + dp[i-1][7]
        dp[i][7] = dp[i-1][2] + dp[i-1][6]
        dp[i][8] = dp[i-1][1] + dp[i-1][3]
        dp[i][9] = dp[i-1][2] + dp[i-1][4]
    # final_ans=0
    # for i in range(10):
    #     final_ans+=dp[n][i]
    #return final_ans
    return sum(dp[-1])%10**9+7


def main():
    assert  solve(2) == 20


if __name__ == '__main__':
    main()
