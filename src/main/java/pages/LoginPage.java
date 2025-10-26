package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.data_Reader.PropertiesUtils;

public class LoginPage extends BasePage {

    // locators
    private By usernameField = AppiumBy.accessibilityId("test-Username");
    private By passwordField = AppiumBy.accessibilityId("test-Password");
    private By loginButton = AppiumBy.accessibilityId("test-LOGIN");
    private By biometry = AppiumBy.accessibilityId("test-biometry");
    private By errorMsg1 = AppiumBy.xpath("//android.widget.TextView[@text=\"Username and password do not match any user in this service.\"]");
    private By errorMsg2 = AppiumBy.xpath("//android.widget.TextView[@text=\"Username is required\"]");
    private By errorMsg3 = AppiumBy.xpath("//android.widget.TextView[@text=\"Password is required\"]");


    // constructor
    public LoginPage(AndroidDriver driver) {
        super(driver);

    }

    // actions


    @Step("Enter username: {0}")
    public void enterUserName(String username) {
        elementActions.sendData(usernameField, username);
    }

    @Step("Enter password: {0}")
    public void enterPassword(String password) {
        elementActions.sendData(passwordField, password);
    }

    @Step("Click login button")
    public void clickLoginButton() {
        elementActions.clickElement(loginButton);
    }

    @Step("login credentials")
    public void login(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        clickLoginButton();
    }

    @Step("Get error message")
    public String getErrorMSG(By locator) {
        return elementActions.getData(locator);
    }


    //validation


    @Step("Assert unsuccessful login")
    public void assertMessageAppearedForInvalidUsernameorpassword() {
        validations.validateContains(getErrorMSG(errorMsg1), PropertiesUtils.getPropertyValue("wrong_Pass_or_user"));
    }

    @Step("Assert unsuccessful login")
    public void assertMessageAppearedforrequiredusername() {
        validations.validateContains(getErrorMSG(errorMsg2), PropertiesUtils.getPropertyValue("usernamerequired"));
    }

    @Step("Assert unsuccessful login")
    public void assertMessageAppearedforrequiredpassword() {
        validations.validateContains(getErrorMSG(errorMsg3), PropertiesUtils.getPropertyValue("passwordrequired"));
    }

    @Step("Assert Logout Successfully")
    public void assertLogoutSuccessfully() {
        validations.validateEquals(elementActions.getData(usernameField), "Username");
    }

}
