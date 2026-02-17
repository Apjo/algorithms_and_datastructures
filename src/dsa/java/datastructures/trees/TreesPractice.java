package dsa.java.datastructures.trees;
import dsa.java.datastructures.trees.BinarySearchTreeDemo.*;

import java.util.*;

//Tree problems from Tushar Roy, William Fisset, and Vivekananda Khyade's Youtube playlists
/**
 * Tree BFS Template:
 *      void bfs(Node  root) {
 *
 *      }
 * Tree DFS Template:
 *    def caller(Node root) {
 *        if root == null
 *              return <appropriate result>
 *        dfs(root, [optional params...])
 *        return result
 *    }
 *    void dfs(Node root, optional params...) {
 *        Base case answer generated here
 *        if root.left == null && root.left == null
 *       Recursive case: internal nodes
 *        if root.left != null
 *          dfs(root.left)
 *        if root.right != null
 *            dfs(root.right)
 *    }
 */
public class TreesPractice {
    public static int verticalSum(Node root) {
        return -1;
    }

    public static void verticalTraversal(Node root) {

    }
    public static int diagonalSum(Node root) {
        return -1;
    }

    public static void diagonalTraversal(Node root) {
    }

    public static List<Integer> bottomView(Node root) {
        if (root == null) { return new ArrayList<>(); }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        TreeMap<Integer, Node> tm = new TreeMap<>();
        tm.put(0, root);
        while (!q.isEmpty()) {
            Node curr = q.poll();

        }

        return null;
    }

    public static void binaryTreeToDLL(Node root) {

    }

    //Print all nodes having K leaves in its subtree
    public static int countKLeaves(Node root, int k) {
        if (root == null) { return 0; }
        if (root.left == null && root.right == null) { return 1; }
        int tc = countKLeaves(root.left, k) + countKLeaves(root.right, k);
        if (tc == k) {
            System.out.print(root.data + " ");
        }
        return tc;
    }
    //Given a binary tree and a number, return the path if the tree has a root-to-leaf path such that adding up all the values
    // along the path equals the given number. Return an empty list if no such path can be found.
    public static List<Integer> rootToLeafSumPath(Node root, int k) {
        if (root == null) { return new ArrayList<>(); }
        List<Integer> result = new ArrayList<>();
        solveHasPathSum(root, k, result);
        return result;
    }

    private static void solveHasPathSum(Node root, int k, List<Integer> buff) {
        k = k - root.data;
        if (root.left == null && root.right == null) {
            if (root.data == k) {
                buff.add(root.data);
            }
        }
        if (root.left != null) {
            solveHasPathSum(root.left, k, buff);
        }
        if (root.right != null) {
            solveHasPathSum(root.right, k, buff);
        }
    }

    public static boolean isIsoMorphic(Node A, Node B) {
        if (A == null && B == null) { return true ;}
        if (A == null || B == null) { return false; }
        if (A.data != B.data) { return false; }

        return (isIsoMorphic(A.left, B.left) && isIsoMorphic(A.right, B.right)
                || isIsoMorphic(A.left, B.right) && isIsoMorphic(A.right, B.left));
    }

    //Diameter is the number of nodes on the longest path in a binary tree.
    public static int diameter(Node root) {
        if (root == null) { return 0; }
        int leftHeight = heightOfTree(root.left);
        int rightHeight = heightOfTree(root.right);
        int leftDia = diameter(root.left);
        int rightDia = diameter(root.right);
        //return the diameter when the dia is calculated if the root node is involved, and when the root node is not involved
        return Math.max(leftHeight + rightHeight + 1, Math.max(leftDia, rightDia));
    }
    public static int countHowManyBSTs(int N) {
        int[]memo = new int[N];
        Arrays.fill(memo, -1);
        if (N <= 1) {
            return 1;
        }
        if (memo[N] != -1) {
            return memo[N];
        }
        int ctr = 0;
        for (int i = 0; i < N; i++) {
            int numBSTsInRight = N - 1 - i;
            ctr += countHowManyBSTs(i) * countHowManyBSTs(numBSTsInRight);
        }
        memo[N] = ctr;
        return ctr;
    }

    public static Node constructBSTFromArray(int []nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        Node root = solve(nums, 0, nums.length - 1);
        return root;
    }
    private static Node solve(int[] a, int lo, int hi) {
        if (lo > hi) { return null; }
        int mid = (lo + hi) / 2;
        Node root = Node.newNode(a[mid]);
        root.left = solve(a, lo, mid - 1);
        root.right = solve(a, mid + 1, hi);
        return root;
    }

