'''
Given a string, return the index of its first unique character. If a unique character does not exist, return -1.
'''


def solve(s:str) -> int:
    # prepare a freq map of each char in s
    # iterate over the string, and return the first character's location whose frequency == 1
    freq={}
    for char in s:
        if char in freq:
            freq[char]+=1
        else:
            freq[char] = 1
    for i in range(0, len(s)):
        if s[i] in freq and freq[s[i]] == 1:
            return i
    return -1


def solve2(s: str) -> int:
    from collections import Counter
    freq = Counter(s)
    for i, ch in enumerate(s):
        if freq[ch] == 1:
            return i
    return -1

def main():
    assert solve("abcabd") == 2
    assert solve("thedailybyte") == 1
    assert solve("developer") == 0

    assert solve2("abcabd") == 2
    assert solve2("thedailybyte") == 1
    assert solve2("developer") == 0


if __name__ == '__main__':
    main()
