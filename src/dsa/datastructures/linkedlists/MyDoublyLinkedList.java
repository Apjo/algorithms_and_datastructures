package dsa.datastructures.linkedlists;
class DNode {
    int key, val;
    DNode next, prev;
    public DNode() {}
    public DNode(int k, int v) {
        this.key = k;
        this.val = v;
        this.next = null;
        this.prev = null;
    }
}
class DLL {
    DNode head, tail;
    public DLL() {
        this.head = new DNode();
        this.tail = new DNode();
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.head.prev = null;
        this.tail.next = null;
    }
    public void unlink(DNode n) {
        n.next.prev = n.prev;
        n.prev.next = n.next;
    }
    public void addInFront(DNode d) {
        d.prev = this.head;
        d.next = this.head.next;
        this.head.next.prev = d;
        this.head.next = d;
    }
    public void printNodes() {
        //Node current will point to head
        DNode current = head;
        if(head == null) {
            System.out.println("Doubly linked list is empty");
            return;
        }
        System.out.println("Nodes of doubly linked list: ");
        while(current != null) {
            //Print each node and then go to next.
            System.out.print("key= " + current.key + " val= " + current.val);
            current = current.next;
        }
    }
}
public class MyDoublyLinkedList {
    DLL dl;
    public MyDoublyLinkedList() {
        dl = new DLL();
    }

}
