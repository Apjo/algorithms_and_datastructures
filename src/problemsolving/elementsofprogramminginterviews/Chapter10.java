package problemsolving.elementsofprogramminginterviews;

import java.util.*;
//Start date: 06/21
public class Chapter10 {
    //merge k sorted lists
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    //Time: O(n*k*logk)
    public ListNode mergeKLists(ListNode[] lists) {
        return divideAndConquer(lists, 0, lists.length - 1);
    }
    private static ListNode divideAndConquer(ListNode[]lists, int st, int end) {
        if (st == end) {
            return lists[st];
        }
        if (st < end) {
            int mid = st + (end - st) / 2;
            ListNode left = divideAndConquer(lists, st, mid);
            ListNode right = divideAndConquer(lists, mid + 1, end);
            return mergeSortedLists(left, right);
        }
        return null;
    }
    private static ListNode mergeSortedLists(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while(l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        if (l1 != null) {
           curr.next = l1;
        }
        if (l2 != null) {
            curr.next = l2;
        }
        return dummy.next;
    }

    public static class ListNodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode l1, ListNode l2) {
            return l1.val - l2.val;
        }
    }
    public static ListNode mergeKSortedListsIter(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        //create a min heap
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new ListNodeComparator());
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (ListNode ll : lists) {
            if (ll != null) {
                pq.add(ll);
            }
        }
        while (!pq.isEmpty()) {
            curr.next = pq.poll();
            curr = curr.next;
            //why we do this? https://leetcode.com/problems/merge-k-sorted-lists/discuss/10528/A-java-solution-based-on-Priority-Queue/186316
            if (curr.next != null) {
                pq.add(curr.next);
            }
        }
        return dummy.next;
    }
    public class KthLargest {
        private PriorityQueue<Integer> minHeap;
        private int k;
        public KthLargest(int k, int [] nums) {
            this.k = k;
            this.minHeap = new PriorityQueue<>();
            for (int x : nums) {
                add(x);
            }
        }
        public int add(int v) {
            minHeap.offer(v);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
            return minHeap.peek();
        }
    }
    /*
    eg: ll=[57,131,493,294,221,339,418,452,190]
    we know that after k=4 array has decreasing element, then again it increases
    a1=[57,131,493]
    a2=[294]
    a3=[221,339,418,452]
    a4=[190]
    basically this boils down to sort k subarrays/lists problem
    o/p=[57,131,190,221,294,339,418,493]
    Ask:
    - do we always get unsorted list?
    - sorted order? asc/desc
    - what to return on empty/null input list
    - k <= list.size()?
    - can k be 0? sure if the list is sorted in asc?
    Try2:
    - we basically use the same approach as above, except whenever we find a decreasing value, we reverse the subarray, and join with the non-decreasing array
    - basically in the end we end up with non-decreasing subarrays, so that we can use mergeKSortedLists function to sort into 1 list
     */
    private enum SORTORDER {
        INCREASING, DECREASING;
    }
    private static boolean condition(List<Integer> l, int i, SORTORDER so) {
        return (i == l.size() || l.get(i - 1) < l.get(i) && so == so.DECREASING || l.get(i - 1) >= l.get(i) && so == so.INCREASING);
    }
    private static ListNode mergeSortedLists(List<List<ListNode>> ll) {
        return mergeSubLists(ll, 0, ll.size() - 1);
    }
    private static ListNode mergeSubLists(List<List<ListNode>> ll, int lo, int hi) {
        if (lo == hi) {
            return ll.get(lo).get(lo);
        }
        if (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            ListNode le = mergeSubLists(ll, lo, mi);
            ListNode ri = mergeSubLists(ll, mi + 1, hi);
            return merge(le, ri);
        }
        return null;
    }
    private static ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            curr.next = l1;
        }
        if (l2 != null) {
            curr.next = l2;
        }
        return dummy.next;
    }
    /*
    public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> ll) {
        if (ll == null || ll.size() == 0) {
            return null;
        }
        ListNode ll1 = convertToListNode(ll);//TODO
        List<List<ListNode>> sortedSubLists = new ArrayList<>();
        SORTORDER type = SORTORDER.INCREASING;
        int startIdx = 0;
        for (int i = 1; i <= ll.size(); i++) {
            if (condition(ll, i, type)) {
                List<ListNode> sub = ll1.subList(startIdx, i);//TODO
                if (type == SORTORDER.DECREASING) {
                    Collections.reverse(sub);
                }
                sortedSubLists.add(sub);
                startIdx = i;
                type = type == SORTORDER.INCREASING ? SORTORDER.DECREASING : SORTORDER.INCREASING;
            }
        }
        return mergeSortedLists(sortedSubLists);
    }*/
    //k-sorted array
    /*
    - maintain a min heap of size k + 1, we do k + 1 because we will find the smallest element in that group at index 0.
    - and all elements after k + 1 guarantee that they will be atmost k away from the smallest
    - once you add k + 1 elements, remove from the heap and add the remaining ones
    - repeat till end of input sequence
    - time: O(n logk) size : O(k)
     */
    public static int[] sortAlmostSortedArray(int[] A, int k) {
        if (A == null || A.length == 0) {
            return null;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < k + 1; i++) {
            minHeap.add(A[i]);
        }
        //remove the min elements, and add the remaining ones
        int ctr = 0;
        for (int i = k ;i < A.length; i++) {
            A[ctr++] = minHeap.peek();
            minHeap.poll();
            minHeap.add(A[i]);
        }
        Iterator<Integer> it = minHeap.iterator();
        while (it.hasNext()) {
            A[ctr++] = minHeap.peek();
            minHeap.poll();
        }
        return A;
    }

    /*
- are points sorted?
- how are points represented? 2d, 1d, or 3d?
- how many points? whats the max no. of points
- what is close?
    - given points a and b, a is closer to x than b iff:
        |a-x| < |b-x| or |a-x| == |b-x| and a < b
    - or euclidean distance sqrt((x1-x2)^2 + (y1-y2)^2) --> we will be using this
- if sorted, and a single dimension arr of points is given:
    - Input: arr = [1,2,3,4,5], k = 4, x = 3, o/p: [1,2,3,4]
    - try1:
        - iterate over the array, and calculate the distance between a[i] and x and a[i +1] and x
        - then check the condition of closeness between a[i], a[i + 1] amd x, and add them to result
        - O(N^2) time, O(k) space
    - try2:
        - iterate over the array, and maintain a min heap of size k
        - while iterating through input arr:
           - add to heap a[i] by comparing distances
           - if size of heap is > k:
                - remove an element from heap
        - finally remove all elements from heap and dump into an arr/list and return
        - time: O(nlogk)
     */
    public static int[][] findKClosestToOrigin(int[][] points, int k) {
        if (points == null || points.length == 0) {
            return null;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(k, new Comparator<int[]> (){
            @Override
            public int compare(int[]p1, int []p2) {
                //d1^2 = x1^2 + y1^2, and d2^2 =  x2^2 + y2^2
                //we calculate difference as:
                //d1^2 - d2^2 = (x2^2 + y2^2) - (x1^2 + y1^2) for representing max heap
                return ((p2[0]*p2[0] + p2[1]*p2[1]) - (p1[0]*p1[0] + p1[1]*p1[1]));
            }
        });
        for (int[] point : points) {
            pq.offer(point);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.toArray(new int[0][0]);
    }
    /*
- given a max heap, without changing the heap calc. k largest elements
Try1:
- iterate over the heap and store elements into a collection
- sort the collection (O(NlogN)), o(N) space
- return k largest
Try2:
eg:   0    1  2   3  4   5   6   7  8
i/p:[561,314,401,28,156,359,271,11,3]
o/p:[561,401,359,314]
- left: 2*i + 1, right: 2*i+ 2
- start from root, mark root as visited, and add to collection
- calc. left and right child.
   - get the max from left/right child
   - add the max to result, mark as visited
   - recurse on left till we reach leaf nodes
   - recurse on right till we reach leaf nodes
- return the collection
     */
    public static List<Integer> kLargestUsingMaxHeap(int []input, int k) {
        if (input == null || input.length == 0) {
            return null;
        }
        List<Integer> coll = new ArrayList<>();

        PriorityQueue<Integer> nextMaxElementCollection = new PriorityQueue<Integer>((a, b) -> input[a] - input[b]);
        coll.add(input[0]);
        nextMaxElementCollection.offer(0);
        while (!nextMaxElementCollection.isEmpty() && k > 0) {
            int nextCandidateIdx = nextMaxElementCollection.poll();

            int le = 2*nextCandidateIdx + 1;
            int ri = 2*nextCandidateIdx + 2;

            if (le < input.length) {
                nextMaxElementCollection.offer(le);
            }
            if (ri < input.length) {
                nextMaxElementCollection.offer(ri);
            }
            k--;
        }
        return coll;
    }
/*
i/p:[1,0,3,5,2,0,1]
o/p:[1,1.5,1,2,2,1.5,1]
try1:
- dump elements into an array
- sort the arr in asc order using insertion sort, to keep elements in sorted order during adding elements to the array -> O(N) for each insertion
- return median -> O(1)
- O(N^2)
try2:
- if N = ODD, then median = max(left hand side), because we have only 1 middle element
- if N = EVEN, then median = max(left half) + min(right half) / 2, because we have 2 middle elements
- min queue holds the larger elements of the stream with the ability to provide the least element in it in O(1)
- max queue holds the smaller elements of the stream with the ability to provide the largest element in it in O(1)
- if N = ODD, then assume maxHeap size > minheapSize + 1 i.e. ==> Invariant1
minHeapSize <= maxHeapSize <= 1 + minHeapSize ie. maxHeap might contain a single extra element than the min heap
- How to determine in which half (or heap) the next number will be in assuming our default heap to be used in maxHeap?
    - Ref: https://youtu.be/1LkOrc-Le-Y?t=912
    - TL;DR:
        - if both heaps are empty, select 1 heap and add element to that heap say max heap(or the first/left half)
        - else if maxHeap.size > minHeap.size:
            - if num < maxHeap.top:             ==> this means num falls in the first/left half
                - top <- pop from maxHeap
                - insert top into minheap
                - insert num in maxHeap
            - else:
                - just add num to minHeap
       - else if maxHeap.size < minHeap.size:
            - if num > minHeap.top:             ==> falls in the second/larger half
                - top <- pop from minHeap
                - insert top into maxHeap
                - insert num in minHeap
            - else:
                - just add num to minHeap
       - if sizes are same, we compare the num with top of the heaps, if num < maxHeap.top add to it, else add to minHeap
 */
public static class MedianFinder {
    private PriorityQueue<Integer> left;
    private PriorityQueue<Integer> right;

    public MedianFinder() {
        this.left = new PriorityQueue<Integer>(Collections.reverseOrder());
        this.right = new PriorityQueue<Integer>();
    }

    public void addNum(int num) {
        //if both halves are empty, add num to left/lower half.
        if (left.size() == 0 && right.size() == 0) {
            left.add(num);
        }
        //if lower half is bigger in size than bigger half
        else if (left.size() > right.size()) {
            //if num < smallest num in left half
            if (num < left.peek()) {
                //remove min from leftHalf, and move to righthalf
                //add the num to left half
                right.add(left.poll());
                left.add(num);
            } else {
                right.add(num);
            }
        }
        else if (left.size() < right.size()) {
            //if num > max num from larger half
            if (num > right.peek()) {
                //remove from right and add to left
                left.add(right.poll());
                //add num to right
                right.add(num);
            } else {
                //just add to lower half
                left.add(num);
            }
        }
        else {
            //both halves have the same size, so just compare the topmost elements from lower and larger halves
            if (num < left.peek()) {
                //num lesser than the smallest element
                left.add(num);
            } else {
                //else num goes into the larger half
                right.add(num);
            }
        }
    }

    public double findMedian() {
        if (left.size() == 0 && right.size() == 0) {
            return 0.0;
        }
        if (left.size() != right.size()) { //odd num.of elements
            return (double)(left.size() > right.size() ?  left.peek() : right.peek());
        }
        return (double)((left.peek() + right.peek()) / 2.0);
    }
}
/*
try1:
- stack is LIFO
Ask:
- why?
- what sort of heap?
    - which one? ascending/descending? ==> minheap for now
- type of data to be stored ==> assume integer for now
- is there a capacity?  => yes
- basically this will be like implementing a stack using queue on a very high level
- to keep a track of the elements getting added to the queue, we will need to use an index(or rank) of an element on insertion
- A custom comparator to will be used to sort the elements in the queue based on the rank, which will keep the "most recently added" element at the front of the queue
- the elements with the "max" rank will be popped first.
Follow up:
- queue using a heap
 */
public static class StackUsingHeap {
    private PriorityQueue<StackUsingHeap> pq;
    int value, rank, capacity;
    public StackUsingHeap(int capacity) {
        this.capacity = capacity;
    }
    public static class RankComparator implements Comparator<StackUsingHeap> {
        @Override
        public int compare(StackUsingHeap s1, StackUsingHeap s2) {
            return (s1.rank - s2.rank);
        }
    }
    public StackUsingHeap(int v, int r) {
        this.pq = new PriorityQueue<>(capacity, new RankComparator());
        this.value = v;
        this.rank = r;
    }

    public void push(int data) {
        if (pq.size() >= capacity) {
            return;
        }
        if (pq.isEmpty()) {
            pq.add(new StackUsingHeap(data, 0));
        } else {
            int currentRank = pq.poll().rank;
            pq.add(new StackUsingHeap(data, currentRank + 1));
        }
    }
    public int pop() {
        if (pq.size() == 0) {
            return -999;
        }
        return pq.poll().value;
    }
}
}
