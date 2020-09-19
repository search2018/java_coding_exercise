package com.search2026.leetcode.problems;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {

    /*
        Pascals Triangle
        Leetcode #118
        https://leetcode.com/problems/pascals-triangle/
        Difficulty: Easy
     */
    public class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> ans = new ArrayList<>();
            if (numRows == 0) {
                return ans;
            }
            List<Integer> lastRow = new ArrayList<>();
            lastRow.add(1);
            ans.add(lastRow);
            for (int r = 2; r <= numRows; r++) {
                ArrayList<Integer> row = new ArrayList<>();
                row.add(1);
                for (int j = 1; j <= r - 2; j++) {
                    row.add(lastRow.get(j - 1) + lastRow.get(j));
                }
                row.add(1);
                lastRow = row;
                ans.add(lastRow);
            }
            return ans;
        }
    }

    /*
        Pascals Triangle
        Leetcode #118
        https://leetcode.com/problems/pascals-triangle/
        Difficulty: Easy
     */
    public class Solution_2 {
        public List<Integer> getRow(int rowIndex) {
            List<Integer> row = new ArrayList<>();
            for (int r = 0; r <= rowIndex; r++) {
                for (int j = r - 1; j >= 1; j--) {
                    row.set(j, row.get(j - 1) + row.get(j));
                }
                row.add(1);
            }
            return row;
        }
    }

}
