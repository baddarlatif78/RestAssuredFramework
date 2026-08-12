package utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    private ExtentManager() {
    }

    public static synchronized ExtentReports getExtentReports() {

        if (extent == null) {

            String reportPath =
                    System.getProperty("user.dir")
                    + File.separator
                    + "reports"
                    + File.separator
                    + "ExtentReport.html";

            File reportDirectory =
                    new File(
                        System.getProperty("user.dir")
                        + File.separator
                        + "reports");

            if (!reportDirectory.exists()) {
                reportDirectory.mkdirs();
            }

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(reportPath);

            sparkReporter.config()
                    .setDocumentTitle(
                            "Rest Assured Automation Report");

            sparkReporter.config()
                    .setReportName(
                            "Restful Booker API Automation");

            extent = new ExtentReports();

            extent.attachReporter(sparkReporter);

            extent.setSystemInfo(
                    "Project",
                    "Rest Assured Framework");

            extent.setSystemInfo(
                    "Environment",
                    "QA");

            extent.setSystemInfo(
                    "Framework",
                    "Rest Assured + TestNG");

            extent.setSystemInfo(
                    "Java",
                    System.getProperty("java.version"));

            extent.setSystemInfo(
                    "OS",
                    System.getProperty("os.name"));
        }

        return extent;
    }

    public static synchronized void flushReports() {

        if (extent != null) {
            extent.flush();
        }
    }
}