package com.jp.file.validator.service;


import java.util.regex.Pattern;

import static com.jp.file.validator.constants.CommonConstant.CSV_EXTENSION;
import static com.jp.file.validator.constants.CommonConstant.PORTFOLIO_CODE_REGEX;
import static com.jp.file.validator.util.CommonUtil.*;
import static com.jp.file.validator.util.CommonUtil.printError;

public class FileNameValidatorImpl {


    private final ReadFileImpl fileReader = new ReadFileImpl();
    public FileNameValidatorImpl(){

    }

    public void validateFileName(String fileName) {
        String extension = getExtension(fileName);

        // Remove the .csv extension
        String nameWithoutExtension = fileName.substring(0, fileName.length() - 4);
        String[] parts = nameWithoutExtension.split("_");
        String prefix = parts[0];


        if (isPrefixValid(fileName, prefix)) return;

        if (parts.length < 3 && !CSV_EXTENSION.equalsIgnoreCase(extension)) {
            printError(fileName,"File format should be 'Test_<portfoliocode>_<ddmmyyyy>_<2digit-sequencenumber>.csv' \n");
            return;
        }


        if (!CSV_EXTENSION.equalsIgnoreCase(extension)) {
            printError(fileName, "Invalid File format. Expected 'csv' found", extension);
            return;
        }


        String portfolioCode = parts[1];
        String date = parts[2];
        String sequence = parts.length == 4 ? parts[3] : "";


        if (!Pattern.matches(PORTFOLIO_CODE_REGEX, portfolioCode)) {
            printError(fileName, "PortfolioCode should be A/B/C found", portfolioCode);
            return;
        }

        if (!isValidDate(date)) {
            printError(fileName, "Valuation Date is not a valid date format 'ddmmyyyy'");
            return;
        }

        if (!sequence.isEmpty()) {
            if (sequence.length() == 2 && sequence.matches("\\d{2}") && hasSequentialDigits(sequence)) {
                System.out.printf("\nFile '%s' passed validation.", fileName);
                fileReader.readFile(fileName);
            } else {
                printError(fileName, "\nInvalid or non-sequential 2-digit number found", sequence);
            }
        } else if (nameWithoutExtension.endsWith("_")) {
            printError(fileName,"File format should be 'Test_<portfoliocode>_<ddmmyyyy>_<2digit-sequencenumber>.csv' \n");
        } else {
            System.out.printf("\nFile '%s' passed validation.", fileName);
            fileReader.readFile(fileName);
        }
    }

}
