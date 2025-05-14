package com.jp.file.validator.service;

import com.jp.file.validator.util.CommonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileNameValidatorImplTest {

    @InjectMocks
    private FileNameValidatorImpl fileNameValidator;

    @BeforeEach
    void setUp() {
        fileNameValidator = new FileNameValidatorImpl();
    }

    @Test
    void testValidFileNameWithSequence() {
        try (MockedStatic<CommonUtil> mocked = mockStatic(CommonUtil.class)) {
            String fileName = "Test_A_14052024_01.csv";

            mocked.when(() -> CommonUtil.getExtension(fileName)).thenReturn("csv");
            mocked.when(() -> CommonUtil.isPrefixValid(fileName, "Test")).thenReturn(false);
            mocked.when(() -> CommonUtil.isValidDate("14052024")).thenReturn(true);
            mocked.when(() -> CommonUtil.hasSequentialDigits("01")).thenReturn(true);


            fileNameValidator.validateFileName(fileName);


            mocked.verify(() -> CommonUtil.printError(any(), any(), any()), never());
            mocked.verify(() -> CommonUtil.printError(any(), any()), never());
        }
    }

    @Test
    void testValidFileNameNoSequence() {
        try (MockedStatic<CommonUtil> mocked = mockStatic(CommonUtil.class)) {
            String fileName = "Test_A_14052024.csv";

            mocked.when(() -> CommonUtil.getExtension(fileName)).thenReturn("csv");
            mocked.when(() -> CommonUtil.isPrefixValid(fileName, "Test")).thenReturn(false);
            mocked.when(() -> CommonUtil.isValidDate("14052024")).thenReturn(true);

            fileNameValidator.validateFileName(fileName);

            mocked.verify(() -> CommonUtil.printError(any(), any(), any()), never());
            mocked.verify(() -> CommonUtil.printError(any(), any()), never());
        }
    }

    @Test
    void testInvalidExtension() {
        try (MockedStatic<CommonUtil> mocked = mockStatic(CommonUtil.class)) {
            String fileName = "Test_A_14052024_01.txt";

            mocked.when(() -> CommonUtil.getExtension(fileName)).thenReturn("txt");
            mocked.when(() -> CommonUtil.isPrefixValid(fileName, "Test")).thenReturn(false);

            fileNameValidator.validateFileName(fileName);

            mocked.verify(() -> CommonUtil.printError(eq(fileName), eq("Invalid File format. Expected 'csv' found"), eq("txt")));
        }
    }

    @Test
    void testInvalidPortfolioCode() {
        try (MockedStatic<CommonUtil> mocked = mockStatic(CommonUtil.class)) {
            String fileName = "Test_X_14052024_01.csv";

            mocked.when(() -> CommonUtil.getExtension(fileName)).thenReturn("csv");
            mocked.when(() -> CommonUtil.isPrefixValid(fileName, "Test")).thenReturn(false);
            mocked.when(() -> CommonUtil.isValidDate("14052024")).thenReturn(true);
            mocked.when(() -> CommonUtil.hasSequentialDigits("01")).thenReturn(true);

            fileNameValidator.validateFileName(fileName);

            mocked.verify(() -> CommonUtil.printError(eq(fileName), eq("PortfolioCode should be A/B/C found"), eq("X")));
        }
    }

    @Test
    void testInvalidDate() {
        try (MockedStatic<CommonUtil> mocked = mockStatic(CommonUtil.class)) {
            String fileName = "Test_A_99999999_01.csv";

            mocked.when(() -> CommonUtil.getExtension(fileName)).thenReturn("csv");
            mocked.when(() -> CommonUtil.isPrefixValid(fileName, "Test")).thenReturn(false);
            mocked.when(() -> CommonUtil.isValidDate("99999999")).thenReturn(false);

            fileNameValidator.validateFileName(fileName);

            mocked.verify(() -> CommonUtil.printError(eq(fileName), eq("Valuation Date is not a valid date format 'ddmmyyyy'")));
        }
    }

    @Test
    void testInvalidSequenceNumber() {
        try (MockedStatic<CommonUtil> mocked = mockStatic(CommonUtil.class)) {
            String fileName = "Test_A_14052024_99.csv";

            mocked.when(() -> CommonUtil.getExtension(fileName)).thenReturn("csv");
            mocked.when(() -> CommonUtil.isPrefixValid(fileName, "Test")).thenReturn(false);
            mocked.when(() -> CommonUtil.isValidDate("14052024")).thenReturn(true);
            mocked.when(() -> CommonUtil.hasSequentialDigits("99")).thenReturn(false);

            fileNameValidator.validateFileName(fileName);

            mocked.verify(() -> CommonUtil.printError(eq(fileName), eq("\nInvalid or non-sequential 2-digit number found"), eq("99")));
        }
    }

    @Test
    void testInvalidFormat() {
        try (MockedStatic<CommonUtil> mocked = mockStatic(CommonUtil.class)) {
            String fileName = "Test.txt";

            mocked.when(() -> CommonUtil.getExtension(fileName)).thenReturn("txt");
            mocked.when(() -> CommonUtil.isPrefixValid(fileName, "Test")).thenReturn(false);


            fileNameValidator.validateFileName(fileName);

            mocked.verify(() -> CommonUtil.printError(eq(fileName), eq("File format should be 'Test_<portfoliocode>_<ddmmyyyy>_<2digit-sequencenumber>.csv' \n")));
        }
    }

    @Test
    void testInvalidPrefix() {
        try (MockedStatic<CommonUtil> mocked = mockStatic(CommonUtil.class)) {
            String fileName = "Hello_A_14052024_89.txt";

            mocked.when(() -> CommonUtil.getExtension(fileName)).thenReturn("txt");
            mocked.when(() -> CommonUtil.isPrefixValid(fileName, "Hello")).thenReturn(false);
            mocked.when(() -> CommonUtil.isValidDate("14052024")).thenReturn(true);
            mocked.when(() -> CommonUtil.hasSequentialDigits("89")).thenReturn(true);

            fileNameValidator.validateFileName(fileName);

            mocked.verify(() -> CommonUtil.printError(eq(fileName), eq("Invalid File format. Expected 'csv' found"), eq("txt")));
        }
    }
}
