package dsa.problemsolving.leetcode.dailychallenge._2024;

/*
There are n 1-indexed robots, each having a position on a line, health, and movement direction.

You are given 0-indexed integer arrays positions, healths, and a string directions (directions[i] is either 'L' for left or 'R' for right). All integers in positions are unique.

All robots start moving on the line simultaneously at the same speed in their given directions. If two robots ever share the same position while moving, they will collide.

If two robots collide, the robot with lower health is removed from the line, and the health of the other robot decreases by one. The surviving robot continues in the same direction it was going. If both robots have the same health, they are both removed from the line.

Your task is to determine the health of the robots that survive the collisions, in the same order that the robots were given, i.e. final heath of robot 1 (if survived), final health of robot 2 (if survived), and so on. If there are no survivors, return an empty array.

Return an array containing the health of the remaining robots (in the order they were given in the input), after no further collisions can occur.


 */
import java.util.*;

public class Solution_2024_07_12 {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        List<Integer> res = new ArrayList<>();
        List<Integer> res1 = new ArrayList<>();
        for(int i=0; i < positions.length; i++) {
            res.add(i);
        }
        res.sort((a, b) -> positions[a] - positions[b]);
        Stack<Integer> st = new Stack<>();
        for(Integer i : res) {
            if (directions.charAt(i) == 'R') {
                st.push(i);
                continue;
            }
            while(!st.isEmpty() && healths[i] > 0) {
                if(healths[st.peek()] > healths[i]) {
                    healths[i] = 0;
                    healths[st.peek()] -= 1;
                } else if(healths[st.peek()] < healths[i]) {
                    healths[st.pop()]=0;
                    healths[i]-=1;
                } else {
                    healths[st.pop()]=0;
                    healths[i]=0;
                }
            }
        }
        for(Integer v : healths) {
            if (v > 0) {
                res1.add(v);
            }
        }
        return res1;
    }
}
