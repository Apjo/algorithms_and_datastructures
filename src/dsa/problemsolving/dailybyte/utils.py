def is_palindrome(s, i, j):
    while i < j:
        if s[i] != s[j]:
            return False
        i+=1
        j-=1
    return True


class MyLinkedList():
    def __init__(self, value):
        self.val = value
        self.next = None