'''
This question is asked by Facebook.
Given a linked list and a value n, remove the nth to last node and return the resulting list.

Ex: Given the following linked lists...

1->2->3->null, n = 1, return 1->2->null
1->2->3->null, n = 2, return 1->3->null
1->2->3->null, n = 3, return 2->3->null
'''
from utils import MyLinkedList


def solve(list1: MyLinkedList, n:int) -> MyLinkedList:

    if list1 is None:
        return list1

    fast, slow = list1, list1

    for i in range(0, n):
        fast = fast.next

    if fast is None:
        return list1.next

    while fast is not None and fast.next is not None:
        fast = fast.next
        slow = slow.next

    slow.next = slow.next.next

    return list1


def printL(l: MyLinkedList):
    temp = l
    while temp:
        print(temp.val)
        temp=temp.next


def main():
    list1 = MyLinkedList(1)
    list1.next = MyLinkedList(2)
    list1.next.next = MyLinkedList(3)
    printL(solve(list1, 1)) # 1->2->NULL

    list2 = MyLinkedList(1)
    list2.next = MyLinkedList(2)
    list2.next.next = MyLinkedList(3)
    printL(solve(list2, 2)) # 1->3->NULL

    list3 = MyLinkedList(1)
    list3.next = MyLinkedList(2)
    list3.next.next = MyLinkedList(3)
    printL(solve(list3, 3)) # 2->3->NULL



if __name__ == '__main__':
    main()
