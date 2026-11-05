"""
Filename: decode_ways.py
Date: 2026-08-22
"""


class Solution:
    # recursion to pick or not pick on decoding. time: O(2^N)
    def numDecodings(self, s: str) -> int:
        def solve(idx):
            if idx == 0:
                if s[idx] == "0":
                    return 0
                else:
                    return 1
            if idx < 0:
                return 1
            # idx is no longer a 0, but we can have a 0 anywhere, and we shouldn't be treating as a valid 1 digit
            one_d = 0
            if s[idx] != "0":
                one_d = solve(idx - 1)

            curr = int(s[idx]) - int("0")
            prev = int(s[idx - 1]) - int("0")
            f_num = int(str(prev) + "" + str(curr))

            two_d = 0

            if 10 <= f_num <= 26:
                two_d = solve(idx - 2)

            return one_d + two_d

        return solve(len(s) - 1)

    # memoized version
    # Each index (0 to N-1) is computed exactly once. After that, it's a cache hit. For each index, you do O(1) work.
    # time: O(N), space:O(N)
    def numDecodings_memo(self, s: str) -> int:
        def solve(idx):
            if idx == 0:
                if s[idx] == "0":
                    return 0
                else:
                    return 1
            if idx < 0:
                return 1
            # idx is no longer a 0, but we can have a 0 anywhere, and we shouldn't be treating as a valid 1 digit
            one_d = 0

            if idx in memo:
                return memo[idx]

            if s[idx] != "0":
                one_d = solve(idx - 1)

            curr = int(s[idx]) - int("0")
            prev = int(s[idx - 1]) - int("0")
            f_num = int(str(prev) + "" + str(curr))

            two_d = 0
            # the number has to be a valid between 10-26
            if 10 <= f_num <= 26:
                two_d = solve(idx - 2)

            memo[idx] = one_d + two_d

            return memo[idx]

        memo = {}
        return solve(len(s) - 1)

    # bottom up DP
    def numDecodings_dp(self, s: str) -> int:
        # f(i) = The number of ways to decode s[0:i] (first i characters).
        # we pick i - 1 or i - 2, more formally
        # f(i) = f(i - 1) if element at i isn't a 0 or is a valid number +
        #        + f(i - 2) if elements at i - 1, and i - 2 form a valid number between 10-26
        
        f = [0] * (len(s) + 1)
        f[0] = 1  # there is only 1 way to decode NOTHING.
        f[1] = 1 if s[0] != "0" else 0
        for i in range(2, len(s) + 1):
            if s[i - 1] != "0":
                f[i] += f[i - 1]
            # extract the 2 digits
            #When you're at loop index i (where you're computing f[i])
            # f[i] represents ways to decode the first i characters = s[0:i]
            # The character you're currently deciding about is s[i-1] (the i-th character, 0-indexed)
            # The two-digit code you're checking is s[i-2:i] (characters at positions i-2 and i-1)
            second_digit = int(s[i - 1]) - int("0")
            first_digit = int(s[i - 2]) - int("0")
            final_number = int(str(first_digit) + "" + str(second_digit))
            if 10 <= final_number <= 26:
                f[i] += f[i - 2]

        return f[len(s)]


if __name__ == "__main__":
    Solution().solve()
