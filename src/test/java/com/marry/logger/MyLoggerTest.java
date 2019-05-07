package com.marry.logger;

import org.testng.annotations.Test;

public class MyLoggerTest {
    MyLogger myLog = MyLogger.getInstance();

    @Test
    void testLogInfo() {
        myLog.logInfo("some information");
    }

    @Test
    void testLogWarning() {
        myLog.logWarning("this is a warning");

    }

    @Test
    void testLogError() {
        myLog.logError(1547956);
    }

    @Test
    void testLogInfoException() {
        myLog.logInfo(new RuntimeException());
    }
    @Test
    void testLogInfoExceptionAndObg() {
        myLog.logInfo(2356.55, new Error());
    }

    @Test
    public void testLogWarning1() {
    }

    @Test
    public void testLogWarning2() {
    }

    @Test
    public void testLogWarning3() {
    }

    @Test
    public void testLogError1() {
    }

    @Test
    public void testLogError2() {
    }

    @Test
    public void testLogError3() {
    }
}