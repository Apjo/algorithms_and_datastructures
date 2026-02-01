package dsa.problemsolving.java.problems.matrixchainmultiplication;

/**
 * Some problems that can be asked under this pattern:
 * Print MCM
 * Evaluate expression true/boolean parenthesized
 * min/max value of an expression
 * palindrome partitioning
 * scramble string
 * Egg dropping problem
 * !!!!!! STEPS !!!!!!!:
 * 1. Find correct locations/starting values for leftIdx, and rightIdx
 * 2. Find base conditions for leftIdx, and rightIdx
 * 3. Find a correct scheme(s) for the k loop
 * 4. Calculate ans from temp
 * Typical format(not official) used
 * int solve(int []A, int leftIdx, int rightIdx) {
 *     if (leftIdx > rightIdx) { return 0;}
 *     //NOTE: k could increment per problem requirement, this sample ex. assumes incrementing k by 1
 *     for (int k = i; k < j; k++) {
 *         //calculate temporary answer. The +/- aor any operation will change per problem requirement
 *         temp_ans = solve(A, i, k) + solve(A, k+1, j);
 *         final_ans = your_function(temp_ans); //this function could change per problem requirement
 *     }
 *     return final_ans;
 * }
 */
public class MatrixChainMultiplication {
    static int[][]dp = new int[1001][1001];
    public static boolean areStringsScrambled(String A, String B) {
        if (A.length() != B.length()) {return false; }
        if (A.isEmpty() && B.isEmpty()) { return true; }
        if (A.equals(B)) { return true; }
        if (A.length() <= 1) { return false; }
        int L = A.length();
        boolean result = false;
        //We consider 2 strings are scrambled if a). they are both swapped at an index i or b). not swapped
        for (int i = 1; i <= L - 1; i++) {
            //we compare substrings( from strings A=great, and B=eatgr that are swapped at index=2) as A1=gr with B2=gr, and A2=eat with B1=eat,
            // where each part could be of different lengths
            if (areStringsScrambled(A.substring(0, i), B.substring(L - i, i)) &&
                    areStringsScrambled(A.substring(i, L - i), B.substring(0, L - i))) {
                result = true;
                break;
            }
            //Next, now we compare strings(A=great, and B=grate), note that one string has its substring scrambled.
            // But, none of the strings are swapped.
            // We compare as A1=gr with B1=gr, and A2=eat with B2=ate,
            if (areStringsScrambled(A.substring(0, i), B.substring(0, i)) &&
                    areStringsScrambled(A.substring(i, L - i), B.substring(i, L - i))) {
                result = true;
                break;
            }
        }
        return result;
    }

    //this is a recursive implementation.
    // TODO: The memoized version requires building a Map<String, Integer> where the string
    //i.e. the key will be made up like "i_j_isTrue" and value will be the final answer calculated. This implementatio is
    //left as a later
    public static int waysEvaluateExpressionBooleanParenthesis(String s, int i, int j, boolean isTrue) {
        if (i > j) { return 0; }
        if (i == j) {
            //if we have a single character in the input string, we determine whether the temporary result is ;T', if it is,
            // we check whether the character at that location is == 'T'. Similarly, for the 'F' case
            if(isTrue) {
                return s.charAt(i) == 'T' ? 1: 0;
            } else {
                return s.charAt(i) == 'F' ? 1 : 0;
            }
        }
        int ans = 0;
        //to determine the loop, k will always jump 2 places, since k will always be pointing at the operator
        for (int k = i + 1; k <= j - 1; k+=2) {
            //when determining to call the recursive functions, we note that, we can get a answer as T for an expression
            // in cases when the LHS evaluates to a T and when the RHS evaluates to a T OR
            //LHS evaluates to a F and when the RHS evaluates to a F. i.e (T || F) OR (F || T). So, each recursive call will make a decision
            // whether it got a F or a T
            //This step just calculate the temporary answer of each subproblem
            int leftTrue = waysEvaluateExpressionBooleanParenthesis(s, i, k - 1, true);
            int leftFalse = waysEvaluateExpressionBooleanParenthesis(s, i, k - 1, false);
            int rightTrue = waysEvaluateExpressionBooleanParenthesis(s,k + 1, j, true);
            int rightFalse = waysEvaluateExpressionBooleanParenthesis(s,k + 1, j, false);
            //Now, to evaluate the 4th step as per the format of MCM i.e. what would be the cost like after applying the
            //one of either operators i.e. |, ^, or & to the expressions from LHS and RHS so that we can get a T as the final answer
            if (s.charAt(k) == '&') {
                //determine ways to get a T from a AND. T&T = T
                if (isTrue) {
                    ans += leftTrue * rightTrue;
                } else {
                    //determine ways to get a F from AND. F&T OR T&F OR F&F
                    ans += leftFalse * rightTrue + leftTrue * rightFalse + leftFalse * rightFalse;
                }
            }

            else if (s.charAt(k) == '|') {
                //determine ways to get a T from a OR. F|T = T OR T|F = T OR T|T = T
                if (isTrue) {
                    ans += leftTrue * rightTrue + leftFalse * rightTrue + leftTrue * rightFalse;
                } else {
                    //determine ways to get a F from a OR. F|F = F
                    ans += leftFalse * rightFalse;
                }
            }

            else if (s.charAt(k) == '^') {
                //determine ways to get a T from a XOR. F^T = T and T^F = T
                if (isTrue) {
                    ans += leftTrue * rightFalse + leftFalse * rightTrue;
                } else {
                    //determine ways to get a F from a XOR. F ^ F = F OR T^T = F
                    ans += leftTrue * rightTrue + leftFalse * rightFalse;
                }
            }
        }
        return ans;
    }

