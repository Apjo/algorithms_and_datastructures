package dsa.problemsolving.neetcode;

/*
You are given an array nums consisting of n elements where each element is an integer representing a color:

0 represents red
1 represents white
2 represents blue
Your task is to sort the array in-place such that elements of the same color are grouped together and arranged in the order: red (0), white (1), and then blue (2).

You must not use any built-in sorting functions to solve this problem.
 */
//date:07/23/2025
public class Problem12 {
    private static void swap(int[]arr, int a, int b) {
        int te = arr[a];
        arr[a] = arr[b];
        arr[b] = te;
    }
    public void sortColors(int[] nums) {
        int i=0, lo=0, hi = nums.length - 1;
        while(i <= hi) {
            switch(nums[i]) {
                case 0: swap(nums, lo, i);i++;lo++;break;
                case 2: swap(nums, hi, i); hi--;break;
                case 1 : i++;break;
            }
        }
    }
    public static void main(String[] args) {
    }
}
