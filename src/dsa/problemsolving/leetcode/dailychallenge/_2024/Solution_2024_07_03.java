package dsa.problemsolving.leetcode.dailychallenge._2024;

public class Solution_2024_07_03 {

 public class ListNode {
     int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
            ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    public ListNode mergeNodes(ListNode head) {
        int s = 0;
        ListNode d = new ListNode(-1);
        ListNode temp = d;
        ListNode curr = head;
        while(curr != null) {
            if (curr.val == 0 && s > 0) {
                //System.out.println("appending sum= " + s);
                temp.next=new ListNode(s);
                temp=temp.next;
                s=0;
            } else {
                s+=curr.val;
            }
            curr=curr.next;
        }
        return d.next;
    }
}
