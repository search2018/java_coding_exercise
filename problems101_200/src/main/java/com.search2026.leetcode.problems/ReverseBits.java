package com.search2026.leetcode.problems;

import java.util.HashMap;
import java.util.Map;

public class ReverseBits {

    /*
        Reverse Bits
        Leetcode #190
        https://leetcode.com/problems/reverse-bits/
        Difficulty: Easy
     */
    public class Solution {
        public int reverseBits(int n) {
            int res = 0, i;
            for (i = 32; i > 0 && n != 0; i--) {
                res = (res << 1) + (n & 1);
                n = n >>> 1;
            }
            return res << i;
        }
    }

    /*
        Reverse Bits
        Leetcode #190
        https://leetcode.com/problems/reverse-bits/
        Difficulty: Easy
     */
    public class Solution_2 {
        public int reverseBits(int n) {
            int res = 0;
            for (int i = 0; i < 32; i++) {
                res += n & 1;
                n >>>= 1;   // CATCH: must do unsigned shift
                if (i < 31) // CATCH: for last digit, don't shift!
                    res <<= 1;
            }
            return res;
        }
    }

    /*
        Reverse Bits
        Leetcode #190
        https://leetcode.com/problems/reverse-bits/
        Difficulty: Easy
     */
    public class Solution_3 {
        private final Map<Byte, Integer> cache = new HashMap<Byte, Integer>();

        private int reverseByte(byte b) {
            Integer value = cache.get(b); // first look up from cache
            if (value != null)
                return value;
            value = 0;
            // reverse by bit
            for (int i = 0; i < 8; i++) {
                value += ((b >>> i) & 1);
                if (i < 7)
                    value <<= 1;
            }
            cache.put(b, value);
            return value;
        }

        public int reverseBits(int n) {
            byte[] bytes = new byte[4];
            for (int i = 0; i < 4; i++) // convert int into 4 bytes
                bytes[i] = (byte) ((n >>> 8 * i) & 0xFF);
            int res = 0;
            for (int i = 0; i < 4; i++) {
                res += reverseByte(bytes[i]); // reverse per byte
                if (i < 3)
                    res <<= 8;
            }
            return res;
        }
    }

}
