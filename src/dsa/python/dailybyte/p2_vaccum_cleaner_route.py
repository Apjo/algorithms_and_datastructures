'''
Given a string representing the sequence of moves a robot vacuum makes, return whether or not it will return to its original position.
The string will only contain L, R, U, and D characters, representing left, right, up, and down respectively.
'''


def is_valid(input_str: str) -> bool:
    start = 0
    for str_char in input_str:
        if str_char == 'U':
            start+=1
        elif str_char == 'D':
            start-=1
        elif str_char == 'L':
            start-=1
        else:
            start+=1
    return start == 0


def main():
    print(is_valid("LR"))
    print(is_valid("URURD"))
    print(is_valid("RUULLDRD"))



if __name__ == "__main__":
    main()