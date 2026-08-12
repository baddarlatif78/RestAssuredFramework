package listeners;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

import utils.ExtentManager;
import utils.LogUtils;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {

	private static final Logger logger = LogUtils.getLogger(TestListener.class);

	private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	@Override
	public void onStart(ITestContext context) {

		logger.info("======================================");
		logger.info("TEST SUITE STARTED");
		logger.info("Suite Name: {}", context.getName());
		logger.info("======================================");
	}

	@Override
	public void onTestStart(ITestResult result) {

		String testName = result.getMethod().getMethodName();

		logger.info("TEST STARTED: {}", testName);

		/*
		 * Only create Extent test if this is the first execution.
		 *
		 * This prevents duplicate Extent tests during retries.
		 */
		if (extentTest.get() == null) {

			ExtentTest test = ExtentManager.getExtentReports().createTest(testName);

			extentTest.set(test);
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		String testName = result.getMethod().getMethodName();

		logger.info("TEST PASSED: {}", testName);

		if (getTest() != null) {
			getTest().pass("Test Passed Successfully");
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {

		String testName = result.getMethod().getMethodName();

		logger.error("TEST FAILED: {}", testName);

		String failureMessage = "Unknown failure";

		if (result.getThrowable() != null) {

			failureMessage = result.getThrowable().getMessage();

			logger.error("Failure Reason: {}", failureMessage);
		}

		if (getTest() != null) {

			getTest().fail("Test execution failed: " + testName);

			if (result.getThrowable() != null) {

				getTest().fail(result.getThrowable());
			}

			// Capture screenshot
			String screenshotPath = ScreenshotUtils.captureFailureScreenshot(testName, failureMessage);

			if (screenshotPath != null) {

				logger.info("Failure screenshot created: {}", screenshotPath);

				try {

					getTest().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");

					logger.info("Failure screenshot attached to Extent Report");

				} catch (Exception e) {

					logger.error("Unable to attach screenshot to Extent Report", e);
				}

			} else {

				logger.error("Failure screenshot could not be created");
			}
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		String testName = result.getMethod().getMethodName();

		logger.warn("TEST SKIPPED: {}", testName);

		if (getTest() != null) {
			getTest().skip("Test Skipped");
		}
	}

	@Override
	public void onFinish(ITestContext context) {

		logger.info("======================================");
		logger.info("TEST SUITE FINISHED");
		logger.info("======================================");

		ExtentManager.flushReports();

		extentTest.remove();
	}

	private ExtentTest getTest() {

		return extentTest.get();
	}
}