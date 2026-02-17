package dsa.java.elementsofprogramminginterviews;
/*
NOTES:

1. it takes constant time to find the length of the string every time, because length is cached at the time of
 String object creation and Strings are immutable after that
2. substring function creates a new String object. every time you use substring function you are taking a space
complexity of O(N)
Tries:
1. If L is the size of the given alphabet, and N is the size of the longest string in the input,
     the time complexity of Insert, Delete and Query operations on a Trie ==> O(N),O(N), and O(N) respectively
2. Given a corpus of N strings, of length L each, if we used a BST instead of a Trie, in order to store strings for efficient lookups
 the time complexity of inserting a new string in such a BST, Assume that it's a self-balancing BST would be
 ==> O(L* log N) : compare each incoming string to the given string in the tree node and make LogN such decisions to find its
 right place

Substring search:
1. subarray, substring, subsequence -> all of them preserve the order
2. no concept of ordering elements in a subset

KMP:
- If N is the size of the text, and M is the size of the pattern, then what is the time complexity of KMP ==> O(M + N)
- we need to preprocess the pattern, but not the text

Suffix trie:
- time to create a suffix trie of adding N suffixes of length N ==> O(N^2)
- lookup a suffix ==> O(length of pattern)
- number of nodes made out of string of length N ==> O(N*N), N suffixes, with max length N and each node is added to the
    tree
- problems that can be solved by first building a suffix trie:

Encodings:
Unicode

Unicode, a letter maps to something called a code point
Code point:
Every platonic letter in every alphabet is assigned a magic number by the Unicode consortium which is written like this: U+0639. This magic number is called a code point. The U+ means “Unicode” and the numbers are hexadecimal.
eg: U+0639 is the Arabic letter Ain.
Encoding

UCS-2: uses 2 bytes for storage
UTF-16: uses 16 bits
UTF-8
In UTF-8, every code point from 0-127 is stored in a single byte.
Only code points 128 and above are stored using 2, 3, in fact, up to 6 bytes.
 ASCII
*/
public class Chapter6 {
    private static void swapStr(char[] A, int i, int j) {
        char temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    public static String reverseStr(String s) {
        char []arr = s.toCharArray();
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            swapStr(arr, i, j);
        }
        return new String(arr);
    }

