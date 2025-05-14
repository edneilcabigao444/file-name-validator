package com.jp.file.validator.service;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ReadFileImpl {

    public ReadFileImpl(){}


    public  void readFile(String fileName) {
        try (InputStream inputStream = ReadFileImpl.class.getClassLoader().getResourceAsStream(fileName)) {

            if (inputStream == null) {
                System.out.printf("\nError: Resource '%s' not found in the classpath.\n", fileName);
                return;
            }


            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                System.out.printf("\nFile content of '%s':\n", fileName);
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {

                System.out.printf("\nError reading file '%s': %s\n", fileName, e.getMessage());
            }
        } catch (IOException e) {

            System.out.printf("\nError opening file '%s': %s\n", fileName, e.getMessage());
        }
    }
}
