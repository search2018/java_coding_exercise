package com.search2026.leetcode.problems;

import java.util.List;

public class Triangle {

    /*
        Triangle
        Leetcode #120
        https://leetcode.com/problems/triangle/
        Difficulty: Medium
     */
    public class Solution {
        public int minimumTotal(List<List<Integer>> triangle) {
            if (triangle == null || triangle.size() == 0) return 0;
            int n = triangle.size();
            int[] dp = new int[n];
            dp[0] = triangle.get(0).get(0);
            for (int i = 1; i < n; i++) {
                dp[i] = triangle.get(i).get(i) + dp[i - 1];
                for (int j = i - 1; j >= 1; j--) {
                    dp[j] = Math.min(dp[j-1], dp[j]) +  triangle.get(i).get(j);
                }
                dp[0] = dp[0] + triangle.get(i).get(0);
            }

            int rslt = dp[0];
            for (int i=1; i<n; i++)
                if (dp[i] < rslt)
                    rslt = dp[i];
            return rslt;
        }
    }

}
