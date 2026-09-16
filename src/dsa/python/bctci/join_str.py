"""
Filename: join_str.py
Date: 2026-09-14
link: https://beyondctci.dev/editor?problem=string-join
"""


class Solution:
    def join(self, arr, s):
        if len(arr) == 0:
            return ""
        N = len(arr)
        res = []
        for i in range(N):
            if i != N - 1:
                res.append(arr[i])
                res.append(s)
            else:
                res.append(arr[i])
        return "".join(res)


if __name__ == "__main__":
    Solution().solve()
