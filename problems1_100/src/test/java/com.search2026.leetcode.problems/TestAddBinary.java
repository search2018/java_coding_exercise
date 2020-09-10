package com.search2026.leetcode.problems;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAddBinary {

    @Test
    public void test1() {
        AddBinary.Solution sol = new AddBinary().new Solution();
        assertEquals("0", sol.addBinary("0", "0"));
        assertEquals("100", sol.addBinary("11", "1"));
        assertEquals("10110", sol.addBinary("11", "10011"));
    }

}
