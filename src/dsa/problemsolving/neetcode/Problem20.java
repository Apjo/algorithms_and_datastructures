package dsa.problemsolving.neetcode;

import java.util.ArrayList;
import java.util.List;
/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Example 1:

Input: nums = [3,2,3]
Output: [3]
Example 2:

Input: nums = [1]
Output: [1]
Example 3:

Input: nums = [1,2]
Output: [1,2]
 */
public class Problem20 {
    public List<Integer> majorityElement(int[] nums) {
        int num1=-1, num2=-1, cnt1=0,cnt2=0;
        for(int x:nums) {
            if (x==num1) {
               cnt1++;
            } else if(x==num2) {
                cnt2++;
            } else if(cnt1==0) {
                num1=x;
                cnt1=1;
            } else if(cnt2==0) {
                num2=x;
                cnt2=1;
            } else {
                cnt1--;
                cnt2--;
            }
        }
        cnt1=0;cnt2=0;
        for(int xx : nums) {
            if(xx==num1) {
                cnt1++;
            }
            if(xx==num2) {
                cnt2++;
            }
        }
        List<Integer> res = new ArrayList<>();
        if(cnt1 > nums.length / 3) {
            res.add(num1);
        }
        if (cnt2 > nums.length / 3) {
            res.add(num2);
        }
        return res;
    }
    public static void main(String[] args) {
    }
}
