"""
Filename: sum_two_ints.py
Date: 2026-11-07
"""


class Solution:
    def getSum(self, a: int, b: int) -> int:
        MASK = 0xFFFFFFF
        """
        In languages like C++ or Java, a carry bit simply falls off the edge after 32 shifts because the integer runs out of space. But in Python, numbers can grow infinitely large. If you add negative numbers, the carry bit can keep shifting left forever, trapping you in an infinite loop.
        """
        # simulates fix 32 bit env.
        for _ in range(32):
            if not b:
                break
            # gives sum without carry
            partial_sum = a ^ b
            # finds the carry, and left shifts by 1. In Python, negative numbers have effectively infinite leading 1 bits, so the carry may keep shifting forever.

            carry = (a & b) << 1
            a = partial_sum
            b = carry
        # if b is still != 0 we mask to keep only lower 32 bits
        if b:
            return a & MASK
        return a


if __name__ == "__main__":
    Solution().solve()
