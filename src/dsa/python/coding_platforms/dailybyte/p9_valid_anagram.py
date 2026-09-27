'''
Given two strings s and t return whether or not s is an anagram of t.
Note: An anagram is a word formed by reordering the letters of another word.
'''


def solve(s:str, t:str) -> bool:
    freq:dict={}
    for char in t:
        if char in freq:
            freq[char]+=1
        freq[char] = 1

    for char in s:
        if char in freq:
            freq[char]-=1

    for cnt in freq.values():
        if cnt > 0:
            return False
    return True


def main():
    assert solve("cat", "tac") is True
    assert solve("listen", "silent") is True
    assert solve("program", "function") is False

if __name__ == '__main__':
    main()
