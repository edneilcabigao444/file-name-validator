package com.jp.file.validator.util;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CommonUtilTest {

    @Test
    void testIsPrefixValid_WhenPrefixIsInvalid_ShouldPrintError() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        boolean result = CommonUtil.isPrefixValid("WrongPrefix_ABC_01012025_01.csv", "WrongPrefix");

        assertTrue(result);
        assertTrue(outContent.toString().contains("Prefix for the file should be 'Test' found"));
    }

    @Test
    void testIsPrefixValid_WhenPrefixIsValid_ShouldReturnFalse() {
        boolean result = CommonUtil.isPrefixValid("Test_ABC_01012025_01.csv", "Test");
        assertFalse(result);
    }

    @Test
    void testGetExtension_WhenFileNameHasExtension() {
        String ext = CommonUtil.getExtension("file.csv");
        assertEquals("csv", ext);
    }

    @Test
    void testGetExtension_WhenFileNameHasNoExtension() {
        String ext = CommonUtil.getExtension("file");
        assertEquals("", ext);
    }

    @Test
    void testPrintError_WithDetails() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CommonUtil.printError("file.csv", "Invalid format", "csv");

        String output = outContent.toString();
        assertTrue(output.contains("file.csv"));
        assertTrue(output.contains("Invalid format"));
        assertTrue(output.contains("csv"));
    }

    @Test
    void testPrintError_WithoutDetails() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CommonUtil.printError("file.csv", "Missing sequence");

        String output = outContent.toString();
        assertTrue(output.contains("file.csv"));
        assertTrue(output.contains("Missing sequence"));
    }

    @Test
    void testHasSequentialDigits_Valid() {
        assertTrue(CommonUtil.hasSequentialDigits("12"));
        assertTrue(CommonUtil.hasSequentialDigits("34"));
    }

    @Test
    void testHasSequentialDigits_Invalid() {
        assertFalse(CommonUtil.hasSequentialDigits("13"));
        assertFalse(CommonUtil.hasSequentialDigits("99"));
        assertFalse(CommonUtil.hasSequentialDigits("21"));
    }

    @Test
    void testIsValidDate_Valid() {
        assertTrue(CommonUtil.isValidDate("14052025")); // ddMMyyyy
    }

    @Test
    void testIsValidDate_InvalidFormat() {
        assertFalse(CommonUtil.isValidDate("14-05-2025"));
        assertFalse(CommonUtil.isValidDate("20251405"));
    }

    @Test
    void testIsValidDate_InvalidValue() {
        assertFalse(CommonUtil.isValidDate("31022025")); // Invalid day for February
    }
}
