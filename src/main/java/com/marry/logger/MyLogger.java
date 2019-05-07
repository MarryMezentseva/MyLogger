package com.marry.logger;

        import java.sql.Timestamp;

/**
 * This class logging different states of program according to its logging levels
 */
public class MyLogger {

    private static MyLogger instance;

    private MyLogger() {
    }

    /**
     * Get the instance of MyLogger, which is created when it is accessed, not beforehand
     *
     * @return
     */

    public static MyLogger getInstance() {
        if (instance == null) {
            instance = new MyLogger();
        }
        return instance;
    }

    private Timestamp getCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * print  info-message to the console with identifier INFO of the logging level
     *
     * @param msg
     */
    public void logInfo(Object msg) {
        System.out.println(getCurrentTime() + LoggingLevels.INFORMATION + msg);

    }

    /**
     * print stack trace to the console with identifier INFO of the logging level
     *
     * @param e
     */
    public void logInfo(Throwable e) {
        System.out.println(getCurrentTime() + LoggingLevels.INFORMATION);
        e.printStackTrace();
    }

    /**
     * print  info-message  and stack trace to the console with identifier INFO of the logging level
     *
     * @param msg
     * @param e
     */
    public void logInfo(Object msg, Throwable e) {
        System.out.println(getCurrentTime() + LoggingLevels.INFORMATION + msg);
        e.printStackTrace();
    }

    /**
     * print warning-message to the console with identifier WARNING of the logging level
     *
     * @param msg
     */
    public void logWarning(Object msg) {
        System.out.println(getCurrentTime() + LoggingLevels.WARNING + msg);
    }

    /**
     * print stack trace to the console with identifier WARNING of the logging level
     *
     * @param e
     */
    public void logWarning(Throwable e) {
        System.out.println(getCurrentTime() + LoggingLevels.WARNING);
        e.printStackTrace();
    }

    /**
     * print warning-message and stack trace to the console with identifier WARNING of the logging level
     *
     * @param msg
     * @param e
     */
    public void logWarning(Object msg, Throwable e) {
        System.out.println(getCurrentTime() + LoggingLevels.WARNING + msg);
        e.printStackTrace();
    }

    /**
     * print error-message to the console with identifier ERROR of the logging level
     *
     * @param msg
     */
    public void logError(Object msg) {
        System.out.println(getCurrentTime() + LoggingLevels.ERROR + msg);
    }

    /**
     * print stack trace to the console with identifier ERROR of the logging level
     *
     * @param e
     */
    public void logError(Throwable e) {
        System.out.println(getCurrentTime() + LoggingLevels.ERROR);
        e.printStackTrace();
    }

    /**
     * print error-message and stack trace to the console with identifier ERROR of the logging level
     *
     * @param e
     * @param msg
     */
    public void logError(Object msg, Throwable e) {
        System.out.println(getCurrentTime() + LoggingLevels.ERROR + msg);
        e.printStackTrace();
    }
}
