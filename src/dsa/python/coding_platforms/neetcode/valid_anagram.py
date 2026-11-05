# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/19/26
"""

class Solution:
    #time:O(n), space: O(1)
    def isAnagram(self, s: str, t: str) -> bool:
        arr=[0]*26
        for i in range(len(s)):
            arr[ord(s[i]) - ord('a')]+=1
        for i in range(len(t)):
            arr[ord(t[i]) - ord('a')]-=1
        return all(item == 0 for item in arr)
def main():
    return Solution().isAnagram("abcde", "edbca")


if __name__ == '__main__':
    main()
