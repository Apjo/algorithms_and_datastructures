package dsa.problemsolving.neetcode;

public class Problem26 {
    /*
    You are given two integer arrays nums1 and nums2, both sorted in non-decreasing order, along with two integers m and n, where:

m is the number of valid elements in nums1,
n is the number of elements in nums2.
The array nums1 has a total length of (m+n), with the first m elements containing the values to be merged, and the last n elements set to 0 as placeholders.

Your task is to merge the two arrays such that the final merged array is also sorted in non-decreasing order and stored entirely within nums1.
You must modify nums1 in-place and do not return anything from the function.

Example 1:

Input: nums1 = [10,20,20,40,0,0], m = 4, nums2 = [1,2], n = 2

Output: [1,2,10,20,20,40]
Example 2:

Input: nums1 = [0,0], m = 0, nums2 = [1,2], n = 2

Output: [1,2]
     */
    //date:07/28/2025
    //time: O(n), space:O(1)
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //NOTES: Observe the initial values of the pointers
        int L = m + n;
        int p1=L-1, p2=m-1, p3=n-1;
        while(p1 >= 0 && p2 >= 0 && p3 >= 0) {
            if (nums1[p2] > nums2[p3]) {
                nums1[p1] = nums1[p2];
                p2--;
            } else {
                nums1[p1] = nums2[p3];
                p3--;
            }
            p1--;
        }
        while(p2 >=0) {
            nums1[p1--] = nums1[p2--];
        }
        while(p3 >=0) {
            nums1[p1--] = nums2[p3--];
        }
    }
    public static void main(String[] args) {
    }
}
