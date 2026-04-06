package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ContactPage;
import pages.HomePage;
import pages.LoginPage;
import utils.RetryAnalyser;
import utils.TestNGListener;

import java.lang.reflect.Method;

import static utils.PropertiesReader.getProperty;

@Listeners(TestNGListener.class)
public class LoginTests extends AppManager {

    LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void goToLoginPage() {
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }

    @Test(retryAnalyzer = RetryAnalyser.class)
    public void loginPositiveTest() {
        loginPage.typeLoginRegistrationForm(
                getProperty("base.properties", "login"),
                getProperty("base.properties", "password")
        );
        loginPage.clickBtnLoginForm();

        Assert.assertTrue(new ContactPage(getDriver()).isTextInBtnAddPresent("ADD"));
    }

    @Test(groups = {"Smoke", "user"})
    public void loginPositiveTestWithUser(Method method) {
        User user = new User(
                getProperty("base.properties", "login"),
                getProperty("base.properties", "password")
        );

        logger.info("start test " + method.getName() + " with user " + user);

        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();

        Assert.assertTrue(new ContactPage(getDriver()).isTextInBtnSignOutPresent("Sign Out"));
    }

    @Test(groups = "negative")
    public void loginNegativeTestWrongEmail() {
        User user = new User("loginyoho.com", getProperty("base.properties", "password"));

        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();

        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test(groups = "negative")
    public void loginNegativeTestIsBlank() {
        User user = new User(" ", " ");

        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();

        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test(groups = "negative")
    public void loginNegativeTestWithTwoDots() {
        User user = new User("login@yoho..com", getProperty("base.properties", "password"));

        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();

        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test(groups = "negative")
    public void loginNegativeTestWithTwoAts() {
        User user = new User("login@@yoho.com", getProperty("base.properties", "password"));

        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();

        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test(groups = "negative")
    public void loginNegativeTestEmailIsBlank() {
        User user = new User("", getProperty("base.properties", "password"));

        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();

        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test(groups = "negative")
    public void loginNegativeTestPasswordIsBlank() {
        User user = new User(getProperty("base.properties", "login"), "");

        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();

        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test(groups = "negative")
    public void loginNegativeTestCorrectPasswordButWrongEmail() {
        User user = new User("wrongemail@gmail.com", getProperty("base.properties", "password"));

        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();

        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }

    @Test(groups = "negative")
    public void loginNegativeTestCorrectEmailButWrongPassword() {
        User user = new User(getProperty("base.properties", "login"), "WrongPassword123!");

        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();

        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }
}