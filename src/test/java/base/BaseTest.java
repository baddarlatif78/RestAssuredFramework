package base;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeSuite;

import config.ConfigReader;
import io.restassured.RestAssured;
import utils.LogUtils;

public class BaseTest {

    protected final Logger logger =
            LogUtils.getLogger(this.getClass());

    @BeforeSuite
    public void setup() {

        RestAssured.baseURI =
                ConfigReader.getProperty("baseURI");

        logger.info("======================================");
        logger.info("Rest Assured API Automation Started");
        logger.info("Environment: {}",
                ConfigReader.getProperty("environment"));
        logger.info("Base URI: {}",
                RestAssured.baseURI);
        logger.info("======================================");
    }
}