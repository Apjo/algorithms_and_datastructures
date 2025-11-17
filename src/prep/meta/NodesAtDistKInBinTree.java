package prep.meta;

import java.util.*;
public class NodesAtDistKInBinTree {
    static class TreeNode {
        TreeNode left, right;
        int val;
        TreeNode(int x) { val = x; }
    }
    private static void build(Map<TreeNode, List<TreeNode>> adj, TreeNode child, TreeNode parent) {
        //mark parent and child only if both are present
        if (parent != null && child != null) {
            adj.computeIfAbsent(parent, k -> new ArrayList<>()).add(child);
            adj.computeIfAbsent(child, k -> new ArrayList<>()).add(parent);
        }
        //if child has left, go left
        if(child!= null && child.left != null) {
            build(adj, child, child.left);
        }
        //if child has right, go right
        if (child != null && child.right != null) {
            build(adj, child, child.right);
        }
    }
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        //perform DFS to maintain a adjacency list of parent to child, and child to parent
        Map<TreeNode, List<TreeNode>> adj = new HashMap<>();
        build(adj, root, null);
        //perform BFS from the target node, while marking visited nodes.
        List<TreeNode> from = new ArrayList<>(Collections.singletonList(target));
        Set<TreeNode> visited = new HashSet<>();
        visited.add(target);
        List<Integer> res = new ArrayList<>();
        for(int i=0; i < k; i++) {
            List<TreeNode> currLevel = new ArrayList<>();
            for(TreeNode treeNode : from) {
                //iterate over the neighbors
                for(TreeNode neighbor : adj.get(treeNode)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        currLevel.add(neighbor);
                    }
                }
            }
            //reset from to the next level
            from = currLevel;
        }
        for(TreeNode treeNode : from) {
            res.add(treeNode.val);
        }
        return res;
    }
        public static void main(String[] args) {
    }
}
