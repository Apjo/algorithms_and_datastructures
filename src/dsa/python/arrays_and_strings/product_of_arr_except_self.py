"""
Filename: product_of_arr_except_self.py
Date: 2026-09-29
"""


class Solution:
    # using 1 extra output array
    def productExceptSelf_optimizedspace(self, nums: list[int]) -> list[int]:
        N = len(nums)
        multiplier = 1
        ans = [1] * (N)
        for i in range(N):
            ans[i] *= multiplier
            multiplier *= nums[i]
        multiplier = 1
        for i in range(N - 1, -1, -1):
            ans[i] *= multiplier
            multiplier *= nums[i]

        return ans

    # using separate pref, suff arrays, note we use a[i-1], and a[i+1] on purpose to multiply!
    def productExceptSelf_extraspace(self, nums: list[int]) -> list[int]:
        N = len(nums)
        pref = [0] * (N)
        pref[0] = 1
        suff = [0] * (N)
        suff[N - 1] = 1
        for i in range(1, N):
            pref[i] = pref[i - 1] * nums[i - 1]
        for i in range(N - 2, -1, -1):
            suff[i] = suff[i + 1] * nums[i + 1]

        ans = [0] * (N)
        for i in range(N):
            ans[i] = pref[i] * suff[i]

        return ans


if __name__ == "__main__":
    Solution().solve()
