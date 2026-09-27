"""
Filename: add_and_search_word.py
Date: 2026-07-16
"""


class WordDictionary:
    class TrieNode:
        def __init__(self):
            self.children = {}
            self.eow = False

    def __init__(self):
        self.root = self.TrieNode()

    def addWord(self, word: str) -> None:
        curr = self.root
        for ch in word:
            if ch not in curr.children:
                curr.children[ch] = self.TrieNode()
            curr = curr.children[ch]
        curr.eow = True
    
    def search(self, word: str) -> bool:
        def solve(index, curr_node):
            if index == len(word):
                return curr_node.eow
            
            curr_char = word[index]
            
            if curr_char == ".":
                #go through all the child nodes, and perform a search
                for child_node in curr_node.children.values():
                    if solve(index+1, child_node):
                        return True
                return False
            else:
                #perform regular search
                #if curr_char is not present in the trie, return False, else recurse down their child nodes
                if curr_char not in curr_node.children:
                    return False
                return solve(index + 1, curr_node.children[curr_char])
                
        return solve(0, self.root)


if __name__ == '__main__':
    Solution().solve()