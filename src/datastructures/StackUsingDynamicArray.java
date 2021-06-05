package datastructures;

import java.util.ArrayList;
import java.util.List;
/*NOTES:
Equivalent class in Java library is Stack.
Important functions to remember:
push(), pop(), peek(), size(), empty()
 */
public class StackUsingDynamicArray {
    private List<Integer> stack;

    public StackUsingDynamicArray() {
        stack = new ArrayList<>();
    }
    public void push(int x) {
        stack.add(x);
    }

    public int getTopElem() {
        if (!stack.isEmpty()) {
            return stack.get(stack.size() - 1);
        }
        return -1;
    }

    public int pop() {
        if(!isEmpty()) {
            return stack.remove(stack.size() - 1);
        }
        return -1;
    }
    public boolean isEmpty() {
        return (stack.isEmpty());
    }
    public static void main(String[] args) {
        StackUsingDynamicArray st = new StackUsingDynamicArray();
        st.push(1);
        st.push(2);
        st.push(3);
        System.out.println("Top: " + st.getTopElem());
        while (!st.isEmpty()) {
            System.out.println(st.pop());
        }
    }
 }
