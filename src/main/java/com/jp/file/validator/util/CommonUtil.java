package com.jp.file.validator.util;

import com.jp.file.validator.constants.CommonConstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.jp.file.validator.constants.CommonConstant.TEST_PREFIX;
import static com.jp.file.validator.constants.CommonConstant.TWO_DIGIT_REGEX;

public class CommonUtil {


    private CommonUtil(){
    }

    /**
     *
     * @param fileName get the original filename
     * @param prefix extract the first set of the character if matches to the "Test" value
     * @return true if the condition is pass.
     */
    public static boolean isPrefixValid(String fileName, String prefix) {
        if (!fileName.startsWith(TEST_PREFIX)) {
            printError(fileName, CommonConstant.TEST_PREFIX_FILENAME, prefix);
            return true;
        }
        return false;
    }


    /**
     *
     * @param fileName get the original filename
     * @return extract the last 3 character.
     */

    public static String getExtension(String fileName) {
        int lastDot = fileName.lastIndexOf(CommonConstant.DELIMITER_PERIOD);
        return (lastDot != -1) ? fileName.substring(lastDot + 1) : "";
    }


    /**
     *
     * @param fileName get filename to display in error if they have error
     * @param message get the initial message of the error
     * @param detail extract what is the problem based on the filename
     */
    public static void printError(String fileName, String message, String detail) {
        System.out.printf(CommonConstant.FAILED_VALIDATION_ERROR, fileName, message, detail);
    }


    /**
     *
     * @param fileName get filename to display in error if they have error
     * @param message get the initial message of the error
     */
    public static void printError(String fileName, String message) {
        System.out.printf(CommonConstant.FAILED_VALIDATION, fileName, message);
    }


    /**
     *
     * @param numberString get the last set in the group if they enter 2 sequence digits
     * @return if null false and if not 2 digits false; true if the current is equal to next by adding 1;
     */
    public static boolean hasSequentialDigits(String numberString) {

        if (numberString == null || !numberString.matches(TWO_DIGIT_REGEX)) {
            return false;
        }

        int current = Character.getNumericValue(numberString.charAt(0));
        int next = Character.getNumericValue(numberString.charAt(1));

        return next == current + 1;
    }


    /**
     *
     * @param date get the string date and if they match proceeds to the validation and check the pattern.
     * @return true if they successfully match to the pattern and pass in SimpleDateFormat.
     */
    public static boolean isValidDate(String date) {
        if (!date.matches(CommonConstant.DATE_LENGTH_REGEX)) return false;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CommonConstant.DATA_FORMAT_PATTERN);
        simpleDateFormat.setLenient(false);
        try {
            simpleDateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}
