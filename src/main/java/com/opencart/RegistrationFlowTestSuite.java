package com.opencart;

import com.opencart.managers.DataFakerManager;
import com.opencart.managers.DriverManager;
import com.opencart.pageobjects.HomePage;
import com.opencart.pageobjects.RegisterPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class RegistrationFlowTestSuite {

    WebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;
    static int counter = 0;


    @BeforeEach
    public void executeTheCodeBeforeEachTestFromThisClass() {

        DriverManager manager = DriverManager.getInstance();
        driver = manager.getDriver();
        driver.get("https://andreisecuqa.host/");
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        counter++;
        System.out.println("The test number " + counter + " started !");

    }

    @Test
    @DisplayName("The URL contains success keyword after registration with valid data")
    public void registerFlowRedirectsTheUserToTheCorrectUrl() throws InterruptedException {

        homePage.navigateToRegisterPageFromHeaderMenu();

        String firstName = DataFakerManager.getRandomName();
        String lastName = DataFakerManager.getRandomName();
        String email = DataFakerManager.getRandomEmail();
        String password = DataFakerManager.getRandomPassword(21, 22);

        registerPage.filInTheRegisterForm(firstName, lastName, email, password);

        registerPage.switchOnThePrivacyToggle(driver);
        registerPage.clickOnContinueButton();

        Thread.sleep(5000);
        System.out.println(driver.getCurrentUrl());

        boolean urlContainsTheCorrectKeyWords = driver.getCurrentUrl().contains("/index.php?route=account/success&language");
        String errorMessage = "The URL " + driver.getCurrentUrl() + " contains success keyword";
        Assertions.assertTrue(urlContainsTheCorrectKeyWords, errorMessage);

    }

    @Test
    @DisplayName("The URL contains register keyword when privacy policy is not accepted")
    public void registerFlowIsBlockedByPrivacyPolicyToggleIsNotAccepted() throws InterruptedException {

        homePage.navigateToRegisterPageFromHeaderMenu();

        String firstName = DataFakerManager.getRandomName();
        String lastName = DataFakerManager.getRandomName();
        String email = DataFakerManager.getRandomEmail();
        String password = DataFakerManager.getRandomPassword(21, 22);

        registerPage.filInTheRegisterForm(firstName, lastName, email, password);

        // Do not enable privacy toggle
        // registerPage.switchOnThePrivacyToggle(driver);
        registerPage.clickOnContinueButton();

        Thread.sleep(1000);
        System.out.println(driver.getCurrentUrl());

        boolean urlContainsTheCorrectKeyWords = driver.getCurrentUrl().contains("/index.php?route=account/success&language");
        String errorMessage = "The URL " + driver.getCurrentUrl() + " does not contain success keyword";
        Assertions.assertFalse(urlContainsTheCorrectKeyWords, errorMessage);

        boolean urlContainsRegisterKeyword = driver.getCurrentUrl().contains("register");
        Assertions.assertTrue(urlContainsRegisterKeyword, "The URL belongs to register page");

    }

    @AfterEach
    public void executeThisMethodAfterEachTestCase() {
        DriverManager.getInstance().quitTheDriver();
        System.out.println("The test number " + counter + " finished !");
    }
}