# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/14/26
"""
#recursive approach giving exponential time
def isInterleave(s1: str, s2: str, s3: str) -> bool:
    def solve(s1, s2, s3, i, j):
        if i == len(s1) and j == len(s2):
            return True
        res=False
        if i < len(s1) and s1[i] == s3[i+j]:
            res |= solve(s1, s2, s3, i+1, j)
        if j < len(s2) and s2[j] == s3[i+j]:
            res |= solve(s1, s2, s3, i, j+1)
        return res
    return solve(s1, s2, s3, 0, 0)
'''
If we were to think of this problem in terms of alignment and we pull apart the interleaving of s1 and s2, is there an alignment of s1 and s2 that could generate s3
We add gaps in the other string to visualize it as an alignment
There is a combinatorial explosion in the number of alignments
An alignment with only insertions/deletions(no matches/mismatches)
Now, focussing on the last or the rightmost element of the alignment,we see a character that could be coming from s1 or s2
i.e. we could have (a,_) or (_, a)to make a decision on
so, question is what if that character doesn't match either of the last characters from s1 or s2?
then its not possible to generate an alignment!
so we have 3 options for this case:
- matches with only last char of s1, then we find out if s3 minus the last char could be the interleaving of s1 minus that last char, and s2
- matches with only last char of s2, then we find out if s3 minus the last char could be the interleaving of s2 minus that last char, and s1
if either of them returns True my answer will also be True, else False
- matches with with both s1 and s2's last character

so, the recurrence to be thought of will be
f(i, j) = f(i-1, j) and s1[i-1] == s3[i+j-1] OR f(i, j - 1) and s2[j-1] == s3[i+j-1]

'''
#time:O(M*N) and space
def isInterleave_dp(s1: str, s2: str, s3: str) -> bool:
    M, N = len(s1), len(s2)
    if M + N != len(s3):
        return False
    ans=[[False]*(N + 1) for _ in range(M + 1)]
    ans[0][0] = True
    #now say if we have s1 to be empty, and s2 to be non-empty, we check to see whether the prefix of first j characters from s2,
    #and nothing from s1, do they match first j characters of s3 or not
    for j in range(1, N + 1):
        #the prefix of s2 should exactly match the prefix of s3
        ans[0][j] = ans[0][j-1] and s3[j - 1] == s2[j - 1]
    #similary, we have non-empty s1, but an empty s2
    #we check to see whether the prefix of first i characters from s1,
    #and nothing from s2, do they match first i characters of s3 or not
    for i in range(1, M+1):
        #the prefix of s1 should exactly match the prefix of s3
        ans[i][0] = ans[i-1][0] and s3[i - 1] == s1[i - 1]

    for i in range(1, M + 1):
        for j in range(1, N + 1):
            ans[i][j] = (ans[i - 1][j] and s1[i - 1] == s3[i + j - 1]) or (ans[i][j-1] and s2[j - 1] == s3[i + j - 1])
    return ans[-1][-1]



def main():
    assert isInterleave_dp(s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc") == False
    assert isInterleave_dp(s1 = "", s2 = "", s3 = "") == True


if __name__ == '__main__':
    main()
