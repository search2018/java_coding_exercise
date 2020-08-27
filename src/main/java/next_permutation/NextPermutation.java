package next_permutation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NextPermutation {
    /*
        Next Permutation
        Leetcode #31
        https://leetcode.com/problems/next-permutation/
        Difficulty: Medium
     */
    public class Solution {
        public void nextPermutation(int[] num) {
            if (num == null || num.length == 0 || num.length == 1) return;
            int i = num.length - 2;
            while (i >= 0 && num[i] >= num[i + 1]) {
                i--;
            }
            if (i >= 0) {
                int j = i;
                while (j < num.length - 1 && num[j + 1] > num[i]) {
                    j++;
                }
                swap(num, i, j);
            }
            reverse(num, i + 1, num.length - 1);
        }

        public void reverse(int[] num, int left, int right) {
            while (left < right) {
                swap(num, left, right);
                left++;
                right--;
            }
        }

        private void swap(int[] num, int i, int j) {
            int temp = num[i];
            num[i] = num[j];
            num[j] = temp;
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new NextPermutation().new Solution();
            int[] start = new int[]{6, 8, 7, 4, 3, 2};
            int[] next = new int[]{7, 2, 3, 4, 6, 8};
            sol.nextPermutation(start);
            for (int i = 0; i < start.length; i++) {
                assertEquals(start[i], next[i]);
            }
        }
    }
}
