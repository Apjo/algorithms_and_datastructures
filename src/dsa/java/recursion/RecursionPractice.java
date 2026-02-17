package dsa.java.recursion;

import java.util.*;

//This class includes problems from Aditya Verma's Recursion playlist
//https://www.youtube.com/watch?v=J2Er5XceU_I&list=PL_z_8CaSLPWeT1ffjiImo0sYTcnLzo-wY
// and a few others from LC
public class RecursionPractice {
    // Approach using the actual theoretical logic of Catalan Number formula
    // Approach using memoization,O(n^2).
    public static long howManyBstsMemoized(int n) {
        long[] memo = new long[17];
        Arrays.fill(memo, -1L);
        if (n == 0) {
            return 1L;
        }

        if (memo[n] != -1L) {
            return memo[n];
        }
        long cnt = 0L;
        for (int numNodesInLeftBST = 0; numNodesInLeftBST < n; numNodesInLeftBST++) {
            int numNodesInRightBST = n - 1 - numNodesInLeftBST;
            cnt += how_many_BSTs(numNodesInLeftBST) * how_many_BSTs(numNodesInRightBST);

        }
        memo[n] = cnt;
        return cnt;
    }

    // Determine how many BSTs can be made from n unique keys. Brute force approach
    // using recursion. Loose bound time complexity O(Catalan number(n)).
    public static long how_many_BSTs(int N) {
        long cnt = 0l;
        if (N == 0) {
            return 1L;
        }
        for (int numNodesInLeftBST = 0; numNodesInLeftBST < N; numNodesInLeftBST++) {
            int numNodesInRightBST = N - 1 - numNodesInLeftBST;
            cnt += how_many_BSTs(numNodesInLeftBST) * how_many_BSTs(numNodesInRightBST);
        }
        return cnt;
    }

