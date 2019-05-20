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
    public void testInfo() {
        log.info("some information ");
        log.info("some information///// ");
        log.info("some information.,.,.,. ");
    }

    @Test
    public void testWarning() {
        log.getContext().setNeedPrintToLogFile(false);
        log.warning("this is a warning");
        assertFalse(log.getContext().getLOG_FILE_NAME().contains("warning"));
    }

    @Test
    public void testError() {
        log.error("error!!!!!!!");

    }

    @Test
    public void testInfoThrowable() {
        log.info("here is RuntimeException: ", new RuntimeException());
        log.info(new Exception());
    }

    @Test
    public void testInfoThrowableAndObject() {
        log.info("error!", new Error());
    }

    @Test
    public void testWarningThrowable() {
        log.warning(new Exception());
    }

    @Test
    public void testWarningThrowableAndObject() {
        log.warning("this is RunTimeException", new RuntimeException());
        log.warning("oh,no! at once... ", new Exception());
    }

    @Test
    public void testErrorThrowable() {
        log.error(new Exception());
    }

    @Test
    public void testErrorThrowableAndObject() {
        log.error("this is error1_", new Error());
        log.error("this is error2_", new RuntimeException());
    }

    @Test
    public void testCreateFileName() {
        assertNotNull(log.createFileName());
        assertTrue(log.createFileName().contains("masha"));
    }

    @AfterMethod
    public void deleteFiles() throws IOException {
        File file = new File("masha.log");
        List lines = FileUtils.readLines(file, "utf-8");
        assertNotNull(lines);
        assertTrue(file.delete());
    }
}



