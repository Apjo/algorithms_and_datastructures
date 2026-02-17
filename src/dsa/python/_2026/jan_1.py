# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/5/26
"""
from typing import List


def plusOne(digits: List[int]) -> List[int]:
    ans=[]
    N, carry = len(digits), 0

    #check for last digit before looping?
    curr_sum = digits[N - 1] + 1
    if curr_sum >= 10:
        carry=1
        ans.insert(0, curr_sum % 10)
    else:
        carry=0
        ans.insert(0, curr_sum)
    for itr in range(N - 2, -1 , -1):
        curr_sum = digits[itr] + carry
        if curr_sum >= 10:
            carry=1
            ans.insert(0, curr_sum % 10)

        else:
            carry=0
            ans.insert(0, curr_sum)
    if carry==1:
        ans.insert(0, 1)
    return ans


def main():
    return plusOne([1,2,3])


if __name__ == '__main__':
    main()
