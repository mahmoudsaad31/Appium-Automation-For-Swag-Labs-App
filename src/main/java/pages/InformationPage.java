package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class InformationPage extends BasePage {

    //locators

    private By infobar = AppiumBy.xpath("//android.widget.TextView[@text=\"CHECKOUT: INFORMATION\"]");
    private By firstName = AppiumBy.accessibilityId("test-First Name");
    private By lastName = AppiumBy.accessibilityId("test-Last Name");
    private By postalCode = AppiumBy.accessibilityId("test-Zip/Postal Code");
    private By continueButton = AppiumBy.accessibilityId("test-CONTINUE");


    //consctructor
    public InformationPage(AndroidDriver driver) {
        super(driver);
    }

    //actions

    public void enterFirstName(String data) {
        elementActions.sendData(firstName, data);
    }

    public void enterLastName(String data) {
        elementActions.sendData(lastName, data);
    }

    public void enterPostalCode(String data) {
        elementActions.sendData(postalCode, data);
    }

    public void clickContinueButton() {
        elementActions.clickElement(continueButton);
    }

    public void fillInformation(String firstname, String lastname, String postalcode) {
        enterFirstName(firstname);
        enterLastName(lastname);
        enterPostalCode(postalcode);
    }

    public String gettext() {
        return elementActions.getData(infobar);
    }

    //validations
    @Step("assert Navigatied To Checkout Information Page")
    public void assertNavigatiedToCheckoutInformationPage() {
        validations.validateEquals(gettext(), "CHECKOUT: INFORMATION");
    }


}
