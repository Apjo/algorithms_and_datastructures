"""
Filename: count_palindromic_substrings.py
Date: 2026-08-20
"""


class Solution:
    def countSubstrings_using_2d_dp(self, s: str) -> int:
        n = len(s)
        dp = [[False for _ in range(n)] for _ in range(n)]
        ans = 0
        for i in range(n - 1, -1, -1):
            for j in range(i, n):
                #len == 1
                if i == j:
                    dp[i][j] = True
                #len==2
                elif j == i + 1:
                    dp[i][j] = s[i] == s[j]
                #len > 2
                else:
                    dp[i][j] = s[i] == s[j] and dp[i + 1][j - 1]

                if dp[i][j]:
                    ans+=1

        return ans

    #time: O(N^2), space: O(1)
    def countSubstrings_using_expand_from_center(self, s: str) -> int:
        ans = 0
        def expand(le, ri):
            nonlocal ans
            while le >= 0 and ri < len(s) and s[le] == s[ri]:
                ans+=1
                le-=1
                ri+=1
        
        for i in range(len(s)):
            #for odd len strings expand from center i.e. i
            expand(i, i)
            # for even len strings expand from i, and i + 1
            expand(i, i + 1)
        
        return ans

        
    # time: O(N^3), and space:O(N^2)
    '''
    STRINGS: Use indices, not substrings
    memo[s[i:j]] = value, --> O(n) allocation
    memo[(i, j)] = value, --> O(1) tuple creation
    '''
    def countSubstrings_using_indices(self, s: str) -> int:
        ans = 0
        memo = {}

        def is_palindrome(start, end):
            if (start, end) in memo:
                return memo[(start, end)]

            while start < end:
                if s[start] != s[end]:
                    memo[(start, end)] = False
                    return memo[(start, end)]
                start += 1
                end -= 1
            memo[(start, end)] = True
            return memo[(start, end)]

        def solve(idx):
            nonlocal ans
            if idx >= len(s):
                return
            for i in range(idx, len(s)):
                if is_palindrome(idx, i):
                    ans += 1
            solve(idx + 1)

        solve(0)

        return ans

    # brute force time: O(N^3) but also creates a lot of substrings, and then TLEs trying to reach a max depth of recursion, space: O(N^2)
    def countSubstrings(self, s: str) -> int:
        ans = 0

        def is_palindrome(s):
            if s in memo:
                return memo[s]
            N = len(s)
            if N == 0:
                return False
            if N == 1:
                return True
            else:
                le, ri = 0, N - 1

                while le < ri:
                    if s[le] != s[ri]:
                        memo[s] = False
                        return memo[s]
                    le += 1
                    ri -= 1
                memo[s] = True
                return memo[s]

        def solve(idx):
            nonlocal ans
            if idx >= len(s):
                return

            for i in range(idx, len(s)):
                curr_substr = s[idx : i + 1]

                if curr_substr not in memo:
                    if is_palindrome(curr_substr):
                        ans += 1
                        memo[curr_substr] = True
                else:
                    if memo[curr_substr]:
                        ans += 1
            # The recursion and loop should not interleave. The loop should finish exploring all substrings from idx, then move to the next starting position.
            solve(idx + 1)

        memo = {}
        solve(0)

        return ans


if __name__ == "__main__":
    Solution().solve()
