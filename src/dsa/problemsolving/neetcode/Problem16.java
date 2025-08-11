package dsa.problemsolving.neetcode;
import java.util.*;
/*
Given an integer array nums, return an array output where output[i] is the product of all the elements of nums except nums[i].

Each product is guaranteed to fit in a 32-bit integer.

Follow-up: Could you solve it in O(n) O(n) time without using the division operation?
 */
//date:07/23/2025
public class Problem16 {
    public int[] productExceptSelf(int[] nums) {
        int N = nums.length;
        int[]prodLeft = new int[N];
        int[]prodRight = new int[N];
        Arrays.fill(prodLeft, 1);
        Arrays.fill(prodRight, 1);
        int mult=1;
        for(int i=0; i < N; i++) {
            prodLeft[i] = mult;
            mult*=nums[i];
        }
        mult=1;
        for(int i = N - 1; i >= 0; i--) {
            prodRight[i] *= mult;
            mult*=nums[i];
        }
        for(int i=0; i< N; i++) {
            nums[i] = prodLeft[i] * prodRight[i];
        }
        return nums;
    }
    public int[] productExceptSelf2(int[] nums) {
        int N = nums.length;
        int mult=1;
        int[]temp = new int[N];
        temp[0]=1;
        for(int i=1; i < N; i++) {
            temp[i] = temp[i - 1] * nums[i - 1];
        }
        for(int i = N - 1; i >= 0; i--) {
            temp[i]*=mult;
            mult*=nums[i];
        }
        return temp;
    }
    public static void main(String[] args) {
    }
}
