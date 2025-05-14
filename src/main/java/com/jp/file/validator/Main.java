package com.jp.file.validator;

import com.jp.file.validator.service.FileNameValidatorImpl;

public class Main {

    public static void main(String[] args)  {

        /**
         * directoryFileNames
         * This is all the files that will retrieve in the resource folder.
         */

        final FileNameValidatorImpl fileNameValidator = new FileNameValidatorImpl();

        String[] directoryFileNames = {
                "Hello_A_07121987.csv",
                "Test_A_07121987.csv",
                "Test_E_07121987.csv",
                "Test_A_31122000_12.csv",
                "Test_E_07121987.csv",
                "Hello_A_07121987.csv",
                "Test_A_07121987.txt",
                "Test.txt",
                "Test_B_07121987_.csv",
                "Testing001.pdf"
        };


        /**
         * This foreach will loop all the directoryFileNames
         * and check each name if they pass the validation.
         */

        for (String fileName : directoryFileNames) {
            fileNameValidator.validateFileName(fileName);
        }

    }

}