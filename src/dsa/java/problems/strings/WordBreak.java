package dsa.java.problems.strings;
import java.util.*;
/**
 * LC#139
 *f(i) = boolean answer to the question"can we segment c1,c2,..., ci in a valid way"?
 * f(i) = if Ci is valid => f(i-1)
 *        if Ci-1Ci is valid => f(i-2)
 *        if C1...Ci is valid => f(0) the entire string is a valid segment
 * n+1 unique subproblems
 * Space:O(n)
 * Time: O(n^2) for each entry in dp table, we are reading back and reading the values to every single subproblem before us
 *
 */
public class WordBreak {
    public static boolean solve(List<String> dict, List<String> ll) {
        int n = ll.size();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for (int i=1;i<n;i++) {
            //characters we chose as the lastWord starting from i
            for (int lastWordLen=1;lastWordLen<i;lastWordLen++) {
                /*look at Ci-lastWordLen+1 to Ci, which are nothing but lastWordLen characters
                //which is nothing but a substring from(i-lastWordLen to i-1)
                //IF the word(i.e.substring from(i-lastWordLen to i-1))
                // we are choosing is valid(is present in the dict), &&
                // our subordinate's subproblem value is true(i.e. the first i-lastWordLen characters
                // can be segmented in a valid way, so that every word is in the dict)THEN
                Eg:
                say S="leetcode", dict=["leet", "code"]
                S.size == 8
                our dp table will be of size +1 = 8+1 = 9
                dp[0] = true
                Starting from
                i = 1:
                    lastWordLen=1 -> word="l" -> is"l" in dict? No -> dp[1]=F
                i = 2:
                    lastWordLen=1 to 2->
                        word="e" -> is "e" in dict? No
                        word="le" -> is "le" in dict? No -> dp[2]=F
                i = 3:
                    lastWordLen=1 to 3->
                        word="e" -> is "e" in dict? No
                        word="ee" -> is "ee" in dict? No
                        word="lee" -> is "lee" in dict? No -> dp[3]=F
                i = 4:
                    lastWordLen=1 to 4->
                        word="t" -> is "t" in dict? No
                        word="et" -> is "et" in dict? No
                        word="eet" -> is "eet" in dict? No
                        word="leet" -> is "lee" in dict? No -> dp[4]=T
                Going through the word,
                dp[5]=F, for word="leetc"
                dp[6]=F, for word="leetco"
                dp[7]=F, for word="leetcod"
                dp[8]=T, for word="leetcode", at this stage, i=8, lastWordLen=4 (for the word "code"). and dp[8-4]=T
                To put into perspective
                say we have S = "c1c2c3...ci"
                len(S)=i
                len(c3..ci) = lastWordLen
                len(c1-c2) = i - lastWordLen
                In the dp table we lookup for dp[i - j], and the string corresponding to that is S0...S(i-lastWordLen-1)
                In the dictionary, we lookup for S(i-lastWordLen)...S(i-1)
                */
                if(dict.contains(ll.get(i).substring(i - lastWordLen, i - 1)) && dp[i - lastWordLen]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
