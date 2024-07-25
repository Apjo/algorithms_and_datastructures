package dsa.problems.strings;

//Pattern: LCS
public class PalindromicSubsequences {
    private static void print(int input) {
        System.out.println(input);
    }
    private static String reverseStr(char[]a, int lo, int hi) {
        while(lo <= hi) {
            char temp = a[lo];
            a[lo] = a[hi];
            a[hi] = temp;
            lo++;
            hi--;
        }
        return String.valueOf(a);
    }

    //TRICK: num_deletions is inversely proportional to len(Longest palindromic subsequence).
    // hence num_deletions = Len(S) - Len(LPS)
    //The same trick is also used for determining min. no.of insertions problem!!
    public static int minDeletionsToMakePalindrome(String A) {
        return A.length() - longestPalindromicSubsequence(A);
    }

    //Trick used: LPS(A) === LCS(A, Reverse(A))
    public static int longestPalindromicSubsequence(String a) {
        String rev = reverseStr(a.toCharArray(), 0, a.length() - 1);
        return LongestCommonSubsequence.solveLcsDP(a, rev);
    }
    public static void main(String[] args) {
        print(PalindromicSubsequences.longestPalindromicSubsequence("AGBCBA")); //should be 5 i.e. Len("ABCBA")
        print(PalindromicSubsequences.minDeletionsToMakePalindrome("AGBCBA")); //should be 1
    }
}
