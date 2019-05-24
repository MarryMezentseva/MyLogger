package com.marry.logger;

import java.io.*;
import java.sql.Timestamp;

/**
 * This class logging different states of program according to its logging levels
 */
public class Logger {
    private final String LOG_FILE_NAME;
    private static final String WHITESPACE = " ";
    private static Logger instance;
    private Context context;

    private Logger() {
        this.context = Context.getInstance();
        this.LOG_FILE_NAME = context.getLogFileName();
    }

    /**
     * @return the instance of Logger
     */
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    protected static Timestamp getCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    private void printToConsole(String msg) {
        System.out.println(msg + printStackTrace());

    }

    private void printToConsole(String msg, Throwable t) {
        System.out.println(msg + printStackTrace());
        t.printStackTrace();
    }

    /**
     * Print  info-message to the console and to the file,
     * in accordance with the permission to turn on/off logging to the file
     *
     * @param msg
     */
    public void info(Object msg) {
        if (context.isInfoOn()) {
            String info = LoggingLevels.INFORMATION
                    .concat(WHITESPACE)
                    .concat(msg.toString());
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
            String info = LoggingLevels.INFORMATION
                    .concat(WHITESPACE);
            printToConsole(info, e);
            printToFile(info, e);
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
            String info = LoggingLevels.INFORMATION
                    .concat(WHITESPACE)
                    .concat(msg.toString());
            printToConsole(info, e);
            printToFile(info, e);
        }
    }

    /**
     * Print warning-message to the console and to the file
     *
     * @param msg
     */
    public void warning(Object msg) {
        if (context.isWarnOn()) {
            String warning = LoggingLevels.WARNING
                    .concat(WHITESPACE)
                    .concat(msg.toString());
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
            String warning = LoggingLevels.WARNING
                    .concat(WHITESPACE);
            printToConsole(warning, e);
            printToFile(warning, e);
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
            String warning = LoggingLevels.WARNING
                    .concat(WHITESPACE)
                    .concat(msg.toString());
            printToConsole(warning, e);
            printToFile(warning, e);
        }
    }

    /**
     * Print error-message to the console and to the file
     *
     * @param msg
     */
    public void error(Object msg) {
        if (context.isErrOn()) {
            String error = LoggingLevels.ERROR
                    .concat(WHITESPACE)
                    .concat(msg.toString());
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
            String error = LoggingLevels.ERROR
                    .concat(WHITESPACE);
            printToConsole(error, e);
            printToFile(error, e);
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
            String error = LoggingLevels.ERROR
                    .concat(WHITESPACE)
                    .concat(msg.toString());
            printToConsole(error, e);
            printToFile(error, e);
        }
    }

    protected void printToFile(String msg) {
        if (context.isNeedPrintToLogFile()) {
            try {
                FileWriter fw = new FileWriter(LOG_FILE_NAME, true);
                fw.write(msg + "\n");
                fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    protected void printToFile(String msg, Throwable e) {
        if (context.isNeedPrintToLogFile()) {
            try {
                FileWriter fw = new FileWriter(LOG_FILE_NAME, true);
                fw.write(msg + printStackTrace() + printEStackTrace() + "\n");
                fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private String printStackTrace() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        return " line:".concat(String.valueOf(stackTraceElements[4].getLineNumber()))
                .concat(WHITESPACE)
                .concat(stackTraceElements[4].getClassName())
                .concat(WHITESPACE)
                .concat("#")
                .concat(stackTraceElements[4].getMethodName())
                .concat("()");
    }


    private String printEStackTrace() {
        Throwable e = new Throwable();
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        return stringWriter.toString();
    }

    public Context getContext() {
        return context;
    }


}



