package com.jp.file.validator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ReadFileImplTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private ReadFileImpl reader;

    @Mock
    private InputStream mockInputStream;

    @Mock
    private BufferedReader mockBufferedReader;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));

        reader = new ReadFileImpl();
    }

    @Test
    void testReadFile_FileExists_PrintsContents() throws IOException {
        String fileName = "Test.txt";

        reader.readFile(fileName);

        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("File content of"), "Expected file content header to be printed");
        assertTrue(output.contains("Hello, world!"), "Expected 'Hello, world!' to be printed");
    }

    @Test
    void testReadFile_FileNotFound_PrintsError() {
        String fileName = "nonexistentfile.txt";

        reader.readFile(fileName);

        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Error: Resource 'nonexistentfile.txt' not found in the classpath."),
                "Expected error message not found in output.");
    }

    @Test
    void testReadFile_IOException_PrintsError() throws IOException {
        String fileName = "testfile.txt";


        reader.readFile(fileName);

        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Error: Resource 'testfile.txt' not found in the classpath."));
    }
}
