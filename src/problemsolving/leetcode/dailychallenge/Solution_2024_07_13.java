package problemsolving.leetcode.dailychallenge;

/*
Given a string formula representing a chemical formula, return the count of each atom.

The atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.

One or more digits representing that element's count may follow if the count is greater than 1. If the count is 1, no digits will follow.

For example, "H2O" and "H2O2" are possible, but "H1O2" is impossible.
Two formulas are concatenated together to produce another formula.

For example, "H2O2He3Mg4" is also a formula.
A formula placed in parentheses, and a count (optionally added) is also a formula.

For example, "(H2O2)" and "(H2O2)3" are formulas.
Return the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.

The test cases are generated so that all the values in the output fit in a 32-bit integer.

 */

import java.util.*;

public class Solution_2024_07_13 {
    public String countOfAtoms(String formula) {
        TreeMap<String, Integer> tm = new TreeMap<>();
        Stack<TreeMap> st = new Stack<>();
        int N = formula.length();
        int i=0;
        while(i < N) {
            char curr = formula.charAt(i);
            if (curr == '(') {
                st.push(tm);
                tm = new TreeMap<>();
                i++;
            } else if (curr == ')') {
                //process the count of atoms
                int val=0;
                i++;
                while(i < N && Character.isDigit(formula.charAt(i))) {
                    val = val * 10 + (formula.charAt(i++) - '0');
                    // i++;
                }
                val = val == 0 ? 1 : val;
                if(!st.isEmpty()) {
                    //get current top of stack
                    TreeMap<String, Integer> temp = tm;
                    tm = st.pop();
                    //go through all the atoms, and update their existing values
                    for(String e: temp.keySet()) {
                        tm.put(e, temp.get(e)*val + tm.getOrDefault(e, 0));
                    }
                }

            } else {
                //starts with a UUPER CASE letter, then get whatever's next to it
                int nextLoc = i + 1;
                //char next = formula.charAt(nextLoc);
                //if LOWERCASE letter,continue appending to the UPPERCASE one
                while (nextLoc < N && Character.isLowerCase(formula.charAt(nextLoc))) {
                    nextLoc++;
                }
                //prepare the atom
                String atom = formula.substring(i, nextLoc);
                int cnt=0;
                //calculate weight/valency?
                while(nextLoc < N && Character.isDigit(formula.charAt(nextLoc))) {
                    cnt = cnt * 10 + (formula.charAt(nextLoc++) - '0');
                }
                cnt = cnt == 0 ? cnt=1 : cnt;
                tm.put(atom, tm.getOrDefault(atom, 0) + cnt);
                i=nextLoc;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(String a : tm.keySet()) {
            sb.append(a);
            sb.append(tm.get(a) == 1 ? "" : tm.get(a));
        }
        return sb.toString();
    }
}
