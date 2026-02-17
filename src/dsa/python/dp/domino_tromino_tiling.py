#link: https://leetcode.com/problems/domino-and-tromino-tiling/description/
'''
Notes:
Using a decrease and conquer approach, we focus on reducing problem of size n to n - 1.
We reduce tiling of 2 by n board to 2 by n - 1 or smaller board. We focus on the nth columns, and ask "how could the last col be tiled?"
options:
Let us define F(n) for a perfectly rectangular 2 by n board to be the number of ways to tile a 2 by n board
 - just use a vertical tile, then solution to remaining subproblem would be f(n-1)
 - or use 2 horizontal dominos, then solution to remaining subproblem would be f(n-2)
 - if we were to use a tromino(of the form L), then the remaining piece of the board isn't a rectangle, so say we call it L(n)
    where L(n)= no. of tiling for a 2 * n board  + an extra lower square(of len n - 1)
    which then brings to solution to remaining subproblems to be L(n - 2)
- if we were to use a tromino(of the form |--), then the remaining piece of the board isn't a rectangle, so say we call it U(n)
    where U(n)= no. of tiling for a 2 * n board  + an extra upper square(of len n - 1)
    which then brings to solution to remaining subproblems to be U(n - 2)
- so F(n) = F(n - 1) + F(n-2) + L(n-2) + U(n-2)
but, L(n) and U(n) are similar because of a 1:1 mapping, i.e. if we were to flip the board for L(n) we would end up having the problem for U(n), so equating L(n)=U(n)
we rewrite
F(n) = F(n - 1) + F(n-2) + 2 * L(n-2)  -- eq(1)
but note we still haven't gotten the expression for L(n), so now we are working on a new subproblem where
L(n) is the number of ways to tile a 2 by n board with an extra square at the bottom
so yet again options for tiling the rightmost lower squareare:
- use a tromino(L) then the solutions to remaining subproblems will be (as we will now have a perfect rectangle with n - 1 columsn) = f(n-1)
- or use a horizontal domino, then solutions to remaining subproblems will be U(n-1) but we argued U(n)==L(N) so  == L(n-1)
finally we get L(n) = F(n-1) + L(n-1) -- eq(2)
so we have a system of 2 recurrence equations

say we substitute L(n-2) using the eq(2)

from eq(2) we get L(n-2) = f(n-3) + L(n-3) --eq(3)

so now we have eq(1) as F(n) = F(n - 1) + F(n-2) + 2 * L(n-2) using eq(3) in this we get
F(n) = F(n - 1) + F(n - 2) + 2 * (F(n-3) + L(n-3)) --eq(4)
     = F(n - 1) + F(n - 2) + 2 * F(n-3) + 2 * L(n-3)
     = rearranging terms
     = F(n - 1) + F(n - 2) + 2 * L(n-3) + 2 * F(n-3)
     = F(n - 1) + F(n - 2) + F(n - 3) + 2 * L(n-3) + F(n-3)
     = F(n - 1) + F(n - 1) + F(n - 3)  --from eq(1) i.e using F(n-1) = F(n - 2) + F(n - 3) + 2L(n - 3)

F(n) = 2 * F(n-1) + F(n - 3) --eq(4)
so we will need 3 base cases as n depends on n - 3
we know n=1 we have output as 1, n=2 -> 2, n=3 -> 5

'''
#time and space O(N) can be brought down to constant values as we need only last 3 values
def solve_simplified(n):
    if n == 0:
        return 0
    if n == 1 or n == 2:
        return n
    if n == 3:
        return 5
    ans=[0]*(n + 1)
    ans[1]=1
    ans[2]=2
    ans[3]=5
    for i in range(4, n + 1):
        #for space optimized ans[i % 4] = (2 * ans[(n - 1) % 4] + ans[(n - 3) % 4]) % 1000000007
        ans[i] = (2 * ans[n - 1] + ans[n - 3]) % 1000000007
    #return ans[n % 4]
    return ans[n] % 1000000007

def solve(n):
    #solved using multiple tables
    '''
    ftable=1d arr of size n where f(1)=1, f(2)=2
where F[n] is the no.of ways to tile an 1*n board.

L table= A table corresponding to a 2*n table ending with a extra square(col) at last row |-
L(n): no. of ways to tile a board of size 2*n with an extra square at the bottom level

U table = A table corresponding to a 2*n table ending with an extra square(col) in first row |-
U(n): no. of ways to tile a board of size 2*n with an extra square at the upper level
So, we have

F(n) = F(n - 1) + F(n-2) + L(n-2) + U(n-2)
L(n) = F(n - 1) + U(n-1)
U(n) = F(n - 1) + L(n - 1)

Now start filling all the 3 tables
for i in range 3 to N:
 ftable[i] = ftable[i - 1]+ftable[i-2]+ltable[i-2] + utable[i - 2]
 ltable[i] = ftable[i - 1] + utable[i - 1]
 utable[i] = ftable[i - 1] + ltable[i - 1]
    :return ftable[n]
    :return:
    '''
    if n <= 1:
        return n
    f_table=[0]*(n + 1)
    f_table[1]=1
    f_table[2]=2

    l_table=[0]*(n + 1)
    l_table[1]=1
    l_table[2]=2

    u_table=[0]*(n + 1)
    u_table[1]=1
    u_table[2]=2

    for i in range(3, n + 1):
        f_table[i] = (f_table[i - 1] + f_table[i-2] + l_table[i-2] + u_table[i - 2]) % 1000000007
        l_table[i] = (f_table[i - 1] + u_table[i - 1])% 1000000007
        u_table[i] = (f_table[i - 1] + l_table[i - 1])% 1000000007

    return f_table[n]


def main():
    return solve()
    assert solve_simplified()


if __name__ == '__main__':
    main()
