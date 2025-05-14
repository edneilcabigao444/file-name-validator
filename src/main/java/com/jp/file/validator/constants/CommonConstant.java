package com.jp.file.validator.constants;


public class CommonConstant {


    /**
     * This is all the constant value that never change;
     */
    public static final String TEST_PREFIX = "Test";
    public static final String CSV_EXTENSION = "csv";
    public static final String PORTFOLIO_CODE_REGEX = "^[ABC]$";
    public static final String TWO_DIGIT_REGEX = "\\d{2}";
    public static final String DATE_LENGTH_REGEX = "\\d{8}";
    public static final String FILE_FORMAT = "File format should be 'Test_<portfoliocode>_<ddmmyyyy>_<2digit-sequencenumber>.csv' \n";
    public static final String INVALID_FILE_FORMAT_EXPECTED_CSV_FOUND = "Invalid File format. Expected 'csv' found";
    public static final String VALID_DATE_FORMAT_DDMMYYYY = "Valuation Date is not a valid date format 'ddmmyyyy'";
    public static final String PORTFOLIO_CODES = "PortfolioCode should be A/B/C found";
    public static final String TWO_DIGITS_REGEX = "\\d{2}";
    public static final String PASSED_VALIDATION = "\nFile '%s' passed validation.";
    public static final String DIGIT_NUMBER_FOUND = "\nInvalid or non-sequential 2-digit number found";
    public static final String DELIMITER = "_";
    public static final String ERROR_RESOURCE_S_NOT_FOUND_IN_THE_CLASSPATH = "\nError: Resource '%s' not found in the classpath.\n";
    public static final String FILE_CONTENT = "\nFile content of '%s':\n";
    public static final String ERROR_READING = "\nError reading file '%s': %s\n";
    public static final String ERROR_OPENING_FILE = "\nError opening file '%s': %s\n";
    public static final String TEST_PREFIX_FILENAME = "Prefix for the file should be 'Test' found";
    public static final char DELIMITER_PERIOD = '.';
    public static final String FAILED_VALIDATION_ERROR = "\nFile '%s' failed validation.\n%s '%s'.\n";
    public static final String FAILED_VALIDATION = "\nFile '%s' failed validation.\n%s";
    public static final String DATA_FORMAT_PATTERN = "ddMMyyyy";

    private CommonConstant(){
    }

}
