package com.marry.logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *  Create the configuration of logger file from property file
 */
public class Context {
    private final String PROPERTY_PATH = "logger.properties";
    private String LOG_FILE_NAME = "_log.log";
    private static Context instance;
    private boolean isInfoOn;
    private boolean isWarnOn;
    private boolean isErrOn;
    private boolean isNeedPrintToLogFile;

    private Context() {
        init();
    }

    /**
     * @return the instance of Context
     */
    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }
        return instance;
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

        LOG_FILE_NAME = property.getProperty("logging.file.path");
        this.isInfoOn = Boolean.valueOf(property.getProperty("logging.level.info"));
        this.isWarnOn = Boolean.valueOf(property.getProperty("logging.level.warn"));
        this.isErrOn = Boolean.valueOf(property.getProperty("logging.level.err"));
        this.isNeedPrintToLogFile =  Boolean.valueOf(property.getProperty("logging.file"));
    }

    public String getLOG_FILE_NAME() {
        return LOG_FILE_NAME;
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

    public boolean isNeedPrintToLogFile() {
        return isNeedPrintToLogFile;
    }

    public void setLOG_FILE_NAME(String LOG_FILE_NAME) {
        this.LOG_FILE_NAME = LOG_FILE_NAME;
    }

    public void setInfoOn(boolean infoOn) {
        isInfoOn = infoOn;
    }

    public void setWarnOn(boolean warnOn) {
        isWarnOn = warnOn;
    }

    public void setErrOn(boolean errOn) {
        isErrOn = errOn;
    }

    public void setNeedPrintToLogFile(boolean needPrintToLogFile) {
        isNeedPrintToLogFile = needPrintToLogFile;
    }
}
