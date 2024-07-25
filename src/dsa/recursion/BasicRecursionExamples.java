package dsa.recursion;

import java.math.*;

//The following problems are from codingbat, and pass their test cases, hence you will not see our main() function here.
//link: https://codingbat.com/java/Recursion-1 and https://codingbat.com/java/Recursion-2 & very simple beginner recursion examples

public class BasicRecursionExamples {
    //time: O(2^n)
    static int countSubsetsDivideAndConquer(int n) {
        if (n == 0) {
            return 1;
        }
        return countSubsetsDivideAndConquer(n -1) + countSubsetsDivideAndConquer(n- 1);
    }
    //time: O(n) using Decrease and conquer
    static int countSubsets1(int n) {
        if(n == 1) {
            return 1;
        }
        return 2*countSubsets1(n - 1);
    }
    public static void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        rev(s, 0, s.length - 1);
    }

    private static void rev(char[] a, int lo, int hi) {
        if (lo > hi) {
            return;
        }
        char t = a[lo];
        a[lo] = a[hi];
        a[hi] = t;
        rev(a, lo + 1, hi - 1);
    }

    int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    int lcm(int a, int b) {
        if (b == 0) {
            return a;
        }
        return ((a * b) / gcd(a, b));
    }

    // Calculate x^y. Time: O(x)
    int powIter(int x, int y) {
        int res = 1;
        for (int i = 1; i < y; i++) {
            res *= x;
        }
        return res;
    }
    // Calculate x^y O(2^n)
    int pow1(int x, int n) {
        if (n == 0) {
            return 1;
        }
        return x * pow1(x, n - 1);
    }

    // Calculate x^y. O(log n)
    int pow2(int x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n % 2 == 0) {
            int result = pow2(x, n / 2);
            return result * result;
        }
        return x * pow2(x, n - 1);
    }

    //O(2^n) time
    public int fibonacci(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // O(N) time
    int fib2(int n) {
        if (n < 2) {
            return 1;
        }
        int f1 = 0;
        int f2 = 1;
        for (int i = 2; i <= n; i++) {
            int s = f1 + f2;
            f1 = f2;
            f2 = s;
        }
        return f2;
    }

    // Same as above to explain using additive sequence, but using recursion.
    // Still gives us O(n)
    int fib22(int n) {
        return fibbAddSeq(n, 0, 1);
    }

    int fibbAddSeq(int n, int f1, int f2) {
        if (n == 0) {
            return f1;
        }
        return fibbAddSeq(n - 1, f2, f1 + f2);
    }

    // O(N) time and space
    int fib3(int N) {
        if (N < 2) {
            return N;
        }
        int[] dp = new int[N + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[N];
    }

    // Calculate exponential modulo, x^n % m
    int myMod(int x, int n, int M) {
        if (n == 0) {
            return 1;
        }
        if (n % 2 == 0) {
            int result = myMod(x, n / 2, M);
            return ((result * result) % M);
        }
        return ((x % M) * myMod(x, n - 1, M) % M);
    }

    public int factorial(int n) {
        if (n == 1 || n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    /*
     * We have a number of bunnies and each bunny has two big floppy ears. We want
     * to compute the total number of ears across all the bunnies recursively
     */
    public int bunnyEars(int bunnies) {
        if (bunnies == 0) {
            return 0;
        } else {
            return 2 + bunnyEars(bunnies - 1);
        }
    }

    /**
     * We have bunnies standing in a line, numbered 1, 2, ... The odd bunnies (1, 3,
     * ..) have the normal 2 ears. The even bunnies (2, 4, ..) we'll say have 3
     * ears, because they each have a raised foot. Recursively return the number of
     * "ears" in the bunny line 1, 2, ... n
     */
    public int bunnyEars2(int bunnies) {
        if (bunnies == 0) {
            return 0;
        }
        if (bunnies % 2 != 0) {
            // odd bunnies -> even ears
            return 2 + bunnyEars2(bunnies - 1);
        } else {
            // even bunnies -> odd ears
            return 3 + bunnyEars2(bunnies - 1);
        }
    }

    /**
     * We have triangle made of blocks. The topmost row has 1 block, the next row
     * down has 2 blocks, the next row has 3 blocks, and so on. Compute recursively
     * (no loops or multiplication) the total number of blocks in such a triangle
     * with the given number of rows.
     */
    public int triangle(int rows) {
        if (rows == 0)
            return 0;
        else {
            return rows + triangle(rows - 1);
        }

    }

    /**
     * Given a non-negative int n, return the sum of its digits recursively (no
     * loops). Note that mod (%) by 10 yields the rightmost digit (126 % 10 is 6),
     * while divide (/) by 10 removes the rightmost digit (126 / 10 is 12)
     */
    public int addDigits(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        int sum = 0;
        int digit = n % 10;
        sum += digit;
        return sum + addDigits(n / 10);
    }

    /**
     * Given a non-negative int n, return the count of the occurrences of 7 as a
     * digit, so for example 717 yields 2. (no loops). Note that mod (%) by 10
     * yields the rightmost digit (126 % 10 is 6), while divide (/) by 10 removes
     * the rightmost digit (126 / 10 is 12). count7(717) → 2 count7(7) → 1
     * count7(123) → 0
     * 
     * @param n
     * @return count of 7's
     */
    public int count7(int n) {
        int ctr = 0;
        if (n == 0) {
            return 0;
        }
        int d = n % 10;
        if (d == 7) {
            ctr++;
        }
        return ctr + count7(n / 10);
    }

    /**
     * Given a non-negative int n, compute recursively (no loops) the count of the
     * occurrences of 8 as a digit, except that an 8 with another 8 immediately to
     * its left counts double, so 8818 yields 4. Note that mod (%) by 10 yields the
     * rightmost digit (126 % 10 is 6), while divide (/) by 10 removes the rightmost
     * digit (126 / 10 is 12)
     * 
     * @param n
     * @return count of 8's
     */
    public int count8(int n) {
        int c = 0;
        if (n == 0) {
            return 0;
        } else {
            if (n % 10 == 8 && (n / 10) % 10 == 8) {
                c += 2;
            } else if (n % 10 == 8 && (n / 10) % 10 != 8) {
                c += 1;
            }
        }
        return c + count8(n / 10);
    }

    /**
     * Given base and n that are both 1 or more, compute recursively (no loops) the
     * value of base to the n power, so powerN(3, 2) is 9 (3 squared). powerN(3, 1)
     * → 3 powerN(3, 2) → 9 powerN(3, 3) → 27
     */
    public int powerN(int base, int n) {
        if (n <= 0) {
            return 1;
        }
        int half = powerN(base, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return base * half * half;
        }
    }

    /**
     * 
     * Given a string, compute recursively (no loops) the number of lowercase 'x'
     * chars in the string.
     * 
     * 
     * countX("xxhixx") → 4 countX("xhixhix") → 3 countX("hi") → 0
     * 
     * @param str
     * @return
     */
    public int countX(String str) {
        if (str == "" || str.length() == 0) {
            return 0;
        } else {
            return (str.charAt(0) == 'x' ? 1 : 0) + countX(str.substring(1));
        }
    }

    /**
     * Given a string, compute recursively (no loops) the number of times lowercase
     * "hi" appears in the string.
     * 
     * 
     * countHi("xxhixx") → 1 countHi("xhixhix") → 2 countHi("hi") → 1
     * 
     * @param str
     * @return count of "hi" (in lowercase) in str
     */
    public int countHi(String str) {
        if (str == null || str.length() <= 1) {
            return 0;
        }
        int c = 0;
        if (str.substring(0, 2).equals("hi")) {
            c = 1;
        }
        return c + countHi(str.substring(1));
    }

    /**
     * Given a string, compute recursively (no loops) a new string where all the
     * lowercase 'x' chars have been changed to 'y' chars.
     * 
     * 
     * changeXY("codex") → "codey" changeXY("xxhixx") → "yyhiyy" changeXY("xhixhix")
     * → "yhiyhiy"
     * 
     * @param str
     * @return
     */
    public String changeXY(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        if (str.equals("x")) {
            return "y";
        }

        if (str.contains("x")) {
            int indx = str.indexOf("x");
            return str.substring(0, indx) + 'y' + changeXY(str.substring(indx + 1));
        }
        return str;
    }

    /**
     * Given a string, compute recursively (no loops) a new string where all
     * appearances of "pi" have been replaced by "3.14".
     * 
     * 
     * changePi("xpix") → "x3.14x" changePi("pipi") → "3.143.14" changePi("pip") →
     * "3.14p"
     * 
     * @param str
     * @return
     */
    public String changePi(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }

        if (str.contains("pi")) {
            int indx = str.indexOf("pi");
            return str.substring(0, indx) + "3.14" + changePi(str.substring(indx + 2));
        }
        return str;
    }

    /**
     * 
     * Given a string, compute recursively a new string where all the 'x' chars have
     * been removed.
     * 
     * 
     * noX("xaxb") → "ab" noX("abc") → "abc" noX("xx") → ""
     * 
     * @param str
     * @return
     */
    public String noX(String str) {
        if (str.length() == 0 || str == null || str == "x") {
            return "";
        }
        if (str.contains("x")) {
            int indx = str.indexOf('x');
            return str.substring(0, indx) + noX(str.substring(indx + 1));
        }
        return str;
    }

    /**
     * 
     * Given an array of ints, compute recursively if the array contains a 6. We'll
     * use the convention of considering only the part of the array that begins at
     * the given index. In this way, a recursive call can pass index+1 to move down
     * the array. The initial call will pass in index as 0.
     * 
     * 
     * array6([1, 6, 4], 0) → true array6([1, 4], 0) → false array6([6], 0) → true
     * 
     * @param nums
     * @param index
     * @return
     */
    public boolean array6(int[] nums, int index) {
        if (nums == null || nums.length == 0 || index < 0 || index > nums.length) {
            return false;
        }

        if (index < nums.length) {
            return nums[index] == 6 ? true : array6(nums, index + 1);
        }

        return false;
    }

    /**
     * 
     * Given an array of ints, compute recursively the number of times that the
     * value 11 appears in the array. We'll use the convention of considering only
     * the part of the array that begins at the given index. In this way, a
     * recursive call can pass index+1 to move down the array. The initial call will
     * pass in index as 0.
     * 
     * 
     * array11([1, 2, 11], 0) → 1 array11([11, 11], 0) → 2 array11([1, 2, 3, 4], 0)
     * → 0
     * 
     * @param nums
     * @param index
     * @return
     */
    public int array11(int[] nums, int index) {
        if (nums == null || nums.length == 0 || index < 0 || index > nums.length) {
            return 0;
        }

        if (index < nums.length) {
            return (nums[index] == 11 ? 1 : 0) + array11(nums, index + 1);
        }
        return 0;
    }

    /**
     * Given an array of ints, compute recursively if the array contains somewhere a
     * value followed in the array by that value times 10. We'll use the convention
     * of considering only the part of the array that begins at the given index. In
     * this way, a recursive call can pass index+1 to move down the array. The
     * initial call will pass in index as 0.
     * 
     * 
     * array220([1, 2, 20], 0) → true array220([3, 30], 0) → true array220([3], 0) →
     * false
     * 
     * @param nums
     * @param index
     * @return
     */
    public boolean array220(int[] nums, int index) {
        if (nums == null || nums.length == 0 || nums.length == 1 | index < 0 || index > nums.length) {
            return false;
        }
        if (index >= nums.length - 1) {
            return false;
        }
        if (index < nums.length) {
            return nums[index] * 10 == nums[index + 1] ? true : array220(nums, index + 1);
        }
        return false;
    }

    /**
     * 
     * Given a string, compute recursively a new string where all the adjacent chars
     * are now separated by a "*".
     * 
     * 
     * allStar("hello") → "h*e*l*l*o" allStar("abc") → "a*b*c" allStar("ab") → "a*b"
     * 
     * @param str
     * @return
     */
    public String allStar(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        if (str.length() == 1) {
            return str;
        } else {
            return str.substring(0, 1) + '*' + allStar(str.substring(1));
        }
    }

    /**
     * 
     * Given a string, compute recursively a new string where identical chars that
     * are adjacent in the original string are separated from each other by a "*".
     * 
     * 
     * pairStar("hello") → "hel*lo" pairStar("xxyy") → "x*xy*y" pairStar("aaaa") →
     * "a*a*a*a"
     * 
     * @param str
     * @return
     */
    public String pairStar(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        if (str.length() == 1) {
            return str;
        } else {
            return ((str.charAt(0) == str.charAt(1)) ? str.substring(0, 1) + '*' + pairStar(str.substring(1))
                    : str.charAt(0) + pairStar(str.substring(1)));
        }
    }

    /**
     * Given a string, compute recursively a new string where all the lowercase 'x'
     * have been moved to the end of the string
     * 
     * endX("xxre") → "rexx" endX("xxhixx") → "hixxxx" endX("xhixhix") → "hihixxx"
     * 
     * @param str
     * @return
     */
    public String endX(String str) {
        if (str == null || str.length() == 0) {
            return "";
        } else {
            if (str.charAt(0) == 'x') {
                return endX(str.substring(1)) + 'x';
            } else {
                return str.charAt(0) + endX(str.substring(1));
            }
        }
    }

    /**
     * We'll say that a "pair" in a string is two instances of a char separated by a
     * char. So "AxA" the A's make a pair. Pair's can overlap, so "AxAxA" contains 3
     * pairs -- 2 for A and 1 for x. Recursively compute the number of pairs in the
     * given string.
     * 
     * 
     * countPairs("axa") → 1 countPairs("axax") → 2 countPairs("axbx") → 1
     * 
     * @param str
     * @return
     */
    public int countPairs(String str) {
        if (str == null || str.length() == 0 || str.length() < 3) {
            return 0;
        }

        else {
            if (str.charAt(0) == str.charAt(2))
                return 1 + countPairs(str.substring(1));
            else
                return countPairs(str.substring(1));
        }
    }

    /**
     * 
     * Count recursively the total number of "abc" and "aba" substrings that appear
     * in the given string.
     * 
     * 
     * countAbc("abc") → 1 countAbc("abcxxabc") → 2 countAbc("abaxxaba") → 2
     * 
     * @param str
     * @return
     */
    public int countAbc(String str) {
        if (str == null || str.length() == 0 || str.length() < 3) {
            return 0;
        }

        String t = str.substring(0, 3);
        if (t.equals("aba"))
            return 1 + countAbc(str.substring(2));
        if (t.equals("abc"))
            return 1 + countAbc(str.substring(3));
        return countAbc(str.substring(1));
    }

    /**
     * 
     * Given a string, compute recursively (no loops) the number of "11" substrings
     * in the string. The "11" substrings should not overlap.
     * 
     * 
     * count11("11abc11") → 2 count11("abc11x11x11") → 3 count11("111") → 1
     * 
     * @param str
     * @return
     */
    public int count11(String str) {
        if (str == null || str.length() == 0 || str.length() < 2) {
            return 0;
        }
        if (!str.contains("11")) {
            return 0;
        }
        if (str.substring(0, 2).contains("11"))
            return 1 + count11(str.substring(2));
        return count11(str.substring(1));
    }

    /**
     * 
     * Given a string, return recursively a "cleaned" string where adjacent chars
     * that are the same have been reduced to a single char. So "yyzzza" yields
     * "yza".
     * 
     * 
     * stringClean("yyzzza") → "yza" stringClean("abbbcdd") → "abcd"
     * stringClean("Hello") → "Helo"
     * 
     * @param str
     * @return
     */
    public String stringClean(String str) {
        if (str.length() == 0 || str == null) {
            return "";
        }

        if (str.length() < 2)
            return str;
        if (str.charAt(0) == str.charAt(1)) {
            return stringClean(str.substring(1));
        }

        return str.charAt(0) + stringClean(str.substring(1));
    }

    /**
     * Given a string, compute recursively the number of times lowercase "hi"
     * appears in the string, however do not count "hi" that have an 'x' immedately
     * before them.
     * 
     * 
     * countHi2("ahixhi") → 1 countHi2("ahibhi") → 2 countHi2("xhixhi") → 0
     * 
     * @param str
     * @return
     */
    public int countHi2(String str) {
        if (str == "" || str.length() == 0 || str.length() < 2) {
            return 0;
        }
        if (str.length() == 2) {
            return (str.equals("hi")) ? 1 : 0;
        }

        if (str.charAt(0) == 'x') {
            if (str.substring(1, 3).equals("hi"))
                return countHi2(str.substring(3));
            else
                return countHi2(str.substring(1));
        }
        if (str.substring(0, 2).equals("hi")) {
            return 1 + countHi2(str.substring(2));
        }
        if (str.substring(1, 3).equals("hi")) {
            return 1 + countHi2(str.substring(3));
        }

        return countHi2(str.substring(2));
    }

    /**
     * Given a string that contains a single pair of parenthesis, compute
     * recursively a new string made of only of the parenthesis and their contents,
     * so "xyz(abc)123" yields "(abc)".
     * 
     * 
     * parenBit("xyz(abc)123") → "(abc)" parenBit("x(hello)") → "(hello)"
     * parenBit("(xy)1") → "(xy)"
     * 
     * @param str
     * @return
     */
    // not really! but added jut for fun :shrug:
    public String parenBitFun(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }

        if (str.equals("()")) {
            return "()";
        }

        if (str.charAt(0) == '(') {
            int indx = str.indexOf(')');
            return str.substring(0, indx + 1);
        } else {
            int indx1 = str.indexOf('(');
            int indx2 = str.indexOf(')');
            return str.substring(indx1, indx2 + 1);
        }
    }

    public String parenBit(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }

        if (str.equals("()")) {
            return "()";
        }

        int len = str.length();

        if (str.charAt(0) != '(') {
            if (str.charAt(len - 1) != ')') {
                return parenBit(str.substring(1, len - 1));
            }
            return parenBit(str.substring(1));
        }
        if (str.charAt(len - 1) != ')') {
            return parenBit(str.substring(0, len - 1));
        }
        return str;
    }

    /**
     * 
     * Given a string, return true if it is a nesting of zero or more pairs of
     * parenthesis, like "(())" or "((()))". Suggestion: check the first and last
     * chars, and then recur on what's inside them.
     * 
     * 
     * nestParen("(())") → true nestParen("((()))") → true nestParen("(((x))") →
     * false
     * 
     * @param str
     * @return
     */
    public boolean nestParen(String str) {
        if (str.length() == 0)
            return true;
        if (str.charAt(0) == '(' && str.charAt(str.length() - 1) == ')')
            return nestParen(str.substring(1, str.length() - 1));
        return false;
    }

    /**
     * Given a string and a non-empty substring sub, compute recursively the number
     * of times that sub appears in the string, without the sub strings overlapping.
     * 
     * 
     * strCount("catcowcat", "cat") → 2 strCount("catcowcat", "cow") → 1
     * strCount("catcowcat", "dog") → 0
     * 
     * @param str
     * @param sub
     * @return
     */
    public int strCount(String str, String sub) {
        if (sub.length() > str.length())
            return 0;

        if (str.substring(0, sub.length()).equals(sub)) {
            return 1 + strCount(str.substring(sub.length()), sub);
        } else {
            return strCount(str.substring(1), sub);
        }
    }

    /**
     * 
     * Given a string and a non-empty substring sub, compute recursively if at least
     * n copies of sub appear in the string somewhere, possibly with overlapping. N
     * will be non-negative.
     * 
     * 
     * strCopies("catcowcat", "cat", 2) → true strCopies("catcowcat", "cow", 2) →
     * false strCopies("catcowcat", "cow", 1) → true
     * 
     * @param str
     * @param sub
     * @param n
     * @return
     */
    public boolean strCopies(String str, String sub, int n) {
        if (n == 0)
            return true;

        if (str.length() < sub.length())
            return false;

        if (str.substring(0, sub.length()).equals(sub)) {
            return strCopies(str.substring(1), sub, n - 1);
        }
        return strCopies(str.substring(1), sub, n);
    }

    /**
     * Given a string and a non-empty substring sub, compute recursively the largest
     * substring which starts and ends with sub and return its length.
     * 
     * 
     * strDist("catcowcat", "cat") → 9 strDist("catcowcat", "cow") → 3
     * strDist("cccatcowcatxx", "cat") → 9
     * 
     * @param str
     * @param sub
     * @return
     */
    public int strDist(String str, String sub) {
        if (str.length() < sub.length())
            return 0;

        if (str.substring(0, sub.length()).equals(sub)) {
            if (str.substring(str.length() - sub.length(), str.length()).equals(sub))
                return str.length();
            return strDist(str.substring(0, str.length() - 1), sub);
        }
        return strDist(str.substring(1), sub);

    }

    /**
     * 
     * Given an array of ints, is it possible to choose a group of some of the ints,
     * such that the group sums to the given target? This is a classic backtracking
     * recursion problem. Once you understand the recursive backtracking strategy in
     * this problem, you can use the same pattern for many problems to search a
     * space of choices. Rather than looking at the whole array, our convention is
     * to consider the part of the array starting at index start and continuing to
     * the end of the array. The caller can specify the whole array simply by
     * passing start as 0. No loops are needed -- the recursive calls progress down
     * the array.
     * 
     * 
     * groupSum(0, [2, 4, 8], 10) → true groupSum(0, [2, 4, 8], 14) → true
     * groupSum(0, [2, 4, 8], 9) → false
     * 
     * @param start
     * @param nums
     * @param target
     * @return
     */
    public boolean groupSum(int start, int[] nums, int target) {
        if (start >= nums.length)
            return target == 0;

        if (groupSum(start + 1, nums, target - nums[start]))
            return true;
        if (groupSum(start + 1, nums, target))
            return true;
        return false;
    }

    /**
     * Given an array of ints, is it possible to choose a group of some of the ints,
     * beginning at the start index, such that the group sums to the given target?
     * However, with the additional constraint that all 6's must be chosen. (No
     * loops needed.)
     * 
     * 
     * groupSum6(0, [5, 6, 2], 8) → true groupSum6(0, [5, 6, 2], 9) → false
     * groupSum6(0, [5, 6, 2], 7) → false
     * 
     * @param start
     * @param nums
     * @param target
     * @return
     */
    public boolean groupSum6(int start, int[] nums, int target) {
        if (start >= nums.length)
            return target == 0;
        if (nums[start] == 6)
            return groupSum6(start + 1, nums, target - 6);

        if (groupSum6(start + 1, nums, target - nums[start]))
            return true;

        if (groupSum6(start + 1, nums, target))
            return true;

        return false;
    }

    /**
     * 
     * Given an array of ints, is it possible to choose a group of some of the ints,
     * such that the group sums to the given target with this additional constraint:
     * If a value in the array is chosen to be in the group, the value immediately
     * following it in the array must not be chosen. (No loops needed.)
     * 
     * 
     * groupNoAdj(0, [2, 5, 10, 4], 12) → true groupNoAdj(0, [2, 5, 10, 4], 14) →
     * false groupNoAdj(0, [2, 5, 10, 4], 7) → false
     * 
     * @param start
     * @param nums
     * @param target
     * @return
     */
    public boolean groupNoAdj(int start, int[] nums, int target) {
        if (start >= nums.length)
            return target == 0;

        if (groupNoAdj(start + 2, nums, target - nums[start]))
            return true;

        if (groupNoAdj(start + 1, nums, target))
            return true;

        return false;
    }

    /**
     * Given an array of ints, is it possible to choose a group of some of the ints,
     * such that the group sums to the given target with these additional
     * constraints: all multiples of 5 in the array must be included in the group.
     * If the value immediately following a multiple of 5 is 1, it must not be
     * chosen. (No loops needed.)
     * 
     * 
     * groupSum5(0, [2, 5, 10, 4], 19) → true groupSum5(0, [2, 5, 10, 4], 17) → true
     * groupSum5(0, [2, 5, 10, 4], 12) → false
     * 
     * @param start
     * @param nums
     * @param target
     * @return
     */
    public boolean groupSum5(int start, int[] nums, int target) {
        if (start >= nums.length)
            return target == 0;

        if (nums[start] % 5 == 0) {
            if (start < nums.length - 1 && nums[start + 1] == 1)
                return groupSum5(start + 2, nums, target - nums[start]);

            return groupSum5(start + 1, nums, target - nums[start]);
        }

        if (groupSum5(start + 1, nums, target - nums[start]))
            return true;

        if (groupSum5(start + 1, nums, target))
            return true;

        return false;
    }

    /**
     * Given an array of ints, is it possible to choose a group of some of the ints,
     * such that the group sums to the given target, with this additional
     * constraint: if there are numbers in the array that are adjacent and the
     * identical value, they must either all be chosen, or none of them chosen. For
     * example, with the array {1, 2, 2, 2, 5, 2}, either all three 2's in the
     * middle must be chosen or not, all as a group. (one loop can be used to find
     * the extent of the identical values).
     * 
     * 
     * groupSumClump(0, [2, 4, 8], 10) → true groupSumClump(0, [1, 2, 4, 8, 1], 14)
     * → true groupSumClump(0, [2, 4, 4, 8], 14) → false
     * 
     * @param start
     * @param nums
     * @param target
     * @return
     */
    public boolean groupSumClump(int start, int[] nums, int target) {
        if (start >= nums.length) {
            return target == 0;
        }
        int ctr = 1;
        int dupSum = nums[start];
        for (int i = start + 1; i < nums.length; i++) {
            if (nums[i] == nums[start]) {
                dupSum += nums[i];
                ctr++;
            }
        }

        return groupSumClump(start + ctr, nums, target - dupSum) || groupSumClump(start + ctr, nums, target);
    }

    /**
     * Given an array of ints, is it possible to divide the ints into two groups, so
     * that the sums of the two groups are the same. Every int must be in one group
     * or the other. Write a recursive helper method that takes whatever arguments
     * you like, and make the initial call to your recursive helper from
     * splitArray(). (No loops needed.)
     * 
     * 
     * splitArray([2, 2]) → true splitArray([2, 3]) → false splitArray([5, 2, 3]) →
     * true
     * 
     * @param nums
     * @return
     */
    public boolean splitArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        return split(nums, 0, 0, 0);
    }

    public boolean split(int[] a, int start, int lsum, int rsum) {
        if (start >= a.length) {
            return lsum == rsum;
        }
        return split(a, start + 1, lsum + a[start], rsum) || split(a, start + 1, lsum, rsum + a[start]);
    }

    /**
     * Given an array of ints, is it possible to divide the ints into two groups, so
     * that the sum of one group is a multiple of 10, and the sum of the other group
     * is odd. Every int must be in one group or the other. Write a recursive helper
     * method that takes whatever arguments you like, and make the initial call to
     * your recursive helper from splitOdd10(). (No loops needed.)
     * 
     * 
     * splitOdd10([5, 5, 5]) → true splitOdd10([5, 5, 6]) → false splitOdd10([5, 5,
     * 6, 1]) → true
     */
    public boolean splitOdd10(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return false;
        }
        return splitOdd(nums, 0, 0, 0);
    }

    boolean splitOdd(int[] a, int st, int ls, int rs) {
        if (st >= a.length) {
            return (ls % 10 == 0 && rs % 2 != 0);
        }
        return splitOdd(a, st + 1, ls + a[st], rs) || splitOdd(a, st + 1, ls, rs + a[st]);
    }

    /**
     * Given an array of ints, is it possible to divide the ints into two groups, so
     * that the sum of the two groups is the same, with these constraints: all the
     * values that are multiple of 5 must be in one group, and all the values that
     * are a multiple of 3 (and not a multiple of 5) must be in the other. (No loops
     * needed.)
     * 
     * 
     * split53([1, 1]) → true split53([1, 1, 1]) → false split53([2, 4, 2]) → true
     */
    public boolean split53(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        return split53Helper(nums, 0, 0, 0);
    }

    boolean split53Helper(int[] a, int st, int ls, int rs) {
        if (st >= a.length) {
            return (ls == rs);
        }
        if (a[st] % 5 == 0) {
            return split53Helper(a, st + 1, ls + a[st], rs);
        }
        if (a[st] % 3 == 0) {
            return split53Helper(a, st + 1, ls, rs + a[st]);
        }

        return split53Helper(a, st + 1, ls + a[st], rs) || split53Helper(a, st + 1, ls, rs + a[st]);
    }

    public static BigInteger karatsuba(BigInteger num1, BigInteger num2) {
        // convert both numbers to String
        String x = num1.toString();
        String y = num2.toString();
        // Pad leading zeros to make x and y of the same length [O(n)]
        if (x.length() > y.length()) {
            y = padZeros(y, x.length() - y.length(), true);
        } else if (x.length() < y.length()) {
            x = padZeros(x, y.length() - x.length(), true);
        }
        int n = x.length();
        // Base case
        if (n <= 10) {
            return num1.multiply(num2);
        }
        // Recursive case
        // [Divide] [O(n)]
        String as = x.substring(0, n / 2);
        String bs = x.substring(n / 2);
        String cs = y.substring(0, n / 2);
        String ds = y.substring(n / 2);

        BigInteger a = new BigInteger(as);
        BigInteger b = new BigInteger(bs);
        BigInteger c = new BigInteger(cs);
        BigInteger d = new BigInteger(ds);
        // calculate ac,and bd
        BigInteger ac = karatsuba(a, c);
        BigInteger bd = karatsuba(b, d);
        // and (a+b)*(c+d) = ad+bc+ac+bd - ac - bd = ad + bc
        BigInteger adPlusbc = karatsuba(a.add(b), c.add(d)).subtract(ac).subtract(bd);
        // return 10**(2*lengthOfHalf)*ac + 10**(lengthOfHalf)*(adPlusbc) + bd
        String part1Str = padZeros(ac.toString(), 2 * (n - n / 2), false);
        String part2Str = padZeros(adPlusbc.toString(), n - n / 2, false);

        return new BigInteger(part1Str).add(new BigInteger(part2Str)).add(bd);
    }

    private static String padZeros(String s, int nZero, boolean atFront) {
        StringBuilder newStr = new StringBuilder();
        if (atFront) {
            for (int i = 0; i < nZero; ++i) {
                newStr.append(0);
            }
            newStr.append(s);
        } else {
            newStr.append(s);
            for (int i = 0; i < nZero; ++i) {
                newStr.append(0);
            }
        }
        return newStr.toString();
        // Running time complexity: O(n)
    }

}