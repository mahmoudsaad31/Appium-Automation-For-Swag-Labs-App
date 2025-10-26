import org.testng.annotations.Test;

public class TestAddOrRemoveProducts extends BaseTest {

    @Test
    public void testAddingProductsToCart() {

        loginPage.login(getTestData("username"), getTestData("password"));
        homePage.assertSuccessfulLogin();
        homePage.addProductToCart(getProductData("product1.price"));
        homePage.assertProductAddedToCartSuccessfully();
        homePage.addAnnotherProductToCart(getProductData("product2.price"));
        homePage.assertAnotherProductAddedToCartSuccessfully();
        homePage.addProductToCart(getProductData("product3.price"));
        homePage.assertProductAddedToCartSuccessfully();
        homePage.addAnnotherProductToCart(getProductData("product4.price"));
        homePage.assertAnotherProductAddedToCartSuccessfully();
        homePage.addProductToCart(getProductData("product5.price"));
        homePage.assertProductAddedToCartSuccessfully();
        homePage.addAnnotherProductToCart(getProductData("product6.price"));
        homePage.assertAnotherProductAddedToCartSuccessfully();
    }

    @Test(dependsOnMethods = "testAddingProductsToCart")
    public void testRemovingProductsFromcart() {
        homePage.removeProductfromCart(getProductData("product1.price"));
        homePage.removeAnnotherProductfromCart(getProductData("product2.price"));
        homePage.removeProductfromCart(getProductData("product3.price"));
        homePage.removeAnnotherProductfromCart(getProductData("product4.price"));
        homePage.removeProductfromCart(getProductData("product5.price"));
        homePage.removeAnnotherProductfromCart(getProductData("product6.price"));

    }

    @Test
    public void testAddingProductsToCartBYDragAndDrop() {

        loginPage.login(getTestData("username"), getTestData("password"));
        homePage.assertSuccessfulLogin();
        homePage.addProductToCartByDragAndDrop(getProductData("product1.price"));
        homePage.assertProductAddedToCartSuccessfully();
        homePage.addAnotherProductToCartByDragAndDrop(getProductData("product2.price"));
        homePage.assertAnotherProductAddedToCartSuccessfully();
        homePage.addProductToCartByDragAndDrop(getProductData("product3.price"));
        homePage.assertProductAddedToCartSuccessfully();
        homePage.addAnotherProductToCartByDragAndDrop(getProductData("product4.price"));
        homePage.assertAnotherProductAddedToCartSuccessfully();

    }

    @Test(dependsOnMethods = "testAddingProductsToCartBYDragAndDrop")
    public void testremovingProductsbySwipe() {

        homePage.clickOnCartIcon();
        cartPage.swipToRemoveProduct();
        cartPage.assertProductRemovedFromCart(getProductData("product1.name"));
        cartPage.swipToRemoveProduct();
        cartPage.assertProductRemovedFromCart(getProductData("product2.name"));
        cartPage.swipToRemoveProduct();
        cartPage.assertProductRemovedFromCart(getProductData("product3.name"));
        cartPage.swipToRemoveProduct();
        cartPage.assertProductRemovedFromCart(getProductData("product4.name"));

    }


}