    private static boolean isPalindrome(String s, int i, int j) {
        if (i == j) { return true; }
        if (i > j) { return false; }
        while(i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static int palindromicPartitioningRec(String s, int left, int right) {
        //here left and right will be set at 0 and s.length() - 1 respectively.
        if (left >= right) {
            return 0;
        }
        if (isPalindrome(s, left, right)) {
            return 0;
        }
        int mn = Integer.MAX_VALUE;
        //even in this case, we need to determine the range of k.
        // 1. if we set k = i, and k = j - 1, that means one partition will go from i to k, and k + 1 to j the second one
        // 2. if we set k = i + 1, and k = j, that means one partition will go from i to k - 1, and k to j the second one
        // we will use 1
        for (int k = left; k <= right - 1; k++) {
            int tempA = palindromicPartitioningRec(s, left, k) + palindromicPartitioningRec(s, k + 1, right) + 1;
            if (tempA < mn) {
                mn = tempA;
            }
        }
        return mn;
    }
    //O(N^3)
    public static int palindromicPartitioningMemoized(String s, int left, int right) {
        //here left and right will be set at 0 and s.length() - 1 respectively.
        if (left >= right) {
            return 0;
        }

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }

        if (isPalindrome(s, left, right)) {
            return 0;
        }

        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        int mn = Integer.MAX_VALUE;
        //even in this case, we need to determine the range of k.
        // 1. if we set k = i, and k = j - 1, that means one partition will go from i to k, and k + 1 to j the second one
        // 2. if we set k = i + 1, and k = j, that means one partition will go from i to k - 1, and k to j the second one
        // we will use 1
        for (int k = left; k <= right - 1; k++) {
            int tempA = palindromicPartitioningRec(s, left, k) + palindromicPartitioningRec(s, k + 1, right) + 1;
            if (tempA < mn) {
                mn = tempA;
            }
        }
        return dp[left][right] = mn;
    }

    //O(N^2)
    public static int palindromicPartitioningMemoizedOptimized(String s, int left, int right) {
        //here left and right will be set at 0 and s.length() - 1 respectively.
        if (left >= right) {
            return 0;
        }

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }

        if (isPalindrome(s, left, right)) {
            return 0;
        }

        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        int mn = Integer.MAX_VALUE;
        //In this case, we check whether any left/right subtree of the recursion tree is already solved,
        // if it is already present/solved, we use the value, else compute it
        for (int k = left; k <= right - 1; k++) {
            int i ,j;
            if (dp[left][k] == -1) {
                dp[left][k] = palindromicPartitioningRec(s, left, k);
            }
            i = dp[left][k];
            if (dp[k + 1][right] == -1) {
                dp[k + 1][right] = palindromicPartitioningRec(s, k, right);
            }
            j = dp[k + 1][right];
            int tempA = i + j + 1;
            if (tempA < mn) {
                mn = tempA;
            }
        }
        return dp[left][right] = mn;
    }

    public static int solveMCM(int[]A, int leftIdx, int rightIdx) {
        int mn = Integer.MAX_VALUE;
        if (leftIdx >= rightIdx) { return 0; }
        //you can have k going from i to j - 1 OR i + 1 to j
        //if k is going from i to j - 1, we break our problem into 2 sub-problems such as problemA(i, k) and problemB(k+1, j)
        //OR, we can have if k is going from i + 1 to j, problemA(i, k - 1) and problemB(k, j)
        for (int k = leftIdx; k <= rightIdx - 1; k++) {
            //the last part represents the cost of multiplication. i.e Row1 * Col1 * Col2 (used when you multiply 2 matrices)
            //Also, at an index i in array A, to get to know the dimensions of a matrix, we have A1 = A[0] * A[1], A2=A[1]*A[2] and so on
            // giving us: Ai = A[i - 1] * A[i]
            int tempA = solveMCM(A, leftIdx, k) + solveMCM(A, k + 1, rightIdx) + (A[leftIdx - 1] * A[k] * A[rightIdx]);
            if (tempA < mn) {
                mn = tempA;
            }
        }
        return mn;
    }

    public static int solveMCMMemoization(int[]A, int leftIdx, int rightIdx) {

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }

        int mn = Integer.MAX_VALUE;
        if (leftIdx >= rightIdx) { return 0; }
        if (dp[leftIdx][rightIdx] != -1) {return dp[leftIdx][rightIdx];}
        //you can have k going from i to j - 1 OR i + 1 to j
        //if k is going from i to j - 1, we break our problem into 2 sub-problems such as problemA(i, k) and problemB(k+1, j)
        //OR, we can have if k is going from i + 1 to j, problemA(i, k - 1) and problemB(k, j)
        for (int k = leftIdx; k <= rightIdx - 1; k++) {
            //the last part represents the cost of multiplication. i.e Row1 * Col1 * Col2 (used when you multiply 2 matrices)
            //Also, at an index i in array A, to get to know the dimensions of a matrix, we have A1 = A[0] * A[1], A2=A[1]*A[2] and so on
            // giving us: Ai = A[i - 1] * A[i]
            int tempA = solveMCM(A, leftIdx, k) + solveMCM(A, k + 1, rightIdx) + (A[leftIdx - 1] * A[k] * A[rightIdx]);
            if (tempA < mn) {
                mn = tempA;
            }
        }
        return dp[leftIdx][rightIdx] = mn;
    }


}