    public static boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() -1; i < j; i++, j--) {
            if (s.charAt(i) == s.charAt(j)) {
                return true;
            }
        }
        return false;
    }
    //O(len(S) * O(len(p) - len(s))
    public static boolean substringSearch(String s, String p) {
        for (int i = 0; i < p.length() - 1; i++) {
            for (int j = 0; j < s.length() - 1; j++) {
                if (p.charAt(i) != s.charAt(j)) {
                    break;
                }
                if (j == s.length() - 1) {
                    return true;
                }
            }
        }
        return false;
    }
    public static String iToA(int N) {
        boolean isNeg = false;
        if (N < 0) {
            isNeg = true;
        }
        StringBuilder sb = new StringBuilder();
        while (N > 0) {
            sb.append((char)(Math.abs(N % 10) + '0'));
            N /= 10;
        }
        if (isNeg) {
            sb.append("-");
        }
        return sb.reverse().toString();
    }

    public static int aToI(String input) {
        boolean isNeg = false;
        if (input.charAt(0) == '-') {
            isNeg = true;
        }
        int res = 0;
        for (int i = input.charAt(0) == '-' ? 1 : 0; i < input.length(); i++) {
            int digit = input.charAt(i) - '0';
            res = res * 10 + digit;
        }
        if (res == 0) return 0;
        return isNeg ? -1 * res : res;
    }

    private static int convertToBase(String input, int base) {
        boolean isNeg = false;
        if (input.charAt(0) == '-') {
            isNeg = true;
        }
        int res = 0;
        for (int i = input.charAt(0) == '-' ? 1 : 0; i < input.length(); i++) {
            int digit = Character.isDigit(input.charAt(i)) ? input.charAt(i) - '0' : input.charAt(i) - 'A' + 10;
            res = res * base + digit;
        }
        return isNeg ? -1 * res : res;
    }
    private static String constructFromBase(int N, int base) {
        if (N == 0) { return "0"; }
        boolean isNeg = false;
        if (N < 0) {
            isNeg = true;
        }
        StringBuilder sb = new StringBuilder();
        while (N > 0) {
            int digit = Math.abs(N % base);
            if (digit >= 10) {
                sb.append((char)('A' + digit - 10));
            } else {
                sb.append((char) (digit + '0'));
            }
            N /= base;
        }
        if (isNeg) {
            sb.append("-");
        }
        return sb.reverse().toString();
    }
    //time: O(N(1 + log(base1)))
    public static String baseConversions(String s, int base1, int base2) {
        //convert s into a number to be represented in base1
        int num = convertToBase(s, base1);
        //now convert the number in base1 to a string in base2
        return constructFromBase(num, base2);
    }
    //time: O(N)
    public static int csvColumnNum(String s) {
        int res = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            char c = s.charAt(i);
            res = (res * 26) + c - 'A' + 1;
        }
        return res;
    }

    //TODO: replaceAndRemove
    //TODO: test palindromicity
    //TODO: reverse words in a sentence
    //TODO: compute all mnemonics in a phone num
    /*Recursive solution:
    Base case is n == 1, return 1.
    Two conditions where we append count to:
        i moved to end of string
        charAt(i) != charAt(i - 1)
    Time:O(n2^n), for each N we can go as long as we have 2^n repeating digits
    Ref: https://leetcode.com/problems/count-and-say/discuss/16000/Show-an-Answer-in-Java/16028
     */
    private static String countAndSay(String curr) {
        int ctr = 0;
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < curr.length(); j++) {
            ctr ++;
            if (j + 1 == curr.length()) {
                sb.append(ctr).append(curr.charAt(j));
            }
            if (j + 1 < curr.length() && curr.charAt(j) != curr.charAt(j + 1)) {
                sb.append(ctr).append(curr.charAt(j));
                ctr = 0;
            }
        }
        return sb.toString();
    }
    public static String countAndSay(int n) {
        if (n <= 0) { return ""; }
        String s = "";
        for (int i = 1; i < n; i++) {
            s = countAndSay(s);
        }
        return s;
    }
    public static int romanToInteger(String roman) {
        int[] values = new int[roman.length()];
        for (int i = 0; i < roman.length() - 1; i++) {
            switch (roman.charAt(i)) {
                case 'I':
                    values[i] = 1;
                    break;
                case 'V':
                    values[i] = 5;
                    break;
                case 'X':
                    values[i] = 10;
                    break;
                case 'L':
                    values[i] = 50;
                    break;
                case 'C':
                    values[i] = 100;
                    break;
                case 'D':
                    values[i] = 500;
                    break;
                case 'M':
                    values[i] = 1000;
                    break;
            }
        }
        int sum = 0;
        for (int i = 0; i <roman.length(); i++) {
            int prev = values[i];
            int next = i + 1 < roman.length() ? values[i + 1] : 0;
            if (prev < next) {
                sum += (next - prev);
                i++;
            } else {
                sum += prev;
            }
        }
        return sum;
    }

    public static String sinusoidal(String s) {
        StringBuilder sb = new StringBuilder();
        int N = s.length();
        for (int i = 1; i < N; i+=4) {
            sb.append(s.charAt(i));
        }
        for (int i = 0; i < N; i+=2) {
            sb.append(s.charAt(i));
        }
        for (int i= 3; i < N; i+=4) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
    public static String decoding(String s) {
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                cnt = cnt * 10 + c - '0';
            } else {
                while(cnt > 0) {
                    sb.append(c);
                    cnt --;
                }
            }
        }
        return sb.toString();
    }
    //O(N)
    public static String encoding(String s) {
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= s.length(); i++) {
            if (i == s.length() || s.charAt(i) != s.charAt(i - 1)) {
                sb.append(cnt).append(s.charAt(i - 1));
                cnt = 0;
            } else {
                ++cnt;
            }
        }
        return sb.toString();
    }
}
