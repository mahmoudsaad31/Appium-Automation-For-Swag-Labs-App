package utils.report;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class ScreenshotsUtils {

    public static final String SCREENSHOTS_PATH = "test-outputs/screenshots/";
    public static AndroidDriver driver;


    public ScreenshotsUtils(AndroidDriver driver) {
        this.driver = driver;

    }

    public static void takeScreenshot(String screenshotName) {
        //code
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenshotFile = new File(SCREENSHOTS_PATH + screenshotName + ".png");
            FileUtils.copyFile(screenshot, screenshotFile);
            AllureUtils.attachScreenshotsToAllure(screenshotName, screenshotFile.getPath());
        } catch (Exception e) {
            LogsUtils.error("Failed to take screenshot: " + e.getMessage());
        }

    }
}
