package tests;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.LogUtils;
import utils.RetryAnalyzer;

public class RetryTest {

    private static final Logger logger =
            LogUtils.getLogger(RetryTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void retryTest() {

        logger.info("========== RETRY TEST ==========");

        logger.info("Intentionally failing test");

        Assert.fail("Intentional failure to test Retry Analyzer");
    }
}