# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/30/26
link https://leetcode.com/problems/generate-parentheses/
"""
from typing import List


class Solution:
    '''
    If we laid out all the parentheses we will be getting 2n such parentheses.
    At each step we make 2 choices i.e. selecting a open paren or a close paren
    so if say we finish using one of the paren in the first n places we make 2^n choices atleast for the first n places,
    but say we make use of either the open or closed for all the 2n places then we end up doing 2^2n == 4^n choices in total
    so,
    lower bound = 2^n(2 choices for first n places)
    upper bound = 4^n(2 choices for all 2n places)
    '''
    def generateParenthesis(self, n: int) -> List[str]:
        res=[]
        def solve(res, buff, op, cl):
            if op == 0 and cl == 0:
                res.append(buff)
                return
            if op > 0 :
                buff = buff+'('
                solve(res, buff, op - 1, cl)
                buff = buff[:-1]
            if cl > op:
                buff = buff+')'
                solve(res, buff, op, cl - 1)
                buff = buff[:-1]
        solve(res, "", n, n)
        return res

    def generateParenthesisValidCount(self, n: int) -> int:
        memo={}

        def solve(memo, op, cl):
            if op == n and cl == n:
                #for each valid parentheses combo, increment by 1
                return 1

            if (op, cl) in memo:
                return memo[(op, cl)]

            x,y=0,0
            if op < n:
                x = solve(memo, op + 1, cl)

            if cl < op:
                y = solve(memo, op, cl + 1)
            memo[(op, cl)] = x + y
            return memo[(op, cl)]
        return solve(memo, 0, 0)



def main():
    sol = Solution()
    print(sol.generateParenthesis(n=2))
    print(sol.generateParenthesisValidCount(n=3))
    print(sol.generateParenthesisValidCount(n=2))


if __name__ == '__main__':
    main()
