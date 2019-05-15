package com.marry.logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *  Create the configuration of logger file  from property file
 */
public class Context {
    private static Context contextInstance;
    private final String PROPERTY_PATH = "logger.properties";
    private String logFileName = "default.log";
    private boolean isInfoOn;
    private boolean isWarnOn;
    private boolean isErrOn;
    private boolean isNeedToLogFile;

    private Context() {
        init();
    }

    /**
     * @return the instance of Context
     */
    public static Context getInstance() {
        if (contextInstance == null) {
            contextInstance = new Context();
        }
        return contextInstance;
    }

    private void init() {
        Properties property = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream(PROPERTY_PATH);
            property.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.logFileName = property.getProperty("logging.file.path");
        this.isInfoOn = Boolean.valueOf(property.getProperty("logging.level.info"));
        this.isWarnOn = Boolean.valueOf(property.getProperty("logging.level.warn"));
        this.isErrOn = Boolean.valueOf(property.getProperty("logging.level.err"));
        this.isNeedToLogFile =  Boolean.valueOf(property.getProperty("logging.file"));
    }

    public String getLogFileName() {
        return logFileName;
    }

    public boolean isInfoOn() {
        return isInfoOn;
    }

    public boolean isWarnOn() {
        return isWarnOn;
    }

    public boolean isErrOn() {
        return isErrOn;
    }

    public boolean isNeedToLogFile() {
        return isNeedToLogFile;
    }
}
