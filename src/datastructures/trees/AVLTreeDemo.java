package datastructures.trees;
import datastructures.trees.BinarySearchTreeDemo.*;
/**
 Insert, Search, and delete in AVL tree happens in O(log N) time
 Four different uses cases of insertions: Left-Left, Left-Right, Right-Right, and Right-Left respectively.
 left left - needs ones right rotation
 left right - needs one left and one right rotation
 right left - needs one right and one left rotation
 right right - needs one left rotation
**/
public class AVLTreeDemo {
    //Use in the case of Right-Right case, or Right-Left case
    Node leftRotate(Node root) {
        Node newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        root.height = setHeight(root);
        newRoot.height = setHeight(newRoot);
        return newRoot;
    }
    //Use in the case of Left-Left case, or Left-Right case
    Node rightRotate(Node root) {
        Node newRoot = root.left;
        root.left = root.left.right;
        newRoot.right = root;
        root.height = setHeight(root);
        newRoot.height = setHeight(newRoot);
        return newRoot;
    }

    int getHeight(Node root) {
        if (root == null) {
            return 0;
        } else {
            return root.height;
        }
    }

    int setHeight(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max((root.left != null ? root.left.height : 0), (root.right != null ? root.right.height : 0));
    }

    int getBalance(Node rootLeft, Node rootRight) {
        return getHeight(rootLeft) - getHeight(rootRight);
    }

    public Node insertKey(Node root, int key) {
        //regular insert in a BST
        if (root == null) {
            return Node.newNode(key);
        }
        if (key <= root.data) {
            root.left = insertKey(root.left, key);
        } else {
            root.right = insertKey(root.right, key);
        }
        //get balance of left & right subtree
        int balance = getBalance(root.left, root.right);
        //if balance > 1, THEN
        if (balance > 1) {
            if (getHeight(root.left.left) >= getHeight(root.left.right)) {
                //case of LL => rotateRight
                root = rightRotate(root);
            } else {
                //first rotate Left
                root.left = leftRotate(root.left);
                root = rightRotate(root);
            }
        } else if (balance < -1) {
            if (getHeight(root.right.right) >= getHeight(root.right.left)) {
                //case of RR => rotate Left
                root = leftRotate(root);
            } else {
                //case of RL
                    //first rotate Right
                root.right = rightRotate(root.right);
                    //THEN rotate LEFT
                root = leftRotate(root);
            }
        } else {
            //set new found heights of the roots
            root.height = setHeight(root);
        }
        return root;
    }
    public static void main(String[] args) {
        AVLTreeDemo avlTree = new AVLTreeDemo();
        Node root = null;
        root = avlTree.insertKey(root, -10);
        root = avlTree.insertKey(root, 2);
        root = avlTree.insertKey(root, 13);
        root = avlTree.insertKey(root, -13);
        root = avlTree.insertKey(root, -15);
        root = avlTree.insertKey(root, 15);
        root = avlTree.insertKey(root, 17);
        root = avlTree.insertKey(root, 20);
        BinarySearchTreeDemo bst = new BinarySearchTreeDemo();
        bst.printTree("", root, false);
        System.out.println("INORDER TRAVERSAL ON AVL");
        bst.inOrderRec(root);
    }
}