    public static Node lcaBinaryTree(Node root, Node a, Node b) {
        if (root == null) { return null; }
        if (a == null || b == null) { return root; }
        Node left = lcaBinaryTree(root.left, a, b);
        Node right = lcaBinaryTree(root.right, a, b);
        if (left == null && right == null) { return null;}
        if (left != null &&  right != null) { return root; }
        return left == null ? right:left;
    }

    public static Node lcaBST(Node root, int a, int b) {
        if (root == null) { return null; }
        if (root.data > Math.max(a, b)) {
            return lcaBST(root.left, a, b);
        }
        else if (root.data < Math.min(a, b)) {
            return lcaBST(root.right, a, b);
        }
        else {
            return root;
        }
    }

    public static void spiralTraversal(Node root) {
        if (root == null) { return ;}
        Stack<Node> st1 = new Stack<>();
        st1.push(root);
        Stack<Node> st2 = new Stack<>();
        while(!st1.isEmpty() || !st2.isEmpty()) {
            while (!st1.isEmpty()) {
                Node curr = st1.pop();
                System.out.print(curr.data + " ");
                if (curr.left != null) {
                    st2.push(curr.left);
                }
                if (curr.right != null) {
                    st2.push(curr.right);
                }
            }
            while (!st2.isEmpty()) {
                Node curr = st2.pop();
                System.out.print(curr.data + " ");
                if (curr.right != null) {
                    st1.push(curr.right);
                }
                if (curr.left != null) {
                    st1.push(curr.left);
                }
            }
        }
    }

