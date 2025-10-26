package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class OverviewPage extends BasePage {

    //locators
    private By infobar = AppiumBy.xpath("//android.widget.TextView[@text=\"CHECKOUT: OVERVIEW\"]");
    private By paymentInfo = AppiumBy.xpath("//android.widget.TextView[@text=\"SauceCard #31337\"]");
    private By shippingInfo = AppiumBy.xpath("//android.widget.TextView[@text=\"FREE PONY EXPRESS DELIVERY!\"]");
    private By finish = AppiumBy.accessibilityId("test-FINISH");

    //constructor
    public OverviewPage(AndroidDriver driver) {
        super(driver);
    }

    //actions

    public void clickFinish() {
        elementActions.scrollToText("FINISH");
        elementActions.clickElement(finish);
    }

    public String gettext(By locator) {
        return elementActions.getData(locator);
    }

    //validation

    @Step("assert Navigated To Overview Page")
    public void asserNavigatedToOverviewPage() {
        validations.validateEquals(gettext(infobar), "CHECKOUT: OVERVIEW");
    }

    @Step("assert Payment information")
    public void assertPaymentinformatio() {
        elementActions.scrollToText("Payment Information");
        validations.validateEquals(gettext(paymentInfo), "SauceCard #31337");
    }

    @Step("assert Shipping information")
    public void assertShippinginformatio() {
        validations.validateEquals(gettext(shippingInfo), "FREE PONY EXPRESS DELIVERY!");
    }
}
