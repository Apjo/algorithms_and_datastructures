class PrefixTree:
    class TrieNode:
        def __init__(self):
            self.children = {}  # basically, a map of Character -> TrieNode
            self.is_end_of_word = False

        def __str__(self):
            return f"{self.children}"

    def __init__(self):
        self.root = self.TrieNode()

    def insert(self, word: str) -> None:
        curr = self.root
        print(f"Trying to insert word={word}")
        for ch in word:
            # if the child node exists, add this ch as a child node to this node
            curr_child = curr.children.get(ch, None)
            # If node for current character does not exist then make a new node
            if curr_child is None:
                print(f"no children for node={ch}")
                # Keep the reference for the newly created node
                curr.children[ch] = self.TrieNode()
            # Move the curr pointer to the newly created node
            curr = curr.children[ch]
        # finally, set this word's end of word to be True
        curr.is_end_of_word = True

    def search(self, word: str) -> bool:
        curr = self.root
        # print(f"searching for word={word}")
        for ch in word:
            # print(f"getting children for node={ch}")
            curr_child = curr.children.get(ch, None)
            # print(f"current children={curr_child}")
            if curr_child is None:
                return False
            curr = curr.children[ch]
        return curr.is_end_of_word

    def startsWith(self, prefix: str) -> bool:
        curr = self.root
        # print(f"searching for string starting with={prefix}")
        for ch in prefix:
            curr_child = curr.children.get(ch, None)
            # print(f"current children for node={ch} is {curr_child}")
            if curr_child is None:
                return False
            curr = curr.children[ch]
        return True
