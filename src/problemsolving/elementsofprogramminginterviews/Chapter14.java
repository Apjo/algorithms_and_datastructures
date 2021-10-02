package problemsolving.elementsofprogramminginterviews;

import datastructures.trees.BinarySearchTreeDemo.*;

import java.util.*;

public class Chapter14 {
    //O(log n)
    public static boolean isBst(Node root) {
        return isTrueBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    private static boolean isTrueBST(Node r, long min, long ma) {
        if (r == null) {
            return true;
        } else if (r.getData() <= min || r.getData() >= ma) {
            return false;
        }
        return isTrueBST(r.getLeft(), min, r.getData()) && isTrueBST(r.getRight(), r.getData(), ma);
    }
    public static Node inorderSuccessor(Node root, Node p) {
        Node ans = null;
        if (root == null) {
            return null;
        }
        //case1: if the node has a rightsubtree, find the smallest node in that subtree
        if (p.getRight() != null) {
            Node temp = p.getRight();
            while (temp.getLeft() != null) {
                temp = temp.getLeft();
            }
            ans = temp;
        }//case2: if the node has no right subtree, then find the node p, and the node from which we took the "last left turn" will be our successor
        else {
            Node temp = root;
            while (temp.getData() != p.getData()) {
                if (p.getData() <= temp.getData()) {
                    ans = temp;
                    temp = temp.getLeft();
                } else {
                    temp = temp.getRight();
                }
            }
        }
        return ans;
    }
    //Implement a BST Iterator where next() and hasNext() runs in avg. O(1) time
    //Ref: https://leetcode.com/explore/learn/card/introduction-to-data-structure-binary-search-tree/140/introduction-to-a-bst/1008/discuss/52526/Ideal-Solution-using-Stack-(Java)
    class BSTIterator {
        private Stack<Node> st;
        private Node temp;
        public BSTIterator(Node root) {
            st = new Stack<>();
            temp = root;
        }

        public int next() {
            while(temp != null) {
                st.push(temp);
                temp = temp.getLeft();
            }
            Node t = st.pop();
            int v = t.getData();
            //now traverse right
            t = t.getRight();
            while (t != null) {
                st.push(t);
                t = t.getLeft();
            }
            return v;
        }

        public boolean hasNext() {
            return (!st.isEmpty() || temp != null);
        }
    }
    public static Node search(Node r, int v) {
        if (r == null) {
            return null;
        }
        Node temp = r;
        while (temp != null) {
            if (temp.getData() == v) {
                return temp;
            } else if (v < temp.getData()) {
                temp = temp.getLeft();
            } else {
                temp = temp.getRight();
            }
        }
        return null;
    }
    private static int minimumKey(Node r) {
        while (r.getLeft() != null) {
            r = r.getLeft();
        }
        return r.data;
    }
    public Node deleteNode(Node root, int key) {
        //first try to search the node
        //once you find the node to delete
        //case 1: no child -> delete the node
        //case 2: single child -> replace -> delete
        if (root == null) {
            return root;
        }
        if (key < root.data) {
            root.left = deleteNode(root.getLeft(), key);
        } else if (key > root.data) {
            root.right = deleteNode(root.getRight(), key);
        } else {
            if (root.getLeft() == null) {
                return root.getRight();
            }
            if (root.getRight() == null) {
                return root.getLeft();
            }
            //case 3: 2 children -> find inorder successor -> replace -> delete

            root.data = minimumKey(root.right);
            root.right = deleteNode(root.right, root.data);
        }
        return root;
    }
    //this can be done using the following approaches
    // 1. min heap
    // 2. augmented BST search + insert (O(log N)) --> ht balanced BST else it will be O(N*h) ~ O(N^2) N: no.of nodes in BST
    // 3. inorder traversal
    // 4. reverse morris inorder traversal
    private static class AugBstNode {
        AugBstNode left ,right;
        int val, count;
        public AugBstNode(int v, int c) {
            this.val = v;
            this.count = c;
        }
    }
    public static class KthLargestInStreamAugUsingBST {
        private AugBstNode root;
        private int k;
        public KthLargestInStreamAugUsingBST(int k, int[] nums) {
            this.k = k;
            for (int i = 0; i < nums.length; i++) {
                root = insertAugBst(root, nums[i]);
            }
        }
        private static AugBstNode insertAugBst(AugBstNode root, int v) {
            if (root == null) {
                return new AugBstNode(v, 1);
            } else if (v < root.val) {
                root.left = insertAugBst(root.left, v);
            } else {
                root.right = insertAugBst(root.right, v);
            }
            return root;
        }
        public static int kthLargest(AugBstNode root, int k) {
            int v = root.right != null ? root.right.val : 0;
            if (k == v + 1) {
                return root.val;
            } else if (k <= v) {
                return kthLargest(root.right, k);
            } else {
                return kthLargest(root.left, k - v -1);
            }
        }
        public static int add(int val) {
            root = insertAugBst(root, val);
            return kthLargest(root, k);
        }
    }
    public static Node lca(Node root, Node p, Node q) {
        if (root.val < p.val && root.val < q.val) {
            return lca(root.right, p, q);
        } else if (root.val > p.val && root.val > q.val) {
            return lca(root.left, p, q);
        }
        return root;
    }
    public static Node LcaBstIter(Node root, Node p, Node q) {
        while(root != null) {
            if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else {
                return root;
            }
        }
        return root;
    }

    //using BST
    /*
    algo:
    Initialize an empty BST set
    Loop through the array, for each element x
    Find the smallest element s in set that is greater than or equal to x, return true if s−x ≤ t ==> successor (ceiling)
    Find the greatest element g in set that is smaller than or equal to x, return true if x−g ≤ t ==> predecessor (floor)
    Put x in set
    If the size of the set is larger than k, remove the oldest item.
    Return false
    TC: O(nlog(min(n, k))), space: O(min(n, k))
     */
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || k < 1 || t < 1) {
            return false;
        }
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            long numL = (long) nums[i];
            //get successor
            Long succ = set.ceiling(numL);
            //get predecessor
            Long pred = set.floor(numL);
            if (succ != null && succ - numL <= t) {
                return true;
            }
            if (pred != null && numL - pred <= t) {
                return true;
            }
            set.add(numL);
            //remove last
            if (set.size() >= k) {
                set.remove((long)nums[i - k ]);
            }
        }
        return false;
    }
