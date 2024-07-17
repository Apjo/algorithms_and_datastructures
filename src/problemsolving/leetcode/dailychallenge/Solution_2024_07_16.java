package problemsolving.leetcode.dailychallenge;

/*
Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest. You may return the result in any order.
 */
import java.io.*;
import java.util.*;

public class Solution_2024_07_16 {
    public class TreeNode {
        int val;
       TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<TreeNode> res = new HashSet<>();
        Queue<TreeNode> q = new LinkedList<>();
        ArrayList<Integer> al = new ArrayList<>(Arrays.asList(Arrays.stream(to_delete).boxed().toArray(Integer[]::new)));

        q.offer(root);
        res.add(root);

        while(!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (al.contains(curr.val)) {
                //if root is itself to be deleted, save its children first, and remove the root from the output list
                res.remove(curr);
                if (curr.left != null) {
                    res.add(curr.left);
                }
                if (curr.right != null) {
                    res.add(curr.right);
                }
            }
            //then check the children individually
            if (curr.left != null) {
                System.out.println("Adding L " + curr.left.val);
                q.offer(curr.left);
                if(al.contains(curr.left.val)) {
                    curr.left=null;
                }
            }
            if (curr.right != null) {
                System.out.println("Adding R " + curr.right.val);
                q.offer(curr.right);
                if (al.contains(curr.right.val)) {
                    curr.right=null;
                }
            }
        }
        return new ArrayList<>(res);
    }
}
