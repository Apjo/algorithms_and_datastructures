package dsa.problemsolving.neetcode;

public class Problem24 {
    /*
    You are given a string s, return true if the s can be a palindrome after deleting at most one character from it.

A palindrome is a string that reads the same forward and backward.

Note: Alphanumeric characters consist of letters (A-Z, a-z) and numbers (0-9).

Example 1:

Input: s = "aca"

Output: true
Explanation: "aca" is already a palindrome.

Example 2:

Input: s = "abbadc"

Output: false
Explanation: "abbadc" is not a palindrome and can't be made a palindrome after deleting at most one character.

Example 3:

Input: s = "abbda"

Output: true
Explanation: "We can delete the character 'd'.

Constraints:

1 <= s.length <= 100,000
s is made up of only lowercase English letters.
     */
    //NOTES:Remember: Drop one char from LEFT, 1 char from RIGHT end of the input, and check for "isPalindrome(arr, le, hi)"
    public boolean validPalindrome(String s) {
        char[]arr = s.toCharArray();
        int lo=0, hi = s.length() - 1;
        while(lo < hi) {
            if(arr[lo] != arr[hi]) {
                if(isPalindrome(arr, lo + 1, hi) || isPalindrome(arr, lo, hi - 1)) {
                    return true;
                } else {
                    return false;
                }
            }
            lo++;hi--;
        }
        return true;
    }
    private static boolean isPalindrome(char[]arr, int lo, int hi) {
        while(lo < hi) {
            if(arr[lo] != arr[hi]) {
                return false;
            }
            lo++;hi--;
        }
        return true;
    }
    public static void main(String[] args) {
    }
}
