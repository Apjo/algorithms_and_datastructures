
#link: https://leetcode.com/problems/climbing-stairs/description/

def solve(n):
    dp={}
    dp[0]=1#to get to n=2 we need to rely on n=0 and n=1 so set 1 for n=0 or we could start from 1 directly
    dp[1]=1
    for i in range(2, n + 1):
        dp[i] = dp[(i - 1)] + dp[(i - 2)]
    return dp[n]

def solve_2(n):
    if n <= 2:
        return n
    dp=[0]*(n + 1)
    dp[1]=1
    dp[2]=2
    for i in range(3, n + 1):
        dp[i] = dp[i - 1] + dp[i - 2]
    return dp[n]

def main():
    assert solve(2)==2
    assert solve(3)==3


if __name__ == '__main__':
    main()
