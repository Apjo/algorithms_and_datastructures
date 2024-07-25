package dsa.problemsolving.leetcode.dailychallenge;

import java.io.*;
import java.util.*;
/*
You are given the root of a binary tree and an integer distance.
A pair of two different leaf nodes of a binary tree is said to be good if the length of the shortest path between them is less than or equal to distance.

Return the number of good leaf node pairs in the tree.
 */
public class Solution_2024_07_17 {
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
    public int countPairs(TreeNode root, int distance) {
        int[] sum=new int[1];
        countPairs(root,distance,sum);
        return sum[0];
    }

    public List<Integer> countPairs(TreeNode root, int distance,int[] sum) {
        if(root==null)return new ArrayList<>();
        if(root.left==null&&root.right==null){
            List list=new ArrayList<>();
            list.add(1);
            return list;
        }
        List<Integer> leftList=countPairs(root.left,distance,sum);
        List<Integer> rightList=countPairs(root.right,distance,sum);
        List<Integer> ret=new ArrayList<>();
        if(!leftList.isEmpty()&&!rightList.isEmpty()){
            for(int i=0;i<leftList.size();i++){
                for(int j=0;j<rightList.size();j++){
                    if(leftList.get(i)+rightList.get(j)<=distance){
                        sum[0]++;
                    }
                }
            }
        }
        for(int i=0;i<leftList.size();i++){
            ret.add(leftList.get(i)+1);
        }
        for(int i=0;i<rightList.size();i++){
            ret.add(rightList.get(i)+1);
        }
        return ret;
    }
}
