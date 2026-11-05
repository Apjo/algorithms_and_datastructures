"""
Filename: plus_one.py
Date: 2026-11-01
"""


class Solution:
    '''
    Basically, simulate from rear end of the input array while making sure the first case to add a 1 to the last digit is handled at the beginning.
    '''
    def plusOne(self, digits: list[int]) -> list[int]:
        N = len(digits)
        carry = 0
        res = []
        i = N - 1

        while i >= 0:
            _sum = 0

            if i == N - 1:
                _sum = digits[i] + 1
            else:
                _sum = digits[i] + carry

            if _sum >= 10:
                carry = 1
                res.append(_sum % 10)
            else:
                carry = 0
                res.append(_sum)

            i -= 1

        if carry == 1:
            res.append(1)

        res.reverse()

        return res


if __name__ == "__main__":
    Solution().solve()
