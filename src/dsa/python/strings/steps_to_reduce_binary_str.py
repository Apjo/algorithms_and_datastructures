"""
Filename: steps_to_reduce_binary_str.py
Date: 2026-05-31
"""


def solve(s: str) -> int:
    ans=0
    num = int(s, 2) #ask interviewer if this is ok, else you will have to implement this!
    while num > 1:
        if num % 2 == 1:
            num+=1
        else:
            num//=2
        ans+=1

    return ans

if __name__ == '__main__':
    assert solve("1101") == 6
    assert solve("10") == 1
    assert solve("1") == 0
