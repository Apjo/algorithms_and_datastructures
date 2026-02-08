# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/4/26
link: https://leetcode.com/problems/longest-palindromic-subsequence/description/
"""

#time: O(N^2)
def longestPalindromeSubseq_memo(s: str) -> int:
    memo={}
    def solve(index, index_end):
        if index > index_end:
            return 0
        if index == index_end:
            return 1
        if (index, index_end) in memo:
            return memo[(index, index_end)]
        #if characters at index, and index_end match, we found a palindrome
        if s[index] == s[index_end]:
            return 2 + solve(index + 1, index_end - 1)
        #else leave character from left end
        leave_left = solve(index + 1, index_end)
        #else leave character from right end
        leave_right = solve(index, index_end - 1)
        memo[(index, index_end)] = max(leave_left, leave_right)
        return max(leave_left, leave_right)
    return solve(0, len(s) - 1)

#approach 1 for bottom up DP: reverse the second string and apply similar logic that of EDIT DISTANCE
#time: O(len(s) * len(t))
def longestPalindromeSubseq(s: str) -> int:
    if not s:
        return 0
    t = s[::-1]
    ans =[[0] * (len(t) + 1) for _ in range(len(s) + 1)]
    for i in range(1, len(s) + 1):
        for j in range(1, len(t) + 1):
            if s[i - 1] == t[j - 1]:
                ans[i][j] = 1 + ans[i - 1][j - 1]
            else:
                ans[i][j] = max(ans[i - 1][j], ans[i][j - 1])
    return ans[-1][-1]

def main():
    assert longestPalindromeSubseq_memo("bbbab")==4
    assert longestPalindromeSubseq_memo("cbbd")==2


if __name__ == '__main__':
    main()
