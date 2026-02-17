# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/1/26
link: https://leetcode.com/problems/unique-binary-search-trees/description/
decrease and conquer:
- take responsibility of only determining the root, we can pick any numbers between 1 to n as the root, then we hire someone
to figure out what are the options for constructing the left subtree, and hire someone to construct right subtree
- A general choice in the middle weill pick i as the root, that means the left subtrees will contain values from 1 to i - 1, and right would contain i+1 to n
- so we can define f(n) as number of structurally unique bsts built out of n consecutive integers
so the recurrence would be:
f(n) = sum(f(i-1)*f(n-i)) where i varies from 1 to n
and if we were to expand the f(n) we would see something like
f(n) = f(0)f(n-1)+f(1)f(n-2)+f(2)f(n-3) +...+ f(n-1)f(0)
f(0)=1
so f(1) = f(0)*f(0) = 1*1

"""


def solve():
    return None


def main():
    return solve()


if __name__ == '__main__':
    main()
