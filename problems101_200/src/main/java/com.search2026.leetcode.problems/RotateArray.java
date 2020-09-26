package com.search2026.leetcode.problems;

public class RotateArray {

    /*
        Rotate Array
        Leetcode #189
        https://leetcode.com/problems/rotate-array/
        Difficulty: Easy
     */
    public class Solution {
        private void reverse(int[] nums, int begin, int end) {
            while (begin < end) {
                int temp = nums[begin];
                nums[begin] = nums[end];
                nums[end] = temp;
                begin++;
                end--;
            }
        }

        public void rotate(int[] nums, int k) {
            k = k % nums.length;
            reverse(nums, 0, nums.length - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, nums.length - 1);
        }
    }

}
