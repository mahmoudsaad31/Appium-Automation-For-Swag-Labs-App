package utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.Select;
import utils.report.LogsUtils;

public class ElementActions {

    private AndroidDriver driver;
    private Waits waits;

    //constructor
    public ElementActions(AndroidDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
    }

    //send keys
    @Step("Sending data: {data} to the element")
    public void sendData(By locator, String data) {
        waits.waitforElementToBeVisible(locator);
        findElement(locator).clear();
        findElement(locator).sendKeys(data);

        LogsUtils.info("Data entered: ", data, " in the field: ", locator.toString());

    }

    //gettext
    @Step("Getting text from the element")
    public String getData(By locator) {
        waits.waitforElementToBeVisible(locator);
        LogsUtils.info("Getting text from the element: ", locator.toString(), " Text: ", findElement(locator).getText());
        return findElement(locator).getText();
    }

    //find element
    public WebElement findElement(By locator) {
        LogsUtils.info("Finding element: ", locator.toString());
        return driver.findElement(locator);
    }


    @Step("Clicking on the element")
    public void clickElement(By locator) {
        waits.waitforElementToBeClickable(locator);
        findElement(locator).click();
        LogsUtils.info("Clicked on the element: ", locator.toString());
    }


    // get text from input field
    public String getTextFromInputField(By locator) {
        waits.waitforElementToBeVisible(locator);
//        scrollToElement(locator);
        LogsUtils.info("Getting text from the input field: ", locator.toString(), " Text: ", findElement(locator).getDomAttribute("text"));
        return findElement(locator).getDomAttribute("text");
    }


    public void selectFromDropDownList(By locator, String text) {
        waits.waitforElementToBeVisible(locator);
        new Select(findElement(locator)).selectByVisibleText(text);
    }

    //scroll to element
    @Step("Scrolling to the element")
    public void scrollToText(String partialtext) {
        LogsUtils.info("scrolling to text ", partialtext);
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().textContains(\"" + partialtext + "\"))"));

    }

    public void longPressAction(By locator) {
        waits.waitforElementToBeVisible(locator);
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) driver.findElement(locator)).getId()
        ));
    }

    public void swipeToElement(By locator, String direction) {
        waits.waitforElementToBeVisible(locator);
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) driver.findElement(locator)).getId(),
                "direction", direction,
                "percent", 0.4
        ));
    }

    public void pressBackBtn() {
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    public void dragAndDrop(By locator, int endX, int endY) {
        waits.waitforElementToBeVisible(locator);
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) driver.findElement(locator)).getId(),
                "endX", endX,
                "endY", endY
        ));
    }

    public void clickByCoordinates(int X, int Y) {
        ((JavascriptExecutor) driver).executeScript("mobile: clickGesture", ImmutableMap.of(
                "x", X,
                "y", Y
        ));
    }


}