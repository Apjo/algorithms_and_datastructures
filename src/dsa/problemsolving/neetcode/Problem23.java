package dsa.problemsolving.neetcode;

public class Problem23 {
 /*
 Given a string s, return true if it is a palindrome, otherwise return false.

A palindrome is a string that reads the same forward and backward. It is also case-insensitive and ignores all non-alphanumeric characters.

Note: Alphanumeric characters consist of letters (A-Z, a-z) and numbers (0-9).

Example 1:

Input: s = "Was it a car or a cat I saw?"

Output: true
Explanation: After considering only alphanumerical characters we have "wasitacaroracatisaw", which is a palindrome.

Example 2:

Input: s = "tab a cat"

Output: false
Explanation: "tabacat" is not a palindrome.

Constraints:

1 <= s.length <= 1000
s is made up of only printable ASCII characters.
  */
 //date:07/23/2025
 public boolean isPalindrome(String s) {
     //preprocess? -> lower case, remove non-alphanumer chars
     s=s.toLowerCase();
     StringBuilder sb = new StringBuilder();
     for(char cc : s.toCharArray()) {
         if(Character.isLetter(cc) || Character.isDigit(cc)) {
             sb.append(cc);
         }
     }
     //2 pointers
     int le=0, ri=sb.length() - 1;
     while(le < ri) {
         if (sb.charAt(le) != sb.charAt(ri)) {
             return false;
         }
         le++;
         ri--;
     }
     return true;
 }
    public boolean isPalindrome2(String s) {
        int le=0, ri = s.length() - 1;
        while(le < ri) {
            while (le < ri && !Character.isLetterOrDigit(s.charAt(le))) {
                le++;
            }
            while (ri > le && !Character.isLetterOrDigit(s.charAt(ri))) {
                ri--;
            }
            if (Character.toLowerCase(s.charAt(le)) != Character.toLowerCase(s.charAt(ri))) {
                return false;
            }
            le++;ri--;
        }
        return true;
    }
    public static void main(String[] args) {
    }
}
