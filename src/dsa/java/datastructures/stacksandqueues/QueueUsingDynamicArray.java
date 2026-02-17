package dsa.java.datastructures.stacksandqueues;
import java.util.List;
import java.util.ArrayList;
/*NOTES:
Equivalent class in Java library is Queue.
Init:
Queue<T> q = new LinkedList<>()
Important functions to remember:
offer(), poll(), seek(), size()
 */
public class QueueUsingDynamicArray {
    List<Integer> queue;
    int frontPtr; //track of where the "front" of the queue is after dequeueing
    public int getSize() {
        return queue.size();
    }
    public QueueUsingDynamicArray() {
        this.queue = new ArrayList<>();
        this.frontPtr = 0;
    }
    public boolean enqueue(int x) {
        queue.add(x);
        return true;
    }
    public boolean dequeue() {
        if(isEmpty()) {
            return false;
        }
        frontPtr++;
        return true;
    }
    public boolean isEmpty() {
        return frontPtr >= queue.size();
    }

    public int getFront() {
        return queue.get(frontPtr);
    }
    public static void main(String[] args) {
        QueueUsingDynamicArray myQ = new QueueUsingDynamicArray();
        myQ.enqueue(1);
        myQ.enqueue(2);
        myQ.enqueue(3);
        System.out.println("Queue front: " + myQ.getFront());
        System.out.println("Queue size: " + myQ.getSize());
        myQ.dequeue();
        if(!myQ.isEmpty()) {
            System.out.println("Queue front after dequeue: " + myQ.getFront());
        }
        myQ.dequeue();
        if(!myQ.isEmpty()) {
            System.out.println("Queue front after dequeue: " + myQ.getFront());
        }
        System.out.println("Queue size: " + myQ.getSize());
    }
}
