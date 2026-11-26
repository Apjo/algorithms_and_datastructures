"""
Filename: nov_24.py
Date: 2026-11-26
"""


class Solution:
    def maxLength(self, nums: list[int]) -> int:
        def gcd(a: int, b: int) -> int:
            if b == 0:
                return a
            return gcd(b, a % b)

        def lcm(a, b):
            if b == 0:
                return a
            return (a * b) // (gcd(a, b))

        # every subarray of len=2 is prod equivalent, so max len needs to be atleast 2
        ans, le = 2, 0

        N = len(nums)

        if not nums:
            return 0

        curr_prod = 1

        for i in range(N):
            print(f"curr product={curr_prod} at i={i}, num={nums[i]}")
            while gcd(curr_prod, nums[i]) > 1:
                curr_prod //= nums[le]
                le += 1
            curr_prod *= nums[i]
            ans = max(ans, i - le + 1)

        return ans


if __name__ == "__main__":
    Solution().solve()
