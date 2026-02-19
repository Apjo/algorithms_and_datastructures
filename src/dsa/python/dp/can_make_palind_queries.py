# -*- coding: utf-8 -*-
"""
@Author: apjoshi
@Date: 2/18/26
"""
from typing import List
import collections
from collections import Counter
#iterate over all the substrings, finding the freq of each char, check how many are odd vs even (divide that count by 2),
#and then check how many of them are <= k
def canMakePaliQueries_brute(s: str, queries: List[List[int]]) -> List[bool]:
    res=[]
    for l, r, q in queries:
        substr = s[l : r + 1]
        count = 0
        for letter, freq in collections.Counter(substr).items():
            count+=freq % 2#we add a modulo to the frequency to filter out the even occurring characters
        need = count // 2
        res.append(need <= q)
    return res

#using a cache to store previously calculated frequency of occurrence of characters
#We can say dp[i] represents the count of all the letters in s[:i].
# Then we know that the counts of dp[l] subtracted from dp[r+1] will give us the count of all the letters in the substring s[l:r+1]
#time: O(N+M*26)
def canMakePaliQueries(s: str, queries: List[List[int]]) -> List[bool]:
    dp = [collections.Counter()] #dp[i] = frequency vector of size 26 it will store something like
    # {
    #     'a': count,
    #     'b': count,
    #     'c': count,
    #     ...
    # }

    for i in range(1, len(s) + 1):
        dp.append(dp[i - 1] + collections.Counter(s[i - 1]))
    res=[]
    for l, r, q in queries:
        count = dp[r + 1] - dp[l]
        freq_sum = sum(freq % 2 for freq in count.values())
        need = freq_sum // 2
        res.append(need <= q)
    return res

#bitmask version
def canMakePaliQueries(s: str, queries: List[List[int]]) -> List[bool]:

    '''
    We start with building a prefix array by xor. Prefix is about positions in the string, not about characters.
    If string length is n, we need to answer: What is the character state up to index i?
    So we need one stored value per index.
    '''
    n = len(s)
    res=[]
    prefix_xor=[0]*(n + 1) #will store parity info about substring s[0:i]
    for i in range(n):
        prefix_xor[i + 1] = prefix_xor[i] ^ (1 << (ord(s[i]) - ord('a')))

    '''
    Why bitmask?
    A bitmask is just a number whose binary representation encodes multiple boolean states. For lowercase letters we need 26 booleans
    is a odd? is b odd?... so instead of storing [0,1,0,0,1,0...] we store 1 integer like 0001010... (binary) where
    bit positions
    bit 0 → 'a'
    bit 1 → 'b'
    bit 2 → 'c'
    ...
    bit 25 → 'z'
    if bit is:
        0 → even count
        1 → odd count
    So, why do we care if a bit is even or odd?
    We understand that a Palindrome rule states:
        At most 1 character may have odd frequency. Others must be even.
    So, we don’t care if a character appears 100 times or 2 times. We only care if it’s odd or even.
    Ok, so how do we chose a mask?
    For a given character 
    bit_index = ord(character) - ord('a')
    Example:
    'a' → 0
    'b' → 1
    'c' → 2
    And, to turn on a bit at position bit_index, we do:
    1 << bit_index, and we do this becuase we know that binary form of 1 is 00000001, and if we were to left shift by 2
    1 << 2, we get 00000100, if you observe(from rhs) only bit 2 is 1, so the left shift creates a number that represents “this character”.  
    '''
    for l, r, query in queries:
        '''
        Why XOR Works?
        When we think in terms of parity math (mod 2).
        If a character appears Even times before l, and Even times before r+1 then even ^ even = even
        Else if it appears Even before l, and Odd before r+1 then we see odd ^ even = odd
        else If it appears Odd before l and Odd before r+1 then it follows that odd ^ odd = even
        So what we see is that prefix_xor[l] cancels out the shared prefix.

XOR is subtraction in mod 2 world.
        '''
        curr_mask = prefix_xor[r + 1] ^ prefix_xor[l]
        odd_bit_count = bin(curr_mask).count('1')
        replacements_needed = odd_bit_count // 2
        #remember Each replacement fixes two odd characters at once, and in a palindrome its allowed one odd character (for the center),
        # and the remaining odds must be fixed in pairs.
        if odd_bit_count <= 1 or query >= replacements_needed:
            res.append(True)
        else:
            res.append(False)
    return res

def main():
    return solve()


if __name__ == '__main__':
    main()
