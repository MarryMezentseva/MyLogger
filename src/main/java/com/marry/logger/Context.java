package com.marry.logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Create the configuration of logger file from property file
 */
public class Context {
    private final String PROPERTY_PATH = "logger.properties";
    private String logFileName = "_log.log";
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

        logFileName = createFileNameSuffix().concat(property.getProperty("logging.file.path"));
        isInfoOn = Boolean.valueOf(property.getProperty("logging.level.info"));
        isWarnOn = Boolean.valueOf(property.getProperty("logging.level.warn"));
        isErrOn = Boolean.valueOf(property.getProperty("logging.level.err"));
        isNeedPrintToLogFile = Boolean.valueOf(property.getProperty("logging.file"));
    }

    /**
     * Creating file name suffix
     */
    protected String createFileNameSuffix() {
        final String SYMBOL1 = ":";
        final String SYMBOL2 = ".";
        final String REPLACEMENT = "_";
        final String WHITESPACE = " ";

        return Logger.getCurrentTime().toString()
                .replace(SYMBOL1, REPLACEMENT)
                .replace(SYMBOL2, REPLACEMENT)
                .replace(WHITESPACE, REPLACEMENT)
                .concat(WHITESPACE);
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

    public boolean isNeedPrintToLogFile() {
        return isNeedPrintToLogFile;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
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
