package dsa.problemsolving.leetcode.dailychallenge._2024;

/*
You are given a 2D integer array descriptions where descriptions[i] = [parenti, childi, isLefti] indicates that parenti is the parent of childi in a binary tree of unique values. Furthermore,

If isLefti == 1, then childi is the left child of parenti.
If isLefti == 0, then childi is the right child of parenti.
Construct the binary tree described by descriptions and return its root.

The test cases will be generated such that the binary tree is valid.

 */

import java.util.*;

public class Solution_2024_07_14 {
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
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> m = new HashMap<>();
        Set<Integer> pars = new HashSet<>();
        for(int i=0; i < descriptions.length; i++) {
            int par = descriptions[i][0];
            int chi = descriptions[i][1];
            pars.add(chi);
            int le = descriptions[i][2];
            if (!m.containsKey(par)) {
                //List<TreeNode> cs = new ArrayList<>();
                m.put(par, new TreeNode(par));
            }
            if(!m.containsKey(chi)) {
                m.put(chi, new TreeNode(chi));
            }
            //List<TreeNode> c = m.get(par);
            if(le == 1) {
                //add chi as left node for par
                //c.add(new TreeNode(par, new TreeNode(chi), null));
                m.get(par).left=m.get(chi);
            } else {
                //c.add(new TreeNode(par, null, new TreeNode(chi)));
                m.get(par).right=m.get(chi);
            }
        }

        m.keySet().removeAll(pars);
        return m.values().iterator().next();
    }
}
