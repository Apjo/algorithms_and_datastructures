package prep.meta;

import dsa.datastructures.trees.BinarySearchTreeDemo.Node;

import java.util.Stack;

public class RangeSumBST {
    private static void solve(Node root, int[] a, int low, int high) {
        if (root == null) {
            return;
        }
        if (root.data >= low && root.data <= high) {
            a[0]+=root.data;
        }
        if(root.left != null && root.data > low) {
            solve(root.left, a, low, high);
        }
        if(root.right != null && root.data < high) {
            solve(root.right, a, low, high);
        }
    }
    public int rangeSum(Node root, int low, int hi) {
        int[]ans = new int[1];
        solve(root, ans, low, hi);
        return ans[0];
    }
    //find average
    public double rangeAvg(Node root, int low, int high) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        int currSum = 0, count = 0;
        while(!stack.isEmpty()) {
            Node curr = stack.pop();
            if(curr.data >= low && curr.data <= high) {
                currSum += curr.data;
                count++;
            }
            if(curr.left != null && curr.data > low) {
                stack.push(curr.left);
            }
            if(curr.right != null && curr.data < high) {
                stack.push(curr.right);
            }
        }
        return (double)(currSum / count);
    }
    //make it work for >= 10^4 operations
    public static void main(String[] args) {
    }
}
