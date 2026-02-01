# https://leetcode.com/problems/fibonacci-number/
#f(0)=0, f(1)=1
#f(n) = f(n - 1) + f(n - 2)

#t:O(n), s:O(n)
def solve_memo(n) -> int:
    memo={}
    memo[0]=0
    memo[1]=1
    if n in memo:
        return memo[n]
    memo[n] = solve_memo(n - 1) + solve_memo(n - 2)
    return memo[n]


def solve(n) -> int:
    dp={}
    dp[0]=0
    dp[1]=1
    for i in range(2, n + 1):
        dp[i] = dp[i - 1] + dp[i - 2]
    return dp[n]

#t:O(n), s: O(1)
def solve_space_optimized(n):
    dp=[0]*3
    dp[0]=0
    dp[1]=1
    for i in range(2, n + 1):
        dp[i % 3] = dp[(i - 1)%3] + dp[(i - 2) % 3]
    return dp[n % 3]

def main():
    assert solve(2) == 1
    assert solve(3) == 2
    assert solve(4) == 3
    assert solve_space_optimized(4) == 3


if __name__ == '__main__':
    main()
