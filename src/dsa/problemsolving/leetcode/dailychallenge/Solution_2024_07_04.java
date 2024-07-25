package dsa.problemsolving.leetcode.dailychallenge;

import java.io.*;
import java.util.*;

public class Solution_2024_07_04 {
    public class ListNode {
        int val;
        ListNode next;
        public ListNode(int v) {
            this.next=null;
            this.val=v;
        }
    }
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null) {
            return new int[]{-1, -1};
        }
        ListNode prev=head, curr = head.next;
        int ctr=0;
        List<Integer> res = new ArrayList<>();
        while(curr.next != null) {
            ++ctr;
            if (prev.val < curr.val && curr.val > curr.next.val) {
                // System.out.println("found local max= " + curr.val+" at index= "+ctr);
                res.add(ctr+1);
            } else if (prev.val > curr.val && curr.val < curr.next.val) {
//System.out.println("found local min= " + curr.val+" at index= "+ctr);
                res.add(ctr+1);
            }
            //ctr++;
            prev=curr;
            curr=curr.next;

        }
        if(res.size() == 0 || res.size() < 2) {
            return new int[]{-1, -1};
        }

        Collections.sort(res);
        int[] op = new int[2];
        int maxD = Math.abs(res.get(res.size() - 1) - res.get(0));
        op[1] = maxD;
        int mi = Integer.MAX_VALUE;
        for(int i=0; i < res.size(); i++) {
            if (i + 1 < res.size() && res.get(i) != Integer.MAX_VALUE) {
                mi = Math.min(mi, Math.abs(res.get(i) - res.get(i+1)));
            }
        }
        op[0] = mi;

        return op;
    }
}
