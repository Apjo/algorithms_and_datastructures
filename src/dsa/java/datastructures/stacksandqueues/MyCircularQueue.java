package dsa.java.datastructures.stacksandqueues;

/**
 * 1. make use of front and rear to determine the front and rear ends of the queue
 * 2. size to know the current size of the queue
 * 3. always insert/enqueue at the rear, and remove/dequeue from the front
 * 4. Queue Full condition:
 *         rear == (rear + 1) % SIZE
 *      IF queue is not full THEN:
 *         IF (rear == SIZE – 1 && front != 0) THEN:
 *              SET rear = 0
 * 5. Dequeue:
 *      Check if queue is empty i.e.
 *      IF front == -1 THEN: return
 *      ELSE IF front == rear THEN:
 *              front = rear = -1
        ELSE IF front == SIZE-1 THEN:
                SET front = 0
 */
public class MyCircularQueue {
    int []queue;
    int front, rear, capacity;

    public MyCircularQueue(int size) {
        this.queue = new int[size];
        this.capacity = size;
        this.front = -1;
        this.rear = -1;
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return ((rear + 1) % capacity == front);
    }

    public boolean enqueue(int x) {
        if (isFull()) {
            return false;
        }
        if (isEmpty()) {
            front = 0;
        }
        rear = rear + 1 % capacity;
        queue[rear] = x;

        return true;
    }

    public boolean dequeue() {
        if (isEmpty()) {
            return false;
        }
        if (front == rear) {
            front = rear = -1;
            return true;
        }
        front = (front + 1) % capacity;
        return true;
    }

    public int getFront() {
        return queue[front];
    }

    public int getRear() {
        return queue[rear];
    } //also called as the "top"

    public static void main(String[] args) {
        MyCircularQueue cq = new MyCircularQueue(3);
        cq.enqueue(1);
        cq.enqueue(2);
        cq.enqueue(3);
        System.out.println("Rear: " + cq.getRear());
        cq.dequeue();
        if (!cq.isEmpty()) {
            System.out.println("Front: " + cq.getFront());
        }
    }
}
