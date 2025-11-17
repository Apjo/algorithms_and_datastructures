package prep.meta;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {
    static class Node {
        int val;
        Node next, random;
        public Node(int n) {
            this.val = n;
            this.next=null;
        }
    }
    static class TreeNodeTemp {
        int val;
        TreeNodeTemp left, right, random;
        TreeNodeTemp(int val) { this.val = val; }
    }
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        Node curr = head;
        Map<Node, Node> oldToNew = new HashMap<>();
        while(curr != null) {
            oldToNew.put(curr, new Node(curr.val));
            curr = curr.next;
        }
        curr=head;
        while(curr != null) {
            oldToNew.get(curr).next = oldToNew.get(curr.next);
            oldToNew.get(curr).random = oldToNew.get(curr.random);
            curr=curr.next;
        }
        return oldToNew.get(head);
    }
    public TreeNodeTemp deepCopyBinTree(TreeNodeTemp root) {
        Map<TreeNodeTemp, TreeNodeTemp> oldToNew = new HashMap<>();
        doCopyNodes(root, oldToNew);
        doRandomCopy(root, oldToNew);
        return oldToNew.get(root);
    }
    private static TreeNodeTemp doCopyNodes(TreeNodeTemp treeNodeTemp, Map<TreeNodeTemp, TreeNodeTemp> m) {
        if (treeNodeTemp == null) {
            return treeNodeTemp;
        }
        m.put(treeNodeTemp, new TreeNodeTemp(treeNodeTemp.val));
        m.get(treeNodeTemp).left = doCopyNodes(treeNodeTemp.left, m);
        m.get(treeNodeTemp).right = doCopyNodes(treeNodeTemp.right, m);
        return m.get(treeNodeTemp);
    }
    private static void doRandomCopy(TreeNodeTemp treeNodeTemp, Map<TreeNodeTemp, TreeNodeTemp> m) {
        if(treeNodeTemp == null) {
            return;
        }
        m.get(treeNodeTemp).random = m.get(treeNodeTemp.random);
        doRandomCopy(treeNodeTemp.left, m);
        doRandomCopy(treeNodeTemp.right, m);
    }
    public static void main(String[] args) {
    }
}
