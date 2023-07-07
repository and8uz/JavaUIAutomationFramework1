package com.opencart;

import com.opencart.managers.DataFakerManager;
import com.opencart.managers.DriverManager;
import com.opencart.pageobjects.AccountCreatedPage;
import com.opencart.pageobjects.HomePage;
import com.opencart.pageobjects.RegisterPage;
import org.openqa.selenium.*;

public class TestRunner {
    public static void main(String[] args) throws InterruptedException {
        //Define a driver that will be used for future actions.

        DriverManager manager = DriverManager.getInstance();
        WebDriver driver = manager.getDriver();
        driver.get("https://andreisecuqa.host/");
        HomePage homePage = new HomePage(driver);
        homePage.navigateToRegisterPageFromHeaderMenu();
        String firstName = DataFakerManager.getRandomName();
        String lastName = DataFakerManager.getRandomName();
        String email = DataFakerManager.getRandomEmail();
        String password = DataFakerManager.getRandomPassword(21, 22);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.filInTheRegisterForm(firstName, lastName, email, password);
        registerPage.switchOnThePrivacyToggle(driver);
        registerPage.clickOnContinueButton();
        Thread.sleep(5000);
        System.out.println(driver.getCurrentUrl());

        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        accountCreatedPage.logOutFromTheAccount();
        Thread.sleep(2000);
        System.out.println(driver.getCurrentUrl());

        driver.quit();
        System.out.println("The execution is finished");
    }
}