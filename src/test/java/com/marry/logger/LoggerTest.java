package com.marry.logger;

import org.testng.annotations.Test;

public class LoggerTest {
    Logger myLog = Logger.getInstance();

    @Test
    void testInfo() {
        myLog.info("some information");
    }

    @Test
    void testWarning() {
        myLog.warning("this is a warning");
    }

    @Test
    void testError() {
        myLog.error("error!!!!!!!");
    }

    @Test
    void testInfoThrowable() {
        myLog.info(new RuntimeException());
    }

    @Test
    void testInfoThrowableAndObject() {
        myLog.info("error!", new Error());
    }

    @Test
    public void testWarningThrowable() {
        myLog.warning(new Exception());
    }

    @Test
    public void testWarningThrowableAndObject() {
        myLog.warning("this is RunTimeException", new RuntimeException());
    }

    @Test
    public void testErrorThrowable() {
        myLog.error(new Exception());
    }

    @Test
    public void testErrorThrowableAndObject() {
        myLog.error("error", new Throwable());
    }

    @Test
    public void testCreateFileName() {
        myLog.createFileName();
    }

    @Test
    public void testPrintInfoToFile(){
        myLog.printToFile(" this info is written to file \n");
        myLog.printToFile(" this info is written to file2 \n");
        myLog.printToFile(" this info is written to file3 \n");
    }
}