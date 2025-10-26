package listeners;

import org.testng.*;
import utils.FilesUtils;
import utils.data_Reader.PropertiesUtils;
import utils.report.AllureUtils;
import utils.report.LogsUtils;
import utils.report.ScreenshotsUtils;

import java.io.File;


public class TestNGListeners implements ITestListener, IExecutionListener, IInvokedMethodListener {
    File allure_results = new File("test-outputs/allure-results");
    File LOGS_PATH = new File("test-outputs/Logs");
    File SCREENSHOTS_PATH = new File("test-outputs/screenshots");


    @Override
    public void onExecutionStart() {
        LogsUtils.info(" Test Execution Start :");
        PropertiesUtils.loadProperties();
        FilesUtils.cleanFiles(allure_results);
        FilesUtils.cleanFiles(SCREENSHOTS_PATH);
        FilesUtils.cleanFiles(LOGS_PATH);
    }

    @Override
    public void onExecutionFinish() {
        LogsUtils.info("Test Execution Finished");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogsUtils.info("Test passed :", result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogsUtils.info("Test Skipped :", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogsUtils.info("Test Failed :", result.getName());
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            switch (testResult.getStatus()) {
                case ITestResult.SUCCESS:
                    ScreenshotsUtils.takeScreenshot("Passed-" + testResult.getName());
                    break;
                case ITestResult.SKIP:
                    ScreenshotsUtils.takeScreenshot("Skipped-" + testResult.getName());
                    break;
                case ITestResult.FAILURE:
                    ScreenshotsUtils.takeScreenshot("Failed-" + testResult.getName());
                    break;
            }
            AllureUtils.attachLogsToAllure();

        }
    }


}