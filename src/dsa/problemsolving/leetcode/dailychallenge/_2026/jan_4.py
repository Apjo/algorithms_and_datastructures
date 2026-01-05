# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/5/26
"""

from typing import List

class Solution:
    def sumFourDivisors(self, nums: List[int]) -> int:
        def num_divisors(n: int):
            lim = int(n**0.5)
            all_divs=set()
            for i in range(1, lim + 1):
                if n % i == 0:
                    all_divs.add(i)
                    all_divs.add(n // i)
                if len(all_divs) > 4:
                    return 0
            if len(all_divs) < 4:
                return 0
            return sum(all_divs)
        ans=0
        for num in nums:
            total_divisors = num_divisors(num)
            ans+=total_divisors

        return ans


def main():
    sol = Solution()
    return sol.sumFourDivisors([21,2,4,7])


if __name__ == '__main__':
    main()
