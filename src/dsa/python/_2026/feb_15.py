# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/17/26
link: https://leetcode.com/problems/add-binary/description/?envType=daily-question&envId=2026-02-17
"""

class Solution:
    def addBinary(self, a: str, b: str) -> str:
        ss=""
        m,n, padding=len(a), len(b), 0
        def solve(sa, sb):
            # print(f"Working on s1={sa}, and s2={sb}")
            itr = len(sa) - 1
            carry=0
            ll=[]
            while itr >= 0 or carry == 1:
                if itr >= 0:
                    # xa = sa[itr]
                    # xb = sb[itr]
                    carry+=int(sa[itr])
                    carry+=int(sb[itr])
                    itr-=1
                ll.append(str(carry % 2))
                carry = carry // 2
            return "".join(ll[::-1])
        if m > n:
            padding = m - n
            #pad b with num of [padding] zeros

            b = b.zfill(m)
            # b = f"{b: 0>m}"
            # print(f"new str b={b}")
            # return solve(a, new_b)
        elif n > m:
            padding = n - m
            #pad a with num of [padding] zeros
            a = a.zfill(n)
            # print(f"new str a={a}")
            # return solve(new_a, b)

        return solve(a, b)
def main():
    return solve()


if __name__ == '__main__':
    main()
