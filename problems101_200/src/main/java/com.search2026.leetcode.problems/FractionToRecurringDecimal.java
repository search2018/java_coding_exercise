package com.search2026.leetcode.problems;

import java.util.HashMap;

public class FractionToRecurringDecimal {

    /*
        Fraction to Recurring Decimal
        Leetcode #166
        https://leetcode.com/problems/fraction-to-recurring-decimal/
        Difficulty: Medium
     */
    public class Solution {
        public String fractionToDecimal(int numerator, int denominator) {
            if (numerator == 0)
                return "0";
            if (denominator == 0)
                return "";

            String result = "";

            // is result is negative
            if ((numerator < 0) ^ (denominator < 0)) {
                result += "-";
            }

            // convert int to long
            long num = numerator, den = denominator;
            num = Math.abs(num);
            den = Math.abs(den);

            // quotient
            long resLong = num / den;
            result += String.valueOf(resLong);

            // if remainder is 0, return result
            long remainder = (num % den) * 10;
            if (remainder == 0)
                return result;

            // right-hand side of decimal point
            HashMap<Long, Integer> map = new HashMap<>();
            result += ".";
            while (remainder != 0) {
                // if digits repeat
                if (map.containsKey(remainder)) {
                    int beg = map.get(remainder);
                    String part1 = result.substring(0, beg);
                    String part2 = result.substring(beg);
                    result = part1 + "(" + part2 + ")";
                    return result;
                }

                // continue
                map.put(remainder, result.length());
                resLong = remainder / den;
                result += String.valueOf(resLong);
                remainder = (remainder % den) * 10;
            }

            return result;
        }
    }

}
