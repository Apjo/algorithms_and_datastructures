package dsa.problemsolving.leetcode.dailychallenge;

/*
You are given an array books where books[i] = [thicknessi, heighti] indicates the thickness and height of the ith book. You are also given an integer shelfWidth.

We want to place these books in order onto bookcase shelves that have a total width shelfWidth.

We choose some of the books to place on this shelf such that the sum of their thickness is less than or equal to shelfWidth, then build another level of the shelf of the bookcase so that the total height of the bookcase has increased by the maximum height of the books we just put down. We repeat this process until there are no more books to place.

Note that at each step of the above process, the order of the books we place is the same order as the given sequence of books.

For example, if we have an ordered list of 5 books, we might place the first and second book onto the first shelf, the third book on the second shelf, and the fourth and fifth book on the last shelf.
Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.
 */

import java.util.Stack;

public class Solution_2024_07_30 {
    //DP with memoization
    static Integer[] ans;
    public int minHeightShelves(int[][] books, int shelfWidth) {
        int N = books.length;
        ans = new Integer[N];
        return solve(books, shelfWidth, 0);
        //return memo[N];
    }

    private static int solve(int[][] arr, int shelfW, int index) {
        if (ans.length == index) {
            return 0;
        }
        if (ans[index] != null) {
            return ans[index];
        }
        int finalAns = 0, currLevelMaxHt = 0, currLevelWidth = 0;
        for (int i = index; i < arr.length; i++) {
            int currBookThickness = arr[i][0];
            currLevelWidth += currBookThickness;
            if (currLevelWidth > shelfW) {
                break;
            }
            int currBookHt = arr[i][1];
            //get maxht
            currLevelMaxHt = Math.max(currLevelMaxHt, currBookHt);
            //if curr book's width is well withing current shelf width, can add the book at this current shelf,
            // then after keeping the book on this shelf, calculate the maxht achieved
            //else, start the new shelf
            finalAns = Math.min(finalAns, currLevelMaxHt + solve(arr, shelfW, i + 1));
        }
        return ans[index] = finalAns;
    }
}
