package dsa.problemsolving.java.neetcode;

public class Problem25 {
    /*
    You are given two strings, word1 and word2. Construct a new string by merging them in alternating order, starting with word1 — take one character from word1, then one from word2, and repeat this process.

If one string is longer than the other, append the remaining characters from the longer string to the end of the merged result.

Return the final merged string.

Example 1:

Input: word1 = "abc", word2 = "xyz"

Output: "axbycz"
Example 2:

Input: word1 = "ab", word2 = "abbxxc"

Output: "aabbbxxc"
     */
    //date:07/28/2025
    //time: O(n+m), space:O(1)
    public String mergeAlternately(String word1, String word2) {
        int i=0, j = 0, L = word1.length(), M=word2.length();
        StringBuilder sb = new StringBuilder();
        while(i < L && j < M) {
            sb.append(word1.charAt(i++));
            sb.append(word2.charAt(j++));
        }
        while(i < L) {
            sb.append(word1.charAt(i++));
        }
        while(j < M) {
            sb.append(word2.charAt(j++));
        }
        return sb.toString();
    }
    public static void main(String[] args) {
    }
}
