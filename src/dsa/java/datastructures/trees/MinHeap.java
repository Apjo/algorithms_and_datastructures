package dsa.java.datastructures.trees;

public class MinHeap {
   private int size;
   private int maxSize;
   private int heap[];

   public MinHeap(int capacity) {
      this.size = 0;
      this.maxSize = capacity;
      this.heap = new int[maxSize + 1];
      this.heap[0] = Integer.MIN_VALUE;
   }

   private int left(int pos) {
      return (2 * pos) + 1;
   }

   private int right(int pos) {
      return (2 * pos) + 2;
   }

   private int parent(int pos) {
      return (int) Math.floor(pos / 2);
   }

   public int getMin() {
      return heap[0];
   }

   private void swap(int fpos, int secpos) {
      int temp = heap[fpos];
      heap[fpos] = heap[secpos];
      heap[secpos] = temp;
   }

   public void insert(int elem) {
      size++;
      int current = size - 1;
      heap[current] = elem;
      while (heap[current] < heap[parent(current)]) {
         swap(current, parent(current));
         current = parent(current);
      }
   }

   private void heapify(int pos) {
      int l = left(pos);
      int r = right(pos);
      int largest = pos;

      if (l <= size && heap[l] > heap[pos]) {
         largest = l;
      } else {
         largest = pos;
      }
      if (r <= size && heap[r] > heap[pos]) {
         largest = r;
      }
      if (largest != pos) {
         swap(pos, largest);
         heapify(largest);
      }
   }

   private void buildHeap() {
      for (int i = (size / 2) - 1; i >= 0; i--) {
         heapify(i);
      }
   }

   public int extractMin() {
      if (size < 1) {
         System.out.println("Underflow occured, exiting..");
         System.exit(0);
      }
      int min = heap[1];
      heap[1] = heap[size--];
      heapify(1);
      return min;
   }

   public void printMinHeap() {
      for (int i = 1; i <= size / 2; i++) {
         System.out
               .print(" PARENT : " + heap[i] + " LEFT CHILD : " + heap[left(i)] + " RIGHT CHILD :" + heap[right(i)]);
         System.out.println();
      }
   }

   public static void main(String[] args) {
      MinHeap minHeap = new MinHeap(15);

      minHeap.insert(5);
      minHeap.insert(3);
      minHeap.insert(17);
      minHeap.insert(10);
      minHeap.insert(84);
      minHeap.insert(19);
      minHeap.insert(6);
      minHeap.insert(22);
      minHeap.insert(9);

      minHeap.printMinHeap();

      System.out.println("Min value is: " + minHeap.extractMin());
   }
}