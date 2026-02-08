# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/7/26
link
"""
from typing import List
#recursion
def diffWaysToCompute(expression: str) -> List[int]:
    #split on operator, if its a digit, ignore, if the entire expression is a digit, return the int form of it inside a list
    '''
    Function returns: List[int] (all possible values)
    For each operator in expression:
        Split expression → left | operator | right
        Recurse on left → list L
        Recurse on right → list R
        Combine via cartesian product (for a in L, for b in R)
    Base case:
        If expression is a pure number → return [number]
    time:
        exponential in terms of number of operators
    '''
    if expression.isdigit():
        return [int(expression)]
    if not expression:
        return []
    res=[]
    for i in range(len(expression)):
        if expression[i].isdigit():
            continue
        left_expr_values = diffWaysToCompute(expression[: i])
        right_expr_values = diffWaysToCompute(expression[i + 1])
        if expression[i] == '+':
            for a in left_expr_values:
                for b in right_expr_values:
                    res.append(a + b)
        if expression[i] == '-':
            for a in left_expr_values:
                for b in right_expr_values:
                    res.append(a - b)
        if expression[i] == '*':
            for a in left_expr_values:
                for b in right_expr_values:
                    res.append(a * b)
    return res

#memoization
#time: O(N*C(N)) C(n) = nth catalan number
def diffWaysToCompute_memoized(expression: str) -> List[int]:
    memo={}
    if expression.isdigit():
        return [int(expression)]
    if not expression:
        return []

    def solve(expr):
        res=[]
        if expr.isdigit():
            return [int(expr)]
        if expr in memo:
            return memo[expr]
        for i in range(len(expr)):
            if expr[i].isdigit():
                continue
            left_expr = expr[: i]
            right_expr = expr[i+1:]
            left_expr_values = solve(left_expr)
            right_expr_values = solve(right_expr)
            if expr[i] == '+':
                for a in left_expr_values:
                    for b in right_expr_values:
                        res.append(a + b)
            if expr[i] == '-':
                for a in left_expr_values:
                    for b in right_expr_values:
                        res.append(a - b)
            if expr[i] == '*':
                for a in left_expr_values:
                    for b in right_expr_values:
                        res.append(a * b)
        memo[expr] = res
        return res
    return solve(expression)


def main():
    return solve()


if __name__ == '__main__':
    main()
