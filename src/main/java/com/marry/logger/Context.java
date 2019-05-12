package com.marry.logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Context {
    private final String propertyPath = "logger.properties";
    private String fileName;
    private String filePath;
    private String info;
    private String warn;
    private String err;
    private FileInputStream inputStream;

       public String getFileName() {
        return fileName;
    }

    public Context() {
        try {
            inputStream = new FileInputStream(propertyPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Properties property = new Properties();
        try {
            property.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        fileName = property.getProperty("file.name");
        filePath = property.getProperty("file.path");
        info = property.getProperty("logging.level.info");
        warn = property.getProperty("logging.level.warn");
        err = property.getProperty("logging.level.err");

    }
}
