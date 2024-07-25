package dsa.problemsolving.leetcode.dailychallenge;

/*
You are given the root of a binary tree with n nodes.
Each node is uniquely assigned a value from 1 to n.
You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.

Find the shortest path starting from node s and ending at node t.
Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'.
Each letter indicates a specific direction:

'L' means to go from a node to its left child node.
'R' means to go from a node to its right child node.
'U' means to go from a node to its parent node.
Return the step-by-step directions of the shortest path from node s to node t.
 */

import java.util.*;
import java.io.*;

public class Solution_2024_07_15 {
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
    private static void exists(TreeNode root, int n, StringBuilder sb, boolean[]res) {
        if (root == null) {
            return;
        }
        if (root.val == n) {
            res[0]= true;
            return;
        }
        if (!res[0] && root.left != null) {
            exists(root.left, n, sb, res);
            if (res[0]) {
                sb.append("L");
            }
        }

        if (!res[0] && root.right != null) {
            exists(root.right, n, sb, res);
            if (res[0]) {
                sb.append("R");
            }
        }
    }
    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        boolean[]res= new boolean[1];
        exists(root, startValue, sb, res);
        res[0]=false;
        exists(root, destValue, sb2, res);
        sb.reverse();
        sb2.reverse();
        while(sb.length() > 0 && sb2.length() > 0 && sb.charAt(0) == sb2.charAt(0)) {
            sb.deleteCharAt(0);
            sb2.deleteCharAt(0);
        }
        return "U".repeat(sb.length()) + sb2.toString();
    }
    }
