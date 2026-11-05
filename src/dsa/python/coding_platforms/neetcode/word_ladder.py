"""
Filename: word_ladder.py
Date: 2026-08-08
"""

from collections import deque
from typing import List


class Solution:
    """
    Total visited nodes N
    each word has a length of say L
    each word iterates through all 26 letters for the L times so 26*L ~ L
    total time: (N*L^2) if we convert the wordList to a set! for O(1) lookup
    space:O(N*L) ?
    The wordList set: N words * L characters
    The visited set: N words * L characters
    The bfs_q: N words * L characters
    Since we drop constants in Big-O notation, 3 * (N * L) simplifies cleanly to O(N * L).
    """

    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        # if end word isn't in the input word list, immediately return a 0
        # create a words set
        # we start with beginword with cost = 1, by adding to a q the beginword, and level/ans = 1, and applying BFS
        # while q:
        # note: we iterate level by level since we want to find the shortest jump that will take us to the next work
        # if the polled word is the end word we are done, and can return whatever the value of ans is here
        # else, we try to create a new word by replacing each character of the current word with characters from a-z
        # if the replaced word is in the wordset, and not visited:
        # add back to the queue with an increment in the ans by 1
        bfs_q = deque()
        visited = set()
        if endWord not in wordList:
            return 0
        visited.add(beginWord)
        bfs_q.append((beginWord, 1))
        while bfs_q:
            N = len(bfs_q)
            for i in range(N):
                curr, level = bfs_q.popleft()
                if curr == endWord:
                    return level
                for i in range(len(curr)):
                    for ch in range(ord("a"), ord("z") + 1):
                        new_word = curr[:i] + chr(ch) + curr[i + 1 :]
                        if new_word in wordList and new_word not in visited:
                            bfs_q.append((new_word, level + 1))
                            visited.add(new_word)
        return 0


if __name__ == "__main__":
    Solution().solve()
