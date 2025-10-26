package pages;

import io.appium.java_client.android.AndroidDriver;
import listeners.TestNGListeners;
import utils.ElementActions;
import utils.Validations;
import utils.Waits;
import utils.report.ScreenshotsUtils;

public class BasePage {
    public AndroidDriver driver;
    public ElementActions elementActions;
    public Validations validations;
    public Waits waits;
    public ScreenshotsUtils screenshotsUtils;
    public TestNGListeners testNGListeners;

    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        elementActions = new ElementActions(driver);
        validations = new Validations(driver);
        waits = new Waits(driver);
        screenshotsUtils = new ScreenshotsUtils(driver);

    }

}
