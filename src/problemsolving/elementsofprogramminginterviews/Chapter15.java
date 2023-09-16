package problemsolving.elementsofprogramminginterviews;
import java.util.*;
import problemsolving.elementsofprogramminginterviews.Chapter7.ListNode;
public class Chapter15 {
    //LC#784: Letter case permutation
    //space:
    // O(n) for input +
    // aux space= implicit stack space = (1+2+3+..n) = O(n^2) since at each level we create string starting from len 0, 1, 2.. n +
    // explicit space in result output = O(2^n) * n => 2^n strings generated each of len n
    // so overall space: O(2^n * n)
    //Time: each leaf is doing constant amount of work= O(1), in worst case we have O(2^n) leaf level workers = O(2^n * 1) + complexity of internal node workers
    // is O(2^n*n)
    //In case of character arrays(mutable slate) aux.space = O(n) because in the call stack we do not have the inner strings proliferating generated at each level,
    // we just have a single master copy that is growing and shrinking, so when look at the snapshot of a call stack it is O(n)
    // vs. in case of time complexity fo this case:
    //leaf workers work for O(n) now they have to make a fresh string out of the slate which takes O(n)
    //each internal node is doing constant work
    //there are 2^n leaf workers
    private static void solveLetterCasePerm(char[]s, int i, List<String> ss) {
        if (i == s.length) {
            ss.add(new String(s));
            return;
        } else {
            if (Character.isDigit(s[i])) {
                solveLetterCasePerm(s, i + 1, ss);
            } else {
                //upper case
                s[i] = Character.toUpperCase(s[i]);
                solveLetterCasePerm(s, i + 1, ss);
                //lower case
                s[i] = Character.toLowerCase(s[i]);
                solveLetterCasePerm(s, i + 1, ss);
            }
        }
    }
    public static List<String> letterCasePermu(String w) {
        List<String> res = new ArrayList<>();
        solveLetterCasePerm(w.toCharArray(), 0, res);
        return res;
    }
    //LC#78: Subsets https://leetcode.com/problems/subsets/
    //space: O(2^n*n)
    //time: leaf (2^n total workers doing n amount of work of mutating the slate + internal(on avg. 2^n nodes + constant work) => O(2^n * n)
    private static void solveSubSets(int[]ar, int i, List<Integer> s1, List<List<Integer>> s2) {
        if (i == ar.length) {
            s2.add(new ArrayList<Integer>(s1));
            return;
        }
        //exclude
        solveSubSets(ar, i + 1, s1, s2);
        //include
        s1.add(ar[i]);
        solveSubSets(ar, i + 1, s1, s2);
        s1.remove(s1.size() - 1);
    }
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        solveSubSets(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    private static void solvePerm(int []n, int i, List<List<Integer>> s1, List<Integer> s2) {
        if (i == n.length) {
            s1.add(new ArrayList<>(s2));
            return;
        }
        //we have arbitrary number of choices infact it decreases as we go down the level
        //Also, we need to make sure the subproblem that gets passed down is a contiguous array, hence the swap
        for (int pick = i; pick < n.length; pick++) {
            swap(n, pick, i);
            s2.add(n[i]);
            solvePerm(n, i + 1, s1, s2);
            s2.remove(s2.size() - 1);
            swap(n, pick, i);
        }
    }
    //LC#46: Permutations
    //answer: https://leetcode.com/problems/permutations/discuss/179932/Beats-100-Java-with-Explanations
    //Space: input O(n) + aux (O(n)) + output(n! at leaf level * n is size of each op) => O(n! * n)
    //time: leaf workers(n! leaf worker and work done by each worker is proportional to size of slate i.e. O(n)= O(n!*n) + internal workers()
    public static List<List<Integer>> permute(int[] n) {
        List<List<Integer>> res = new ArrayList<>();
        solvePerm(n, 0, res, new ArrayList<>());
        return res;
    }

    private static void solvePermUnique(int[]a, int i, List<List<Integer>> s1, List<Integer> s2) {
        if (i == a.length) {
            s1.add(new ArrayList<>(s2));
            return;
        }
        Set<Integer> appeared = new HashSet<>();
        for (int pick = i; pick < a.length; pick++) {
            if (appeared.add(a[pick])) {
                swap(a, pick, i);
                s2.add(a[i]);
                solvePermUnique(a, i + 1, s1, s2);
                s2.remove(s2.size() - 1);
                swap(a, pick, i);
            }
        }
    }
    //LC#47: https://leetcode.com/problems/permutations-ii/
    //time: O(n!*n)
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        solvePermUnique(nums, 0, res, new ArrayList<>());
        return res;
    }

