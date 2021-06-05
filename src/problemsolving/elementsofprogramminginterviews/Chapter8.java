package problemsolving.elementsofprogramminginterviews;

import datastructures.trees.BinaryTreeDemo;

import java.util.*;

//Stacks & queues
public class Chapter8 {
    //The problem is for max stack, we started with implementation of min stack
    //1. min stack with 2 stacks
    static class MinStackWith2Stacks {
        Stack<Integer> s1;
        Stack<Integer> s2;
        public MinStackWith2Stacks() {
            s1 = new Stack<>();
            s2 = new Stack<>();
        }
        public void push(int x) {
            s1.push(x);
            if (s2.isEmpty() || x <= getMin()) {
                s2.push(x);
            }
        }
        public int pop() {
            int v1 = s1.pop();
            if (v1 == getMin()) {
                return s2.pop();
            }
            return v1;
        }
        public int getMin() {
            if (s2.isEmpty()) {
                return -1;
            } else {
                return s2.peek();
            }
        }
        public int getTop() {
            if (s1.isEmpty()) {
                return -1;
            } else {
                return s1.peek();
            }
        }
    }
    //the interview accepted solution. 1 stack with a custom Node for denoting the min and the val
    static class MinStackCustom {
        static class MinStackNode {
            MinStackNode next;
            int val;
            int currMin;
            public MinStackNode(int v, int mi) {
                this.currMin = mi;
                this.val = v;
                this.next = null;
            }
        }
        MinStackNode head;
        public void push(int x) {
            if (head == null) {
                head = new MinStackNode(x, Math.min(x, getMin()));
            } else {
                head.next = new MinStackNode(x, Math.min(x, getMin()));
            }
        }
        public void pop() {
            head = head.next;
        }
        public int getTop() {
            return head.val;
        }
        public int getMin() {
            return head.currMin;
        }
    }

    public static int rpn(String[] tokens) {
        Stack<Integer> st = new Stack<>();
        for (String tok: tokens) {
            switch(tok) {
                case "+":
                    st.push(st.pop() + st.pop());
                    break;
                case "-":
                    st.push(-st.pop() + st.pop());
                    break;
                case "*":
                    st.push(st.pop() * st.pop());
                    break;
                case "/":
                    int op1 = st.pop();
                    int op2 = st.pop();
                    st.push(op2 / op1);
                    break;
                default:
                    st.push(Integer.parseInt(tok));
            }
        }
        return st.pop();
    }

    public static boolean iswellFormedParantheses(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        Stack<Character> st = new Stack<>();
        for(char cc : s.toCharArray()) {
            if (cc == '(' || cc == '[' || cc == '{') {
                st.push(cc);
            } else {
                if (st.isEmpty()) {
                    return false;
                } else if (cc == ')' && st.peek() != '('
                        || cc == '[' && st.peek() != ']'
                        || cc == '{' && st.peek() != '}') {
                    return false;
                }
            }
            st.pop();
        }
        return st.isEmpty();
    }

    public static String resolvePathNames(String s) {
        if (s == null || s.isEmpty()) {
            return "/";
        }
        Deque<String> st = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (String ss : s.split("/+")) {
            if (ss.equals("") || ss.equals(".")) {
                continue;
            } else if (ss.equals("..")) {
                if (!st.isEmpty()) {
                    st.poll();
                }
            } else {
                st.offer(ss);
            }
        }
        if (st.isEmpty()) {
            return "/";
        }
        while(!st.isEmpty()) {
            sb.append("/").append(st.pollLast());
        }
        return sb.toString();
    }

    public static int longest(int arr[],int n) {
        if (arr == null || n <= 0 || arr.length == 0) {
            return 0;
        }
        Stack<Integer> st = new Stack<>();
        for (int height = arr.length - 1; height >= 0; height--) {
            System.out.println("current height= " + arr[height]);
            System.out.println("current stack size= " + st.size());
            //lookout for all buildings whose ht is greater than the most recent one, if it is, pop from stack all buildings
            //whose ht is  < current one.
            while ((!st.isEmpty()) && (arr[height] > st.peek())) {
                System.out.println("popping= " + st.peek());
                st.pop();
            }
            System.out.println("adding="+arr[height]);
            //else, keep on adding the heights
            st.push(arr[height]);
        }
        int ctr = 0;
        while(!st.isEmpty()) {
            int v = st.pop();
            ctr ++;
        }
        return ctr;
    }
    //O(m) operations, each element is pushed no more than twice and popped no more than twice for m operations
    static class QueueUsingStacks {
        private Stack<Integer> front;
        private Stack<Integer> rear;
        public QueueUsingStacks() {
            this.front = new Stack<>();
            this.rear = new Stack<>();
        }
        public boolean empty() {
            return front.isEmpty() && rear.isEmpty();
        }
        public void push(int x) {
            rear.push(x);
        }
        public int pop() {
            //if front is empty then pop from rear and add to front, else always pop() from front
            if (front.isEmpty()) {
                while(!rear.isEmpty()) {
                    front.push(rear.pop());
                }
            }
            return front.pop();
        }
        public int peek() {
            //if front is empty then pop from rear and add to front, else always peek() from front
            if (front.isEmpty()) {
                while (!rear.isEmpty()) {
                    front.push(rear.pop());
                }
            }
            return front.peek();
        }
        public void peek(int x) {
            rear.push(x);
        }
    }
    //extra problem as part of LC explore
    //pop(), and peek() are guaranteed to be not throwing NPEs
    static class StackUsingQueues {
        private Queue<Integer> myQ;
        public StackUsingQueues() {
            this.myQ = new LinkedList<>();
        }
        public void push(int x) {
            myQ.add(x);
            for (int i = 1; i < myQ.size(); i++) {
                myQ.add(myQ.remove());
            }
        }
        public int pop() {
            return myQ.remove();
        }
        public int peek() {
            return myQ.peek();
        }
        public boolean isEmpty() {
            return myQ.isEmpty();
        }
    }

