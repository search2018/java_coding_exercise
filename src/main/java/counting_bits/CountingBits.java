package counting_bits;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountingBits {
    /*
        Counting Bits
        Leetcode #338
        https://leetcode.com/problems/counting-bits/
        Difficulty: Medium
     */
    public class Solution {
        public int[] countBits(int num) {
            int[] f = new int[num + 1];
            for (int i = 1; i <= num; i++) f[i] = f[i >> 1] + (i & 1);
            return f;
        }
    }

    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new CountingBits().new Solution();
            int[] res = sol.countBits(5);
            int[] expected = {0,1,1,2,1,2};
            assertArrayEquals(expected, res);
        }
    }
}

