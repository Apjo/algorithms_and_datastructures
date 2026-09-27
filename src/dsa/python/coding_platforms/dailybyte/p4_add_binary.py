'''
Given two binary strings (strings containing only 1s and 0s) return their sum (also as a binary string).
Note: neither binary string will contain leading 0s unless the string itself is 0
'''

def solve(str1:str, str2:str) -> str:
    #NOTES:Rules of Binary Addition: 0 + 0 = 0, 0 + 1 = 1, 1 + 0 = 1, and 1 + 1 = 10 (0 with a carry-over of 1).
    #try1
    #convert str1 and str2 to their respective binary integer representation with base=2
    #add them
    #convert back the result of addtion to string
    #try2:
    #apply 2 pointers from R to L on both strings, and add them 1 at a time
    p1 = len(str1) - 1
    p2 = len(str2) - 1
    res = []
    carry = 0
    while p1 >= 0 and p2 >= 0 or carry == 1:
        if p1 >=0:
            carry += int(str1[p1])
            p1-=1
        if p2 >=0:
            carry += int(str2[p2])
            p2-=1

        res.append(str(carry % 2))
        carry = carry // 2

    while p1 >= 0:
        if p1 >=0:
            carry += int(str1[p1])
            p1-=1
        res.append(str(carry % 2))
        carry = carry // 2

    while p2 >= 0:
        if p2 >=0:
            carry += int(str2[p2])
            p2-=1
        res.append(str(carry % 2))
        carry = carry // 2

    return "".join(res[::-1])


def main():
    assert solve("100", "1") == "101"
    assert solve("11", "1") == "100"
    assert solve("1", "0") == "1"


if __name__ == '__main__':
    main()
