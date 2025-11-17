package prep.meta;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class MinRemoveToMakeStringValid {
    //using a Stack solution
    public static String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> st =  new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char cc = s.charAt(i);
            //if open paren, keep on adding its index
            if (cc == '(') {
                st.push(i);
            } else if(cc == ')') {
                if (!st.isEmpty() && s.charAt(st.peek()) == '(') {
                    st.pop();
                } else {
                    st.push(i);
                }
            }
            Set<Integer> excluded = new HashSet<>(st);
            for(int ii = 0; ii < s.length(); ii++) {
                if(excluded.contains(ii)) {
                    continue;
                } else {
                    sb.append(s.charAt(ii));
                }

            }
            //once you find a matching closing paren
                //if stk isn't empty and if the top of stk matches an open
                    //pop from stk
                //else add this index to the stk
        }
        //move the indices from the stk to a set
        //iterate over the original string, while checking whether or not the index is present in the set
        // if its not -> build the final string by popping from the stk
        //return the new string
        return sb.toString();
    }
    //using constant space, only working for (,) parens
    public static String minRemoveToMakeValidConstSpace(String s) {
        int open=0, unMatchedOpen = 0, j = 0;
        //A unmatchedOpen variable keeps track of the number of open parentheses ( that have not yet found a matching closing parenthesis ).
        char[]arr = s.toCharArray();
        for(int i=0; i < arr.length; i++) {
            char curr = arr[i];
            if(curr == '(') {
                //When an ( is encountered, unmatchedOpen is incremented, and the character is temporarily kept.
                open++;
                unMatchedOpen++;
                arr[j++] = curr;
            } else if(curr == ')') {
                /*
                When a ) is encountered:
                1. If unmatchedOpen is > 0, it means there's an available open paren to match this closing paren.
                So, unmatchedOpen is decremented, and the ) is kept.
                If unmatchedOpenCount is 0, it means this ) does not have a preceding ( to match with.
                In this case, this ) is invalid, and it's skipped (effectively removed by not copying it to the new array).
               */
                    if(unMatchedOpen > 0) {
                    unMatchedOpen--;
                    arr[j++] = curr;
                }
            } else {
                //All non-parenthesis characters are always kept.
                arr[j++] = curr;
            }
        }
        int len=j, keep = unMatchedOpen;
        j=0;
        //After this pass, the arr contains the string with all invalid ) characters removed.
        // len holds the new length of this partially validated string, and unmatchedOpen now represents the number of ( characters that are still unmatched (i.e., they are excess ( characters).
        /*Second Pass: Removing invalid '(' characters.
          This pass iterates through the partially validated string (up to len).
          The unmatchedOpen from the first pass now directly tells us how many ( characters are in excess, and needs to be removed.
          This value is assigned to keep.
       */
        for(int k=0; k < len; k++) {
            char curr = arr[k];
            if(curr == '(') {
                //When an ( is encountered:
                //If keep is 1 than 0, it means we still need to keep valid ( characters. So, keep is decremented, and the ( is kept.
                if (keep > 0) {
                    keep--;
                    arr[j++] = curr;
                }
            } else {
                //If keep is 0, it means all necessary ( characters have been accounted for, and this ( is an excess opening parenthesis. Thus, it's skipped (removed).
                //All non-parenthesis characters and already validated ) characters from the first pass are always kept.
                arr[j++] = curr;
            }
        }
        return new String(arr, 0, j);
    }
    public static void main(String[] args) {
        assert minRemoveToMakeValid("lee(t(c)o)de)") == "lee(t(c)o)de";
    }
}
