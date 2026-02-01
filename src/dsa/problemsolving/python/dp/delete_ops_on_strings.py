# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/7/26
link: https://leetcode.com/problems/delete-operation-for-two-strings/
"""

def lcs(s1: str, s2:str) -> int:
    M, N = len(s1), len(s2)
    ans = [[0] * (N + 1) for _ in range(M + 1)]
    #for converting an non empty string word1 to a empty one word2 since all chars are unqequal cost will be 0
    for i in range(1, M+1):
        ans[i][0] = 0

    for i in range(1, N+1):
        ans[0][i] = 0
    for i in range(1, M+1):
        for j in range(1, M+1):
            ans[i][j] = max(ans[i-1][j], ans[i][j-1], ans[i-1][j-1]+(0 if s1[i - 1] != s2[j - 1] else 1))
    return ans[-1][-1]

def minDistance(word1: str, word2: str) -> int:
    if word1 == word2:
        return 0
    M, N = len(word1), len(word2)
    #calc. the len of LCS
    #the LCS tells us the common set of characters in both words, so those will be the ones we will keep from both the strings.
    #, and everything else will be deleted.
    # so len of word1 = L1, characters we keep from LCS = l1, so total we delete= L1 - l1
    # Similarly, len of word2 = L2, characters we keep from LCS = l2, so total we delete= L2 - l2
    #we finally arrive at the formula:
    # Total deletions = (M - LCS) + (N - LCS)
    # M + N - 2*LCS
    return M + N - (2*lcs(word1, word2))

#Another approach to refer to using edit distance approach.
'''
We know we want to minimize the number of deletions from both strings
IF we think from an alignment perspective, we want to minimize deletions from both the strings, so that we end up with 
common strings
So,we penalize deletion from string 1, deletion from string 2, and only keep to have matches.Ignoring mismatches.
also, we know m + n = num matches + num mismatches + substitutions(2 num matches + 2 num mismatches)
in lcs, we maximized 2 * num matches.which is the same as minimizing (num matches + num mismatches)provided we did not have 
2 * num mismatches.But, we will have mismatches, so we need a way to get rid of any mismatches.
So, if we never want to have substitutions, and want to have only insert/delete we will need to make the cost of substitution higher than insert/delete combined, so basically
if cost of 1 insert and 1 delete is 1, and combined is 2, we can have any cost of 2+ for a substitution.
So, in this case the algo will always find a way to avoid a substitution by just having an insertion/deletion next to each other.
Also, Edit distance was already minimizing, but if we view deletions from second string as insertions from the first string,
both the strings are trying to arrive at a common string, so we basically try to maximize the number of matches by minimizing
the number of insertions and deletions, OR we could leave out the case of mismatches!
 
'''
def minDistance_using_edit(word1: str, word2: str) -> int:
    M, N = len(word1), len(word2)
    ans = [[0] * (N + 1) for _ in range(M + 1)]
    for i in range(M + 1):
        ans[i][0] = i
    for i in range(N + 1):
        ans[0][i] = i
    for i in range(M + 1):
        for j in range(N + 1):
            ans[i][j] = min(ans[i - 1][j] + 1, ans[i][j - 1] + 1, ans[i - 1][j - 1] +(0 if word1[i - 1] == word2[j - 1] else 3))
    return ans[-1][-1]
def main():
    assert minDistance("sea", "eat") == 2
    assert minDistance_using_edit("sea", "eat") == 2



if __name__ == '__main__':
    main()
