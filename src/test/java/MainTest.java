

import com.jp.file.validator.service.FileNameValidatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private FileNameValidatorImpl fileNameValidator;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        fileNameValidator = new FileNameValidatorImpl();
    }

    @Test
    void testValidateMultipleFileNames() {
        String[] testFileNames = {
                "Hello_A_07121987.csv",       // Invalid prefix
                "Test_A_07121987.csv",        // Valid without sequence
                "Test_E_07121987.csv",        // Invalid portfolio code
                "Test_A_31122000_12.csv",     // Valid with sequence
                "Test_A_07121987.txt",        // Invalid extension
                "Test.txt",                   // Invalid format
                "Testing001.pdf"              // Invalid prefix and extension
        };

        for (String fileName : testFileNames) {
            fileNameValidator.validateFileName(fileName);
        }

        String output = outContent.toString();

        assertTrue(output.contains("Prefix for the file should be 'Test' found"), "Prefix check failed");
        assertTrue(output.contains("File 'Test_A_07121987.csv' passed validation."), "Valid file with no sequence");
        assertTrue(output.contains("PortfolioCode should be A/B/C"), "Invalid portfolio code check failed");
        assertTrue(output.contains("File 'Test_A_31122000_12.csv' passed validation."), "Valid file with sequence");
        assertTrue(output.contains("Invalid File format. Expected 'csv' found"), "Extension check failed");
        assertTrue(output.contains("File format should be 'Test_<portfoliocode>_<ddmmyyyy>_<2digit-sequencenumber>.csv'"), "Format check failed");
    }
}
