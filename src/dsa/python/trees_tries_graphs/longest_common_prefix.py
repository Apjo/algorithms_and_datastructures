"""
Filename: longest_common_prefix.py
Date: 2026-11-12
"""


class MyTrie:
    class TrieNode:
        def __init__(self):
            self.children = [None] * 10
            self.is_end = False

    def __init__(self):
        self.root = self.TrieNode()

    def insert(self, word):

        if not self.root:
            return

        temp = self.root

        for ch in word:
            idx = ord(ch) - ord("0")
            if temp.children[idx] is None:
                temp.children[idx] = self.TrieNode()
            temp = temp.children[idx]

        temp.is_end = True

    def check_prefix(self, word):
        if not self.root:
            return 0
        temp = self.root
        i = 0
        while i < len(word):
            idx = ord(word[i]) - ord("0")
            if temp.children[idx] is None:
                break
            else:
                temp = temp.children[idx]
                i += 1
        return i


class Solution:
    def longestCommonPrefix(self, arr1: list[int], arr2: list[int]) -> int:
        # add all prefixes of arr1 into a hashset
        # for all prefixes of arr2 figure out which exists in the hashset
        # instead of a hashset we could use a Trie and do the same thing as we did with the hashset impl
        trie_root = MyTrie()
        for num in arr2:
            trie_root.insert(str(num))
        ans = float("-inf")
        for num in arr1:
            ans = max(ans, trie_root.check_prefix(str(num)))
        return ans


if __name__ == "__main__":
    Solution().solve()
