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

    public LoggerTest() {
    }

    @BeforeMethod
    public void getInstance() {
        this.log = Logger.getInstance();
    }

    @Test
    public void testInfo() throws IOException {
        log.info("some information ");
        log.info("some information///// ");
        log.info("some information.,.,.,. ");
        List lines = FileUtils.readLines(new File(log.getContext().getLogFileName()), "utf-8");
        assertTrue(lines.size() > 0);
        assertTrue(lines.get(1).toString().contains("INFO"));
    }

    @Test
    public void testWarning() {
        log.getContext().setNeedPrintToLogFile(false);
        log.warning("there we need not to print to file");
        assertFalse(new File(log.getContext().getLogFileName()).exists());
    }

    @Test
    public void testWarningOff() {
        log.getContext().setWarnOn(false);
        log.warning("this message are not printed anywhere");
        assertFalse(new File(log.getContext().getLogFileName()).exists());
       }

    @Test
    public void testError() throws IOException {
        log.error("error!!!!!!!");
        List lines = FileUtils.readLines(new File(log.getContext().getLogFileName()), "utf-8");
        assertTrue(lines.size() > 0);
        assertTrue(lines.get(0).toString().contains("ERROR"));
    }

    @Test
    public void testInfoThrowable() throws IOException {
        log.info("here is RuntimeException: ", new RuntimeException());
        log.info(new Exception());
        List lines = FileUtils.readLines(new File(log.getContext().getLogFileName()), "utf-8");
        assertTrue(lines.size() > 0);
        assertTrue(lines.get(0).toString().contains("INFO"));
    }

    @Test
    public void testInfoThrowableAndObject() throws IOException {
        log.info("error!", new Error());
        List lines = FileUtils.readLines(new File(log.getContext().getLogFileName()), "utf-8");
        assertTrue(lines.size() > 0);
        assertTrue(lines.get(0).toString().contains("INFO"));
    }

    @Test
    public void testWarningThrowable() throws IOException {
        log.getContext().setNeedPrintToLogFile(true);
        log.warning(new Exception());
        log.warning(new RuntimeException());
        log.warning(new RuntimeException());
        List lines = FileUtils.readLines(new File(log.getContext().getLogFileName()), "utf-8");
        assertTrue(lines.size() > 0);
        assertTrue(lines.get(0).toString().contains("WARNING"));
    }

    @Test
    public void testWarningThrowableAndObject() throws IOException {
        log.getContext().setNeedPrintToLogFile(true);
        log.warning("this is RunTimeException", new RuntimeException());
        log.warning("oh,no! at once... ", new Exception());
        List lines = FileUtils.readLines(new File(log.getContext().getLogFileName()), "utf-8");
        assertTrue(lines.size() > 0);
        assertTrue(lines.get(0).toString().contains("WARNING"));
    }

    @Test
    public void testErrorThrowable() throws IOException {
        log.error(new Error());
        List lines = FileUtils.readLines(new File(log.getContext().getLogFileName()), "utf-8");
        assertTrue(lines.size() > 0);
        assertTrue(lines.get(0).toString().contains("ERROR"));
    }

    @Test
    public void testErrorThrowableAndObject() throws IOException {
        log.error("this is error1_", new Error());
        log.error("this is error2_", new Error());
        List lines = FileUtils.readLines(new File(log.getContext().getLogFileName()), "utf-8");
        assertTrue(lines.size() > 0);
        assertTrue(lines.get(0).toString().contains("ERROR"));
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



