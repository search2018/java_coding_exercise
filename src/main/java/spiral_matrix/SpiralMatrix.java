package spiral_matrix;

import java.util.*;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpiralMatrix {
    /*
        Spiral Matrix
        Leetcode #54
        https://leetcode.com/problems/spiral-matrix/
        Difficulty: Medium
     */
    public class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> spiralList = new ArrayList<>();
            if (matrix == null || matrix.length == 0 ||
                    matrix[0] == null || matrix[0].length == 0) return spiralList;

            int rowStart = 0;
            int rowEnd = matrix.length - 1;
            int colStart = 0;
            int colEnd = matrix[0].length - 1;

            while (rowStart <= rowEnd && colStart <= colEnd) {
                for (int i = colStart; i <= colEnd; i++) spiralList.add(matrix[rowStart][i]);
                if (++rowStart > rowEnd) break;

                for (int i = rowStart; i <= rowEnd; i++) spiralList.add(matrix[i][colEnd]);
                if (--colEnd < colStart) break;

                for (int i = colEnd; i >= colStart; i--) spiralList.add(matrix[rowEnd][i]);
                if (--rowEnd < rowStart) break;

                for (int i = rowEnd; i >= rowStart; i--) spiralList.add(matrix[i][colStart]);
                if (colStart++ > colEnd) break;
            }

            return spiralList;
        }
    }

    /*
        Spiral Matrix
        Leetcode #54
        https://leetcode.com/problems/spiral-matrix/
        Difficulty: Medium
     */
    public class Solution_2 {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> spiralList = new ArrayList<>();
            if (matrix == null || matrix.length == 0 ||
                    matrix[0] == null || matrix[0].length == 0) return spiralList;

            int rowStart = 0;
            int rowEnd = matrix.length - 1;
            int colStart = 0;
            int colEnd = matrix[0].length - 1;

            while (rowStart <= rowEnd && colStart <= colEnd) {
                // Traverse Right
                for (int j = colStart; j <= colEnd; j++) {
                    spiralList.add(matrix[rowStart][j]);
                }
                rowStart++;

                // Traverse Down
                for (int i = rowStart; i <= rowEnd; i++) {
                    spiralList.add(matrix[i][colEnd]);
                }
                colEnd--;

                if (rowStart <= rowEnd) {
                    // Traverse Left
                    for (int j = colEnd; j >= colStart; j--) {
                        spiralList.add(matrix[rowEnd][j]);
                    }
                }
                rowEnd--;

                if (colStart <= colEnd) {
                    // Traver Up
                    for (int i = rowEnd; i >= rowStart; i--) {
                        spiralList.add(matrix[i][colStart]);
                    }
                }
                colStart++;
            }

            return spiralList;
        }
    }

    /*
        Spiral Matrix II
        Leetcode #59
        https://leetcode.com/problems/spiral-matrix-ii/
        Difficulty: Medium
     */
    public class Solution_3 {
        public int[][] generateMatrix(int n) {
            if (n < 0) return null;
            int[][] matrix = new int[n][n];
            if (n == 0) return matrix;

            int num = 1;
            int rowStart = 0, rowEnd = n - 1;
            int colStart = 0, colEnd = n - 1;

            while (rowStart <= rowEnd && colStart <= colEnd) {
                for (int j = colStart; j <= colEnd; j++) {
                    matrix[rowStart][j] = num++;
                }
                if (++rowStart > rowEnd) break;

                for (int i = rowStart; i <= rowEnd; i++) {
                    matrix[i][colEnd] = num++;
                }
                if (--colEnd < colStart) break;

                for (int j = colEnd; j >= colStart; j--) {
                    matrix[rowEnd][j] = num++;
                }
                if (--rowEnd < rowStart) break;

                for (int i = rowEnd; i >= rowStart; i--) {
                    matrix[i][colStart] = num++;
                }
                if (++colStart > colEnd) break;
            }

            return matrix;
        }
    }

    /*
        Spiral Matrix II
        Leetcode #59
        https://leetcode.com/problems/spiral-matrix-ii/
        Difficulty: Medium
     */
    public class Solution_4 {
        public int[][] generateMatrix(int n) {
            if (n < 0) return null;
            int[][] matrix = new int[n][n];
            if (n == 1) return matrix;

            int rowStart = 0, rowEnd = n - 1;
            int colStart = 0, colEnd = n - 1;
            int num = 1;

            while (rowStart <= rowEnd && colStart <= colEnd) {
                for (int j = colStart; j <= colEnd; j++) {
                    matrix[rowStart][j] = num++;
                }
                rowStart++;

                for (int i = rowStart; i <= rowEnd; i++) {
                    matrix[i][colEnd] = num++;
                }
                colEnd--;

                if (rowStart <= rowEnd) {
                    for (int j = colEnd; j >= colStart; j--) {
                        matrix[rowEnd][j] = num++;
                    }
                    rowEnd--;
                }

                if (colStart <= colEnd) {
                    for (int i = rowEnd; i >= rowStart; i--) {
                        matrix[i][colStart] = num++;
                    }
                    colStart++;
                }
            }

            return matrix;
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new SpiralMatrix().new Solution();
            int[][] matrix = {
                    {1, 2, 3},
                    {4, 5, 6},
                    {7, 8, 9}
            };
            List<Integer> printed = sol.spiralOrder(matrix);
            List<Integer> expected = new ArrayList<>();
            expected.add(1);
            expected.add(2);
            expected.add(3);
            expected.add(6);
            expected.add(9);
            expected.add(8);
            expected.add(7);
            expected.add(4);
            expected.add(5);
            assertEquals(printed, expected);
        }

        @Test
        public void test2() {
            Solution_2 sol = new SpiralMatrix().new Solution_2();
            int[][] matrix = {
                    {1, 2, 3},
                    {4, 5, 6},
                    {7, 8, 9}
            };
            List<Integer> printed = sol.spiralOrder(matrix);
            List<Integer> expected = new ArrayList<>();
            expected.add(1);
            expected.add(2);
            expected.add(3);
            expected.add(6);
            expected.add(9);
            expected.add(8);
            expected.add(7);
            expected.add(4);
            expected.add(5);
            assertEquals(printed, expected);
        }

        @Test
        public void test3() {
            Solution_3 sol = new SpiralMatrix().new Solution_3();
            int[][] matrix = {
                    {1, 2, 3},
                    {8, 9, 4},
                    {7, 6, 5}
            };
            int[][] printed = sol.generateMatrix(3);
            assertEquals(3, printed.length);
            assertEquals(3, printed[0].length);
            for (int i = 0; i < 3; i++) {
                assertArrayEquals(matrix[i], printed[i]);
            }
        }

        @Test
        public void test4() {
            Solution_4 sol = new SpiralMatrix().new Solution_4();
            int[][] matrix = {
                    {1, 2, 3},
                    {8, 9, 4},
                    {7, 6, 5}
            };
            int[][] printed = sol.generateMatrix(3);
            assertEquals(3, printed.length);
            assertEquals(3, printed[0].length);
            for (int i = 0; i < 3; i++) {
                assertArrayEquals(matrix[i], printed[i]);
            }
        }
    }
}
