package com.test.TestProject.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateMaxTest {
    @Test
    public void testFindMax() {
        ClaculateMax obj = new ClaculateMax();
        assertEquals(2, obj.findMax(new int[] { 1,-1,0,2}));
        assertEquals(-1, obj.findMax(new int[] { -12, -1, -3, -4, -2 }),"CASE-2");
    }
}
