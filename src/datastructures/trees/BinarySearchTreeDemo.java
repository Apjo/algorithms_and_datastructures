package datastructures.trees;

public class BinarySearchTreeDemo {
    Node root;

    static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            left = right = null;
            this.data = data;
        }
    }
    public Node insertKeyIter(int key) {
        Node nn = new Node(key);
        if (root == null) {return nn;}
        Node parent = root;
        Node curr = root;
        while (curr != null) {
            parent = curr;
            if (curr.data <= key) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        if (parent.data <= key) {
            parent.right = nn;
        } else {
            parent.left = nn;
        }
        return parent;
    }

    public Node insertKey(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key < root.data) {
            root.left = insertKey(root.left, key);
        } else {
            root.right = insertKey(root.right, key);
        }
        return root;
    }

    public Node deleteKey(Node root, int key) {
        if (root == null)
            return root;
        if (key < root.data)
            root.left = deleteKey(root.left, key);
        else if (key > root.data)
            root.right = deleteKey(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            // Find smallest node in the right subtree, since we got a node which has non
            // empty left and right subtrees, we
            // could find the largest node in the left subtree too, since in this case of
            // deletion the node to be deleted could have
            // 2 successors
            root.data = minimumKey(root.right);

            root.right = deleteKey(root.right, root.data);
        }
        return root;
    }

    public int minimumKey(Node root) {
        int minKey = root.data;
        while (root.left != null) {
            minKey = root.left.data;
            root = root.left;
        }
        return minKey;
    }

    public int maximumKey(Node root) {
        int maxKey = root.data;
        while (root.right != null) {
            maxKey = root.right.data;
            root = root.right;
        }
        return maxKey;
    }

    public void inOrderRec(Node root) {
        if (root == null)
            return;
        inOrderRec(root.left);
        System.out.print(" " + root.data);
        inOrderRec(root.right);
    }

    public void preOrderRec(Node root) {
        if (root == null)
            return;
        System.out.print(" " + root.data);
        preOrderRec(root.left);
        preOrderRec(root.right);
    }

    public void postOrderRec(Node root) {
        if (root == null)
            return;
        postOrderRec(root.left);
        postOrderRec(root.right);
        System.out.print(" " + root.data);
    }
    public static void main(String[] args) {
        BinarySearchTreeDemo tree = new BinarySearchTreeDemo();

        Node root = tree.insertKey(null, 50);

        tree.insertKey(root, 30);
        tree.insertKey(root, 20);
        tree.insertKey(root, 40);
        tree.insertKey(root, 70);
        tree.insertKey(root, 60);
        tree.insertKey(root, 80);

        System.out.println("Minimum: " + tree.minimumKey(root));
        System.out.println("Maximum: " + tree.maximumKey(root));

        System.out.println("InOrder traversal...");
        tree.inOrderRec(root);

        System.out.println("\nDeleting key 20...");
        tree.deleteKey(root, 20);
        System.out.println("Inorder traversal after deleting a key");
        tree.inOrderRec(root);

        System.out.println("\nDeleting key 30...");
        tree.deleteKey(root, 30);
        System.out.println("PreOrder traversal after deletion");
        tree.preOrderRec(root);

        System.out.println("\nDeleting key 50");
        tree.deleteKey(root, 50);
        System.out.println("PostOrder traversal after deletion");
        tree.postOrderRec(root);
    }
}