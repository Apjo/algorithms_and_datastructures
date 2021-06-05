package datastructures.trees;

public class BinaryTreeDemo {
    static class BinaryTreeNode {
        BinaryTreeNode left;
        BinaryTreeNode right;
        BinaryTreeNode next;
        int data;
        public BinaryTreeNode(int v) {
            this.data = v;
            this.left = this.right = this.next = null;
        }
    }
}
