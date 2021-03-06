package com.search2026.leetcode.problems;

public class SqrtX {

    /*
        Sqrt(x) - Binary Search
        Leetcode #69
        https://leetcode.com/problems/sqrtx/
        Difficulty: Medium
     */
    public class Solution {
        public int sqrt(int x) {
            if (x < 0)
                throw new IllegalArgumentException("invalidate input");
            if (x < 2) return x;

            int left = 1, right = x / 2;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (x / mid == mid) {
                    return mid;
                }
                if (x / mid > mid) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return right;
        }
    }

    /*
        Sqrt(x) - Newton's Method
        Leetcode #69
        https://leetcode.com/problems/sqrtx/
        Difficulty: Medium
     */
    public class Solution_2 {
        public int sqrt(int x) {
            if (x < 0)
                throw new IllegalArgumentException("invalidate input");
            if (x == 1) return x;

            double root = 1.0;
            while (Math.abs(root * root - x) > 10E-6) {
                root = (root + x / root) / 2;
            }
            return (int) root;
        }
    }

}
