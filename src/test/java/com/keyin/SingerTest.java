
package com.keyin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SingerTest {

    @Test
    public void testGetAnnualSalary() {
        Singer singerUnderTest = new Singer(1, "Mariah", "Carey", 5000);
        Assertions.assertEquals(60000, singerUnderTest.getAnnualSalary());
    }

    @Test
    public void testSingersAreEqual() {
        Singer singerUnderTest1 = new Singer(1, "Lady", "Gaga", 6000);
        Singer singerUnderTest2 = new Singer(2, "Lady", "Gaga", 6000);
        Assertions.assertEquals(singerUnderTest1, singerUnderTest2);
    }

    @Test
    public void testRaiseSalary() {
        Singer singerUnderTest = new Singer(1, "Axl", "Rose", 4000);
        singerUnderTest.raiseSalary(25);
        Assertions.assertEquals(5000, singerUnderTest.getMonthlySalary());
    }
}