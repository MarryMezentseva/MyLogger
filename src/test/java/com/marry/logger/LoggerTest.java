package com.marry.logger;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.*;

public class LoggerTest {
    private Logger log;

    private List getLinesFromFile() {
        List lines = null;
        try {
            lines = FileUtils.readLines(new File(log.getContext().getLogFileName()), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public LoggerTest() {
    }

    @BeforeMethod
    public void getInstance() {
        this.log = Logger.getInstance();
    }

    @Test
    public void testInfo() {
        log.getContext().setInfoOn(true);
        log.getContext().setWarnOn(true);
        log.getContext().setErrOn(true);
        log.getContext().setNeedPrintToLogFile(true);
        log.info("some information ");
        log.info("some information///// ");
        log.info("some information.,.,.,. ");
        assertTrue(getLinesFromFile().size() > 0);
        assertTrue(getLinesFromFile().get(1).toString().contains("INFO"));
    }

    @Test
    public void testWarning() {
        log.getContext().setNeedPrintToLogFile(false);
        log.getContext().setInfoOn(true);
        log.getContext().setWarnOn(true);
        log.getContext().setErrOn(true);
        log.warning("there we need not to print to file");
        assertFalse(new File(log.getContext().getLogFileName()).exists());
    }

    @Test
    public void testWarningOff() {
        log.getContext().setWarnOn(false);
        log.getContext().setInfoOn(true);
        log.getContext().setErrOn(true);
        log.getContext().setNeedPrintToLogFile(true);
        log.warning("this message are not printed anywhere");
        assertFalse(new File(log.getContext().getLogFileName()).exists());
    }

    @Test
    public void testError() {
        log.getContext().setInfoOn(true);
        log.getContext().setWarnOn(true);
        log.getContext().setErrOn(true);
        log.getContext().setNeedPrintToLogFile(true);
        log.error("error!!!!!!!");
        assertTrue(getLinesFromFile().size() > 0);
        assertTrue(getLinesFromFile().get(0).toString().contains("ERROR"));
    }

    @Test
    public void testInfoThrowable() {
        log.getContext().setInfoOn(true);
        log.getContext().setWarnOn(true);
        log.getContext().setErrOn(true);
        log.getContext().setNeedPrintToLogFile(true);
        log.info("here is RuntimeException: ", new RuntimeException());
        log.info(new Exception());
        assertTrue(getLinesFromFile().size() > 0);
        assertTrue(getLinesFromFile().get(0).toString().contains("INFO"));
    }

    @Test
    public void testInfoThrowableAndObject() {
        log.getContext().setInfoOn(true);
        log.getContext().setWarnOn(true);
        log.getContext().setErrOn(true);
        log.getContext().setNeedPrintToLogFile(true);
        log.info("error!", new Error());
        assertTrue(getLinesFromFile().size() > 0);
        assertTrue(getLinesFromFile().get(0).toString().contains("INFO"));
    }

    @Test
    public void testWarningThrowable() {
        log.getContext().setInfoOn(true);
        log.getContext().setWarnOn(true);
        log.getContext().setErrOn(true);
        log.getContext().setNeedPrintToLogFile(true);
        log.warning(new Exception());
        log.warning(new RuntimeException());
        log.warning(new RuntimeException());
        assertTrue(getLinesFromFile().size() > 0);
        assertTrue(getLinesFromFile().get(0).toString().contains("WARNING"));
    }

    @Test
    public void testWarningThrowableAndObject() {
        log.getContext().setInfoOn(true);
        log.getContext().setWarnOn(true);
        log.getContext().setErrOn(true);
        log.getContext().setNeedPrintToLogFile(true);
        log.warning("this is RunTimeException", new RuntimeException());
        log.warning("oh,no! at once... ", new Exception());
        assertTrue(getLinesFromFile().size() > 0);
        assertTrue(getLinesFromFile().get(0).toString().contains("WARNING"));
    }

    @Test
    public void testErrorThrowable() {
        log.getContext().setInfoOn(true);
        log.getContext().setWarnOn(true);
        log.getContext().setErrOn(true);
        log.getContext().setNeedPrintToLogFile(true);
        log.error(new Error());
        assertTrue(getLinesFromFile().size() > 0);
        assertTrue(getLinesFromFile().get(0).toString().contains("ERROR"));
    }

    @Test
    public void testErrorThrowableAndObject() {
        log.getContext().setInfoOn(true);
        log.getContext().setWarnOn(true);
        log.getContext().setErrOn(true);
        log.getContext().setNeedPrintToLogFile(true);
        log.error("this is error1_", new Error());
        log.error("this is error2_", new Error());
        assertTrue(getLinesFromFile().size() > 0);
        assertTrue(getLinesFromFile().get(0).toString().contains("ERROR"));
    }

    @Test
    public void testCreateFileNameSuffix() {
        assertNotNull(log.getContext().createFileNameSuffix());
        assertTrue(log.getContext().createFileNameSuffix().contains("2019"));
    }

    @AfterMethod
    public void after() {
        File file = new File(log.getContext().getLogFileName());
        if (file.exists()) {
            file.delete();
        }
    }
}



