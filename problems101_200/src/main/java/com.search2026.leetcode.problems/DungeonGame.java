package com.search2026.leetcode.problems;

public class DungeonGame {

    /*
        Dungeon Game
        Leetcode #174
        https://leetcode.com/problems/dungeon-game/
        Difficulty: Hard
     */
    public class Solution {
        public int calculateMinimumHP(int[][] dungeon) {
            int m = dungeon.length;
            int n = dungeon[0].length;
            int[][] dp = new int[m][n];
            dp[m - 1][n - 1] = 1;
            for (int i = m - 2; i >= 0; i--) {
                dp[i][n - 1] = Math.max(1, dp[i + 1][n - 1] - dungeon[i + 1][n - 1]);
            }
            for (int i = n - 2; i >= 0; i--) {
                dp[m - 1][i] = Math.max(1, dp[m - 1][i + 1] - dungeon[m - 1][i + 1]);
            }
            for (int i = m - 2; i >= 0; i--) {
                for (int j = n - 2; j >= 0; j--) {
                    dp[i][j] = Math.max(1,
                            Math.min(dp[i][j + 1] - dungeon[i][j + 1], dp[i + 1][j] - dungeon[i + 1][j])
                    );
                }
            }
            return Math.max(1, dp[0][0] - dungeon[0][0]);
        }
    }

}