/*
Algo2: using buckets like bucket sort
remember: For a given element x is there an item in the window that is within the range of [x-t, x+t]?
time: O(N) for each N elements we do 3 searches  one insert,
and one delete on the HashMap, which costs constant time on average. Thus, the entire algorithm costs O(n)O(n) time.
space:
 */
    private static Long getId(long a, long b) {
        return (a < 0 ? (a + 1)/ b - 1 : a/b);
    }
    public static boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        Map<Long, Long> m = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long w = (long)(t + 1);
            long longNum = (long)nums[i] - Integer.MIN_VALUE;
            long bucketId = longNum / w;
            if (m.containsKey(bucketId)) {
                return true;
            }
            //check for boundaries x - t
            if (m.containsKey(bucketId - 1) && Math.abs((long)nums[i] - m.get(bucketId - 1)) <= t) {
                return true;
            }
            //x + t
            if (m.containsKey(bucketId + 1) && Math.abs((long)nums[i] - m.get(bucketId + 1)) <= t) {
                return true;
            }
            if (m.size() >= k) {
                long lastBucket = ((long)nums[i - k] - Integer.MIN_VALUE) / w;
                m.remove(lastBucket);
            }
            m.put(bucketId, longNum);
        }
        return false;
    }
    private static int ht(TreeNode r) {
        if (r == null) {
            return 0;
        }
        return Math.max(ht(r.left), ht(r.right)) + 1;
    }
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int lh = ht(root.left);
        int rh = ht(root.right);
        if (Math.abs(lh - rh) > 1) {
            return false;
        }
        return (lh - rh) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
    public TreeNode sortedArrayToBST(int[] nums) {

    }

}
