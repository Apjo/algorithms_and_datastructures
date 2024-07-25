package dsa.trees;

public class MaxHeap {
   private int size;
   private int maxSize;
   private int heap[];

   public MaxHeap(int capacity) {
      this.size = 0;
      this.maxSize = capacity;
      this.heap = new int[maxSize + 1];
      this.heap[0] = Integer.MAX_VALUE;
   }

   public int[] getHeap() {
      return heap;
   }

   private int left(int pos) {
      return 2 * pos;
   }

   private int right(int pos) {
      return 2 * pos + 1;
   }

   private int parent(int pos) {
      return (int) pos / 2;
   }

   private void swap(int fpos, int secpos) {
      int temp = heap[fpos];
      heap[fpos] = heap[secpos];
      heap[secpos] = temp;
   }

   public void insert(int elem) {
      heap[++size] = elem;

      // Traverse up and fix violated property
      int current = size;
      while (heap[current] > heap[parent(current)]) {
         swap(current, parent(current));
         current = parent(current);
      }
   }

   private void heapify(int pos) {
      if (pos >= (heap.length / 2) && pos <= heap.length) {
         return;
      }

      int l = left(pos);
      int r = right(pos);
      int largest = pos;
      int n = heap.length;
      if (l < n && heap[l] > heap[pos]) {
         largest = l;
      } else {
         largest = pos;
      }
      if (r < n && heap[r] > heap[largest]) {
         largest = r;
      }
      if (largest != pos) {
         swap(largest, pos);
         heapify(largest);
      }
   }

   private void heapify2(int[] a, int pos) {
      if (pos >= (a.length / 2) && pos <= a.length) {
         return;
      }

      int l = 2 * pos + 1;
      int r = 2 * pos + 2;
      int largest = pos;
      int n = a.length;
      if (l < n && a[l] > a[pos]) {
         largest = l;
      } else {
         largest = pos;
      }
      if (r < n && a[r] > a[largest]) {
         largest = r;
      }
      if (largest != pos) {
         swap(largest, pos);
         heapify2(a, largest);
      }
   }

   private void buildMaxHeap() {
      int n = heap.length;
      for (int i = n / 2 - 1; i >= 0; i--) {
         heapify(i);
      }
   }

   private void buildMaxHeap2(int[] a) {
      int n = a.length;
      for (int i = n / 2 - 1; i >= 0; i--) {
         heapify(i);
      }
   }
//O(logN)
   public int extractMax() {
      if (size < 1) {
         System.out.println("Underflow occured, exiting..");
         System.exit(0);
      }
      int max = heap[1];
      heap[1] = heap[size--];
      heapify(1);
      return max;
   }

   public int getMax() {
      if (size < 1) {
         System.out.println("Underflow occured, exiting..");
         System.exit(0);
      }
      return heap[1];
   }

   public void printMaxHeap() {
      for (int i = 1; i <= size / 2; i++) {
         System.out
               .print(" PARENT : " + heap[i] + " LEFT CHILD : " + heap[left(i)] + " RIGHT CHILD :" + heap[right(i)]);
         System.out.println();
      }
   }

   public void printArray(int arr[]) {
      int n = arr.length;
      for (int i = 0; i < n; ++i)
         System.out.print(arr[i] + " ");
      System.out.println();
   }

   public static void main(String[] args) {
      MaxHeap maxHeap = new MaxHeap(15);

      maxHeap.insert(5);
      maxHeap.insert(3);
      maxHeap.insert(17);
      maxHeap.insert(10);
      maxHeap.insert(384);
      maxHeap.insert(911);
      maxHeap.insert(84);
      maxHeap.insert(-90);
      maxHeap.insert(9);
      maxHeap.insert(284);
      maxHeap.insert(4);
      maxHeap.insert(2);
      maxHeap.insert(-4);
      maxHeap.insert(184);

      maxHeap.printMaxHeap();

      System.out.println("Max value is: " + maxHeap.getMax());
   }
}