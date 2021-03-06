package com.search2026.leetcode.problems;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegularExpressionMatchingTests {

    @Test
    public void testSolution() {
        RegularExpressionMatching.Solution sol = new RegularExpressionMatching().new Solution();
        assertFalse(sol.isMatch("aa", "a"));
        assertTrue(sol.isMatch("aa", "aa"));
        assertFalse(sol.isMatch("aaa", "aa"));
        assertTrue(sol.isMatch("aa", "a*"));
        assertTrue(sol.isMatch("aa", ".*"));
        assertTrue(sol.isMatch("ab", ".*"));
        assertTrue(sol.isMatch("aab", "c*a*b"));
        assertTrue(true);
    }

    @Test
    public void testSolution2() {
        RegularExpressionMatching.Solution_2 sol = new RegularExpressionMatching().new Solution_2();
        assertFalse(sol.isMatch("aa", "a"));
        assertTrue(sol.isMatch("aa", "aa"));
        assertFalse(sol.isMatch("aaa", "aa"));
        assertTrue(sol.isMatch("aa", "a*"));
        assertTrue(sol.isMatch("aa", ".*"));
        assertTrue(sol.isMatch("ab", ".*"));
        assertTrue(sol.isMatch("aab", "c*a*b"));
        assertTrue(true);
    }

    @Test
    public void testSolution3() {
        RegularExpressionMatching.Solution_3 sol = new RegularExpressionMatching().new Solution_3();
        assertFalse(sol.isMatch("aa", "a"));
        assertTrue(sol.isMatch("aa", "aa"));
        assertFalse(sol.isMatch("aaa", "aa"));
        assertTrue(sol.isMatch("aa", "a*"));
        assertTrue(sol.isMatch("aa", ".*"));
        assertTrue(sol.isMatch("ab", ".*"));
        assertTrue(sol.isMatch("aab", "c*a*b"));
        assertTrue(true);
    }

}