    //LC#90: https://leetcode.com/problems/subsets-ii/
    private static void solveSubWithDup(int[] a, int i, List<Integer> l1, List<List<Integer>> l2, Set<List<Integer>> ss) {
        if (i == a.length) {
            if (ss.add(l1)) {
                l2.add(new ArrayList<>(l1));
                return;
            }
            return;
        }
        solveSubWithDup(a, i + 1, l1, l2, ss);
        l1.add(a[i]);
        solveSubWithDup(a, i + 1, l1, l2, ss);
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        Set<List<Integer>> ss = new HashSet<>();
        solveSubWithDup(nums, 0, new ArrayList<>(), res, ss);
        return res;
    }
    //LC#17: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
    private static void solveLetterCombo(String d, List<String> l, int i, char[][]m, StringBuilder sb) {
        if (i == d.length()) {
            l.add(new String(sb));
            return;
        }
        int num = d.charAt(i) - '0';
        for(int idx = 0; idx < m[num].length; idx++) {
            sb.append(m[num][idx]);
            solveLetterCombo(d, l, i + 1, m, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char[][] map = new char[][] {{},{},{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},{'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}};
        solveLetterCombo(digits, res, 0, map, sb);
        return res;
    }
    //LC# 51: N-Queens https://leetcode.com/problems/n-queens/
    private static boolean isSafe(int r, int c, char[][]b) {
        int cr = r;
        int cc = c;
        //top-left
        while(cr >= 0 && cc >= 0) {
            if(b[cr--][cc--] == 'Q') {
                return false;
            }
        }
        cr = r;
        cc = c;
        //top-right
        while(cr >= 0 && cc < b.length) {
            if(b[cr--][cc++] == 'Q') {
                return false;
            }
        }
        cr = r;
        cc = c;
        //col check
        while(cr >= 0) {
            if(b[cr--][cc] == 'Q') {
                return false;
            }
        }
        return true;
    }
    private static void solveNQRec(int N, char[][] b, int startRow, List<List<String>> res) {
        if (startRow == b.length) {
            List<String> row = new ArrayList<>();
            for (char[] r : b) {
                row.add(String.valueOf(r));
            }
            res.add(row);
            return;
        }
        for (int col = 0; col < N; col ++) {
            if (isSafe(startRow, col, b)) {
                //make choice
                b[startRow][col]='Q';
                solveNQRec(N, b, startRow + 1, res);
                //unmake
                b[startRow][col]='.';
            }
        }
    }
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n < 4) {
            return res;
        }
        char[][]board = new char[n][n];
        for (char[] r : board) {
            Arrays.fill(r, '-');
        }
        solveNQRec(n, board, 0, res);
        return res;
    }

    //LC# 77: https://leetcode.com/problems/combinations/
    /*
    k=3,n=4
    [1,2,3,4]
    op:[[1,2,3],[1,2,4],[2,3,4]]
    nCk=f(n-1,k-1) + f(n-1,k)
    we can basically start out with writing the code to enumerate all combinations of 1..n i.e. subsets problems
    Then we could before adding the subset to the global bag check on the size of the subset ==k, if it is add subproblem solution to the bag, else continue
    But, how do optimize this?
    If there is an internal node that when received a slate can determine whether theres any point in continuing?
     consider backtracking
      */
    private void solveCombine(int n, int k, List<List<Integer>> res, List<Integer> l, int i) {
        if (l.size() == k) {
            res.add(new ArrayList<>(l));
            return;
        }
        //this would mean the partial solution follows a single path of exclusions, and is empty
        if(l.size() == i) {
            return;
        }
        for (int j = i; j < n; j++) {
            l.add(j);
            solveCombine(n, k, res, l, j + 1);
            l.remove(l.size() - 1);
        }
    }

    //using the recurrence relation of nck = c(n-1,k-1) + c(n-1, k)
    private void solveCombine2(int n, int k, List<List<Integer>> res, List<Integer> l, int i) {
        if (l.size() == k) {
            res.add(new ArrayList<>(l));
            return;
        }
        //this would mean the partial solution follows a single path of exclusions, and is empty
        if(i == n + 1) {
            return;
        }
        //exclude
        solveCombine2(n, k, res, l, i + 1);
        l.add(i);
        solveCombine2(n, k, res, l, i + 1);
        l.remove(l.size() - 1);
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        solveCombine(n, k, res, new ArrayList<>(), 0);
        return res;
    }

    //LC#22
    //Reverse linkedlist recursively, refer: https://www.youtube.com/watch?v=KYH83T4q6Vs
    public static ListNode reverseListRec(ListNode l) {
        ListNode cc = l;
        return solveListRec(l, cc);
    }
    private static ListNode solveListRec(ListNode head, ListNode temp) {
        if (temp == null || temp.next == null){
            head = temp;
            return head;
        }
        head = solveListRec(head, temp.next);
        ListNode q = temp.next;
        q.next = temp;
        temp.next = null;
        return head;
    }

    private static void solveCombinationSum2(int[]a, List<Integer> ll, List<List<Integer>> r, int k, int idx) {
        //this has an overhead of calculating sum in O(size)
        int sum = ll.stream().mapToInt(Integer::intValue).sum();
        if (sum == k) {
            r.add(new ArrayList<>(ll));
            return;
        }
        else if (sum > k) {
            return;
        }

        if(idx == a.length) {
            r.add(new ArrayList<>(ll));
            return;
        }

        //exclude
        solveCombinationSum2(a,ll, r, k,idx + 1);
        //include
        if(!ll.contains(a[idx])) {
            ll.add(a[idx]);
            solveCombinationSum2(a, ll, r, k, idx + 1);
            ll.remove(ll.size() - 1);
        }
    }
    //LC#40: LC#40 https://leetcode.com/problems/combination-sum-ii/
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> resu = new ArrayList<>();
        List<Integer> r = new ArrayList<>();
        Arrays.sort(candidates);
        solveCombinationSum2(candidates, r, resu, target, 0);
        return resu;
    }
    private static void solveCombinationSum2SlateSum(int[]a, int k, int slateSum, int idx, List<Integer> ll, List<List<Integer>> r) {
        //backtracking case
        if (slateSum == k) {
            r.add(new ArrayList<>(ll));
            return;
        }
        else if (slateSum > k) {
            return;
        }
        //exclude
        solveCombinationSum2SlateSum(a, k, slateSum, idx + 1, ll, r);
        //include
        ll.add(a[idx]);
        solveCombinationSum2SlateSum(a, k, slateSum + a[idx], idx + 1, ll, r);
        ll.remove(ll.size() - 1);
    }
    //this makes use of a slateSum which is passed by the manager to its subordinates
    public List<List<Integer>> combinationSum2PassingSlateSum(int[] candidates, int target) {
        List<List<Integer>> resu = new ArrayList<>();
        List<Integer> r = new ArrayList<>();
        Arrays.sort(candidates);
        solveCombinationSum2SlateSum(candidates, target, 0, 0, r, resu);
        return resu;
    }
    //we can have the variant for the above problem to "count number of subsets that equal to k" or
    // is there a subset whose sum == k

    //LC#39 https://leetcode.com/problems/combination-sum/
    //time: O(n^target), space: O(target)
    private static void solveCombinationSum(int[]a, int k, List<Integer> ll, List<List<Integer>> res, int idx) {
        if(k == 0) {
            res.add(new ArrayList<>(ll));
            return;
        } else if (k < 0) {
            return;
        }
        if (idx == a.length) {
            return;
        }
        for(int i = idx; i < a.length; i++) {
            ll.add(a[i]);
            solveCombinationSum(a, k - a[i], ll, res, i);
            ll.remove(ll.size() - 1);
        }
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        solveCombinationSum(candidates, target, new ArrayList<>(), res, 0);
        return res;
    }
    //LC#22: Generate balanced parantheses
    //ignoring the pruning due ot backtracking we can say each node makes 2 choices and the height is 2n, so the time will O(2^2n), the leaf level
    //node is doing work proportional to the length of the slate.i.e. O(2n) hence total time complexity will be O(n*2^2n) = (n*4^n), i.e. this is the case when there is
    //no upper bound, remember if a node has b choices and ht of tree is h, then no.of leaf nodes at bottom will (b^h), and each node is making a copy of slate of len h
    //hence, total time will be h*b^h
    //but after pruning, the no.of leaf nodes will not b^h but the nth Catalan number. so time will be O(nth catalan number)
    //space: explicit: b^h i.e. 4^n entries in result, each entry is taking space n. implicit stack space: O(n)
    private static void solveBalParen(int leftRemain, int rightRemain, StringBuilder sb, List<String> l) {
        //backtracking
        if (leftRemain > rightRemain) {
            return;
        }
        //base
        if (leftRemain == 0 && rightRemain == 0) {
            l.add(new String(sb));
            return;
        }
        //recursive
        //add left paren
        if(leftRemain > 0) {
            sb.append("(");
            solveBalParen(leftRemain - 1, rightRemain, sb, l);
            sb.deleteCharAt(sb.length() - 1);
        }
        if(rightRemain > 0) {
            sb.append(")");
            solveBalParen(leftRemain, rightRemain - 1, sb, l);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    public List<String> balancedParen(int n) {
        List<String> res = new ArrayList<>();
        solveBalParen(n, n, new StringBuilder(), res);
        return res;
    }
    //TODO: Palindromic decomposition of a string
    //LC#96: https://leetcode.com/problems/unique-binary-search-trees/
    //time: O(n*n!)
    //refer: https://www.youtube.com/watch?v=kT_VabdscHk&t=1s
    public static long numTrees(int n) {
        long cnt = 0;
        if (n == 0) {
            return 1;
        }
        for(int leftCount = 0; leftCount < n; leftCount++) {
            int rightCount = n - 1 - leftCount;
            cnt+=numTrees(leftCount)*numTrees(rightCount);
        }
        return cnt;
    }
    //solution using dp gives O(n^2) and o(n) space,
    public static int numTreesDP(int n) {
        int []dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i<= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
    // TODO: LC#95 https://leetcode.com/problems/unique-binary-search-trees-ii/
    //TODO: Sudoku solver
    //Nqueens to get a O(n!) solution
    private static void solveNQ2(int n, int row, char[][] b, boolean[] cols, boolean[] ld, boolean[] rd, List<List<String>> res) {
        if (row == n) {
            List<String> r = new ArrayList<>();
            for (char[]rr : b) {
                r.add(new String(rr));
            }
            res.add(r);
            return;
        }
        for(int col = 0; col < n; col++) {
            int dia = row - col + n - 1;
            int antiDia = row + col;
            if (cols[col] || ld[dia] || rd[antiDia]) {
                continue;
            } else {
                b[row][col] = 'Q';
                cols[col] = true;
                ld[dia] = true;
                rd[antiDia] = true;
                solveNQ2(n, row + 1, b, cols, ld, rd, res);
                b[row][col] = '-';
                cols[col] = false;
                ld[dia] = false;
                rd[antiDia] = false;
            }
        }
    }
    public static String[][] NQueens2(int n) {
        char[][] board = new char[n][n];
        for(char[] row: board) {
            Arrays.fill(row, '-');
        }
        boolean[]cols = new boolean[n];
        boolean[]ld = new boolean[2*n - 1];
        boolean[]rd = new boolean[2*n - 1];
        List<List<String>> res = new ArrayList<>();
        solveNQ2(n, 0, board, cols, ld, rd, res);
        String[][] res2 = new String[res.size()][];
        int idx = 0;
        for (List<String> nl : res) {
            res2[idx++] = nl.toArray(new String[nl.size()]);
        }
        return res2;
    }
    private static int myAtoI(String s) {
        int c = 0;
        for (int i = 0; i < s.length(); i++) {
            c = c * 10 + (s.charAt(i) - '0');
        }
        return c;
    }
    private static void solveGenAllExprsn(char[] s, int idx, long currSum, long valAfterRightMostAdditionOperator, long target, StringBuilder sb, List<String> res) {
        if (idx == s.length) {
            if (currSum == target) {
                res.add(new String(sb));
                return;
            }
        }
        long currNum = 0;
        for (int i = idx; i < s.length; i++) {
            //String subStr = s.substring(idx, i + 1);
            currNum = currNum * 10 + (s[i] - '0');
            int sbLen = sb.length();
            //if idx == 0 no need to append any operator just recurse to next location
            if (idx == 0) {
                solveGenAllExprsn(s, i + 1, currNum, currNum, target, sb.append(currNum), res);
                sb.setLength(sbLen);
            }
            //update currSum = currSum + currNum
            solveGenAllExprsn(s, i + 1, currSum + currNum, currNum, target, sb.append("+").append(currNum), res);
            sb.setLength(sbLen);
            //for multiplication case, first just append the *
            //to get the value say for ex. our expression looks like after appending * and subStr = 1+3*4, and currently currSum = 4 for 1 + 3
            //now, we perform currSum = currSum - valAfterRightMostAdditionOperator + (valAfterRightMostAdditionOperator * number appended after *)
            //so in our case currSum = 4 - 3 + (3 * 4) = 1 + 12 = 13
            solveGenAllExprsn(s,
                    i + 1,
                    currSum - valAfterRightMostAdditionOperator + (currNum * valAfterRightMostAdditionOperator),
                    valAfterRightMostAdditionOperator * currNum,
                    target,
                    sb.append("*").append(currNum),
                    res);
            sb.setLength(sbLen);
        }
    }
    //lc#282: https://leetcode.com/problems/expression-add-operators/
    public static String[] generate_all_expressions(String s, long target) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        long currSum = 0;
        solveGenAllExprsn(s.toCharArray(), idx, currSum, 0, target, sb, res);
        return res.toArray(new String[0]);
    }

    public static boolean isValidSudoku3Arrays(char[][] board) {
        int N = 9;
        int[][] rows = new int[N][N];
        int[][] cols = new int[N][N];
        int[][] boxes = new int[N][N];
        for (int row = 0; row < 9; row ++) {
            for (int col = 0; col < 9; col++) {
                //check for empty cells
                if (board[row][col] == '.') {
                    continue;
                }
                int elem = board[row][col] - '1';
                //check row
                if (rows[row][elem] == 1) {
                    return false;
                }
                rows[row][elem] = 1;
                //check col
                if (cols[col][elem] == 1) {
                    return false;
                }
                cols[col][elem] = 1;
                //check a box
                int idx = (row / 3) * 3 + col / 3;
                if (boxes[idx][elem] == 1) {
                    return false;
                }
                boxes[idx][elem] = 1;
            }
        }
        return true;
    }
    //isValid sudoku using HashSet solution
    private static boolean canPlaceNum(char[][]b, int i, int j, char val) {
        //check row
        for (int c = 0; c < 9; c++) {
            if (b[i][c] == val) {
                return false;
            }
        }
        //check for col
        for (int ro = 0; ro < 9; ro++) {
            if (b[ro][j] == val) {
                return false;
            }
        }
        //check for boxes
        int nr = i - i % 3;
        int nc = j - j % 3;
        for (int ro = 0; ro < nr + 3; ro++) {
            for (int co = 0; co < nc + 3; co++) {
                if (b[ro][co] == val) {
                    return false;
                }
            }
        }
        return true;
    }
    //O(N^2)
    public static boolean isValidSudoku2(char[][] b) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (b[row][col] != '.') {
                    if (!canPlaceNum(b, row, col, b[row][col])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean sudokuSolvable(char[][]b) {
        for (int i = 0; i < 9; i ++) {
            for (int j =0; j < 9; j ++) {
                if (b[i][j] == '.') {
                    for(char s = '1'; s <= '9'; s++) {
                        if(canPlaceNum(b, i, j, s)) {
                            b[i][j] = s;
                            if(sudokuSolvable(b)) {
                                return true;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    public static void solveSudoku(char[][] b) {
        sudokuSolvable(b);
    }
}
