# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/17/26
link https://leetcode.com/problems/longest-balanced-substring-ii/description/?envType=daily-question&envId=2026-02-17

"""

class Solution:
    def longestBalanced(self, s: str) -> int:
        ans, N = 0,len(s)
        def get_single_char_freq(ch):
            max_len=0
            cnt=0
            for i in range(N):
                if s[i] != ch:
                    max_len=max(max_len, cnt)
                    cnt=0
                    continue
                else:
                    cnt+=1
            max_len=max(max_len, cnt)
            return max_len

        def get_two_char_freq(ch1, ch2):
            max_len= 0
            i=0
            while i < N:
                while i < N and s[i] not in (ch1, ch2):
                    i += 1
                mp = {0: i - 1}
                cnt = 0
                while i < N and s[i] in (ch1, ch2):
                    cnt += 1 if s[i] == ch1 else -1
                    if cnt in mp:
                        max_len = max(max_len, i - mp[cnt])
                    else:
                        mp[cnt] = i
                    i += 1
            return max_len

        def get_three_char_freq():
            mp = {(0, 0): -1}
            cnt = [0, 0, 0]
            max_len = 0
            for i, c in enumerate(s):
                cnt[ord(c) - ord('a')] += 1
                ab = cnt[0] - cnt[1]
                bc = cnt[1] - cnt[2]
                key = (ab, bc)
                if key in mp:
                    max_len = max(max_len, i - mp[key])
                else:
                    mp[key] = i
            return max_len

        #calc. max result when only char a
        ans=max(ans, get_single_char_freq('a'))
        #calc. max result when only char b
        ans=max(ans, get_single_char_freq('b'))
        #calc. max result when only char c
        ans=max(ans, get_single_char_freq('c'))
        #calc. max result when only chars are ab
        ans=max(ans, get_two_char_freq('a', 'b'))
        #calc. max result when only chars are bc
        ans=max(ans, get_two_char_freq('b', 'c'))
        #calc. max result when only chars are ac
        ans=max(ans, get_two_char_freq('a', 'c'))
        #calc. max result when all chars are present abc
        ans=max(ans, get_three_char_freq())
        return ans

def main():
    return solve()


if __name__ == '__main__':
    main()
