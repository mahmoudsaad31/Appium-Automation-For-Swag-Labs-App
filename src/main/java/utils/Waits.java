package utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.report.LogsUtils;

import java.time.Duration;

public class Waits {

    private AndroidDriver driver;


    //constructor
    public Waits(AndroidDriver driver) {
        this.driver = driver;
    }


    public void waitforElementToBeVisible(By locator) {
        LogsUtils.info("Waiting for element to be visible: ", locator.toString());
        new WebDriverWait(driver, Duration.ofSeconds(15)).
                until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public void waitforElementToBeClickable(By locator) {
        LogsUtils.info("Waiting for element to be clickable: ", locator.toString());
        waitforElementToBeVisible(locator);
        new WebDriverWait(driver, Duration.ofSeconds(15)).
                until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForTextToBe(By locator, String value) {
        LogsUtils.info("Waiting element: ", locator.toString(), "to change text to be", value);
        new WebDriverWait(driver, Duration.ofSeconds(15)).
                until(ExpectedConditions.textToBe(locator, value));
    }

    public void sleepForCertainTime(int timeOutInMilliSeconds) {
        try {
            Thread.sleep(timeOutInMilliSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

