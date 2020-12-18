package datastructures;

public class StackArray<T> {
    int size = 0;
    Object[] arr;
    public StackArray <T>(int cap)
    {
        this.arr = new Object[cap];
    }

    boolean isFull() {
        return size >= arr.length;
    }

    boolean isEmpty() {
        return size == 0;
    }

    void push(T d) {
        if (isFull()) {
            throw new IllegalArgumentException("STACK FULL");
        }
        arr[size++] = d;
    }

    T pop() {
		if(isEmpty()) {throw new Exception("STACK EMPTY");}
		T d = (T) arr[--size];
		arr[size] = null;
		return d;
    }

T peek() {
	if(isEmpty()) {throw new Exception("STACK EMPTY");}
	return (T) arr[size - 1];
}
}
