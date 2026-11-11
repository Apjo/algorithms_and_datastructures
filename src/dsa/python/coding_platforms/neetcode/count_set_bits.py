"""
Filename: count_set_bits.py
Date: 2026-11-05
"""


class Solution:
    def hammingWeight(self, n: int) -> int:
        ctr = 0
        while n > 0:
            # if n has a set bit at the ith position the result is positive, else its a 0. We increment ctr if result is positive
            if n & 1:
                ctr += 1
            # shifting n by 1 to right moves us to next bit
            n >>= 1
            # ctr+=1
        return ctr


if __name__ == "__main__":
    Solution().solve()
