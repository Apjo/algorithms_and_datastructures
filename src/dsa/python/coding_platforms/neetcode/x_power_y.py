"""
Filename: x_power_y.py
Date: 2026-11-01
"""


class Solution:
    """
    We halve the exponent at each step, and square the base accordingly.
    """

    # time: O(log N), space: O(log N)
    def myPow(self, x: float, n: int) -> float:
        def solve(a, b):
            if a == 0:
                return 0
            if b == 0:
                return 1
            # if b is even then its a^b = a^2 ^ (b // 2)
            if b % 2 == 0:
                res = solve(a, b // 2)
                return res * res
            # else if b is odd then its a^b = a * a^2 ^ b - 1
            else:
                return a * solve(a, b - 1)

        # we handle negative powers as, pass in the exponent as positive, and get the result, if exponent >= 0 return result as is, else return 1 / result
        ans = solve(x, abs(n))
        if n >= 0:
            return ans
        else:
            return 1 / ans


if __name__ == "__main__":
    Solution().solve()
