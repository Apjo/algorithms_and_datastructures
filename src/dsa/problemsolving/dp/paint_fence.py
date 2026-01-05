# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/2/26
"""

'''
look at the last post, we have k options to paint it
we need to know how many valid colorings are present before me, so my preceeding neighbor will provide it
so the total number of valid colorings for the first n-1 posts are depends on 
"how many of the fences of len N-1 had last 2 colors the same and 
how many of the fences of len N-1 had the last 2 colors different"  
if at i i knew the last 2 colors were different i can use all k colors, but if they were same i have to exclude that color, and use remaining k - 1 colors
We will be using 3 arrays
same[i]     = total num ways to paint posts 1..i so that posts i - 1 and i have same color
different[i]= total num ways to paint posts 1..i so that posts i - 1 and i have different color
total[i]    = total num ways to paint fences from 0 to i
'''
def solve(n:int, k: int):
    if n == 0:
        return 0
    same = [0 for i in range(n)]
    different = [0 for i in range(n)]
    total = [0 for i in range(n)]
    same[0]=0 #since we have a single post no previous post to color with same color.
    different[0]=k
    total[0] = k
    for i in range(1, n):
        #same is restrictive, in order to use the same color, that means the last post of my neighbor has to be different than its previous post, since we can't have our neigbors with same 2 colors, and me too using the same color hence we need to have preceeding neighbors to have different color
        same[i]= different[i - 1]
        different[i] = total[i - 1] * (k - 1) #to make the last post different from the previous color we take all the fences my neighbor painted and just pick a color that is different from last post
        total[i] = same[i] + different[i]
    return total[n - 1]


'''
Further simplifying the equation for total from above:
we know same[i]    = different[i-1]
, and different[i] = total[i - 1]*(k-1)
so we can consider rewriting same[i] in terms of total becomes
same[i]            = total[i-2]*(k-1)
so total[i]        = same[i] + different[i]
                   = total[i-2]*(k-1) + total[i-1]*(k-1)
                   = (total[i-2] + total[i-1])*(k-1)

'''
def solve_2(n: int, k: int) -> int:
    # if there are 0 posts, nothing to color
    if n==0:
        return 0
    # if there is exactly 1 post, we can use any of the available k colors
    if n==1:
        return k
    # if there are exactly 2 posts, we need not worry about 3+ posts having the same color, each post gets k options so k*k
    if n==2:
        return k * k
    total = [0 for _ in range(n+1)]
    total[0]=0
    total[1]=k
    total[2]=k*k
    for i in range(3, n+1):
        total[i] = (total[i - 1] + total[i - 2])*(k - 1)
    return total[n]


def main():
    ans= solve_2(n=3, k=3)
    print(ans)
    assert ans == 12
    return solve(n=3, k=2)


if __name__ == '__main__':
    main()
