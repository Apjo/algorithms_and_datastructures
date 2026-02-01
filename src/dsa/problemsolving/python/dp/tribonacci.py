#link: https://leetcode.com/problems/n-th-tribonacci-number/description/

#t:O(n), s:O(n)
def solve(n):
    if n <= 1:
        return n
    dp=[0]*(n + 1)
    dp[1]=1
    dp[2]=1
    for i in range(3, n + 1):
        dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3]
        #NOTE: for O(1) space dp[i % 4] = dp[i - 1 % 4] + dp[i - 2 % 4] + dp[i - 3 % 4]
    return dp[n]


def main():
    assert solve(4) == 4


if __name__ == '__main__':
    main()
