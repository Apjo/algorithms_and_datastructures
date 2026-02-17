package dsa.java.datastructures.trees;

import java.util.Arrays;

/**
 * Wiki: <a href= https://en.wikipedia.org/wiki/Segment_tree</a>

 * Helps in answering range queries. O(n) time to build, O(n) space, and O(log n) to answer a query.
 * An array [-1,3,4,0,2,1] will be represented as a segment tree as(ex: to find minimum between a given range):
 * Step 1 : Split the array into 2 halves, left half = [-1,3,4], and right half = [0,2,1], and continue splitting them
 * into halves
 * [-1, 3], [4], [0,2], [1]
 * Step 2: Once the leaves of the tree are decided we then recursively find the minimum between the elements and go up.
 * Finally, we end up having the minimum element at the root, which is the minimum between the entire range in our example
 * [0..5]. The `[]` around the integers in the tree represent the minimum element in that range
 *
 *                          -1[0, 5]
 *              /                                \
 *         -1[0, 2]                             0[3, 5]
 *        /       \                           /        \
 *    -1[0, 1]   4[2, 2]                0[3, 4]    1[5, 5]
 *   /            \                    /        \
 * -1[0, 0]        3[1,1]           0[3, 3]    2[4, 4]
 *
 * Step 3: Now, say we want to find the minimum element in the range [2..4], we need to remember 3 cases:
 * 1. Partial overlap of intervals: i.e. the given(requested) interval partially overlaps the known intervals.
 * In this case we look on both the sides of the tree
 * 2. No overlap: stop, and return a really large number
 * 3. Total overlap : stop, and return a really large number
 * BUILDING a Segment tree from an Array of Length N:
 * if N is even: the size of the array will be 2*N - 1
 * if N is odd:  Find the next power of 2, and then do 2*N - 1
 * We number the elements starting from 0(denoting the root of the binary tree)
 * To get the LEFT child: 2*i + 1
 * To get the RIGHT child: 2*i + 2
 * To get the PARENT: (i - 1) / 2
 * The size of this array in worst case will be atleast 4* size of the input array
 */
public class SegmentTreeDemo {
    private int nextPowerOf2(int N) {
        if (N == 0) {return 1; }
        if (N > 0 && (N & (N - 1)) == 0) { return N; }
        while ((N & (N - 1)) > 0) {
            N = N & (N - 1);
        }
        return N << 1;
    }
    public int[] constructSegmentTree(int[] input) {
        int N = input.length;
        int actual = nextPowerOf2(N);
        int[]segmentTree = new int[actual*2 - 1]; //Length of binary tree represented as array will be 2*N -1
        Arrays.fill(segmentTree, Integer.MAX_VALUE);
        //pos = 0         : indicates the location of the root of the segment tree
        //pos = 2*pos + 1 : the left child of the segment tree at pos=pos
        //pos = 2*pos + 2 : the right child of the segment tree at pos=pos
        buildTree(segmentTree, input, 0 , input.length - 1, 0);
        return segmentTree;
    }
    public int rangeMinimumQuery(int[]segTree, int low, int high, int left, int right, int pos) {
        //NO OVERLAPPING INTERVAL
        if (left > high || right < low) {
            return Integer.MAX_VALUE;
        }
        //COMPLETE OVERLAPPING INTERVAL FOUND
        if (left <= low && right >= high) {
            return segTree[pos];
        }
        //ELSE, search on both Left & Right halves
        int mid = (low + high) / 2;
        return Math.min(
                rangeMinimumQuery(segTree, low, mid, left, right, 2 *pos + 1),
                rangeMinimumQuery(segTree, mid + 1, high, left, right, 2 *pos + 2));
    }

    private void buildTree(int []segTree, int[] input, int low, int high, int pos) {
        if (low == high) {
            segTree[pos] = input[low];
            return;
        }
        int mid = (low + high) / 2;
        buildTree(segTree, input, low, mid, 2*pos + 1); //traverse left
        buildTree(segTree, input, mid + 1, high, 2*pos + 2); //traverse right
        segTree[pos] = Math.min(segTree[2*pos + 1], segTree[2*pos + 2]);
    }

    public static void main(String[] args) {
        SegmentTreeDemo st = new SegmentTreeDemo();

        int[] input = {0,3,4,2,1,6,-1};

        System.out.println("INPUT ARRAY= " + Arrays.toString(input));
        int[] segTree = st.constructSegmentTree(input);
        System.out.println("SEG TREE ARRAY= " + Arrays.toString(segTree));
        assert 1 == st.rangeMinimumQuery(segTree, 0, input.length - 1, 1, 5, 0);
    }

}
