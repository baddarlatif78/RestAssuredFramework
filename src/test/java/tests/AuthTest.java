package tests;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utils.AuthUtils;
import utils.LogUtils;
import utils.TestDataUtils;

public class AuthTest extends BaseTest {

    private static final Logger logger =
            LogUtils.getLogger(AuthTest.class);

    @Test(priority = 1)
    public void createAuthToken() {

        logger.info("========== CREATE AUTH TOKEN ==========");

        String token = AuthUtils.generateToken();

        Assert.assertNotNull(
                token,
                "Authentication token was not generated");

        Assert.assertFalse(
                token.isEmpty(),
                "Authentication token is empty");

        // Store token for other tests
        TestDataUtils.setToken(token);

        logger.info("Authentication token generated successfully");
        logger.info("Authentication token stored successfully");
    }
}