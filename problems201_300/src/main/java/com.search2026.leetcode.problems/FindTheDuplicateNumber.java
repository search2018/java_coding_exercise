package com.search2026.leetcode.problems;

public class FindTheDuplicateNumber {

    /*
        Find the Duplicate Number
        Leetcode #287
        https://leetcode.com/problems/find-the-duplicate-number/
        Difficulty: Medium
     */
    public class Solution {
        private int countNumbers(int[] nums, int mid) {
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }

            return count;
        }

        public int findDuplicate(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int left = 1;
            int right = nums.length - 1;

            while (left < right) {
                int mid = left + (right - left) / 2;
                int count = countNumbers(nums, mid);

                if (count <= mid) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return right; // Or lo
        }
    }

}
