# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/14/26
"""
#recursive approach, time: O(2^N)
def numDecodings(s: str) -> int:
    def solve(arr, index) -> int:
        if index >= len(arr):
            return 1
        if arr[index] == '0':
            return 0
        #single digits that are > 0 are all valid and will contribute 1
        one_val = solve(arr, index + 1)
        #now we consider pairs, but these pairs need to be 10 <= pair <=26
        curr_str = arr[index : index + 2]
        curr_num = int(curr_str)
        pair_val = 0
        if 10 <= curr_num <= 26:
            pair_val = solve(arr, index + 2)
        return one_val + pair_val
    return solve(s, 0)

#using DP
def solve(s: str):
    N = len(s)
    ans=[0]*(N + 1)
    print(ans)
    ans[0]=1
    ans[1] = 1 if s[0] != '0' else 0
    for i in range(2, N + 1):
        ans[i] = 0
        if s[i - 1] != '0':
            ans[i] = ans[i] + ans[i - 1]
        digit = int(s[i - 1])
        prev_digit = int(s[i - 2])
        if prev_digit == 1 or prev_digit == 2 and (0 <= digit <= 6):
            ans[i] = ans[i] + ans[i - 2]
    return ans[-1]

def main():
    assert solve("26") == 2
    return solve("226") == 3


if __name__ == '__main__':
    main()
