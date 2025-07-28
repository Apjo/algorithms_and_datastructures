'''
Given a string, return whether or not it forms a palindrome ignoring case and non-alphabetical characters.
Note: a palindrome is a sequence of characters that reads the same forwards and backwards.
'''


def valid_palindrome(input_str):
    le, hi = 0, len(input_str) - 1
    while le < hi:
        #continue going ahead till you find a char which is alphanumeric
        while le < hi and not input_str[le].isalnum():
            le += 1
        #continue going back till you find a char which is alphanumeric
        while hi > le and not input_str[hi].isalnum():
            hi -= 1
        #if chars do not match, immediately return False
        if input_str[le].lower() != input_str[hi].lower():
            return False
        le += 1
        hi -= 1
    return True


def main():
    print(valid_palindrome("A man, a plan, a canal: Panama."))
    print(valid_palindrome("Algorithm"))


if __name__ == "__main__":
    main()
