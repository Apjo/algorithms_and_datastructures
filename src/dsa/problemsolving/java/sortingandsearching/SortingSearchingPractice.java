package dsa.problemsolving.java.sortingandsearching;

import java.util.*;

// This class uses some sample examples to demonstrate sorting & searching
// problem solving techniques.
public class SortingSearchingPractice {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void printList(ListNode n) {
        ListNode t = n;
        while (t != null) {
            System.out.print(" " + t.val);
            t = t.next;
        }
        System.out.println();
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = new ListNode(0);
        ListNode temp = head;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        if (l1 != null) {
            temp.next = l1;
        }
        if (l2 != null) {
            temp.next = l2;
        }
        return head.next;
    }

    // This function calculates the median in O(M+N) time
    // LC: https://leetcode.com/problems/median-of-two-sorted-arrays/
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null && nums2 == null) {
            return 0.0;
        }
        if (nums1 == null) {
            return Double.valueOf(nums2.length);
        }
        if (nums2 == null) {
            return Double.valueOf(nums1.length);
        }
        return calculate(nums1, nums2, nums1.length, nums2.length);
    }

    // merge 2 sorted arrays
    private static double calculate(int[] A, int[] B, int m, int n) {
        int[] C = new int[m + n];
        int p1 = 0, p2 = 0, curr = 0;
        while (p1 <= m - 1 && p2 <= n - 1) {
            if (A[p1] <= B[p2]) {
                C[curr++] = A[p1++];
            } else {
                C[curr++] = B[p2++];
            }
        }
        while (p1 < m) {
            C[curr++] = A[p1++];
        }
        while (p2 < n) {
            C[curr++] = B[p2++];
        }
        System.out.println(Arrays.toString(C));
        return calcMedian(C);
    }

    private static double calcMedian(int[] A) {
        int N = A.length;
        int lo = 0;
        int hi = A.length - 1;
        int mid = ((lo + hi) / 2);
        if (N % 2 == 0) {
            int next = mid + 1;
            double add = (double) (A[next] + A[mid]);
            return (add / 2.0);
        } else {
            return (double) (A[mid]);
        }
    }

    // Count number of common elements in A, and B, does it in O(M+N) time
    static int numberOfIntersections(int[] A, int[] B) {
        if (A == null && B == null) {
            return 0;
        }
        if (A == null) {
            return B.length;
        }
        if (B == null) {
            return A.length;
        }
        int p1 = 0, p2 = 0;
        HashSet<Integer> hs = new HashSet<>();
        while (p1 < A.length - 1 && p2 < B.length - 1) {
            if (A[p1] == B[p2]) {
                hs.add(A[p1]);
                p1++;
                p2++;
            } else if (A[p1] < B[p2]) {
                p1++;
            } else {
                p2++;
            }
        }
        return hs.size();
    }

    /*
     * // O(M+N) Possible Follow up: Follow up: Given access to multicore processor,
     * how would you implment the same function so that we can do an union faster
     * (in terms of time complexity) Hint: split the inputs to perform union and
     * merge them back in O(1) time.
     */
    static int[] unionTwoSortedArrays(int[] A, int[] B) {
        if (A == null && B == null) {
            return null;
        }
        if (A == null) {
            return B;
        }
        if (B == null) {
            return A;
        }
        TreeSet<Integer> ss = new TreeSet<>();
        int i = 0, j = 0;
        while (i < A.length - 1 && j < B.length - 1) {
            if (A[i] <= B[j]) {
                ss.add(A[i]);
                i++;
            } else {
                ss.add(B[j]);
                j++;
            }
        }
        while (i < A.length) {
            ss.add(A[i]);
            i++;
        }
        while (j < B.length) {
            ss.add(B[j]);
            j++;
        }
        int[] C = new int[ss.size()];
        int x = 0;
        for (Integer elem : ss) {
            C[x++] = elem;
        }
        return C;
    }

    static int[] topK(int[] arr, int k) {
        TreeSet<Integer> ts = new TreeSet<>();
        for (int i = 0; i < arr.length; i++) {
            ts.add(arr[i]);
            if (ts.size() > k) {
                ts.pollFirst();
            }
        }
        int[] aa = new int[ts.size()];
        int ctr = 0;
        for (Integer elem : ts) {
            aa[ctr++] = elem;
        }
        return aa;
    }

    private static String buildRes(int x, int y, int z) {
        StringBuilder sb = new StringBuilder();
        sb.append(x).append(", ").append(y).append(", ").append(z);
        return sb.toString();
    }

    // Find a triplet whose sum is 0
    static String[] findZeroSum(int[] A) {
        if (A.length < 3) {
            return null;
        }
        HashSet<String> hs = new HashSet<>();
        Arrays.sort(A);
        for (int curr = 0; curr < A.length; curr++) {
            int first = A[curr];
            int X = -first; // basicaly checking for a + b = -c
            int lo = curr + 1;
            int hi = A.length - 1;
            while (lo < hi) {
                int currSum = A[lo] + A[hi];
                if (currSum == X) {
                    String s = buildRes(A[lo], A[hi], first);
                    hs.add(s);
                    lo++;
                } else if (currSum < X) {
                    lo++;
                } else {
                    hi--;
                }
            }
        }
        String[] res = new String[hs.size()];
        return hs.toArray(res);
    }

    private static String prepStr(int[] A, int a, int b) {
        StringBuilder sb = new StringBuilder();
        sb.append(A[a]);
        sb.append(", ");
        sb.append(A[b]);
        return sb.toString();
    }

    // Find a pair of integers whose sum is equal to the given target.
    public static String[] twoSum(int[] A, int k) {
        if (A == null || A.length == 0) {
            return null;
        }
        Arrays.sort(A);
        HashSet<String> res = new HashSet<>();
        int le = 0;
        int ri = A.length - 1;
        while (le <= ri) {
            int currSum = A[le] + A[ri];
            if (currSum == k) {
                res.add(prepStr(A, le, ri));
                le++;
                ri--;
            } else if (currSum < k) {
                le++;
            } else {
                ri--;
            }
        }
        String[] r = new String[res.size()];
        return res.toArray(r);
    }

    // O(N), lastA corresponds to non-zero location in array A.
    public static int[] mergeOneArrayIntoAnother(int[] a, int[] b, int lastA, int lastB) {
        int mergedIndx = a.length - 1;
        while (lastB >= 0) {
            if (lastA >= 0 && a[lastA] > b[lastB]) {
                a[mergedIndx] = a[lastA];
                lastA--;
            } else {
                a[mergedIndx] = b[lastB];
                lastB--;
            }
            mergedIndx--;
        }
        return a;
    }

    public static int kthSmallestUsingMaxHeap(List<Integer> L, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < k; i++) {
            pq.add(L.get(i));
        }
        for (int i = k; i < L.size(); i++) {
            if (L.get(i) < pq.peek()) {
                pq.poll();
                pq.add(L.get(i));
            }
        }
        return pq.peek();
    }

    // O(NLogk)
    public static int kthSmallestUsingMinHeap(List<Integer> aa, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.addAll(aa);
        while (--k > 0) {
            pq.poll();
        }
        return pq.peek();
    }

    // O(k+(N - k)Log k)
    public static int kthLargestUsingMinHeap(List<Integer> ip, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(ip.subList(0, k));
        // int i = k + 1;
        for (int i = k; i < ip.size(); i++) {
            if (ip.get(i) > pq.peek()) {
                pq.poll();
                pq.add(ip.get(i));
            }
        }
        return pq.peek();

    }

    // O(N + KLogN), call extractMax, k times, each extractMax happens in LogN
    // times.
    public static int kthLargestUsingMaxHeap(List<Integer> a, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a1, b) -> b - a1);
        pq.addAll(a);
        // pop k - 1 times
        while (--k > 0) {
            pq.poll();
        }
        return pq.peek();
    }

    // This function returns the kth largest using QuickSelect Algo. The partition
    // function uses Quicksort's partition ligic to select
    // the leftmost element as the pivot
    // O(N)
    public static int kthLargestUsingQuickSelect(int[] a, int k) {
        if (a.length == 0 || a == null || k < 0 || k > a.length) {
            return -1;
        }
        return qs(a, 0, a.length - 1, a.length - k);
    }

    public static int qs(int[] a, int lo, int hi, int idx) {
        if (lo > hi) {
            return -1;
        }
        int piv = partition(a, lo, hi);
        if (idx < piv) {
            qs(a, lo, piv - 1, idx);
        } else if (idx > piv) {
            qs(a, piv + 1, hi, idx);
        }
        return a[piv];
    }

    static int partition(int[] a, int lo, int hi) {
        int piv = a[lo];
        int idx = -1;
        for (int j = 0; j <= hi; j++) {
            if (a[j] <= piv) {
                idx++;
                swap(a, idx, j);
            }
        }
        swap(a, idx + 1, lo);
        return idx + 1;
    }

    static void swap(int[] a, int x, int y) {
        int tt = a[x];
        a[x] = a[y];
        a[y] = tt;
    }

    // This function tries to separate all evens to the left and odds to the right
    // in O(N) time
    static int[] solve(int[] arr) {
        int lo = 0, hi = arr.length - 1, mid = 0;
        while (mid <= hi) {
            switch (arr[mid] % 2) {
                case 0:
                    swap(arr, lo, mid);
                    lo++;
                    mid++;
                    break;
                case 1:
                    swap(arr, mid, hi);
                    hi--;
            }
        }
        return arr;

    }

    static boolean isSortAsc(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i][0] < a[i][a[i].length - 1]) {
                return true;
            }
        }
        return false;
    }

    static int[] mergeArrays(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        boolean isAsc = isSortAsc(arr);
        return ms(arr, isAsc, 0, arr.length - 1);
    }

    static int[] ms(int[][] a, boolean isAsc, int lo, int hi) {
        if (lo >= hi) {
            return a[lo];
        }
        int mid = lo + ((hi - lo) / 2);
        int[] le = ms(a, isAsc, lo, mid);
        int[] ri = ms(a, isAsc, mid + 1, hi);
        return merge(le, ri, isAsc);
    }

    static int[] merge(int[] a, int[] b, boolean isAsc) {
        int l = 0, r = 0, curr = 0;
        int[] mm = new int[a.length + b.length];
        if (a.length == 0) {
            return b;
        }
        if (b.length == 0) {
            return a;
        }
        while (l < a.length && r < b.length) {
            if (isAsc) {
                if (a[l] <= b[r]) {
                    mm[curr++] = a[l++];
                } else {
                    mm[curr++] = b[r++];
                }
            } else {
                if (a[l] > b[r]) {
                    mm[curr++] = a[l++];
                } else {
                    mm[curr++] = b[r++];
                }
            }
        }
        while (l < a.length) {
            mm[curr++] = a[l++];
        }
        while (r < b.length) {
            mm[curr++] = b[r++];
        }

        return mm;
    }

    public static void main(String[] args) {
        int[] A = new int[] { 7, 10, 4, 3, 15, 20 };
        List<Integer> ll = new ArrayList<>();
        ll.add(7);
        ll.add(10);
        ll.add(4);
        ll.add(3);
        ll.add(15);
        ll.add(20);

        System.out.println("Kth (k=3) Largest using Quickselect: " + kthLargestUsingQuickSelect(A, 3)); // op: 7
        System.out.println("Kth (k=4) Largest using Maxheap: " + kthLargestUsingMaxHeap(ll, 4)); // op: 7
        System.out.println("Kth (k=1) Largest using Minheap: " + kthLargestUsingMinHeap(ll, 1)); // op: 20
        System.out.println("Kth (k=1) Smallest using Minheap: " + kthSmallestUsingMinHeap(ll, 1)); // op:3
        System.out.println("Kth Smallest using Maxheap: " + kthSmallestUsingMaxHeap(ll, 5)); // op:15
        int[] arr1 = new int[] { 1, 3, 5 };
        int[] arr2 = new int[] { 2, 4, 6, 0, 0, 0 };
        System.out.println("Merged arr2 into arr1 " + Arrays.toString(mergeOneArrayIntoAnother(arr2, arr1, 2, 2)));
        int[] B = new int[] { 0, -2, 2, 2, 0 };
        System.out.println("2 sum for target=0 " + Arrays.toString(twoSum(B, 0)));
        int[] a1 = new int[] { 1, 3, 4, 5, 7 };
        int[] a2 = new int[] { 2, 3, 5, 8 };
        System.out.println(Arrays.toString(unionTwoSortedArrays(a1, a2)));
        int[] a11 = new int[] { 1, 3 };
        int[] a12 = new int[] { 2 };
        System.out.println("Median= " + findMedianSortedArrays(a11, a12));

        ListNode l0 = new ListNode(1);
        ListNode l11 = new ListNode(2);
        ListNode l12 = new ListNode(4);
        l0.next = l11;
        l0.next.next = l12;
        l12.next = null;

        ListNode l00 = new ListNode(2);
        ListNode l011 = new ListNode(3);
        ListNode l012 = new ListNode(4);
        l00.next = l011;
        l00.next.next = l012;
        l012.next = null;

        printList(l0);
        printList(l00);
        System.out.println("Merged sorted List");
        printList(mergeTwoLists(l0, l00));
    }
}