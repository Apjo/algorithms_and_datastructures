'''
Given a string, return whether or not it uses capitalization correctly.
A string correctly uses capitalization if all letters are capitalized, no letters are capitalized, or only the first letter is capitalized.

"USA", return true
"Calvin", return true
"compUter", return false
"coding", return true
'''


def solve(input_str: str) -> bool:
    '''
    if input_str is UPPER, or LOWER -> ret True
    if first char is lower, and next one is upper -> ret false

    '''

    if input_str.islower() or input_str.isupper():
        return True

    first = input_str[0]
    second_char = input_str[1]
    if first.islower() and second_char.isupper():
        return False
    is_second_upper = second_char.isupper()
    for curr in input_str[1:]:
        if is_second_upper and curr.islower():
            return False
        if not is_second_upper and curr.isupper():
            return False

    return True


def main():
    assert solve("USA") is True
    assert solve("USa") is False
    assert solve("Usa") is True
    assert solve("UsA") is False
    assert solve("uSOfA") is False
    assert solve("usa") is True


if __name__ == "__main__":
    main()
