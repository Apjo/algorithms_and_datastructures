#link: https://neetcode.io/problems/evaluate-reverse-polish-notation/question
from typing import List
class Solution:
    def evalRPN(self, tokens: List[str]) -> int:
        stk=[]
        for token in tokens:
            if token == "+":
                a, b = stk.pop(), stk.pop()
                stk.append(a + b)
            elif token == "-":
                a, b = stk.pop(), stk.pop()
                stk.append(b - a)
            elif token == "*":
                a, b = stk.pop(), stk.pop()
                stk.append(b * a)
            elif token == "/":
                a, b = stk.pop(), stk.pop()
                stk.append(int(float(b) / a))
            else:
                stk.append(int(token))
        return stk[0]
