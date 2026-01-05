# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/5/26
link: https://leetcode.com/problems/integer-break/description/
Problem:
Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.
Return the maximum product you can get.
This is similar to the rod cutting problem. Here to we ask what could be the last piece be?
The last possible piece could be of len=1,2,3,..,n
so f(n)= max product obtained from 1..n
In general f(i)= max possible product obtainable from rod of len=i
The overall rod must be broken up once(as per requirements), but the smaller pieces need not in general
"""


def solve(n: int) -> int:
    if n == 2:
        return 1
    ans=[0] * (n + 1)
    ans[1] = 1
    ans[2] = 2 # this is the case when we have a subproblem whose len == 2 in this case we need to further split the rod len, the above case handles when the rod itself if of len=2
    for i in range(3, n + 1):
        if i == n:#case when we reach len equal to the rod's len, and we know we have to cut the rod atleast once so we won't consider it remaining at len=n
            #we can pick either 1 or n-1
            ans[i] = n - 1
        else:
            #else, if the rod was not cut at all, this would be default
            ans[i] = i
        for cutlen in range(1, i + 1):
            ans[i] = max(ans[i], ans[i - cutlen] * ans[cutlen])
    return ans[n]


def main():
    assert solve(10) == 36


if __name__ == '__main__':
    main()
