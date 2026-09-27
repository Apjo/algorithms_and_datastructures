'''
 Given a string and the ability to delete at most one character, return whether or not it can form a palindrome.
Note: a palindrome is a sequence of characters that reads the same forwards and backwards.
'''

from utils import is_palindrome


def solve(ip_str : str) -> bool:
    # set 2 pointers, i to be at the start of string, and j to be at the end of the string
    # while i < str.len and j >=0
        # continue to increment the i to see if str is a palindrome when we skip a char from left,
        # and decrement j to see if string becomes a palindrome when we skip a char from right
        # if its not, then immediately return False
    # finally return True
    i,j=0,len(ip_str) - 1
    L = len(ip_str)
    while i < L and j >=0:
        # When characters from left, and right mismatch
        #   determine whether the string becomes a palindrome if we "skip" / drop a char from left,
        #   or skip/drop a char from right
        if ip_str[i] != ip_str[j]:
         if is_palindrome(ip_str, i + 1, j) or is_palindrome(ip_str, i, j - 1):
             return True
         else:
             return False
        i+=1
        j-=1

    return True


def main():
    assert solve("abcdaf") is False
    assert solve("abcba") is True
    assert solve("foobof") is True
    assert solve("abccab") is False




if __name__ == '__main__':
    main()
