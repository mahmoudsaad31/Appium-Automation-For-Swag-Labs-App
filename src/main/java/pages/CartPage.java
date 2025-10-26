package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.report.LogsUtils;

public class CartPage extends BasePage {

    //locators
    private By infobar = AppiumBy.xpath("//android.widget.TextView[@text=\"YOUR CART\"]");
    private By continueShopping = AppiumBy.accessibilityId("test-CONTINUE SHOPPING");
    private By checkout = AppiumBy.accessibilityId("test-CHECKOUT");
    private By remove = AppiumBy.accessibilityId("test-REMOVE");
    private By redRemove = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Delete\"]/android.view.ViewGroup");

    //constructor
    public CartPage(AndroidDriver driver) {
        super(driver);
    }

    //actions


    public String getProductName(String product) {
        elementActions.scrollToText(product);
        By productname = AppiumBy.xpath("//android.widget.TextView[@text=\"" + product + "\"]");
        return elementActions.getData(productname);
    }

    public String getProductPrice(String price) {
        elementActions.scrollToText(price);
        By productprice = AppiumBy.xpath("//android.widget.TextView[@text=\"" + price + "\"]");
        return elementActions.getData(productprice);
    }


    public void swipToRemoveProduct() {
        elementActions.swipeToElement(remove, "left");
        elementActions.clickElement(redRemove);
        LogsUtils.info("product removed successfully");
    }

    public void clickCheckoutButton() {
        elementActions.scrollToText("CHECKOUT");
        elementActions.clickElement(checkout);
    }

    public void clickContinueShopping() {
        elementActions.scrollToText("CONTINUE");
        elementActions.clickElement(continueShopping);
    }

    public String gettext() {
        return elementActions.getData(infobar);
    }

    public String getpageSource() {
        return driver.getPageSource();
    }


    //validation

    @Step("assert product details")
    public void assertProductDetails(String productname, String productPrice) {
        String actualProductname = getProductName(productname);
        String actualProductPrice = getProductPrice(productPrice);

        validations.validateEquals(actualProductname, productname);
        validations.validateEquals(actualProductPrice, productPrice);
        LogsUtils.info("ProductDetails is successfully passed for " + productname);
    }

    @Step("assert Navigated To Cart Page")
    public void assertNavigatedToCartPage() {
        validations.validateEquals(gettext(), "YOUR CART");
    }

    @Step("assert Product Removed From Cart")
    public void assertProductRemovedFromCart(String productname) {
        validations.validateFalse(getpageSource().contains(productname));
        LogsUtils.info("assert" + productname + "RemovedFromCart successfully");
    }
}
