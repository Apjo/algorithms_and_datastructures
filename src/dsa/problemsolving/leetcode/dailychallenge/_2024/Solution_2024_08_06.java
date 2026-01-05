package dsa.problemsolving.leetcode.dailychallenge._2024;

/*
Convert a non-negative integer num to its English words representation.

Example 1:

Input: num = 123
Output: "One Hundred Twenty Three"
Example 2:

Input: num = 12345
Output: "Twelve Thousand Three Hundred Forty Five"
Example 3:

Input: num = 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

Constraints:

0 <= num <= 231 - 1

 */

public class Solution_2024_08_06 {
    static String[] unitsPlace = {"", "One", "Two", "Three", "Four", "Five", "Six","Seven","Eight","Nine","Ten", "Eleven", "Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
    static String[] tensPlace = {"", "Ten", "Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};

    public String numberToWords(int num) {
        if (num < 0) { return ""; }
        if (num == 0) { return "Zero";}
        return solve(num).trim();
    }
    private static String solve(int n) {
        if (n >= 1000000000) {
            return (solve(n / 1000000000) + " Billion " + solve(n % 1000000000)).trim();
        }
        if (n >= 1000000) {
            return (solve(n / 1000000) + " Million " + solve(n % 1000000)).trim();
        }
        if (n >= 1000) {
            return (solve(n / 1000) + " Thousand " + solve(n % 1000)).trim();
        }
        if (n >= 100) {
            return (solve(n / 100) + " Hundred " + solve(n % 100)).trim();
        }
        if (n >= 20) {
            return (tensPlace[n / 10] + " " + solve(n % 10)).trim();
        }
        return unitsPlace[n].trim();
    }
}
