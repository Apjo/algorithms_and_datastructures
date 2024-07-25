package dsa.stacksandqueues;

/*
This class is a simple demonstration of a Stack data structure
The type of elements stored are Integers, not for production use
*/
public class MyStack {
    private int top;
    private int MAX = 10;
    private int[] stack = new int[MAX];

    public MyStack() {
        this.top = -1;
    }

    private boolean isFull() {
        return (top >= MAX - 1);
    }

    private boolean isEmpty() {
        return (top < 0);
    }

    public void push(int elem) {
        if (!isFull()) {
            stack[++top] = elem;
            System.out.println("Element " + elem + " pushed into the stack");
        } else {
            System.out.println("Stack overflow!");
        }
    }

    public int pop() {
        if (!isEmpty()) {
            int elem = stack[top--];
            return elem;
        } else
            return -1;
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("Element " + stack.pop() + " popped out from the stack");
        System.out.println("Element " + stack.pop() + " popped out from the stack");
        System.out.println("Element " + stack.pop() + " popped out from the stack");
    }
}