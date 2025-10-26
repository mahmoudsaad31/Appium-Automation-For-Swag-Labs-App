import org.testng.annotations.Test;
import utils.report.LogsUtils;

public class TestLogin extends BaseTest {

    @Test(priority = 1)
    public void testUnSuccessfullLoginWithInvalidUserName() {
        LogsUtils.info("start of method testUnSuccessfullLoginWithInvalidUserName");
        loginPage.login(getTestData("invalidUsername"), getTestData("password"));
        loginPage.assertMessageAppearedForInvalidUsernameorpassword();

    }

    @Test(priority = 2)
    public void testUnSuccessfullLoginWithInvalidpassword() {
        LogsUtils.info("start of method testUnSuccessfullLoginWithInvalidPassword");
        loginPage.login(getTestData("username"), getTestData("invalidPassword"));
        loginPage.assertMessageAppearedForInvalidUsernameorpassword();
    }

    @Test(priority = 3)
    public void testUnSuccessfullLoginWithEmptyUsername() {
        LogsUtils.info("start of method testUnSuccessfullLoginWithEmptyFields");
        loginPage.login("", getTestData("invalidPassword"));
        loginPage.assertMessageAppearedforrequiredusername();
    }

    @Test(priority = 4)
    public void testUnSuccessfullLoginWithEmptyPassword() {
        LogsUtils.info("start of method testUnSuccessfullLoginWithEmptyFields");
        loginPage.login(getTestData("username"), "");
        loginPage.assertMessageAppearedforrequiredpassword();
    }

    @Test(priority = 5)
    public void testSuccessfullLoginWithValidCredentials() {
        LogsUtils.info("start of method testSuccessfullLogin");
        loginPage.login(getTestData("username"), getTestData("password"));
        homePage.assertSuccessfulLogin();


    }


}


