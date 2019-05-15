package com.marry.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * This class logging different states of program according to its logging levels
 */
public class Logger {

    private static Logger instance;
    private final String logFileName;
    private static Context context = Context.getInstance();
    private static final String SPACE = " ";


    private Logger() {
        this.logFileName = context.getLogFileName();
    }

    /**
     * Get the instance of Logger, which is created when it is accessed, not beforehand
     *
     * @return
     */
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    private Timestamp getCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    private void printToConsole(String msg) {
        System.out.println(msg);
    }

    private void printToConsole(String msg, Throwable t) {
        System.out.println(msg);
    }


    /**
     * Print  info-message to the console and to the file,
     * in accordance with the permission to turn on/off logging to the file
     *
     * @param msg
     */
    public void info(Object msg) {
        if (context.isInfoOn()) {
            String info = createFileName() + SPACE + LoggingLevels.INFORMATION + msg + SPACE;
            printToConsole(info);
            printToFile(info);
        }
    }

    /**
     * Print stack trace to the console and to the file
     *
     * @param e
     */
    public void info(Throwable e) {
        if (context.isInfoOn()) {
            String info = createFileName() + SPACE + LoggingLevels.INFORMATION + e;
            printToConsole(info, e);
            e.printStackTrace();
            printToFile(info);
        }
    }

    /**
     * Print  info-message  and stack trace to the console and to the file
     *
     * @param msg
     * @param e
     */
    public void info(Object msg, Throwable e) {
        if (context.isInfoOn()) {
            String info = createFileName() + SPACE + LoggingLevels.INFORMATION + msg + SPACE + e;
            printToConsole(info, e);
            e.printStackTrace();
            printToFile(info);
        }
    }

    /**
     * Print warning-message to the console and to the file
     *
     * @param msg
     */
    public void warning(Object msg) {
        if (context.isWarnOn()) {
            String warning = createFileName() + SPACE + LoggingLevels.WARNING + msg + SPACE;
            printToConsole(warning);
            printToFile(warning);
        }
    }

    /**
     * Print stack trace to the console and to the file
     *
     * @param e
     */
    public void warning(Throwable e) {
        if (context.isWarnOn()) {
            String warning = createFileName() + SPACE + LoggingLevels.WARNING + e;
            printToConsole(warning, e);
            e.printStackTrace();
            printToFile(warning);
        }
    }

    /**
     * Print warning-message and stack trace to the console and to the file
     *
     * @param msg
     * @param e
     */
    public void warning(Object msg, Throwable e) {
        if (context.isWarnOn()) {
            String warning = createFileName() + SPACE + LoggingLevels.WARNING + msg + SPACE + e;
            printToConsole(warning, e);
            e.printStackTrace();
            printToFile(warning);
        }
    }

    /**
     * Print error-message to the console and to the file
     *
     * @param msg
     */
    public void error(Object msg) {
        if (context.isErrOn()) {
            String error = createFileName() + SPACE + LoggingLevels.ERROR + msg;
            printToConsole(error);
            printToFile(error);
        }
    }

    /**
     * Print stack trace to the console and to the file
     *
     * @param e
     */
    public void error(Throwable e) {
        if (context.isErrOn()) {
            String error = createFileName() + SPACE + LoggingLevels.ERROR + e;
            printToConsole(error, e);
            e.printStackTrace();
            printToFile(error);
        }
    }

    /**
     * Print error-message and stack trace to the console and to the file
     *
     * @param e
     * @param msg
     */
    public void error(Object msg, Throwable e) {
        if (context.isErrOn()) {
            String error = createFileName() + LoggingLevels.ERROR + msg + SPACE + e;
            printToConsole(error, e);
            e.printStackTrace();
            printToFile(error);
        }
    }


    /**
     * Creating file name
     */
    protected String createFileName() {
        final String SYMBOL1 = ":";
        final String SYMBOL2 = ".";
        final String REPLACEMENT = "_";

        return getCurrentTime().toString().replace(SYMBOL1, REPLACEMENT)
                .replace(SYMBOL2, REPLACEMENT)
                .replace(SPACE, REPLACEMENT)
                + SPACE + context.getLogFileName();
    }

    /**
     * Print logging to the file
     * Is used logger.properties for configuration
     */

    private void printToFile(String msg) {
        if (context.isNeedToLogFile()) {
            try {
                FileWriter fw = new FileWriter(logFileName, true);
                fw.write(msg + "\n");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void printStackTraceToFile() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTrace : stackTraceElements) {
            String msg = stackTrace.getClassName() + SPACE
                    + stackTrace.getMethodName() + SPACE
                    + stackTrace.getLineNumber();
        }
    }
}



