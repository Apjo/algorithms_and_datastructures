"""
Filename: list_of_divisors.py
Date: 2026-09-12
https://beyondctci.dev/editor?problem=list-of-divisors
"""

import math

class Solution:

    # time:O(sqrt(n)), space:O(sqrt(n))
    def list_of_divisors(self, n):
        # find factors upto sqrt(n), as n = a*b, and one of the smaller factors say a <= sqrt(n), the other b=n/a is larger than sqrt(n)
        # so by iterating from 1 to sqrt(n) we find one of the 2 factors i as n // i
        large, small = [], []
        curr_sqrt = int(math.sqrt(n))
        for i in range(1, curr_sqrt + 1):
            if n % i == 0:
                # perfect square case
                if i == n // i:
                    small.append(i)
                else:
                    small.append(i)
                    large.append(n // i)

        return large + small


if __name__ == '__main__':
    Solution().solve()