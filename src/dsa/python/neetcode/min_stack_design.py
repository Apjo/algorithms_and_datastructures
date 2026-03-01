#link: https://neetcode.io/problems/minimum-stack/question
class MinStack:
    def __init__(self):
        self.s1 = []
        self.min_stk = []
    
    def push(self, val) -> None:
        self.s1.append(val)
        self.min_stk.append(min(val, self.min_stk[-1]) if self.min_stk else val)
    
    def pop(self) -> None:
        self.s1.pop()
        self.min_stk.pop()
    
    def top(self) -> int:
        return self.s1[-1]
    
    def getMin(self) -> int:
        return self.min_stk[-1]