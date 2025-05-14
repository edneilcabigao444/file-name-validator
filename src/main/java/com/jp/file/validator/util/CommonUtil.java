package com.jp.file.validator.util;

import com.jp.file.validator.constants.CommonConstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.jp.file.validator.constants.CommonConstant.TEST_PREFIX;
import static com.jp.file.validator.constants.CommonConstant.TWO_DIGIT_REGEX;

public class CommonUtil {


    private CommonUtil(){

    }

    public static boolean isPrefixValid(String fileName, String prefix) {
        if (!fileName.startsWith(TEST_PREFIX)) {
            printError(fileName, "Prefix for the file should be 'Test' found", prefix);
            return true;
        }
        return false;
    }



    public static String getExtension(String fileName) {
        int lastDot = fileName.lastIndexOf('.');
        return (lastDot != -1) ? fileName.substring(lastDot + 1) : "";
    }

    public static void printError(String fileName, String message, String detail) {
        System.out.printf("\nFile '%s' failed validation.\n%s '%s'.\n", fileName, message, detail);
    }
    public static void printError(String fileName, String message) {
        System.out.printf("\nFile '%s' failed validation.\n%s", fileName, message);
    }


    //checkSequentialDigits
    public static boolean hasSequentialDigits(String numberStr) {

        if (numberStr == null || !numberStr.matches(TWO_DIGIT_REGEX)) {
            return false;
        }

        int current = Character.getNumericValue(numberStr.charAt(0));
        int next = Character.getNumericValue(numberStr.charAt(1));

        return next == current + 1;
    }


    public static boolean isValidDate(String date) {
        if (!date.matches(CommonConstant.DATE_LENGTH_REGEX)) return false;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyy");
        simpleDateFormat.setLenient(false);
        try {
            simpleDateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}
