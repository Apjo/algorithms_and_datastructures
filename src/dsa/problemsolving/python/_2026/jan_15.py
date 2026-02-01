# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 1/14/26
"""

from typing import List

class Solution:
    def maximizeSquareHoleArea(self, n: int, m: int, hBars: List[int], vBars: List[int]) -> int:
        def find_consecutive_sequences(data):
            if not data: return []
            streak=1
            ans=1
            for i in range(1, len(data)):
                if data[i] == data[i-1] + 1:
                    streak+=1
                else:
                    streak=1
                ans=max(ans, streak)
            return ans + 1
        # h_longest_consecutive_seq = []
        # v_longest_consecutive_seq = []

        hs1= sorted(hBars)
        # print(hs1)
        vs1 = sorted(vBars)
        # print(vs1)

        v_longest_consecutive_seq = find_consecutive_sequences(vs1)
        #print(v_longest_consecutive_seq)
        h_longest_consecutive_seq = find_consecutive_sequences(hs1)
        #print(h_longest_consecutive_seq)

        # hx,hy = h_longest_consecutive_seq[0],h_longest_consecutive_seq[-1]
        # vx,vy = v_longest_consecutive_seq[0], v_longest_consecutive_seq[-1]
        sq_len = min(v_longest_consecutive_seq, h_longest_consecutive_seq)
        return sq_len**2


def main():
    return solve()


if __name__ == '__main__':
    main()