    public static void reverseLevelOrderTraversal(Node root) {
        if (root == null) { return; }
        Stack<Node> st = new Stack<>();
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node curr = q.poll();
            if (curr.right!= null) {
                q.add(curr.right);
            }
            if (curr.left!= null) {
                q.add(curr.left);
            }
            st.push(curr);
        }
        while(!st.isEmpty()) {
            root = st.pop();
            System.out.print(root.data + " ");
        }
    }
    public static void printLevelByLevelTree(Node root) {
        int childCnt = 0;
        int levelCnt = 1;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            while(levelCnt > 0) {
                Node curr = q.poll();
                System.out.print(curr.data + " ");
                if (curr.left != null) {
                    q.add(curr.left);
                    childCnt++;
                }
                if (curr.right != null) {
                    q.add(curr.right);
                    childCnt++;
                }
                levelCnt --;
            }
            System.out.println();
            levelCnt = childCnt;
            childCnt = 0;
        }
    }
    public static void inorderIter1Stack(Node root) {
        if (root == null) { return; }
        Stack<Node> st = new Stack<>();
        while(true) {
            if (root != null) {
                st.push(root);
                root = root.left;
            } else {
                if (st.isEmpty()) {
                    break;
                }
                root = st.pop();
                System.out.print(root.data + " ");
                root = root.right;
            }
        }
    }
    public static void preOrderIter(Node root) {
        if (root == null) { return; }
        Stack<Node> st = new Stack<>();
        st.push(root);
        while (!st.isEmpty()) {
            Node curr = st.pop();
            System.out.print(curr.data + " ");
            if (curr.left != null) {
                st.push(curr.left);
            }
            if (curr.right != null) {
                st.push(curr.right);
            }
        }
    }
    public static void postOrderIterTwoStacks(Node root) {
        if (root == null) { return ;}
        Stack<Node> st1 = new Stack<>();
        Stack<Node> st2 = new Stack<>();
        st1.add(root);
        while(!st1.isEmpty()) {
            Node curr = st1.pop();
            if (curr.left != null) {
                st1.push(curr.left);
            }
            if (curr.right != null) {
                st1.push(curr.right);
            }
            st2.push(curr);
        }
        while(!st2.isEmpty()) {
            System.out.print(st2.pop().data + " ");
        }
    }

    public static void levelOrderTraversal(Node root) {
        if (root == null) {return ; }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        System.out.println();
        while(!q.isEmpty()) {
            Node curr = q.poll();
            System.out.print(curr.data + " ");
            if (curr.left != null) {
                q.add(curr.left);
            }
            if (curr.right != null) {
                q.add(curr.right);
            }
            System.out.println();
        }
    }
    //O(N)
    public static boolean isBST(Node root, int min, int max) {
        if (root == null) { return true;}
        if (root.data <= min || root.data > max) {
            return false;
        }
        return isBST(root.left, min, root.data) && isBST(root.right, root.data, max);
    }

    //O(N) where N is the number of nodes in the tree
    public static boolean isSameTree(Node t1, Node t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        return (t1.data == t2.data && isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right));
    }
    //O(N) where N is the number of nodes in the tree
    public static int sizeOfBinaryTree(Node t) {
        if (t == null) {return 0;}
        int leftCount = sizeOfBinaryTree(t.left);
        int rightCount = sizeOfBinaryTree(t.right);
        return 1 + leftCount + rightCount;
    }
    //O(N) where N is the number of nodes in the tree
    public static int heightOfTree(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(heightOfTree(root.left), heightOfTree(root.right));
    }
    //This is the DFS on a tree pattern as mentioned above
    public static boolean hasPathSumDfs(Node root, int target) {
        if (root == null) {return false; }
        boolean[]result = new boolean[]{false};
        dfs(root, result, target);
        return result[0];
    }
    private static void dfs(Node root, boolean[]res, int k) {
        k = k - root.data;
        if (root.left == null && root.right == null) {
            if (k == 0) {
                res[0] = true;
            }
        }
        if (root.left != null) {
            dfs(root.left, res, k);
        }
        if (root.right != null) {
            dfs(root.right, res, k);
        }
    }
    //O(N)
    public static boolean hasPathSum(Node root, int target, List<Integer> path) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            if (root.data == target) {
                path.add(root.data);
                return true;
            }
        }
        if (hasPathSum(root.left, target - root.data, path) || hasPathSum(root.right, target - root.data, path)) {
            path.add(root.data);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        BinarySearchTreeDemo t1 = new BinarySearchTreeDemo();
        Node root1 = t1.insertKey(null, 50);
        BinarySearchTreeDemo t2 = new BinarySearchTreeDemo();
        Node root2 = t2.insertKey(null, 50);
        System.out.println("Creating Tree 1");
        t1.insertKey(root1, 30);
        t1.insertKey(root1, 20);
        t1.insertKey(root1, 40);
        t1.insertKey(root1, 70);
        t1.insertKey(root1, 60);
        t1.insertKey(root1, 80);
        System.out.println("Tree 1 is");
        t1.printTree("", root1, false);
        System.out.println();
        System.out.println("Creating Tree 2");
        t2.insertKey(root2, 30);
        t2.insertKey(root2, 20);
        t2.insertKey(root2, 40);
        t2.insertKey(root2, 70);
        t2.insertKey(root2, 60);
        t2.insertKey(root2, 80);
        System.out.println("Tree 2 is");
        t2.printTree("", root2, false);
        System.out.println();
        System.out.println("Are Trees tree1 and tree2 are same? " + isSameTree(root1, root2));
        System.out.println("Size tree 1 is= " + sizeOfBinaryTree(root1));
        System.out.println("Height tree 2 is= " + heightOfTree(root2));
        List<Integer> path = new ArrayList<>();
        boolean hasPath = hasPathSum(root1, 100, path);
        if (hasPath) {
            System.out.println("Path from root to leaf having sum=100");
            System.out.println(Arrays.toString(path.toArray()));
        }
        System.out.println("tree 1 isBst?= " + isBST(root1, Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println("Level order traversal of Tree 2");
        levelOrderTraversal(root2);
        System.out.println("POSTORDER Iterative traversal of Tree 2 using 2 Stacks");
        postOrderIterTwoStacks(root2);
        System.out.println();
        System.out.println("PREORDER Iterative traversal of Tree 2");
        preOrderIter(root2);
        System.out.println();
        System.out.println("INORDER Iterative traversal of Tree 2");
        inorderIter1Stack(root2);
        System.out.println();
        System.out.println("Level by level order printing of Tree 2");
        printLevelByLevelTree(root2);
        System.out.println();
        System.out.println("REVERSE Level by level order printing of Tree 2");
        reverseLevelOrderTraversal(root2);
        System.out.println();
        System.out.println("SPIRAL order printing of Tree 2");
        spiralTraversal(root2);
        System.out.println();
        System.out.println("LOWEST COMMON ANCESTOR of (Node=20, Node=80) BST Tree 1= " + lcaBST(root1, 20, 80).data);
        System.out.println();
        Node a = t1.searchKey(root1, 50);
        Node b = t1.searchKey(root1, 80);
        System.out.println("LOWEST COMMON ANCESTOR of (Node=50, Node=80) BST Tree 2= " + lcaBinaryTree(root1, a, b).data);
        int[]nums = new int[]{-10,-3,0,5,9};
        System.out.println("BST from a sorted array");
        Node root3 = constructBSTFromArray(nums);
        printLevelByLevelTree(root3);
    }
}
