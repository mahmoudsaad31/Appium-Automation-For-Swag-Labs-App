import org.testng.annotations.Test;
import utils.report.LogsUtils;

public class E2ETest extends BaseTest {

    @Test
    public void testSuccessfullLoginWithValidCredentials() {
        LogsUtils.info("start of method testSuccessfullLogin");
        loginPage.login(getTestData("username"), getTestData("password"));
        homePage.assertSuccessfulLogin();
    }

    @Test(dependsOnMethods = "testSuccessfullLoginWithValidCredentials")
    public void testAddingProductsToCart() {
        homePage.addProductToCart(getProductData("product1.price"));
        homePage.assertProductAddedToCartSuccessfully();
        homePage.addAnnotherProductToCart(getProductData("product2.price"));
        homePage.assertAnotherProductAddedToCartSuccessfully();
        homePage.addProductToCartByDragAndDrop(getProductData("product3.price"));
        homePage.assertProductAddedToCartSuccessfully();
    }

    @Test(dependsOnMethods = "testAddingProductsToCart")
    public void checkoutProducts() {
        homePage.clickOnCartIcon();
        cartPage.assertNavigatedToCartPage();
        cartPage.swipToRemoveProduct();
        cartPage.assertProductRemovedFromCart(getProductData("product1.name"));
        cartPage.assertProductDetails(getProductData("product2.name"), getProductData("product2.price"));
        cartPage.assertProductDetails(getProductData("product3.name"), getProductData("product3.price"));
    }

    @Test(dependsOnMethods = "checkoutProducts")
    public void fillInformationForm() {
        cartPage.clickCheckoutButton();
        informationPage.assertNavigatiedToCheckoutInformationPage();
        informationPage.fillInformation(getTestData("firstname"), getTestData("lastname"), getTestData("postalcode"));
    }

    @Test(dependsOnMethods = "fillInformationForm")
    public void finishCheckout() {
        informationPage.clickContinueButton();
        overviewPage.asserNavigatedToOverviewPage();
        overviewPage.assertPaymentinformatio();
        overviewPage.assertShippinginformatio();
        overviewPage.clickFinish();
        confirmationPage.assertConfirmationMessageAppeared();
    }

    @Test(dependsOnMethods = "finishCheckout")
    public void logOut() {
        confirmationPage.clickBackHomeButton();
        homePage.clickOnMenuIcon();
        homePage.clickLogout();
        loginPage.assertLogoutSuccessfully();

    }

}

