# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/4/26
link: https://leetcode.com/problems/longest-palindromic-subsequence/description/
"""
#time:O(2^n),space:O(N)call stack
def longestPalindromeSubseq(s: str) -> int:
    def solve(index, index_end):
        if index > index_end:
            return 0
        if index == index_end:
            return 1
        #if characters at index, and index_end match, we found a palindrome
        if s[index] == s[index_end]:
            return 2 + solve(index + 1, index_end - 1)
        #else leave character from left end
        leave_left = solve(index + 1, index_end)
        #else leave character from right end
        leave_right = solve(index, index_end - 1)
        return max(leave_left, leave_right)
    return solve(0, len(s) - 1)

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


def main():
    assert longestPalindromeSubseq("bbbab")==4
    assert longestPalindromeSubseq_memo("bbbab")==4
    assert longestPalindromeSubseq("cbbd")==2


if __name__ == '__main__':
    main()
