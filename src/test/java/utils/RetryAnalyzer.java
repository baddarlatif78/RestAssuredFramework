package utils;

import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final Logger logger =
            LogUtils.getLogger(RetryAnalyzer.class);

    private int retryCount = 0;

    private static final int MAX_RETRY_COUNT = 2;

    @Override
    public boolean retry(ITestResult result) {

        String testName =
                result.getMethod().getMethodName();

        if (retryCount < MAX_RETRY_COUNT) {

            retryCount++;

            logger.warn(
                    "RETRYING TEST: {} | Attempt {} of {}",
                    testName,
                    retryCount,
                    MAX_RETRY_COUNT
            );

            return true;
        }

        logger.error(
                "MAX RETRIES REACHED: {} | Total retries: {}",
                testName,
                MAX_RETRY_COUNT
        );

        return false;
    }
}