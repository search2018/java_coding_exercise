package com.search2026.leetcode.problems;

public class BurstBalloons {

    /*
        Burst Ballons
        Leetcode #312
        https://leetcode.com/problems/burst-balloons/
        Difficulty: Hard
     */
    public class Solution {
        public int maxCoins(int[] nums) {
            int n = nums.length + 2;
            int[] arr = new int[n];  //expanded nums
            arr[0] = arr[n - 1] = 1;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0)
                    arr[i + 1] = nums[i];
            }
            int[][] dp = new int[n][n];
            for (int i = 2; i <= n - 1; i++) {
                for (int l = 0; l <= n - 1 - i; l++) {
                    int r = l + i;
                    for (int j = l + 1; j < r; j++) {
                        dp[l][r] = Math.max(dp[l][r], arr[l] * arr[r] * arr[j] + dp[l][j] + dp[j][r]);
                    }
                }
            }
            return dp[0][n - 1];
        }
    }

}
