package problemsolving.elementsofprogramminginterviews;

import datastructures.SinglyLinkedListDemo;

public class Chapter7 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //merge 2 sorted lists, time: O(m+n)
    //ref: https://leetcode.com/problems/merge-two-sorted-lists/discuss/9772/Java-solution-with-real-world-concerns-real-world-concerns
    public static ListNode mergeSortedLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        //append remaining of the l1 or l2
        if (l1 != null) {
            curr.next = l1;
        } else {
            curr.next = l2;
        }
        return dummy.next;
    }

    //Could result in stackoverflow if list is too big
    public static ListNode mergeSortedListsRec(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode curr;
        if (l1.val < l2.val) {
            curr = l1;
            curr.next = mergeSortedListsRec(l1.next, l2);
        } else {
            curr = l2;
            curr.next = mergeSortedListsRec(l1, l2.next);
        }
        return curr;
    }

    //Ref: https://leetcode.com/problems/reverse-linked-list-ii/discuss/30667/Easy-understanding-java-solution/736368
    private static ListNode reverseL(ListNode l, int k) {
        ListNode curr = null, prev = null, next = null;
        curr = l;
        while (k >= 0 && curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            k--;
        }
        l.next = curr;
        return prev;
    }

    public static ListNode reverseSublist(ListNode head, int left, int right) {
        int diff = left - right;
        if (left == 1) {
            return reverseL(head, diff);
        }
        ListNode prev = null;
        ListNode curr = head;
        //go till the left node
        while (left > 0) {
            prev = curr;
            curr = curr.next;
            left--;
        }
        //reverse the sublist between left to right
        ListNode temp = reverseL(curr, diff);
        prev.next = temp; //re-connect to the original list
        return head;
    }

    public static boolean listHasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (slow != null && fast != null) {
            slow = slow.next;
            if (fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static ListNode findStartOfCycle1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;
        while (fast != null && slow != null) {
            slow = slow.next;
            if (fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle) {
            return null;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static ListNode findCycle2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            //cycle found!
            if (slow == fast) {
                slow = head; //reset head
                //now try to find the start of cycle
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    private static int lengthOf(ListNode l) {
        int ctr = 0;
        while (l != null) {
            l = l.next;
            ctr++;
        }
        return ctr;
    }

    private static void prependZeros(ListNode l, int k) {
        while (k > 0) {
            ListNode temp = new ListNode(0);
            temp.next = l;
            l = temp;
            k--;
        }
    }

    public static ListNode intersectionPoint(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        int len1 = lengthOf(l1);
        int len2 = lengthOf(l2);
        if (len1 > len2) {
            prependZeros(l2, len1 - len2);
        } else {
            prependZeros(l1, len2 - len1);
        }
        ListNode p1 = l1;
        ListNode p2 = l2;
        while (p1 != null && p2 != null) {
            if (p1 == p2) {
                return p1;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return null;
    }

    //No preprocessing of the lists
    //Ref:https://leetcode.com/problems/intersection-of-two-linked-lists/discuss/49785/Java-solution-without-knowing-the-difference-in-len!
    public static ListNode findIntersectionOnePass(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        ListNode p1 = l1;
        ListNode p2 = l2;
        while (p1 != p2) {
            p1 = p1 == null ? l2 : p1.next;
            p2 = p2 == null ? l1 : p2.next;
        }
        return p1;
    }

    /**
     * case 1: no cycle, overlap
     * l1 = 1->2->3->4->NULL
     * l2 = 12->33->4
     * op: 4
     * case 2: cycle in l1, overlap
     * l1 = 1->2->3->4->2
     * l2 = 12->33->44->4
     * op:4?
     * case 3: cycle in l2, overlap
     * l1 = 1->2->3->4->44
     * l2 = 12->33->44->12
     * op: 44
     * case 4: cycle in both
     * l1 = 1->2->3->4->2
     * l2 = 12->2->33->44->2
     * TODO: the problem solution is difficult to understand.
     */
    public static ListNode findIntersectionWithCycles(ListNode l1, ListNode l2) {
        ListNode c1 = findStartOfCycle1(l1);
        ListNode c2 = findStartOfCycle1(l2);
        //IF no cycle in either of them, return normally to findIntersectionOnePass
        if (c1 == null && c2 == null) {
            return findIntersectionOnePass(l1, l2);
        }
        return null;
    }

    public static void deleteMiddleNode(ListNode ptrToMiddle) {
        if (ptrToMiddle == null) {
            return;
        }
        ptrToMiddle.val = ptrToMiddle.next.val;
        ptrToMiddle.next = ptrToMiddle.next.next;
    }

    public static ListNode kthToLastNode(ListNode l, int k) {
        if (l == null) {
            return null;
        }
        //first move the fast pointer to k+1th location
        ListNode fast = l;
        while (k-- > 0) {
            fast = fast.next;
        }
        ListNode slow = l;
        //encounter for a single node list when k == 1
        if (fast == null) {
            return slow.next;
        }
        //else move fast to end of list, slow will be pointing to kth loc
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next; //delte the kth node
        l = slow;//reset head
        return l;
    }

    public static ListNode removeDuplicatesSortedWithInnerLoop(ListNode l) {
        if (l == null) {
            return null;
        }
        ListNode curr = l;
        while (curr != null) {
            ListNode temp = curr.next;
            while (temp != null && curr.val == temp.val) {
                temp = temp.next;
            }
            curr.next = temp;
            curr = temp;
        }
        return l;
    }

    public static ListNode removeDuplicatesSortedWith1pass(ListNode l) {
        if (l == null) {
            return null;
        }
        ListNode curr = l;
        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) {
                curr = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return l;
    }

    public static ListNode deleteNodesRec(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        head.next = deleteNodesRec(head.next);
        return head.val == head.next.val ? head.next : head;
    }
    //ref: https://leetcode.com/problems/rotate-list/discuss/22735/My-clean-C%2B%2B-code-quite-standard-(find-tail-and-reconnect-the-list)
    //ref2: https://leetcode.com/problems/rotate-list/discuss/22735/My-clean-C++-code-quite-standard-(find-tail-and-reconnect-the-list)/229476
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) { return null; }
        int ctr = 1;
        ListNode temp = head;
        while (temp.next != null) {
            ctr++;
            temp = temp.next;
        }

        temp.next = head;

        k %=ctr;
        int newK = ctr - k;

        if (newK == 0) { return head; }

        for (int i = 0; i < newK; i++) {
            temp = temp.next;
        }
        head = temp.next;
        temp.next = null;
        return head;
    }
    //tries to rotate without calculating the len of the list initially
    //Ref: https://leetcode.com/problems/rotate-list/discuss/22726/Anyone-solve-the-problem-without-counting-the-length-of-List/22221
    public static ListNode cyclicRightShift(ListNode head, int k) {
        if (head == null || k <= 0) { return null; }
        ListNode slow = head, fast = head;
        for (int i = 0; i < k; i++) {
            if (fast.next != null) {
                fast = fast.next; //move fast by k steps into the list
            } else {
                k %=(i-1);//if k > len of list or too big
                i-=1;//count from beginning
                fast = head;//reset to head of list
            }
        }
        while(fast.next!=null) {
            fast = fast.next;
            slow = slow.next;//slow will k+1th step from tail when fast reaches the end
        }
        fast.next = head;
        head = slow.next;
        slow.next = null;
        return head;
    }
    //Ref: https://leetcode.com/problems/odd-even-linked-list/discuss/78120/Straigntforward-Java-solution-O(1)-space-O(n)-time
    public static ListNode evenOddMerge(ListNode head) {
        if (head == null) { return null; }
        ListNode odd = head;
        ListNode evenHead = head.next;
        ListNode even = evenHead;
        while (even!=null && even.next!=null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even=even.next;
        }
        odd.next=evenHead; //odd to be followed by even
        return head;
    }
    private static ListNode reverseList(ListNode l) {
        ListNode curr = l;
        ListNode prev = null, next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        l = prev;
        return prev;
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null) { return false; }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;//increment fast so as to get slow to the mid of the list
        }
        if (fast != null) { slow = slow.next; } //to account of for odd. numbered list
        slow = reverseList(slow); //reverse first part of the list
        fast = head; //reset head
        while (slow != null) {
            if (slow.val != fast.val) { return false; }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }
    private static ListNode appendTo(ListNode l, int v) {
        if (l == null) {
            l = new ListNode(v);
        } else {
            ListNode c = l;
            ListNode nn = new ListNode(v);
            nn.next = null;
            while(c.next != null){
                c = c.next;
            }
            c.next = nn;
        }
        return l;
    }
    public static ListNode partitionList(ListNode head, int x) {
        if (head == null) { return null; }
        ListNode curr = head;
        ListNode left = head;
        ListNode right = head;
        while (curr != null) {
            if (curr.val < x) {
                left = appendTo(left, curr.val);
            } else {
                right = appendTo(right, curr.val);
            }
            curr = curr.next;
        }
        if (right == null && left != null) { return left; }
        if (left == null && right != null) { return right; }
        if (right == null && left == null) { return null; }
        ListNode temp = left;
        while (temp.next!=null) {
            temp = temp.next;
        }
        temp.next = right;
        right.next = null;
        return left;
    }

    private static ListNode solve(ListNode l1, ListNode l2, int c) {
        if (l1 == null && l2 == null && c == 0) {
            return null;
        }
        ListNode res = new ListNode();
        int val = c;
        if (l1 != null) {
            l1.val += val;
        }
        if (l2 != null) {
            l2.val += val;
        }
        res.val = val % 10; //here the result is getting appended to the tail of the list, and carry is passed
        if (l1 != null && l2 != null) {
            res.next = solve(l1 == null ? null : l1.next, l2 == null ? null : l2.next, val >= 10 ? 1: 0);
        }
        return res;
    }
    public static ListNode addListsReverse(ListNode l1, ListNode l2) {
        return solve(l1, l2, 0);
    }
}
