# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/7/26
link:https://leetcode.com/problems/longest-common-subsequence/description/
is it possible to visualize common subsequence in an alignment?
so LCS consists characters that are common to both, so ideally what we want is for those characters to line up
using the concepts we learnt in edit distance, if we were to consider m + n to be the lengths of strings s1 and s2 respectively,
then what we have seen is m + n = num deletions + num of insertions + 2 * num substitutions as it could be match/mismatches)
so in the edit dist we were trying to minimize the above operations i.e. num insertions + num deletions + num substitutions
so the question now becomes does
minimizing num insertions + num deletions + num mismatches === maximizing matches?
we could try to use edit distance formula to be:
f(i,j) = len of LCS of x1..xi, and y1..yj where 0<= i <=m, and 0 <= j <= n
    = max(f(i-1,j)+0,f(i, j -1)+0,f(i-1, j-1) + 1 if x[i]==y[j] else 0)
base cases:
f(0,i)=0 for all i
f(j,0)=0 for all j
"""


def longestCommonSubsequence(text1: str, text2: str) -> int:
    if text1 == text2:
        return len(text1)
    M, N = len(text1), len(text2)
    ans=[[0]*(N+1) for _ in range(M+1)]

    #for converting an non empty string word1 to a empty one word2 since all chars are unqequal cost will be 0
    for i in range(1, M+1):
        ans[i][0]=0

    #for converting an empty string word1 to a non-empty one word2 since all chars are unqequal cost will be 0
    for i in range(1, N+1):
        ans[0][i]=0

    for i in range(1, M+1):
        for j in range(1, N+1):
            ans[i][j] = max(ans[i - 1][j], ans[i][j - 1], ans[i - 1][j - 1] + (1 if text1[i - 1]==text2[j - 1] else 0))
    return ans[M][N]



def main():
    assert longestCommonSubsequence("abcdbca", "aaabc") == 3
    assert longestCommonSubsequence("abcde", "d") == 1
    assert longestCommonSubsequence("abcde", "ace") == 3
    assert longestCommonSubsequence("abcde", "abc") == 3
    assert longestCommonSubsequence("abcde", "f") == 0
    assert longestCommonSubsequence("abac", "cab") == 2


if __name__ == '__main__':
    main()
