# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/21/26
link:https://leetcode.com/problems/count-numbers-with-unique-digits/description/
Notes:
Another subproblem we can think of solving would be: given a number n count how many n digit numbers are there with unique digits
if we consider the rightmost bit i.e. the nth digit, we need answers to all the n-1 digits so far,
and from all the 10 available digits for the last digit total number of available digits to select from will be 10-(n-1)=11-n
say c(n) be count of n-digits, and it will be = c(n-1)*(11-n) for all n >= 2
c(n)=1 for n == 1
c(2) = 9 * (11-2) = 9 * 9, for c(2) we can't use c(1) as we cant have c(1)=10 since 0 isn't allowed as the leftmost digit in a 2-digit no, so choices in that case drop from 10 to 9

so,
for 0-digit numbers -> 1
for 1-digit numbers -> 1 + 9
for 2-digit numbers -> 1 + 9 + 9 * 9
for 3-digit numbers -> 1 + 9 + 9 * 9 + 9 * 9 * 8
for 4-digit numbers -> 1 + 9 + 9 * 9 + 9 * 9 * 8 + 9 * 9 * 8 * 7
... and so on
"""
def countNumbersWithUniqueDigits(n: int) -> int:
    if n < 0:
        return 0
    ans=[0]*(n+1)
    ans[0]=1
    ans[1]=9
    for i in range(2, n+1):
        ans[i] = ans[i - 1] * (11 - i)
    return sum(ans)

def countNumbersWithUniqueDigits_space_optimized(n: int) -> int:
    if n < 0:
        return 0
    if n == 0:
        return 1
    global_ans=10 #includes 1 number for a number with 0 digits, and 9 numbers with 1 digit
    total_only_one_digits = 9 #includes 1 - 9
    for i in range(2, n+1):
        #ans[i] = ans[i - 1] * (11 - i)
        total_only_one_digits = total_only_one_digits * (11 - i)
        global_ans+=total_only_one_digits
    return global_ans

def main():
    assert countNumbersWithUniqueDigits(2) == 91
    assert countNumbersWithUniqueDigits_space_optimized(2) == 91


if __name__ == '__main__':
    main()
