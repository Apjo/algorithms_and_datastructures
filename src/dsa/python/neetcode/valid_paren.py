class Solution:
    def isValid(self, s: str) -> bool:
        stk=[]
        for cc in s:
            if cc in ['(', '[', '{']:
                stk.append(cc)
            else:
                if stk:
                    curr=stk[-1]
                    if curr=='(' and cc == ')' or curr=='[' and cc == ']' or curr=='{' and cc == '}':
                        stk.pop()
                    else:
                        #could be only closing paren in the input
                        return False
                else:
                    
                    return False
        return len(stk) == 0