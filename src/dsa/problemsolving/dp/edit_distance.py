# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/6/26
link:
Consider i have 2 strings X and Y, and we are basically considering aligning the strings pairwise.
i.e. for each character in X, we have 2 choices i.e. either the character in X got substituted, or removed.
consider the below example for clarity
X = a        _         a               a
Y = _        a         b               a
    deletion insertion substitution    match
so basically, when a mismatch occurs we could have a substitution
Say we are given 2 strings each represented
X as x1, x2, x3,..., xm
and Y as y1, y2, y3,..., yn
and we are now focussing on the last pair, what could the last pair be?
given what we know from above, we have below options(3) for aligning the characters of X and Y:
1).DELETION
xm
_
2). INSERTION
_
yn
3). SUBSTITUTION
note: in the below case we have 2 possibilities i.e. both could match or mismatch
xm
yn

or(which we will ignore, or is not relevant)
_
_
so now question is, and if we rely on previous neighbors to give me a solution for edit distance between the following:
1).DELETION
x1 x2 x3....  xm
y1 y2 y3...yn _

2).INSERTION
x1 x2 x3....xm-1 _
y1 y2 y3........ yn

3).SUBSTITUTION
note: in the below case we have 2 possibilities i.e. both could match or mismatch
x1 x2 x3...xm
y1 y2 y3...yn

so score/cost of an alignment = sum(num insertions + num deletions + num substitutions) where if its a match there is no cost
Now that we have the above choices as shown, can i say that,if the optimal edit distance corresponds to any of the above alignment,
does that mean that previous prefix of X, and Y what we have must be the optimal way to align X and Y?

f(i, j)= is the edit distance between x1..xi, and y1,,yj strings
i goes from 0 to len of word1
j goes from 0 to len of word2
        = min(f(i-1, j) + 1 for deletion in x,
              f(i, j -1) + 1 for insertion in x,
              f(i - 1, j - 1) + Z where Z=1 if x[i]!=y[j] else 0 for substitution case
The table will look like:
         y1  y2.....yn
     0   1   2 .... N
   0 0   1   2..... n (this tells us the cost of aligning entire string y with an empty string X)
x1 1 1
x2 2 2
....         .(the meaning of this number here is to calc edit distance between prefix y1..yj, and x1..xi)
xm M m  ...         f(m,n) is the answer here
(this tells us the cost of aligning entire string x with an empty string Y)


"""
#time:O(M*N)
def minDistance(word1: str, word2: str) -> int:
    if word1 == word2:
        return 0
    M, N = len(word1), len(word2)
    ans=[[0]*(N+1) for _ in range(M+1)]

    #for converting an non empty string word1 to a empty one word2
    for i in range(1, M+1):
        ans[i][0]=i

    #for converting an empty string word1 to a non-empty one word2
    for i in range(1, N+1):
        ans[0][i]=i

    for i in range(1, M+1):
        for j in range(1, N+1):
            ans[i][j] = min(ans[i - 1][j] + 1, ans[i][j - 1]+1, ans[i - 1][j - 1] + (0 if word1[i - 1]==word2[j - 1] else 1))
    return ans[M][N]




def main():
    assert minDistance("horse", "ros") == 3
    assert minDistance("a", "a") == 0


if __name__ == '__main__':
    main()