    // done it as per LC#131 requirements
    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        List<String> buff = new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        solvePalindromPartition(s, buff, res);
        return res;
    }

    private static void solvePalindromPartition(String s, List<String> buff, List<List<String>> res) {
        if (s.length() == 0 || s == null) {
            // buff.add(s);
            res.add(new ArrayList<>(buff));
            return;
        }
        for (int i = 1; i <= s.length(); i++) {
            String ss = s.substring(0, i);
            if (!isStrPalindrome(ss.toCharArray(), 0, ss.length() - 1)) {
                continue;
            }
            buff.add(ss);
            solvePalindromPartition(s.substring(i, s.length()), buff, res);
            buff.remove(buff.size() - 1);
        }
    }

    private static boolean isStrPalindrome(char[] aa, int lo, int hi) {
        while (lo < hi) {
            if (aa[lo] == aa[hi]) {
                lo++;
                hi--;
            } else {
                return false;
            }
        }
        return true;
    }

    public static String[] allPalindromicSubstrings(String s) {
        List<String> buff = new ArrayList<>();
        char[] op = new char[s.length() * 2];
        solvePalind(s.toCharArray(), buff, op, 0, 0);
        String[] finalOp = new String[buff.size()];
        return buff.toArray(finalOp);
    }

    private static boolean isPalindrome(char[] a, int lo, int hi) {
        while (lo < hi) {
            if (a[lo] == a[hi]) {
                lo++;
                hi--;
            } else {
                return false;
            }
        }
        return true;
    }

    private static void solvePalind(char[] s, List<String> res, char[] op, int ipIdx, int opIdx) {
        if (ipIdx == s.length) {
            res.add(new String(op, 0, opIdx - 1));
            return;
        }
        for (int i = ipIdx; i < s.length; i++) {
            op[opIdx++] = s[i];
            if (isPalindrome(s, ipIdx, i)) {
                op[opIdx] = '|';
                solvePalind(s, res, op, i + 1, opIdx + 1);
            }
        }
    }

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

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(head.next.next);
        newHead.next = head;
        return newHead;

    }

    // Print N-bit binary numbers having more 1’s than 0’s in all prefixes
    // time: O(2^N)
    public static List<String> binaryStringPrefixes(int N) {
        if (N < 2) {
            return null;
        }
        List<String> res = new ArrayList<>();
        solveBinaryStringPrefixes(N, 0, 0, new StringBuilder(), res);
        return res;
    }

    private static void solveBinaryStringPrefixes(int N, int ones, int zeros, StringBuilder sb, List<String> res) {
        if (N == 0) {
            res.add(sb.toString());
            return;
        }
        sb.append("1");
        solveBinaryStringPrefixes(N - 1, ones + 1, zeros, sb, res);
        sb.deleteCharAt(sb.length() - 1);
        if (ones > zeros) {
            sb.append("0");
            solveBinaryStringPrefixes(N - 1, ones, zeros + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    // time: O(2^n) for every input there are 2 choices of either '(' or ')'. Space:
    // O(n)
    public static List<String> generateBalancedParens(int N) {
        List<String> result = new ArrayList<>();
        solve(N, N, result, new StringBuilder());
        return result;
    }

    private static void solve(int open, int close, List<String> res, StringBuilder sb) {
        // if (open > close) { // backtracking case
        // return;
        // }
        if (open == 0 && close == 0) { // base case
            res.add(sb.toString());
            return;
        }
        if (open > 0) {// we still have open, use them!
            sb.append("(");
            solve(open - 1, close, res, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close > open) { // continue using close now that we have consumed open
            sb.append(")");
            solve(open, close - 1, res, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    // time: O(n*2^n), space: O(n*2^n)
    public static List<String> letterCasePermutations(String s) {
        List<String> result = new ArrayList<>();
        solveLetterCasePermutations("", s, result);
        return result;
    }

    private static void solveLetterCasePermutations(String prefix, String input, List<String> res) {
        if (input.length() == 0) {
            res.add(prefix);
            return;
        }
        Character curr = input.charAt(0);
        if (Character.isDigit(curr)) {
            solveLetterCasePermutations(prefix + curr, input.substring(1), res);
        } else {
            solveLetterCasePermutations(prefix + Character.toLowerCase(curr), input.substring(1), res);
            solveLetterCasePermutations(prefix + Character.toUpperCase(curr), input.substring(1), res);
        }
    }

    // Time: O(2^n * n)
    public static void permuteCaseChange(String s) {
        solvepermuteCaseChange2("", s);
    }

    // Aditya Verma's way, ends up creating 2 StringBuilders
    private static void solvepermuteCaseChange(String prefix, String s) {
        if (s.length() == 0) {
            System.out.println(prefix);
            return;
        }
        StringBuilder op1 = new StringBuilder();
        op1.append(prefix);
        StringBuilder op2 = new StringBuilder();
        op2.append(prefix);
        op1.append(Character.toLowerCase(s.charAt(0)));
        op2.append(Character.toUpperCase(s.charAt(0)));
        solvepermuteCaseChange(op1.toString(), s.substring(1));
        solvepermuteCaseChange(op2.toString(), s.substring(1));
    }

    // Not making extra space
    private static void solvepermuteCaseChange2(String prefix, String s) {
        if (s.length() == 0) {
            System.out.println(prefix);
            return;
        }
        char ss = s.charAt(0);
        solvepermuteCaseChange2(prefix + Character.toUpperCase(ss), s.substring(1));
        solvepermuteCaseChange2(prefix + Character.toLowerCase(ss), s.substring(1));
    }

    // Time: O(10^n * n), space: O(n)
    public static void printDecimal(int N) {
        solveDecimal("", N);
    }

    private static void solveDecimal(String prefix, int N) {
        if (N == 0) {
            System.out.println(prefix);
            return;
        }
        for (int i = 0; i <= 9; i++) {
            solveDecimal(prefix + i, N - 1);
        }
    }

    // time: O(n*2^n)
    public static void printBinaryOfLength(int N) {
        solveBin("", N);
    }

    private static void solveBin(String prefix, int N) {
        if (N == 0) {
            System.out.println(prefix);
            return;
        }
        solveBin(prefix + "0", N - 1);
        solveBin(prefix + "1", N - 1);
    }

    public static void printPermutationsWithSpaces(String s) {
        solveSpaces(String.valueOf(s.charAt(0)), s.substring(1));
    }

    private static void solveSpaces(String prefix, String s) {
        if (s.length() == 0) {
            System.out.println(prefix);
            return;
        }
        StringBuilder op1 = new StringBuilder();
        op1.append(prefix);
        StringBuilder op2 = new StringBuilder();
        op2.append(prefix);
        op1.append(" ");
        op1.append(s.charAt(0));
        op2.append(s.charAt(0));
        solveSpaces(op1.toString(), s.substring(1));
        solveSpaces(op2.toString(), s.substring(1));
    }

    // Time: O()
    public static void printPermutations(String s) {
        solve("", s);
    }

    private static void solve(String prefix, String s) {
        if (s.length() == 0) {
            System.out.println(prefix);
        }
        for (int i = 0; i < s.length(); i++) {
            String selected = prefix + s.charAt(i);
            String remaining = s.substring(0, i) + s.substring(i + 1);
            solve(selected, remaining);
        }
    }

    // TC: O(2^N). here the subsets should not contain duplicates if the input
    // contains duplicates
    public static List<List<Integer>> subsetsWithNoDups(int[] a) {
        if (a == null) {
            return null;
        }
        Arrays.sort(a);
        List<Integer> buff = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        solveNoDup(a, res, buff, 0);
        return res;
    }

    private static void solveNoDup(int[] a, List<List<Integer>> res, List<Integer> temp, int idx) {
        res.add(new ArrayList<>(temp));
        for (int i = idx; i < a.length; i++) {
            if (i > idx && a[i] == a[i - 1])
                continue;
            temp.add(a[i]);
            solve(a, res, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    // recursive or the dfs approach
    public static List<List<Integer>> subsetsRec(int[] a) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        solveDfs(a, res, temp, 0);
        return res;
    }

    private static void solveDfs(int[] a, List<List<Integer>> res, List<Integer> temp, int idx) {
        if (idx == a.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        // exclude case
        solve(a, res, temp, idx + 1);
        // include case
        temp.add(a[idx]);
        solve(a, res, temp, idx + 1);
        temp.remove(temp.size() - 1);
    }

    // Ref:
    // https://leetcode.com/problems/subsets/discuss/27288/My-solution-using-bit-manipulation/26402
    public static List<List<Integer>> solveBitmasks(int[] A) {
        int N = A.length;
        List<List<Integer>> res = new ArrayList<>((1 << N));
        for (int i = 0; i < (1 << N); i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) {
                    temp.add(A[j]);
                }
            }
            res.add(temp);
        }
        return res;
    }

    // Generate subsets of a set, where input contains all unique elements, and so
    // does the output
    // Time: O(n*2^n)
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<Integer>();
        solve(nums, res, temp, 0);
        return res;
    }

    private static void solve(int[] A, List<List<Integer>> res, List<Integer> temp, int idx) {
        // Each time the temp List changes during the recursive course of the function.
        // First it was [], then [1], then [1, 2], then [1,2,3], each of them is
        // actually a new list which is just using
        // the same name "temp". If we just do "add(temp)", the object temp
        // which was just having a value as [], gets
        // added, and gets again and again added, resulting in a answer of just empty
        // list like []. But we want each of our
        // intermediate results like [1], [1,2] added as a new object into the list res,
        // so we use "new ArrayList<>(temp)"
        res.add(new ArrayList<Integer>(temp));
        for (int i = idx; i < A.length; i++) {
            temp.add(A[i]);
            solve(A, res, temp, i + 1);
            temp.remove(temp.size() - 1); // backtrack step
        }
    }

    // Tower of Hanoi, O(2^N), makes 2^(N -1) moves
    static void solveHanoi(int N, int src, int dest, int aux) {
        if (N == 1) {
            System.out.println("Move disk= " + N + " from src= " + src + " dest= " + dest);
            return;
        }
        solveHanoi(N - 1, src, aux, dest);
        System.out.println("Move disk= " + N + " from src= " + src + " dest= " + dest);
        solveHanoi(N - 1, aux, dest, src);
    }

    // Return kth symbol in grammar
    // TC: O(log N)
    static int kthSymbolInGrammar(int n, int k) {
        if (n == 1 && k == 1) {
            return 0;
        }
        int mid = (int) (Math.pow(2, n - 1)) / 2;
        if (k <= mid) {
            return kthSymbolInGrammar(n - 1, k);
        } else {
            if (kthSymbolInGrammar(n - 1, k - mid) == 0)
                return 1;
            else
                return 0;
        }
    }

    // reverse a stack
    public static void reverseStack(Stack<Integer> st) {
        if (st == null || st.size() == 1) {
            return;
        }
        int v = st.peek();
        st.pop();
        reverseStack(st);
        insertRev(st, v);
    }

    private static void insertRev(Stack<Integer> st, int v) {
        if (st.isEmpty()) {
            st.push(v);
            return;
        } else {
            int vv = st.peek();
            st.pop();
            insertRev(st, v);
            st.push(vv);
        }
    }

    // delete middle element of a stack(of Integers) recursively
    public static void delMid(Stack<Integer> st) {
        if (st.isEmpty()) {
            return;
        }
        int k = (st.size() / 2) + 1;
        solve(st, k);
    }

    private static void solve(Stack<Integer> st, int k) {
        if (k == 1) {
            st.pop();
        } else {
            int v = st.peek();
            st.pop();
            solve(st, k - 1);
            st.push(v);
        }
    }

    // print a sorted stack(of Integers) in descending order recursively.
    public static void sortStack(Stack<Integer> st) {
        if (st == null || st.size() == 1) {
            return;
        }
        int v = st.pop();
        sortStack(st);
        insert(st, v);
    }

    private static void insert(Stack<Integer> st, int e) {
        if (st.isEmpty() || st.peek() <= e) {
            st.push(e);
        } else {
            int v = st.pop();
            insert(st, e);
            st.push(v);
        }
    }

    private static void printStack(Stack<Integer> st) {
        System.out.println(Arrays.toString(st.toArray()));
    }

    public static void main(String[] args) {
        Stack<Integer> input = new Stack<>();
        input.push(1);
        input.push(5);
        input.push(0);
        input.push(2);
        System.out.println("Stack before sorting= ");
        printStack(input);
        sortStack(input);
        System.out.println("Stack after sorting= ");
        printStack(input);
        input.push(22);
        System.out.println("Stack before deleting mid= ");
        printStack(input);
        delMid(input);
        System.out.println("Stack after deleting mid= ");
        printStack(input);
        System.out.println("Stack before reversing= ");
        printStack(input);
        System.out.println("Stack after reversing= ");
        reverseStack(input);
        printStack(input);
        System.out.println("N= " + 4 + " K= " + 5 + " kth grammar symbol= " + kthSymbolInGrammar(4, 5));
        System.out.println("Tower of Hanoi for disks=3, src=0, destination=1, aux=2");
        solveHanoi(3, 0, 1, 2);
        int[] arr = new int[] { 1, 2, 3 };
        List<List<Integer>> result = subsetsRec(arr);
        System.out.println("Subset recursive approach= " + result);
        System.out.println("Printing permutations(duplicates included) of string=abb");
        printPermutations("abb");
        System.out.println("Printing permutations with spaces of string=ABC");
        printPermutationsWithSpaces("ABC");
        System.out.println("Printing all binary strings of Length = 4");
        printBinaryOfLength(4);
        System.out.println("Printing all decimal strings of Length = 2");
        printDecimal(2);
        System.out.println("Printing case change permutations of string=ab");
        permuteCaseChange("ab");
        List<String> permutations = letterCasePermutations("a1B2");
        System.out.println("Printing letter case change permutations of string=a1B2");
        System.out.println(Arrays.toString(permutations.toArray()));

        System.out.println("ALL Balanced parantheses for N=3");
        List<String> parens = generateBalancedParens(3);
        System.out.println(Arrays.toString(parens.toArray()));
        System.out.println("Print for N=3, N-bit binary numbers having more 1’s than 0’s for any prefix");
        List<String> prefixes = binaryStringPrefixes(3);
        System.out.println(Arrays.toString(prefixes.toArray()));
        System.out.println("ALL PALINDROMIC DECOMPOSITIONS for \"ab\"");
        System.out.println(Arrays.toString(allPalindromicSubstrings("abracadabra")));
    }

}