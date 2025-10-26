package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.report.LogsUtils;

public class HomePage extends BasePage {
    // locators

    private By test_menu = AppiumBy.accessibilityId("test-Menu");
    private By test_Cart = AppiumBy.accessibilityId("test-Cart");
    private By topBar = AppiumBy.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]");
    private By addproduct1 = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]");
    private By addproduct2 = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"]");

    private By addproductdrag1 = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"test-Drag Handle\"])[1]");
    private By addproductdrag2 = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Drag Handle\"]");

    private By removeproduct1 = AppiumBy.xpath("(//android.widget.TextView[@text=\"REMOVE\"])[1]");
    private By removeproduct2 = AppiumBy.xpath("//android.widget.TextView[@text=\"REMOVE\"]");
    private By removeproduct3 = AppiumBy.xpath("(//android.widget.TextView[@text=\"REMOVE\"])[2]");

    //constructor
    public HomePage(AndroidDriver driver) {
        super(driver);
    }

    //actions

    public void clickOnMenuIcon() {
        elementActions.clickElement(test_menu);
    }

    public void clickOnCartIcon() {
        elementActions.clickElement(test_Cart);
    }

    public void clickOnSpecificProduct(String productname) {
        elementActions.scrollToText(productname);
        By product = AppiumBy.xpath("//android.widget.TextView[@content-desc=\"test-Item title\" and @text='" + productname + "']");
        elementActions.clickElement(product);
    }

    public void addProductToCart(String productname) {
        elementActions.scrollToText(productname);
        elementActions.clickElement(addproduct1);
    }

    public void addAnnotherProductToCart(String productprice) {
        elementActions.scrollToText(productprice);
        elementActions.clickElement(addproduct2);
    }

    public void addProductToCartByDragAndDrop(String productname) {
        elementActions.scrollToText(productname);
        elementActions.dragAndDrop(addproductdrag1, 510, 440);
    }

    public void addAnotherProductToCartByDragAndDrop(String productname) {
        elementActions.scrollToText(productname);
        elementActions.dragAndDrop(addproductdrag2, 510, 440);
    }

    public void removeProductfromCart(String productname) {
        elementActions.scrollToText(productname);
        elementActions.clickElement(removeproduct1);
    }

    public void removeAnnotherProductfromCart(String productname) {
        elementActions.scrollToText(productname);
        elementActions.clickElement(removeproduct2);
    }

    public void clickLogout() {
        waits.sleepForCertainTime(1000);
        elementActions.clickByCoordinates(180, 1455);
    }

    public void pressBackButton() {
        elementActions.pressBackBtn();
    }

    public String gettext(By locator) {
        return elementActions.getData(locator);
    }

    // validations
    @Step("Assert successful login")
    public void assertSuccessfulLogin() {
        validations.validateEquals(gettext(topBar), "PRODUCTS");
    }

    @Step("assert Product Added To Cart Successfully")
    public void assertProductAddedToCartSuccessfully() {
        validations.validateEquals(gettext(removeproduct2), "REMOVE");
        LogsUtils.info("product Removed From Cart successfully");

    }

    @Step("assert Product Added To Cart Successfully")
    public void assertAnotherProductAddedToCartSuccessfully() {
        validations.validateEquals(gettext(removeproduct3), "REMOVE");
        LogsUtils.info("another product Removed From Cart successfully");

    }


}
