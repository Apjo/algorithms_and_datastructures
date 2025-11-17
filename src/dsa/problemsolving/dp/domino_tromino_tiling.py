#link: https://leetcode.com/problems/domino-and-tromino-tiling/description/

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


if __name__ == '__main__':
    main()
