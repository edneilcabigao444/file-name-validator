package com.jp.file.validator.service;




import com.jp.file.validator.constants.CommonConstant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ReadFileImpl {

    public ReadFileImpl(){}


    /**
     *
     * @param fileName reading the filename in the resource folder and if the filename is not
     *                 exist it will return error to the user; if exist but no value inside still will throw an error;
     *                 lastly it will iterate all the list of the csv file if all the validation is successfully pass.
     *
     */
    public  void readFile(String fileName) {
        try (InputStream inputStream = ReadFileImpl.class.getClassLoader().getResourceAsStream(fileName)) {

            if (inputStream == null) {
                System.out.printf(CommonConstant.ERROR_RESOURCE_S_NOT_FOUND_IN_THE_CLASSPATH, fileName);
                return;
            }


            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                System.out.printf(CommonConstant.FILE_CONTENT, fileName);
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {

                System.out.printf(CommonConstant.ERROR_READING, fileName, e.getMessage());
            }
        } catch (IOException e) {

            System.out.printf(CommonConstant.ERROR_OPENING_FILE, fileName, e.getMessage());
        }
    }
}
