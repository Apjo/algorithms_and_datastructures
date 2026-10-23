"""
Filename: partition_labels.py
Date: 2026-10-22
"""


class Solution:
    def partitionLabels(self, s: str) -> list[int]:
        '''
        Basically, we are dividing the substrings so that no character from one substring is present in the next
        we start by maintaining a map of the last index of each char.
        Then we maintain 2 pointers st, and end both init to 0, we always set end to be the max of end and the last occurring position of that char, when the iteration index i matches end, we then calculate our partition len, and update our start to the next index
        '''
        mp={}
        for i in range(len(s)):
            mp[s[i]] = s.rindex(s[i])

        i,st, end, res = 0,0,0,[]

        while i < len(s):
            if i == end:
                partition_len = end - st + 1
                res.append(partition_len)
                st = i + 1
            i+=1
        
        return res

        


if __name__ == '__main__':
    Solution().solve()