package maximal_rectangle;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import static org.junit.Assert.assertEquals;

public class MaximalRectangle {
    /*
        Maximal Rectangle - Stack
        Leetcode #85
        https://leetcode.com/problems/maximal-rectangle/
        Difficulty: Hard
     */
    public class Solution {
        public int maximalRectangle(char[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
            int[] height = new int[matrix[0].length];
            int maxArea = 0;

            for (int row = 0; row < matrix.length; row++) {
                Deque<Integer> stack = new ArrayDeque<>();
                for (int i = 0; i < matrix[0].length; i++) {
                    if (matrix[row][i] != '0') height[i] = height[i] + 1;
                    else height[i] = 0;
                }
                for (int col = 0; col < matrix[0].length; col++) {
                    if (stack.isEmpty() || height[col] > height[stack.peek()]) {
                        stack.push(col);
                    } else {
                        int temp = stack.pop();
                        int width = (stack.isEmpty()) ? col : col - stack.peek() - 1;
                        maxArea = Math.max(maxArea, width * height[temp]);
                        col--;
                    }
                }
                while (stack.size() != 0) {
                    int temp = stack.pop();
                    int width = (stack.size() == 0) ? height.length : height.length - stack.peek() - 1;
                    maxArea = Math.max(maxArea, width * height[temp]);
                }
            }

            return maxArea;
        }
    }

    /*
        Maximal Rectangle - Dynamic Programming
        https://leetcode.com/problems/maximal-rectangle/
        Difficulty: Hard
    */
    public class Solution_2 {
        public int maximalRectangle(char[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
            int m = matrix.length;
            int n = matrix[0].length;
            int[] right = new int[n];
            int[] left = new int[n];
            int[] height = new int[n];
            Arrays.fill(right, n-1);

            int maxArea = 0;
            for (int i = 0; i < m; i++) {
                int curLeft = 0, curRight = n - 1;
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '1') height[j]++;
                    else height[j] = 0;
                }
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '1') left[j] = Math.max(left[j], curLeft);
                    else {
                        left[j] = 0;
                        curLeft = j + 1;
                    }
                }
                for (int j = n - 1; j >= 0; j--) {
                    if (matrix[i][j] == '1') right[j] = Math.min(right[j], curRight);
                    else {
                        right[j] = n - 1;
                        curRight = j - 1;
                    }
                }
                for (int j = 0; j < n; j++)
                    maxArea = Math.max(maxArea, (right[j] - left[j] + 1) * height[j]);
            }
            return maxArea;
        }
    }

    /*
        Maximal Rectangle - Dynamic Programming
        https://leetcode.com/problems/maximal-rectangle/
        Difficulty: Hard
    */
    public class Solution_3 {
        public int maximalRectangle(char[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
            int m = matrix.length;
            int n = matrix[0].length;
            int[][] width = new int[m][n];

            for (int i = 0; i < m; ++i) {
                width[i][n - 1] = ('0' == matrix[i][n - 1]) ? 0 : 1;
                for (int j = n - 2; j >= 0; j--) {
                    width[i][j] = ('0' == matrix[i][j]) ? 0 : 1 + width[i][j + 1];
                }
            }

            int maxArea = 0;
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    int w = width[i][j];
                    for (int k = i; k < m && w > 0; ++k) {
                        w = Math.min(w, width[k][j]);
                        maxArea = Math.max(maxArea, (k - i + 1) * w);
                    }
                }
            }
            return maxArea;
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new MaximalRectangle().new Solution();
            char[][] grid = new char[][]{
                    new char[]{'1'}
            };
            assertEquals(1, sol.maximalRectangle(grid));
            grid = new char[][]{
                    new char[]{'0', '1'}
            };
            assertEquals(1, sol.maximalRectangle(grid));
            grid = new char[][]{
                    new char[]{'1', '0', '1', '0', '0'},
                    new char[]{'1', '0', '1', '1', '1'},
                    new char[]{'1', '1', '1', '1', '1'},
                    new char[]{'1', '0', '0', '1', '0'}
            };
            assertEquals(6, sol.maximalRectangle(grid));
        }

        @Test
        public void test2() {
            Solution_2 sol = new MaximalRectangle().new Solution_2();
            char[][] grid = new char[][]{
                    new char[]{'1'}
            };
            assertEquals(1, sol.maximalRectangle(grid));
            grid = new char[][]{
                    new char[]{'0', '1'}
            };
            assertEquals(1, sol.maximalRectangle(grid));
            grid = new char[][]{
                    new char[]{'1', '0', '1', '0', '0'},
                    new char[]{'1', '0', '1', '1', '1'},
                    new char[]{'1', '1', '1', '1', '1'},
                    new char[]{'1', '0', '0', '1', '0'}
            };
            assertEquals(6, sol.maximalRectangle(grid));
        }

        @Test
        public void test3() {
            Solution_3 sol = new MaximalRectangle().new Solution_3();
            char[][] grid = new char[][]{
                    new char[]{'1'}
            };
            assertEquals(1, sol.maximalRectangle(grid));
            grid = new char[][]{
                    new char[]{'0', '1'}
            };
            assertEquals(1, sol.maximalRectangle(grid));
            grid = new char[][]{
                    new char[]{'1', '0', '1', '0', '0'},
                    new char[]{'1', '0', '1', '1', '1'},
                    new char[]{'1', '1', '1', '1', '1'},
                    new char[]{'1', '0', '0', '1', '0'}
            };
            assertEquals(6, sol.maximalRectangle(grid));
        }
    }
}
