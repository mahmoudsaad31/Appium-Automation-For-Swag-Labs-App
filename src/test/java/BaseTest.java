import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import listeners.TestNGListeners;
import org.openqa.selenium.Platform;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.*;
import utils.data_Reader.JsonUtils;
import utils.data_Reader.PropertiesUtils;
import utils.report.LogsUtils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;


@Listeners(TestNGListeners.class)
public class BaseTest {

    protected AndroidDriver driver;
    protected AppiumDriverLocalService service;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected CartPage cartPage;
    protected InformationPage informationPage;
    protected OverviewPage overviewPage;
    protected ConfirmationPage confirmationPage;


    public void createObjects() {
        this.loginPage = new LoginPage(driver);
        this.homePage = new HomePage(driver);
        this.cartPage = new CartPage(driver);
        this.informationPage = new InformationPage(driver);
        this.overviewPage = new OverviewPage(driver);
        this.confirmationPage = new ConfirmationPage(driver);
    }

    public String getTestData(String data) {
        JsonUtils testData = new JsonUtils("test_data");
        return testData.getJsonData(data);
    }

    public String getProductData(String data) {
        JsonUtils ProductData = new JsonUtils("Product_Data");
        return ProductData.getJsonData(data);
    }

    //Set Up Appium Service on time before all tests run
    @BeforeClass(alwaysRun = true)
    public void setupServerAndDriver() throws MalformedURLException, URISyntaxException {
        setUpAppiumService();
        setUpDriver();
    }


    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        createObjects();
    }

    //Close the Running Service after all tests finish
    @AfterClass(alwaysRun = true)
    public void tearDownAppiumServiceAndDriver() {
        if (driver != null) {
            LogsUtils.info("quit the driver");
            driver.quit();
        }
        if (service != null) {
            LogsUtils.info("stop the server");
            service.stop();
        }
    }

    public void setUpAppiumService() {
        service = new AppiumServiceBuilder()
                .withArgument(() -> "--use-drivers", PropertiesUtils.getPropertyValue("driver")) //To Run Appium with specific driver
                .withIPAddress(PropertiesUtils.getPropertyValue("ipAddress")) // Set the address
                .usingPort(Integer.parseInt(PropertiesUtils.getPropertyValue("port")))// Set the port
                .build();
        if (service.isRunning()) {
            LogsUtils.info("The server is running");
        } else {
            service.start();
            LogsUtils.info("starting The server");
        }
    }

    public void setUpDriver() throws URISyntaxException, MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options() //To add UiAutomator Capabilities (Options)
                .setUdid(PropertiesUtils.getPropertyValue("deviceudid")) //Running Device Name(e.g. like the name from Android Studio)
                .setPlatformName(Platform.ANDROID.name()) //Platform name
                .setPlatformVersion(PropertiesUtils.getPropertyValue("platformVersion"))
                .setApp(System.getProperty("user.dir") + "/src/test/resources/" + PropertiesUtils.getPropertyValue("app"))//Path of the App
                .setAppPackage("com.swaglabsmobileapp")
                .setAppActivity("com.swaglabsmobileapp.MainActivity")
                .setIsHeadless(false)
                .setAutoGrantPermissions(true);

        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
        LogsUtils.info("driver started successfully");

    }


}