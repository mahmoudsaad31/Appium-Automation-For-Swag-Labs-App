package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ConfirmationPage extends BasePage {

    //locators
    private By confrimMeesage = AppiumBy.xpath("//android.widget.TextView[@text=\"THANK YOU FOR YOU ORDER\"]");
    private By backhome = AppiumBy.accessibilityId("test-BACK HOME");

    //constructor
    public ConfirmationPage(AndroidDriver driver) {
        super(driver);
    }

    //action

    public void clickBackHomeButton() {
        elementActions.clickElement(backhome);
    }

    public String getConfirmationMessage() {
        return elementActions.getData(confrimMeesage);
    }

    //validation

    @Step("assert Confirmation Message Appeared")
    public void assertConfirmationMessageAppeared() {
        validations.validateEquals(getConfirmationMessage(), "THANK YOU FOR YOU ORDER");
    }
}
