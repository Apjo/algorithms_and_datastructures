'''
This question is asked by Apple.
Given two sorted linked lists, merge them together in ascending order and return a reference to the merged list
'''
class MyLinkedList():
    def __init__(self, value):
        self.val = value
        self.next = None


def solve(list1: MyLinkedList, list2: MyLinkedList) -> MyLinkedList:
    p1, p2 = list1, list2
    dummy = MyLinkedList(-999)
    temp = dummy
    while p1 != None and p2 != None:
        if p1.val < p2.val:
            temp.next = p1
            p1=p1.next
        else:
            temp.next = p2
            p2=p2.next
        temp=temp.next

    while p1 != None:
        temp.next = p1
        p1=p1.next
        temp=temp.next

    while p2 != None:
        temp.next = p2
        p2=p2.next
        temp=temp.next

    return dummy.next


def printL(l: MyLinkedList):
    temp = l
    while temp:
        print(temp.val)
        temp=temp.next

def main():
    # list1 = 1->2->3, list2 = 4->5->6->null, return 1->2->3->4->5->6->null
    list1 = MyLinkedList(1)
    list1.next= MyLinkedList(2)
    list1.next.next = MyLinkedList(3)
    list2 = MyLinkedList(4)
    list2.next= MyLinkedList(5)
    list2.next.next = MyLinkedList(6)
    printL(solve(list1, list2))


if __name__ == '__main__':
    main()
