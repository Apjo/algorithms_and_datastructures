package prep.amz.oa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
You and a colleague are responsible for dispatching goods from multiple warehouses. The inventory[i] array represents the amount of stock in each warehouse.

Rules:

Each warehouse is initially handled by you, and you dispatch dispatch1 units of goods from it.
Then it's your colleague's turn, and he can either:
Dispatch dispatch2 units of goods, or
Skip his turn. He can skip up to skips times in total (across all warehouses).
The two of you alternate turns until the warehouse is emptied, then move on to the next warehouse.
A point is earned only if you are the one who removes the last unit of goods from a warehouse (i.e., the warehouse becomes empty on your turn).
Goal:

Determine the best skipping strategy for your colleague (i.e., in which warehouses he should skip) to maximize the total number of points you and your colleague can earn. Return the maximum number of points that can be obtained.

Function Description

Complete the function maxPoints in the editor.

maxPoints has the following parameters:

1. int[] inventory: an array representing the stock in each warehouse
2. int dispatch1: the number of units you dispatch in your turn
3. int dispatch2: the number of units your colleague dispatches in his turn
4. int skips: the total number of skips your colleague is allowed
Returns

int: the maximum number of points that can be obtained

Example 1:

Input: inventory = [10, 6, 12, 8, 15, 1], dispatch1 = 2, dispatch2 = 3, skips = 3
Output: 5
Explanation:

An optimal dispatch strategy would be ~

1. Your co-worker skips 2 turns, allowing you to empty the inventory of the 1st warehouse (Inventory: 10 -> 8 -> 5 -> 3 -> 1 -> -1).

2. Your co-worker does not skip any turns, and you empty the inventory of the 2nd warehouse (Inventory: 6 -> 4 -> 1 -> -1).

3. Your co-worker does not skip any turns, and you empty the inventory of the 3rd warehouse (Inventory: 12 - > 10 -> 7 -> 5 -> 2 -> 0).

4. Your co-worker skips 1 turn, and you drain the  inventory of the 4th warehouse (Inventory: 8 -> 6 -> 3 -> 1 -> -1).

5. Your co-worker does not skip any turns, and they empty the inventory of the 5th warehouse (Inventory: 15 -> 13 -> 10 -> 8 -> 5 -> 3 -> 0).

6. Your co-worker does not skip any turns, and you empty the inventory of the 6th warehouse (Inventory: 1 -> -1).

As a result, the 1st, 2nd, 3rd, 4th, and 6th warehouses were completely dispatched by you, and the two of you collectively earned 5 credits! 🍻
This is the maximum possible in this scenario.

So, answer is 5.

This test case example was added on 06-25-2025. Relevant source image was added in the Problem Source section below.

Constraints:
1 <= n <= 10^5
1 <= inventory[i] <= 10^9
1 <= dispatch1, dispatch2, skips <= 10^9
Complete constraints set was added on 06-25-2025
 */
public class SkippingStrategy {
    public int maxPoints(int[] inventory, int dispatch1, int dispatch2, int skips) {
        /*
        Step 1: Compute required skips per warehouse
        For each warehouse:
            Compute remainder R=inventory[i]mod(dispatch1+dispatch2)
            Classify outcome and calculate skips:
            If R == 0 → colleague would finish naturally → you need 1 skip (to flip the last move to you).
            If 0 < R <= d1 → you finish naturally → 0 skips needed, add 0.
            If R > d1 → normally the colleague would finish → to flip it:
            Compute your_moves = ceil(R / dispatch1)
            Skips needed = your_moves - 1 (skip all colleague turns in between)
            Add this number to a list: temp
         */
        List<Integer> skipsNeeded = new ArrayList<>();
        for(int warehouse : inventory) {
            int outcome = warehouse%(dispatch1 + dispatch2);
            if (outcome == 0 || outcome > dispatch1) {
               if (outcome == 0) {
                   //colleague will finish, so will need 1 skip
                   skipsNeeded.add(1);
               } else {
                   //colleague may finish
                  int myNumMoves = (int)Math.ceil(outcome / dispatch1);
                  int skipsNeededForMeToBeLast = myNumMoves - 1;
                  skipsNeeded.add(skipsNeededForMeToBeLast);
               }
            } else {
                //I finish
                skipsNeeded.add(0);
            }
        }
        /*
        Step2:
            Once we have the list of skips needed per warehouse, we can use the total skip budget to pick which warehouses to flip.
            Free wins (0 skips) are automatically counted.
            For warehouses requiring skips, we can sort by skips ascending and pick as many as the budget allows to maximize points.
         */
        Collections.sort(skipsNeeded);
        int totalPoints = 0;
        for(Integer skip : skipsNeeded) {
            if(skip == 0) {
                totalPoints++;
            }
            if(skips >= skip) {
                skips-=skip;
                totalPoints++;
            }
        }
        return totalPoints;
    }
    public static void main(String[] args) {
    }
}
