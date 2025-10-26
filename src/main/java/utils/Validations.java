package utils;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.testng.Assert;

public class Validations {
    private AndroidDriver driver;

    public Validations(AndroidDriver driver) {
        this.driver = driver;
    }

    @Step("Validate True")
    public void validateTrue(boolean condition, String message) {
        Assert.assertTrue(condition);
    }

    @Step("Validate False")
    public void validateFalse(boolean condition) {
        Assert.assertFalse(condition);
    }

    @Step("Validate Equals")
    public void validateEquals(String actual, String expected) {
        Assert.assertEquals(actual, expected);
    }

    @Step("Validate Not Equals")
    public void validateNotEquals(String actual, String expected) {
        Assert.assertNotEquals(actual, expected);
    }


    @Step("Validate Contains: {expected}")
    public void validateContains(String actual, String expected) {
        Assert.assertTrue(actual.contains(expected));
    }


}
