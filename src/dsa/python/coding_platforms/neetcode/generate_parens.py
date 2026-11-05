"""
Filename: generate_parens.py
Date: 2026-07-09
"""

from typing import List

class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        # time: O(2^2N) = O(4^N) since at each char we have 2 choices to make (, or ) which means there are a total of 2^2n unique strings
        #space:O(n) max depth of recursion which is 2n, as each call adds a open paren or a close paren, and total parens added is 2n, hence atmost n levels of recursion will be created, and each level consumes constant amount of space
        def solve(op, cl, buff, res):
            if op == 0 and cl == 0:
                res.append(buff)
                return
            if op > 0:
                buff+="("
                solve(op - 1, cl, buff, res)
                buff=buff[:-1]
            if cl > op:
                buff += ")"
                solve(op, cl - 1, buff, res)
                buff = buff[:-1]
        
        res: List[str]= []
        buff=""

        solve(n, n, buff, res)
        
        return res

        


if __name__ == '__main__':
    Solution().generateParenthesis(n=2)