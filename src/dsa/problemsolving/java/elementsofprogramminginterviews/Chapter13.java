package dsa.problemsolving.java.elementsofprogramminginterviews;
import java.util.*;
import dsa.problemsolving.java.elementsofprogramminginterviews.Chapter7.ListNode;
public class Chapter13 {

    //returned output should consists of unique elements only that are present in both the lists
    /*
    Try1:
      //have a res list/set
        //iterate over both lists using ctr1, and ctr2
            //if l1.get(ctr1) <= l2.get(ctr2):
                //add to res(l1.get(ctr1))
            //else
                //add to res(l2.get(ctr2))
        //for lists of unequal lengths iterate over l1 and l2 again to add remanining elements
        //return the set/list
        time: O(L1+L2) and space will be O(l1 size + l2size)
        Try2:

     */
    private static class LLNode {
        private int val;
        private LLNode next;
    }
    //time: O(L1 size + l2 size), space:O(1)
    public static LLNode intersectionOfSortedLists(LLNode l1, LLNode l2) {
        LLNode dummy = new LLNode();
        LLNode curr = dummy;
        while(l1 != null && l2 != null) {
            if (l1.val == l2.val) {
                curr.next = l1;
                curr = curr.next;
                l1 = l1.next;
                l2 = l2.next;
            } else if (l1.val < l2.val) {
                l1 = l1.next;
            } else {
                l2 = l2.next;
            }
        }
        curr.next = null; //reached the end of intersection;
        return dummy.next;
    }
    /*l1 has extra space in the end to accomodate more elements in it
    ac=11

    l1=[5,13,17,_,_,_,_,_]
               l2
    l2=[3,7,11,19]
     */
    public static List<Integer> merge2SortedLists(List<Integer> l1, int m, List<Integer> l2, int n) {
        if (l1 == null && l2 == null || m < n) {
            return new ArrayList<>();
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        int actual = m + n - 1;
        int last1 = m - 1;
        int last2 = n - 1;
        while (last1 >= 0 && last2 >= 0) {
            if (l1.get(last1) > l2.get(last2)) {
                l1.set(actual, l1.get(last1));
                last1--;
            } else {
                l1.set(actual, l2.get(last2));
                last2--;
            }
            actual --;
        }
        while (last2 >= 0) {
            l1.set(actual, l2.get(last2));
            last2--;
        }
        return l1;
    }
    //time: O(m+n)
    public static int[] mergeTwoSortedArrays(int[] a, int []b) {
        int M = a.length;
        int N = b.length;
        int []merged = new int[M + N];
        int i =0, j =0, ctr = 0;
        while (i < M && j < N) {
            if (a[i] <= b[j]) {
                merged[ctr++] = a[i++];
            } else {
                merged[ctr++] = b[j++];
            }
        }
        while (i < M) {
            merged[ctr++] = a[i++];
        }
        while (j < N) {
            merged[ctr++] = b[j++];
        }
        return merged;
    }

    private static class Name implements Comparable<Name> {
        String fName;
        String lName;

        @Override
        public int compareTo(Name s1) {
            int c = this.fName.compareTo(s1.fName);
            if (c != 0) {
                return c;
            }
            return this.lName.compareTo(s1.lName);
        }
    }
    public static void eliminateDuplicate(List<Name> N) {
        Collections.sort(N);
        int idx=1;
        for (int i=1; i< N.size(); i++) {
            if (!N.get(i).fName.equals(N.get(idx).fName)) {
                N.set(++idx, N.get(i));
            }
        }
        N.subList(idx, N.size()).clear();
    }
    /*
    ref: https://www.geeksforgeeks.org/find-smallest-value-represented-sum-subset-given-array/
    eg:
    Input:  arr[] = {1, 3, 6, 10, 11, 15};
    Output: 2
    Input:  arr[] = {1, 1, 1, 1};
    Output: 5

    Input:  arr[] = {1, 1, 3, 4};
    Output: 10

    Input:  arr[] = {1, 2, 5, 10, 20, 40};
    Output: 4

    Input:  arr[] = {1, 2, 3, 4, 5, 6};
    Output: 22
    try1:
    1. you cannot create a sum > sum(a)
    2. while i < len(a):
        - init a sum
        - calc. sum+=a[i]
        - store sum in a set
    3. sort the set
    4. try to find the min number not present in the set, that will be the answer
    time: O(n*n) to calc. sum and O(nlog) to sort, space:O(n+c)
    try2:
    1. sort the input
    2. check if each element is ==1 OR ==0 diff apart, if they are, ret. sum(a) + 1 --> not needed
    3. init a min sum
    4. iterate over the arr. calculating sum+=a[i]
    5. when to stop?
        - when we get a number i such that sum +a[i] > sum + 1, return sum, else loop
    6. O(len(a)), space: O(1)
    trick: a collection of numbers are able to generate every value including and upto V, but not V + 1.
    */
    public static int smallestNonConstructibleValue(int[] a) {
        Arrays.sort(a);
        int currSum = 0;
        for (int i: a) {
            if (currSum + 1 < i) {
                break;
            }
            currSum +=i;
        }
        return currSum + 1;
    }
    /*
        A program that takes a set of events and determines the max. no.of events that take place concurrently
     */
    private static class Event {
        int start, end;
    }
    private static class Endpoint implements Comparable<Endpoint>{
        int time;
        boolean isStart;
        @Override
        public int compareTo(Endpoint e) {
            if (time != e.time) {
                return Integer.compare(time, e.time);
            }
            //if 2 endpoints have the same times(1 is start and 1 is finish, start will take precedence)
            //else if 2 endpoints have equal either start or end times, ties are broken arbitrarily
            return isStart && !e.isStart ? -1 : !isStart && e.isStart ? 1 : 0;
        }
        public Endpoint(int t, boolean b) {
            this.time = t;
            this.isStart = b;
        }
    }

    public static int findMaxSimultaneousEvents(List<Event> events) {
        List<Endpoint> ea = new ArrayList<>();
        for (Event e : events) {
            ea.add(new Endpoint(e.start, true));
            ea.add(new Endpoint(e.end, false));
        }
        Collections.sort(ea);//sort on time, putting start time before end time
        int maxSimultaneousEvents = 0, simultaneousEvents = 0;
        for (Endpoint ep : ea) {
            if (ep.isStart) {
                ++simultaneousEvents;
                maxSimultaneousEvents = Math.max(maxSimultaneousEvents, simultaneousEvents);
            } else {
                --simultaneousEvents;
            }
        }
        return maxSimultaneousEvents;
    }
    public static int[][] mergeIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return null;
        }
        List<int[]> res = new ArrayList<>();
        int[] newInterval = intervals[0];
        //we need something to start with
        res.add(newInterval);
        for (int[] interval : intervals) {
            //if there is an overlap, i.e. interval[i]'s start time <= newInterval's end time, update the endtime of the
            //newInterval to be max(newInterval's endtime, intervals[i]'s end time)
            if (interval[0] <= newInterval[1]) {
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            } else {
                //there is no overlap, just append to the result the next interval
                newInterval = interval;
                res.add(newInterval);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
    public static int[][] insertInterval(int[][] intervals, int[] newInterval) {
        //since that is where our newInterval is creating an overlap. Once we find the bail index, we call mergeIntervals(intervals, newInterval)
        List<int[]> res = new ArrayList<>();
        int bailIndex=0;
        //search for the index where we can bail out of a linear scan where intervals[bailIndex][endTime] < newInterval[startTime]
        while(bailIndex < intervals.length && intervals[bailIndex][1] < newInterval[0]) {
            res.add(intervals[bailIndex]);
            bailIndex++;
        }
        //starting from the bail index, merge the intervals(OR ref. to above code the if/else code will go here)
        while(bailIndex < intervals.length && intervals[bailIndex][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[bailIndex][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[bailIndex][1]);
            bailIndex++;
        }
        res.add(newInterval);
        //add the rest of the intervals
        while(bailIndex < intervals.length) {
            res.add(intervals[bailIndex]);
            bailIndex++;
        }
        return res.toArray(new int[res.size()][]);
    }
    //Ref: https://leetcode.com/discuss/interview-question/566541/facebook-phone-arrange-players-in-football-team-according-to-their-heights
    private static class Player implements Comparable<Player> {
        private int height;
        public Player(int h) {
            this.height = h;
        }
        public int compareTo(Player p) {
            return Integer.compare(this.height, p.height);
        }
    }
    private static class Team {
        private List<Player> players;
        public Team(List<Integer> heights) {
            players = new ArrayList<>(heights.size());
            for (int i =0; i < heights.size(); i++) {
                players.add(new Player(heights.get(i)));
            }
        }
        private void sortPlayersByHeight() {
            Collections.sort(this.players);
        }
        public static boolean isPossible(Team A, Team B) {
            A.sortPlayersByHeight();
            B.sortPlayersByHeight();
            for (int i = 0; i < A.players.size() && i < B.players.size(); i++) {
                if (A.players.get(i).compareTo(B.players.get(i)) >= 0) {
                    return false;
                }
            }
            return true;
        }
    }
    //stable sort algo on lists
    //we can either use insertion sort or merge sort. Insertion will give us O(n^2) and merge will give us O(nlogn)
    private static ListNode mergeSortedListsChaptr13(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) { return null; }
        if (l1 == null) { return l2; }
        if (l2 == null) { return l1; }
        ListNode dummy = new ListNode(-999);
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
        while (l1 != null) {
            curr.next = l1;
            l1 = l1.next;
        }
        while (l2 != null) {
            curr.next = l2;
            l2 = l2.next;
        }
        return dummy.next;
    }
    public static ListNode sortList(ListNode l) {
        if (l == null || l.next == null) { return null; }
        ListNode slow = l,fast = l, dummy = l;
        while(fast != null && fast.next != null) {
            dummy = slow;
            fast = fast.next.next;
            slow= slow.next;
        }
        dummy.next = null; //we split list l into 2 halves
        return mergeSortedListsChaptr13(sortList(l), sortList(slow));
    }
    /*
    salaries=[90,30,100,40,20], target=210, cap=60
    try1:
    1. sort the salaries [20,30,40,90,100]
    2. sum of salaries = 280
    280-210 = 70
    3. [20,30,40,70,70],target=210, sum=230
    4.
    try2:
    k = cap is supposed to lie between kth and k+1 th salaries
    come up with a formula for c(n - k) = T - (sum from 0 to k - 1 over salaries)
     */
    public static double salaryCap(double targetPayroll, List<Double> salaries) {
        double sum = 0;
        Collections.sort(salaries);
        for (int i=0;i< salaries.size(); i++) {
            //basically calculating
            int adjusted = (int)(salaries.get(i) * (salaries.size() - i));
            if (sum + adjusted >= targetPayroll) {
                return ((targetPayroll - sum) / adjusted);
            } else {
                sum +=salaries.get(i);
            }
        }
        return -1.0;
    }

    /*
    SORT5:
    given 25 distinct integers, and a function SORT5, get 1st/2nd/3rd largest while making as minimum no.of calls to SORT5
    try1:
    1. if possible to make use of auxiliary space, call SORT5 five times on those distinct integers.
    2. store the set of 5 sorted integers into 5 collections
    3. iterate over all 5 collections from rear to front. The last elements will be having max value. calc. the max that will
    be the max elem, dec. pointers, repeat the procedure for second largest, and continue
    4. get the req. result.
    5. total calls: 5
    try2:
    1. sort the initial set of numbers into 5 buckets say B1..B5
    2. we can find the largest which will be the max(max1,max2...max5), to find second and third largest is tricky
    3. so, we assume m1 > m2 > m3 > m4 > m5, and m1 will be the only max. To find second and third largest, we cannot get them from b4 and b5
    neither are m4 and m5 as the answers. So, we can either have
    a1,a2 the next largest elements from B1 to be second/third largest
    m2,b2  the next largest elements from B2 to be second/third largest
    m3 will be the third smallest value from B3
    so having a collection of above elements and calling SORT5 again will give us the answer to second and third largest elements
     */

}
