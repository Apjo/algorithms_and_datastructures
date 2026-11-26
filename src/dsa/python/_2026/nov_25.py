"""
Filename: nov_25.py
Date: 2026-11-26
"""


class Solution:
    def smallestUniqueSubarray(self, nums: list[int]) -> int:
        # binary search from 1-N
        if not nums:
            return 0
        N = len(nums)
        le, ri = 1, N
        ans = 0
        base = max(nums)
        mod = 10**9 + 7

        def my_pow(a, b):
            if a == 0:
                return 0
            if b == 0:
                return 1
            a %= mod
            if b % 2 == 0:
                res = my_pow(a, b // 2)
                return (res * res) % mod
            else:
                return (a * my_pow(a, b - 1)) % mod

        def is_present(k):
            freq_map = {}
            roll_hash = 0
            highest_pow = my_pow(base, k - 1)

            for i in range(k):
                roll_hash = (roll_hash * base + nums[i]) % mod
            freq_map[roll_hash] = 1
            for i in range(k, N):
                roll_hash = (
                    (roll_hash - nums[i - k] * highest_pow) * base + nums[i]
                ) % mod
                freq_map[roll_hash] = freq_map.get(roll_hash, 0) + 1

            return 1 in freq_map.values()

        while le <= ri:
            mi = le + ((ri - le) // 2)
            # count frequencies for every subarray of len=mid
            # if any frequency == 1, we know there exists a unique subarray, and we continue searching for even smaller one by setting ri = mid - 1
            if is_present(mi):
                ans = mi
                ri = mi - 1
            # else, continue searching for a larger len by setting le=mid + 1
            else:
                le = mi + 1
        return ans


if __name__ == "__main__":
    Solution().solve()
