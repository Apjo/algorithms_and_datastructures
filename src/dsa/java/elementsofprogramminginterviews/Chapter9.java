package dsa.java.elementsofprogramminginterviews;

import java.util.*;

import dsa.java.elementsofprogramminginterviews.Chapter9.TreeChapter.TreeNode;
//Dt: 06/07/2021
public class Chapter9 {
    static class TreeChapter {
        TreeChapter root;
        static class TreeNode {
            int data;
            TreeNode left, right;
            public TreeNode(int v) {
                this.data = v;
                this.left = this.right = null;
            }
        }
    }
    //O(N) time, implicit stack space of height h O(h)
    public static List<Integer> preOrderRec(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        preOrderRec(root.left);
        res.add(root.data);
        preOrderRec(root.right);
        return res;
    }

    public static List<Integer> preOrderIter(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        while (!st.isEmpty()) {
            TreeNode curr = st.pop();
            res.add(curr.data);
            if (curr.right != null) {//add right child first
                st.push(curr.right);
            }
            if (curr.left != null) {//add left child
                st.push(curr.left);
            }
        }
        return res;
    }
    public static List<Integer> postOrderRec(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();

        postOrderRec(root.left);
        postOrderRec(root.right);
        res.add(root.data);

        return res;
    }
    public static List<Integer> postOrderIter(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> st1 = new Stack<>();
        st1.push(root);
        Stack<TreeNode> st2 = new Stack<>();
        while (!st1.isEmpty()) {
            TreeNode curr = st1.pop();
            st2.push(curr);
            if (curr.left != null) { //add left child first
                st1.push(curr.left);
            }
            if (curr.right != null) { //then add right child
                st1.push(curr.right);
            }
        }
        while (!st2.isEmpty()) {
            res.add(st2.pop().data);
        }
        return res;
    }
    public static List<Integer> inorderRec(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();

        res.add(root.data);
        inorderRec(root.left);
        inorderRec(root.right);

        return res;
    }
    public static List<Integer> inorderIter(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Stack<TreeNode> st1 = new Stack<>();
        List<Integer> res = new ArrayList<>();
        while (true) {
            if (root != null) {
                st1.push(root);
                root = root.left;
            } else {
                if (st1.isEmpty()) {
                    break;
                } else {
                    root = st1.pop();
                    res.add(root.data);
                    root = root.right;
                }
            }
        }
        return res;
    }
    public static List<List<Integer>> levelOrderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            int qSize = q.size();
            List<Integer> level = new ArrayList<>(qSize);
            while (qSize > 0) {
                TreeNode curr = q.poll();
                if (curr.left != null) {
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                }
                qSize--;
            }
            res.add(level);
        }
        return res;
    }
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root.left);
        q.add(root.right);
        while(!q.isEmpty()) {
            TreeNode le = q.poll();
            TreeNode ri = q.poll();
            if (le == null && ri == null) {
                continue;
            }
            if (le == null || ri == null || le.data != ri.data) {
                return false;
            }
            q.add(le.left);
            q.add(ri.right);
            q.add(le.right);
            q.add(ri.left);
        }
        return true;
    }
    private static boolean solve(TreeNode le, TreeNode ri) {
        if (le == null && ri == null) {
            return true;
        }
        if (le == null || ri == null) {
            return false;
        }
        if (le.data == ri.data) {
            return solve(le.left, ri.right) && solve(le.right, ri.left);
        }
        return false;
    }
    public static boolean isSymmetricRec(TreeNode root) {
        if (root == null) { return true; }
        return solve(root.left, root.right);
    }
    //Ref:leetcode.com/explore/learn/card/data-structure-tree/17/solve-problems-recursively/537/discuss/36378/AcceptedMy-recursive-solution-in-Java/34584
    public static boolean hasPathSum(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.data == k;
        }
        return hasPathSum(root.left, k - root.data) || hasPathSum(root.right, k - root.data);
    }
    public static int height(TreeNode r) {
        if (r == null) {
            return 0;
        }
        return Math.max(height(r.left), height(r.right)) + 1;
    }
    public static boolean isBalancedBottomUp(TreeNode root) {
        if (root == null) {
            return true;
        }
        int lh = height(root.left);
        int rh = height(root.right);
        if (Math.abs(lh - rh) > 1) {
            return false;
        }
        return Math.abs(lh - rh) <= 1 && isBalancedBottomUp(root.left) && isBalancedBottomUp(root.right);
    }
    public static int ht(TreeNode r) {
        if (r == null) {
            return 0;
        }
        int lh = ht(r.left);
        if (lh == -1) {
            return -1;
        }
        int rh = ht(r.right);
        if (rh == -1) {
            return -1;
        }
        if (Math.abs(lh - rh) > 1) {
            return -1;
        }
        return Math.max(lh, rh) + 1;
    }
    //Ref: https://leetcode.com/problems/balanced-binary-tree/discuss/35691/The-bottom-up-O(N)-solution-would-be-better
    public static boolean isBalancedDfs(TreeNode r) {
        if (r == null) {
            return true;
        }
        return ht(r) != -1;
    }

    /**
     * what lowestCommonAncestor() does:
     * if both p and q exist in Tree rooted at root, then return their LCA
     * if neither p and q exist in Tree rooted at root, then return null
     * if only one of p or q (NOT both of them), exists in Tree rooted at root, return it
     */
    public static TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode le = lca(root.left, p, q);
        TreeNode ri = lca(root.right, p, q);
        if (le == null && ri == null) {
            return null;
        }
        if (le != null && ri != null) {
            return root;
        }
        return le != null ? le : ri;
    }
    //Ref:https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/
    //O(N) time and space
    public static TreeNode lcaWithParentPointers(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Deque<TreeNode> st = new ArrayDeque<>();
        parent.put(root, null);
        st.push(root);

        while(!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode curr = st.pop();
            if (curr.left != null) {
                parent.put(curr.left, curr);
                st.push(curr.left);
            }
            if (curr.right != null) {
                parent.put(curr.right, curr);
                st.push(curr.right);
            }
        }
        Set<TreeNode> ancestors = new HashSet<>();
        while(p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        while(!ancestors.contains(q)) {
            q = parent.get(q);
        }
        return q;
    }
    //If the sum exceeds 2^31 -1, then divide the result by 10^9 + 7
    //each node is either a 0 or 1 representing a binary number
    private static int solveSumRootToLeafBinary(TreeNode r, int currentSum) {
        if (r == null) {
            return 0;
        }
        currentSum = currentSum * 2 + r.data;
        if (r.left == null && r.right == null) {
            return currentSum;
        }
        int lhs = solveSumRootToLeafBinary(r.left, currentSum);
        int rhs = solveSumRootToLeafBinary(r.right, currentSum);
        return lhs + rhs;
    }
    public static int sumRootToLeafBinary(TreeNode root) {
        return solveSumRootToLeafBinary(root, 0);
    }
    private static int solveRootToLeafIntegers(TreeNode r, int s) {
        if (r == null) {
            return 0;
        }
        s = s * 10 + r.data;
        if (r.left == null && r.right == null) {
            return s;
        }
        return solveRootToLeafIntegers(r.left, s) + solveRootToLeafIntegers(r.right, s);
    }
    //same as above problem except each node is a digit between 0-9
    //Ref: https://leetcode.com/problems/sum-root-to-leaf-numbers/
    public static int sumRootToRootLeafIntegers(TreeNode r) {
        return solveRootToLeafIntegers(r, 0);
    }

    /* eg:
      		25(4)                      k=3
		  /    \
	   20(2)   13(0)
	   /  \
    1(0)  4(0)
  *inoder traversal: 1-20-4-25-13
  * k=3, return: 4
  * k=2, return: 20
  * Try1:
  1. get inorder traversal -> O(N)
  2. get kth node -> O(K)
  O(N) time, O(N) space
  Try2:
  1. left -> visit-> right
  2. if root is null -> return -1
  3. if k == 0 then:
    ret. root.val

  4. add root to a queue q=[25]
  5. while q is not empty loop:
        - get current qsize 1;2;3
        - curr <- poll from q q=[];q=[13];q=[1,4]
        - children <- curr.children.size 2;2;0
        - if children > 0 add that node to q first q=[20,13],k=2;q=[13,1,4],k=1
        - then add remainig nodes


  * /

     */
    static class TreeNodeWithSize {
        int data, size;
        TreeNodeWithSize left, right;
        public TreeNodeWithSize(int v) {
            this.data = v;
            this.left = this.right = null;
            this.size = 0;
        }
    }
    //ref: https://leetcode.com/discuss/interview-question/479911/GoogleorPhoneorFind-nth-node-in-inorder-traversal
    public TreeNodeWithSize getNode(TreeNodeWithSize r, int k) {
        if (r == null) {
            return null;
        }
        int leftSize = r.left == null ? 0 : r.left.size;
        //if kth node lies in the right subtree
        if (leftSize + 1 < k) {
            k = k - (leftSize + 1);
            return getNode(r.right, k);
        } else if (leftSize == k -1) {
            return r;
        } else {
            return getNode(r.left, k);
        }
    }
    /*
    Try1:
    assume: each node has parent pointers
    1. perform inorder traversal
    2. get the n + 1th node
    3. O(n) time to perform traversal + O(n) space to store nodes
    Try2:
      10
    /   \
    2     12
   / \   /\
   3  5 6  4
      t2:
      10
       \
         12
        /\
       6  4
   traversal = 3-2-5-10-6-12-4
   n=6
   1. start from node, save node to curr
   2. if curr.right is not null then:
        - while curr.left is not null then:
            - find leftmost node in the right subtree
      else:
        - find closest ancestor whose left subtree contains node i.e. go up to the parent
   3. ret curr.parent
     */
    static class TreeNodeWithParent {
        int data;
        TreeNodeWithParent left, right, parent;
        public TreeNodeWithParent(int v) {
            this.data = v;
            this.left = this.right = null;
            this.parent = null;
        }
    }
    public static TreeNodeWithParent inorderSuccessor(TreeNodeWithParent inputNode) {
        TreeNodeWithParent curr = inputNode;
        if (curr.right != null) {
            while (curr.left != null) {
                curr = curr.left;
            }
            return curr;
        }
        while(curr.parent != null && curr.parent.right == curr) {
            curr = curr.parent;
        }
        return curr.parent;
    }
    public static int successorInBst(TreeNode root, int k) {
        int ans = Integer.MAX_VALUE;
        while (root != null) {
            if (root.data > k) {
                ans = Math.min(ans, root.data);
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return ans;
    }
    //O(h) time, O(1) space, no modifications to the input tree
    public static void inorderTraversalMorris(TreeNode r) {
        //init a curr pointer to root
        TreeNode curr = r;
        while (curr != null) {
            if (curr.left == null) {
                System.out.println(curr.data);
                curr = curr.right;
            } else {
                // else:
                //look for inorder predecessor of curr = findPredecessor(curr) i.e. find the rightmost node on the left subtree
                // this would mean
                // a). we reached the end of the node and the node doesn't have a right child &&
                // b). this would help us establish a link between the node and its parent/inorder predecessor, so that we will know if we have visited a node or not.
                TreeNode pred = curr.left;
                while (pred.right != null && pred.right != curr) {
                    pred = pred.right;
                }
                //a). we reached the end of the node and the node doesn't have a right child &&
                if (pred.right == null) {
                    pred.right = curr;
                    curr = curr.left;
                } else {
                    //remove link between a node and it's predecessor
                    pred.right = null;
                    System.out.println(curr.data);
                    curr = curr.right;
                }
            }
        }
    }
    private static TreeNode buildTree(int[]pre, int preSt, int preEnd, int ino[], int inorderStIdx, int inoEnd, Map<Integer, Integer> m) {
        if (preSt > preEnd || inorderStIdx > inoEnd || pre.length != ino.length) {
            return null;
        }
        //get root from pre[preSt]
        int currentRoot = pre[preSt];
        //calculate the rootIndex from inorder[]
        int inorderRootIdx = m.get(currentRoot);
        //create root of the new tree
        TreeNode root = new TreeNode(currentRoot);
        /*
        Remember:
        Preorder traversing implies that PRE[0] is the root node.
        Then we can find this PRE[0] in IN[], say it's IN[5].
        Now we know that IN[5] is root, so we know that IN[0] - IN[4] is on the left side, IN[6] to the end is on the right side.
        Recursively doing this on subarrays, we can build a tree out of it :)
        here:
        IN[0] ==> 0            ==> inorderRootIdx
        IN[4] ==> 4 ==> inoEnd ==> inorderRootIdx - 1 //for left subtree
        IN[6] ==> 6 ==> inoSt  ==> inorderRootIdx + 1 //for right subtree

        numberOfNodesOnLeftOfRoot is nothing but the left subtree or the leftsubtree size
        eg: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
        in this case for currentRoot = 3, inorderRootIdx = 1, inoSt = 0, numberOfNodesOnLeftOfRoot = 1 - 0 = 1
        */
        int numberOfNodesOnLeftOfRoot = inorderRootIdx - inorderStIdx;
        //Ref: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/discuss/34538/My-Accepted-Java-Solution/32854
        int indexOfRightChildInPre = preSt + numberOfNodesOnLeftOfRoot + 1;
        root.left = buildTree(pre, preSt + 1, preSt + numberOfNodesOnLeftOfRoot, ino, inorderStIdx, inorderRootIdx - 1, m);
        root.right = buildTree(pre, indexOfRightChildInPre, preEnd, ino, inorderRootIdx + 1, inoEnd, m);

        return root;
    }
    public static TreeNode buildTreeFromPreAndInorders(int[] pre, int[] ino) {
        Map<Integer, Integer> inorderRootIdx = new HashMap<>();
        for (int i = 0; i < ino.length; i++) {
            inorderRootIdx.put(ino[i], i);
        }
        TreeNode r = buildTree(pre, 0, pre.length - 1, ino, 0, ino.length -1, inorderRootIdx);
        return r;
    }
    /*
    i/p=[1, null, 2, null, 3]
    o/p:
    1
     \
      2
      /
     3
i/p: [1,2,3,null,null,4,5,null,null,null,6,null,7,null,8,9,null,null,null]
o/p:  1
     /\
    2  6
   / \  \
  3   4  7
     /    \
    5      8
          /
         9
    1. root is always at the 0th index, pre[0]
    2.
     */
    private static TreeNode buildPreTree(int[] pre, int idx) {
        int subTreeKey = pre[idx];
        idx++;
        if (subTreeKey == -1) {
            return null;
        }
        TreeNode root = new TreeNode(subTreeKey);
        root.left = buildPreTree(pre, idx);
        root.right = buildPreTree(pre, idx);
        return root;
    }
    public static TreeNode buildWithPreorderSeqWithMarkers(int[] pre) {
        return buildPreTree(pre, 0);
    }
    /*
    i/p:
      1
     /\
    2  6
   / \  \
  3   4  7
     /    \
    5      8
          /
         9
 o/p: 3->5->9->NULL
 Try1:
 1. traverse each path, collect leaves
 2. then create a LL from the collection
 Try2:
 1. try to do a preorder traversal
 2. whenever you reach a node where left and right child are null i.e. you are at a leaf, add the node to the list
 3. recurse on left then on right
 4. O(n) space, O(h) time
     */
    private static void solveCreateLLFromLeaves(TreeNode r, List<Integer> ll) {
        if (r != null) {
            if (r.left == null && r.right == null) {
                ll.add(r.data);
            } else {
                solveCreateLLFromLeaves(r.left, ll);
                solveCreateLLFromLeaves(r.right, ll);
            }
        }
    }
    public static List<Integer> createLLFromLeaves(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        solveCreateLLFromLeaves(root, res);
        return res;
    }
    /*
    Ref: https://www.geeksforgeeks.org/boundary-traversal-of-binary-tree/
    i/p:
      1
     /\
    2  6
   / \  \
  3   4  7
     /    \
    5      8
          /
         9
 o/p: [1,2,3,5,9,8,7,6]
 try1:
 1. preorder only on left hand side, and store in a list l1
 2. collect all leaves, store in list l2
 3. preorder on right subtree, collect in a stack, convert/pop from stack to a list l3
 4. combine l1, l2 and l3 in 1 collection and return
 5. O(A+B+C+D) space and o(h)
 try2:
 1.
     */
    private static void solveGetLeftBoundary(TreeNode r, List<Integer> l) {
        if (r == null) {
            return;
        }
        if (r.left != null) {
            l.add(r.data);
            solveGetLeftBoundary(r.left, l);
        }
        else if (r.right != null) {
            l.add(r.data);
            solveGetLeftBoundary(r.right, l);
        }
    }
    private static void solveGetRightBoundary(TreeNode r, List<Integer> l) {
        if (r == null) {
            return;
        }
        if (r.right != null) {
            l.add(r.data);
            solveGetRightBoundary(r.right, l);
        }
        else if (r.left != null) {
            l.add(r.data);
            solveGetRightBoundary(r.left, l);
        }
    }
    private static ArrayList<Integer> solveLe(TreeNode n, boolean isBoundary) {
        ArrayList<Integer> res = new ArrayList<>();
        if (n != null) {
            if (isBoundary || isLeaf(n)) {
                res.add(n.data);
            }
            res.addAll(solveLe(n.left, isBoundary));
            res.addAll(solveLe(n.right, isBoundary && n.left == null));
        }
        return res;
    }
    private static ArrayList<Integer> solveRi(TreeNode n, boolean isBoundary) {
        ArrayList<Integer> res = new ArrayList<>();
        if (n != null) {
            res.addAll(solveRi(n.left, isBoundary && n.right == null));
            res.addAll(solveRi(n.right, isBoundary));
            if (isBoundary || isLeaf(n)) {
                res.add(n.data);
            }
        }
        return res;
    }
    private static boolean isLeaf(TreeNode n) {
        return (n.left == null && n.right == null);
    }
    private static void solve(TreeNode n, List<Integer> ll) {
        if (n != null) {
            ll.add(n.data);
            ll.addAll(solveLe(n.left, true));
            ll.addAll(solveRi(n.right, true));
        }
    }
    //This throws a TLE in GFG
    ArrayList <Integer> printBoundary(TreeNode node) {
        if (node == null) {
            return null;
        }
        ArrayList<Integer> res = new ArrayList<>();
        solve(node, res);
        return res;
    }
    static class TreeNodeNext {
        int data;
        TreeNodeNext next, left, right;
        public TreeNodeNext(int n) {
            this.data = n;
            this.next = this.left = this.right = null;
        }
    }
    //hint: make use of the links we are creating
    //ref1: leetcode.com/explore/learn/card/data-structure-tree/133/conclusion/994/discuss/37472/A-simple-accepted-solution/265892
    public static TreeNodeNext constructRightSiblingPerfectBinTreeRec(TreeNodeNext r) {
        solveConstruct(r);
        return r;
    }
    private static void solveConstruct(TreeNodeNext r) {
        if (r == null) {
            return;
        }
        //make root's left child's next point to root's right child at that level
        if (r.left != null) {
            r.left.next = r.right;
        }
        //if root's next is not null i.e. if the node is not at the right boundary or the node has a sibling to its right
        if (r.right != null && r.next != null) {
            //make node's right child's next point to the node's sibling's left child
            r.right.next = r.next.left;
        }
        solveConstruct(r.left);
        solveConstruct(r.right);
    }

    public static TreeNodeNext constructRightSiblingPerfectBinTreeIter(TreeNodeNext r) {
        if (r == null) {
            return null;
        }
        TreeNodeNext temp = r;
        while (temp != null && temp.left != null) {
            TreeNodeNext curr = temp;
            while (curr != null) {
                curr.left.next = curr.right; //connect left child to right child
                if (curr.next != null) { //avoid nodes on the rightmost boundary
                    curr.right.next = curr.next.left; //connect right child to curr's right sibling's left child
                }
                curr = curr.next;//we move from left to right at a level
            }
            temp = temp.left; //go down to next level
        }
        return r;
    }

    public static TreeNodeNext constructRightSiblingBinTreeIter(TreeNodeNext root) {
        TreeNodeNext parent = root;
        TreeNodeNext child = null, childHead = null;
        //outer loop goes top down
        while (parent != null) {
            //inner loop goes from left to right
            while (parent != null) {
                if (parent.left != null) {
                    if (childHead == null) {
                        childHead = parent.left;
                    } else {
                        child.next = parent.left;
                    }
                    child = parent.left;
                }
                if (parent.right != null) {
                    if (childHead == null) {
                        childHead = parent.right;
                    } else {
                        child.next = parent.right;
                    }
                    child = parent.right;
                }
                parent = parent.next; //parent moves from left -> right
            }
            parent = childHead; //parent goes down 1 level
            childHead = child = null;
        }
        return root;
    }

}
