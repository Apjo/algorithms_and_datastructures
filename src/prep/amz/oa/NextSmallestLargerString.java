package prep.amz.oa;
/*
Problem: Lexicographically Smallest Special String

Developers at Amazon are working on a text generation utility for one of their new products.

Currently, the utility generates only special strings. A string is special if there are no matching adjacent characters. Given a string s of length n, generate a special string of length n that is lexicographically greater than s. If multiple such special strings are possible, then return the lexicographically smallest string among them.

Notes:
Special String: A string is special if there are no two adjacent characters that are the same.
Lexicographical Order: This is a generalization of the way words are alphabetically ordered in dictionaries. For example, "abc" is lexicographically smaller than "abd" because 'c' comes before 'd' in the alphabet.
A string a is lexicographically smaller than a string b if and only if one of the following holds:

a is a prefix of b, but is not equal to b. For example, "abc" is smaller than "abcd".
In the first position where a and b differ, the character in a comes before the character in b in the alphabet. For example, "abc" is smaller than "abd" because 'c' comes before 'd'.
Important Considerations:
If the character is 'z', it is the last character in the alphabet and cannot be increased further. The string should not wrap around to 'a' after 'z'.
The output string must not have any adjacent characters that are the same.
Example:
Suppose s = "abbd":

Some of the special strings that are lexicographically greater than s are shown below:

abbd -> abca
The lexicographically smallest special string that is greater than "abbd" is "abca".
 */

public class NextSmallestLargerString {
    public static String solve(String s) {
        char[] arr = s.toCharArray();
        int N = arr.length;
        //start with rightmost char
        for(int i = N - 1; i >= 0; i--) {
            //determine lexicographically the next one
            char next = (char)(arr[i] + 1);
            while(next <= 'z') {
                //we don't want same adjacent characters
                if ((i > 0 && arr[i - 1] == next) || (i + 1 < N && arr[i+1] == next)) {
                    continue;
                }
                next++;
            }
            //replace the ith char with this
            arr[i] = next;
            //continuing further with rest of the chars, fill the suffix greedily with smallest valid chars
            for(int j = i + 1; j < N; j++) {
                for(char st = 'a'; st <= 'z'; st++) {
                    if ((j > 0 && arr[j - 1] == st) || (j + 1< N && arr[j + 1] == st)) {
                        continue;
                    }
                    arr[j] = st;
                    break;
                }
            }
        }
        return new String(arr);
    }
    public static void main(String[] args) {
        assert solve("abcd").equals("abca");
    }
}
