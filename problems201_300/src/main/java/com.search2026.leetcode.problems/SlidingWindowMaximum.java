package com.search2026.leetcode.problems;

import java.util.LinkedList;

public class SlidingWindowMaximum {

    /*
        Sliding Windows Maximum
        Leetcode #239
        https://leetcode.com/problems/sliding-window-maximum/
        Difficulty: Hard
     */
    public class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (k == 0) return new int[0];

            LinkedList<Integer> q = new LinkedList<>();

            int[] res = new int[nums.length - k + 1];

            for (int i = 0; i < nums.length; i++) {
                while (!q.isEmpty() && nums[i] >= nums[q.getLast()]) {
                    q.removeLast();
                }
                q.addLast(i);

                if (i - q.getFirst() + 1 > k) {
                    q.removeFirst();
                }
                if (i + 1 >= k) res[i - k + 1] = nums[q.getFirst()];
            }

            return res;
        }
    }

}
