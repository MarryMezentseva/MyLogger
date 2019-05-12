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

    private Logger() {
        Context context = new Context();
        this.logFileName = context.getFileName();
        // this.logFileName = createFileName();
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

    /**
     * allows us to print actual date and time in ms
     *
     * @return
     */
    private Timestamp getCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * print  info-message to the console with identifier INFO of the logging level
     *
     * @param msg
     */
    public void info(Object msg) {
        String info = getCurrentTime() + LoggingLevels.INFORMATION + msg;
        System.out.println(info);
        printToFile(info);
    }

    /**
     * print stack trace to the console with identifier INFO of the logging level
     *
     * @param e
     */
    public void info(Throwable e) {
        String info = getCurrentTime() + LoggingLevels.INFORMATION + e;
        System.out.println(info);
        e.printStackTrace();
        printToFile(info);
    }

    /**
     * print  info-message  and stack trace to the console with identifier INFO of the logging level
     *
     * @param msg
     * @param e
     */
    public void info(Object msg, Throwable e) {
        String info = getCurrentTime() + LoggingLevels.INFORMATION + msg + e;
        System.out.println(info);
        e.printStackTrace();
        printToFile(info);
    }

    /**
     * print warning-message to the console with identifier WARNING of the logging level
     *
     * @param msg
     */
    public void warning(Object msg) {
        String warning = getCurrentTime() + LoggingLevels.WARNING + msg;
        System.out.println(warning);
        printToFile(warning);
    }

    /**
     * print stack trace to the console with identifier WARNING of the logging level
     *
     * @param e
     */
    public void warning(Throwable e) {
        String warning = getCurrentTime() + LoggingLevels.WARNING + e;
        System.out.println(warning);
        e.printStackTrace();
        printToFile(warning);
    }

    /**
     * print warning-message and stack trace to the console with identifier WARNING of the logging level
     *
     * @param msg
     * @param e
     */
    public void warning(Object msg, Throwable e) {
        String warning = getCurrentTime() + LoggingLevels.WARNING + msg + e;
        System.out.println(warning);
        e.printStackTrace();
        printToFile(warning);
    }

    /**
     * print error-message to the console with identifier ERROR of the logging level
     *
     * @param msg
     */
    public void error(Object msg) {
        String error =  getCurrentTime() + LoggingLevels.ERROR + msg;
        System.out.println(error);
        printToFile(error);
    }

    /**
     * print stack trace to the console with identifier ERROR of the logging level
     *
     * @param e
     */
    public void error(Throwable e) {
        String error =  getCurrentTime() + LoggingLevels.ERROR + e;
        System.out.println(error);
        e.printStackTrace();
        printToFile(error);
    }

    /**
     * print error-message and stack trace to the console with identifier ERROR of the logging level
     *
     * @param e
     * @param msg
     */
    public void error(Object msg, Throwable e) {
        String error = getCurrentTime() + LoggingLevels.ERROR + msg + e;
        System.out.println(error);
        e.printStackTrace();
        printToFile(error);
    }

    /**
     * creating file name
     */
    public String createFileName() {
        String s1 = getCurrentTime().toString().replace(".", "_");
        String s2 = s1.replace(":", "_");
        String fileName = s2.replace(" ", "_") + ".log";
        return fileName;
    }

    /**
     * print logging to the file
     * is used for configuration logger.properties
     */
    public void printToFile(String msg) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(logFileName, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (fw != null) {
                fw.write(msg + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (fw != null) {
                fw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

