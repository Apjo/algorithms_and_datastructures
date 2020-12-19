package datastructures.trees;
import datastructures.trees.BinarySearchTreeDemo.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Tree problems from Tushar Roy, William Fisset, and Vivekananda Khyade's Youtube playlists
public class TreesPractice {
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
    }
}
