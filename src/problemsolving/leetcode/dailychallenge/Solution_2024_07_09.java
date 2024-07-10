package problemsolving.leetcode.dailychallenge;

import java.util.*;

/*
The Leetcode file system keeps a log each time some user performs a change folder operation.

The operations are described below:

"../" : Move to the parent folder of the current folder. (If you are already in the main folder, remain in the same folder).
"./" : Remain in the same folder.
"x/" : Move to the child folder named x (This folder is guaranteed to always exist).
You are given a list of strings logs where logs[i] is the operation performed by the user at the ith step.

The file system starts in the main folder, then the operations in logs are performed.

Return the minimum number of operations needed to go back to the main folder after the change folder operations.
 */
public class Solution_2024_07_09 {
    //O(N) time, O(N) space
    public int minOperations(String[] logs) {
        Stack<String> st = new Stack<>();
        st.push("+");
        int steps=0;
        for(String log : logs) {
            //case folder/ : add folder to stack
            //case ../ : pop 1 item from stack
            //case ./ : no op
            //if commands remain but stack only contains a +, return a 0
            if(log.equals("./")) {
                continue;
            } else if(log.equals("../")) {
                if(!st.isEmpty()) {
                    st.pop();
                }
            } else {
                st.push(log);
            }
        }
        while(!st.isEmpty()) {
            if (st.peek().equals("+")) {
                break;
            } else {
                steps++;
                st.pop();
            }
        }
        return steps;
    }
}
