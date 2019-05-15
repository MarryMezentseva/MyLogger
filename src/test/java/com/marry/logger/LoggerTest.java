package com.marry.logger;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class LoggerTest {
    Logger myLog = Logger.getInstance();
//todo
    @AfterClass
    void testDelLogFiles() {
    }

    @Test
    void testInfo() {
        myLog.info("some information ");
        myLog.info("some information///// ");
        myLog.info("some information.,.,.,. ");
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
        myLog.info("here is RuntimeException: ", new RuntimeException());
        myLog.info(new Exception());
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
        myLog.warning("oh,no! at once... ", new Exception());
    }

    @Test
    public void testErrorThrowable() {
        myLog.error(new Exception());
    }

    @Test
    public void testErrorThrowableAndObject() {
        myLog.error("error", new Error());
        myLog.error("error2", new Error());
    }

    @Test
    public void testCreateFileName() {

        System.out.println(myLog.createFileName());
    }


}