    public List<List<Integer>> levelOrderTraversal(BinaryTreeDemo.BinaryTreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<BinaryTreeDemo.BinaryTreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            List<Integer> inner = new ArrayList<>();
            int qSize = q.size();
            while (qSize > 0) {
                BinaryTreeDemo.BinaryTreeNode current = q.poll();
                inner.add(current.data);
                if (current.left != null) {
                    q.add(current.left);
                }
                if (current.right != null) {
                    q.add(current.right);
                }
                qSize--;
            }
            res.add(inner);
        }
        return res;
    }

    /**implements enqueue, dequeue, max operations, max returns maximum element currently stored in the queue.
     * Try1:
     * 1. we can use the simple Queue interface with LinkedList implementation
     * 2. a custom/known comparator while enqueuing elements.The comparator will be useful to order elements in the queue
     * 3. dequeue which will return the head
     * 4. max will return the max
     * Enqueue:
     * - collect current elements in a array
     * - sort the arr
     * - add back the elements into the q
     * Max:
     * try1:
     *  - could use a separate collection - O(n) space will be consumed
     *  - get all elements in the queue -> sort -> return nth item -> O(n log n)
     *  - queue is already sorted, last element in the queue or reverse the list and return the max(either O(1) or O(N))
     *  Try2:
     *  - think of implementing a single Q with 2 stacks?
     *  - one stack(front) to keep a track of elements, and 2nd stack(rear) keeping track of current max element
     *  - enqueue x:
     *      - add x to st2
     *
     *  - dequeue:
     *      if s1 is empty:
     *          while s2 is not empty:
     *              s1.push(s2.pop)
     *      ret s1.pop()
     *  - max:
     *      - s2.peek
     * Try3:
     * - combine solution of Max stack and Queue using 2 stacks(max stacks in this case)
     * - O(1) amortized for enqueue/deq/max
     */
    //O(1) amortized for push and pop and max
    static class CustomMaxStack {
        private Stack<Integer> s1;
        private Stack<Integer> s2;
        public CustomMaxStack() {
            this.s1 = new Stack<>();
            this.s2 = new Stack<>();
        }
        public boolean isEmpty() {
            return s1.isEmpty() && s2.isEmpty();
        }

        public void push(int x) {
            if (s2.isEmpty() || x >= getMax()) {
                s2.push(x);
            }
            s1.push(x);
        }
        public int pop() {
            if (s1.isEmpty()) {
                return Integer.MAX_VALUE;
            } else {
                int v = s1.pop();
                if (v == getMax()) {
                    return s2.pop();
                }
                return v;
            }
        }

        public int getMax() {
            if (s2.isEmpty()) {
                return Integer.MAX_VALUE;
            } else {
                return s2.peek();
            }
        }
    }
    static class MaxQueue {
        CustomMaxStack front;
        CustomMaxStack rear;

        public MaxQueue() {
            this.front = new CustomMaxStack();
            this.rear = new CustomMaxStack();
        }
        public void enqueue(int x) {
            rear.push(x);
        }
        public int dequeue() {
            if (front.isEmpty()) {
                while (!rear.isEmpty()) {
                    front.push(rear.pop());
                }
            }
            return front.pop();
        }
        public int max() {
            if (!rear.isEmpty()) {
                if (front.isEmpty()) {
                    return rear.getMax();
                } else {
                    return Math.max(front.getMax(), rear.getMax());
                }
            } else {
                if(!front.isEmpty()) {
                    return front.getMax();
                } else {
                    return Integer.MAX_VALUE;
                }
            }
        }
    }

    public static void main(String []args) {
        int[]buildings = new int[]{4, 7, 6, 5, 5, 7, 1, 8, 6};
        System.out.println(longest(buildings, buildings.length));
    }
}
