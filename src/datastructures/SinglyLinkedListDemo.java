package datastructures;

public class SinglyLinkedListDemo {
    Node head;

    static class Node {
        Node next;
        int data;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }
    public Node reverseRecursive(Node head) {
        if (head == null || head.next == null) { return head;}
        Node q = head.next;
        q = reverseRecursive(q);
        head.next.next = head;
        head.next = null;
        return q;
    }
    public void addNodeAtHead(int data) {
        Node nn = new Node(data);
        nn.next = head;
        head = nn;
    }

    public void addNodeAfter(Node prev, int data) {
        if (prev == null) {
            return;
        }
        Node nn = new Node(data);
        nn.next = prev.next;
        prev.next = nn;
    }

    public void addAtLast(int data) {
        Node nn = new Node(data);
        nn.next = null;
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = nn;
    }

    public void deleteNodeAtPosition(int position) {
        if (head == null) {
            return;
        }
        Node temp = head;
        if (position == 0) {
            head = temp.next;
            return;
        }
        for (int i = 0; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null) {
            return;
        }
        temp.next = temp.next.next;
    }

    public void reverse() {
        Node curr = head;
        Node prev = null;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(" " + temp.data);
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SinglyLinkedListDemo sll = new SinglyLinkedListDemo();
        sll.addNodeAtHead(34);
        sll.addAtLast(13);
        sll.addAtLast(15);
        sll.addAtLast(33);
        sll.addNodeAfter(sll.head.next, 9);

        System.out.println("Original LinkedList created...");
        sll.printList();

        sll.reverse();
        System.out.println("After reversal...");
        sll.printList();

        sll.deleteNodeAtPosition(3);
        System.out.println("After deleting a node at position 3...");
        sll.printList();
    }
}