package datastructures;

public class MyQueue {
    private int front, rear, size, capacity;
    private int[] queue;

    public MyQueue(int capacity) {
        this.capacity = capacity;
        this.front = this.size = 0;
        this.rear = capacity - 1;
        this.queue = new int[capacity];
    }

    private boolean isFull() {
        return this.size == this.capacity;
    }

    private boolean isEmpty() {
        return this.size == 0 || this.front == this.rear;
    }

    public void add(int elem) {
        if (isFull())
            return;
        else {
            this.rear = (this.rear + 1) % this.capacity;
            this.queue[this.rear] = elem;
            this.size = this.size + 1;
            System.out.println(elem + " enqueued");
        }
    }

    public int remove() {
        if (isEmpty())
            return -1;
        else {
            int elem = this.queue[this.front];
            this.front = (this.front + 1) % this.capacity;
            this.size = this.size - 1;
            return elem;
        }
    }

    public int front() {
        if (isEmpty())
            return Integer.MIN_VALUE;
        else
            return this.queue[this.front];
    }

    public int rear() {
        if (isEmpty())
            return Integer.MIN_VALUE;
        else
            return this.queue[this.rear];
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue(5);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        System.out.println(queue.remove() + " dequeued");
        System.out.println(queue.front() + " is at the front");
        System.out.println(queue.rear() + " is at the rear");
    }

}