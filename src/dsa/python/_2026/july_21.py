"""
Filename: july_21.py
Date: 2026-07-28
"""


class Solution:
    def maxActiveSectionsAfterTrade(self, s: str) -> int:
        # find number of 1s
        # find count of consecutive blocks of 0s before seeing a 1
        # then find count of consecutive blocks of 0s after this 1
        # then update max=(currmax, block1+block2)
        # get the count = max+count of 1s
        # return the count
        curr_max, i, count1s, block1, block2 = 0, 0, 0, 0, 0
        N = len(s)
        while i < N:
            if s[i] == "0":
                block1 += 1
                i += 1
            else:
                while i < N and s[i] == "1":
                    count1s += 1
                    i += 1
                while i < N and s[i] == "0":
                    block2 += 1
                    i += 1
                if block1 != 0 and block2 != 0:
                    curr_max = max(curr_max, block1 + block2)
                block1 = block2
                block2 = 0
        return count1s + curr_max
        


if __name__ == '__main__':
    Solution().solve()