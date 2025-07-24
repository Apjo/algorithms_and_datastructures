package dsa.datastructures.trees;

public class BinaryTreeDemo {
    public static class BinaryTreeNode {
        private BinaryTreeNode left;
        private BinaryTreeNode right;
        private BinaryTreeNode next;
        private int data;
        public BinaryTreeNode(int v) {
            this.data = v;
            this.left = this.right = this.next = null;
        }
        public int getData() {
            return this.data;
        }
        public BinaryTreeNode getLeft() {
            return this.left;
        }
        public BinaryTreeNode getRight() {
            return this.right;
        }
    }
}
