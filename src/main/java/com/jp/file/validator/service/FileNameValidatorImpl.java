package com.jp.file.validator.service;


import com.jp.file.validator.constants.CommonConstant;

import java.util.regex.Pattern;

import static com.jp.file.validator.constants.CommonConstant.CSV_EXTENSION;
import static com.jp.file.validator.constants.CommonConstant.PORTFOLIO_CODES;
import static com.jp.file.validator.constants.CommonConstant.PORTFOLIO_CODE_REGEX;
import static com.jp.file.validator.constants.CommonConstant.TWO_DIGITS_REGEX;
import static com.jp.file.validator.util.CommonUtil.getExtension;
import static com.jp.file.validator.util.CommonUtil.hasSequentialDigits;
import static com.jp.file.validator.util.CommonUtil.isPrefixValid;
import static com.jp.file.validator.util.CommonUtil.isValidDate;
import static com.jp.file.validator.util.CommonUtil.printError;

public class FileNameValidatorImpl {


    private final ReadFileImpl fileReader = new ReadFileImpl();

    public FileNameValidatorImpl(){
    }

    /**
     *
     * @param fileName get the file name and after the multiple validation it will present the status if is success;
     *                 isPrefixValid is the checking of the first 4 character in the filename if is match to 'Test' value;
     *                 checking the length of the split value in the filename and checking if the extension name is .csv;
     *                 checking of the portfolio code if matches in A,B,C value;
     *                 checking the date is valid or not, if not it will automatically throw display error to the user.
     *                 checking the optional value of 2 digit sequence.
     */
    public void validateFileName(String fileName) {
        /**
         * validation for getting the extensionName;
         */
        String extension = getExtension(fileName);

        String nameWithoutExtension = fileName.substring(0, fileName.length() - 4);
        String[] parts = nameWithoutExtension.split(CommonConstant.DELIMITER);
        String prefix = parts[0];


        if (isPrefixValid(fileName, prefix)) return;

        if (parts.length < 3 && !CSV_EXTENSION.equalsIgnoreCase(extension)) {
            printError(fileName, CommonConstant.FILE_FORMAT);
            return;
        }


        if (!CSV_EXTENSION.equalsIgnoreCase(extension)) {
            printError(fileName, CommonConstant.INVALID_FILE_FORMAT_EXPECTED_CSV_FOUND, extension);
            return;
        }


        String portfolioCode = parts[1];
        String date = parts[2];
        String sequence = parts.length == 4 ? parts[3] : "";


        if (!Pattern.matches(PORTFOLIO_CODE_REGEX, portfolioCode)) {
            printError(fileName, PORTFOLIO_CODES, portfolioCode);
            return;
        }

        if (!isValidDate(date)) {
            printError(fileName, CommonConstant.VALID_DATE_FORMAT_DDMMYYYY);
            return;
        }

        if (!sequence.isEmpty()) {
            if (sequence.length() == 2 && sequence.matches(TWO_DIGITS_REGEX) && hasSequentialDigits(sequence)) {
                System.out.printf(CommonConstant.PASSED_VALIDATION, fileName);
                fileReader.readFile(fileName);
            } else {
                printError(fileName, CommonConstant.DIGIT_NUMBER_FOUND, sequence);
            }
        } else if (nameWithoutExtension.endsWith(CommonConstant.DELIMITER)) {
            printError(fileName, CommonConstant.FILE_FORMAT);
        } else {
            System.out.printf(CommonConstant.PASSED_VALIDATION, fileName);
            fileReader.readFile(fileName);
        }
    }

}
