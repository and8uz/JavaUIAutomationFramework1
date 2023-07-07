package com.opencart;

import com.opencart.managers.DataFakerManager;
import com.opencart.managers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import com.opencart.managers.ScrollManager;
import org.openqa.selenium.*;

public class TestRunner {

        public static void main (String[]args) throws InterruptedException {
            //Define a driver that will be used for future actions.

            DriverManager manager = DriverManager.getInstance();
            WebDriver driver = manager.getDriver();

            driver.get("https://www.google.com/");

            String currentWindowName = driver.getWindowHandle();

            driver.switchTo().newWindow(WindowType.TAB);

            driver.get("https://www.apache.org/");
            driver.get("https://999.md/ru/");
            driver.get("https://andreisecuqa.host/");

            WebElement accountIcon = driver.findElement(By.xpath("//i[@class='fa-solid fa-user']"));
            accountIcon.click();

            WebElement registerBtn = driver.findElement(By.xpath("//a[normalize-space()='Register']"));
            registerBtn.click();

            String firstName = DataFakerManager.getRandomName();
            System.out.println("The generated first name is:" + firstName);

            String lastName = DataFakerManager.getRandomName();
            System.out.println("The generated last name is:" + lastName);

            String email = DataFakerManager.getRandomEmail();
            System.out.println("The generated email is:" + email);

            String password = DataFakerManager.getRandomPassword(21, 22);
            System.out.println("The generated password is:" + password);

            WebElement firstNameInput = driver.findElement(By.id("input-firstname"));
            firstNameInput.sendKeys(firstName);

            WebElement lastNameInput = driver.findElement(By.id("input-lastname"));
            lastNameInput.sendKeys(lastName);

            WebElement emailInput = driver.findElement(By.id("input-email"));
            emailInput.sendKeys(email);

            WebElement passwordInput = driver.findElement(By.id("input-password"));
            passwordInput.sendKeys(password);

            WebElement privacyToggleBox = driver.findElement(By.cssSelector("input[value='1'][name='agree']"));
            try {
                ScrollManager.scrollToElement(driver, privacyToggleBox);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            privacyToggleBox.click();


            WebElement continueButton = driver.findElement(By.xpath("//button[normalize-space()='Continue']"));
            continueButton.click();

            Thread.sleep(5000);
            System.out.println(driver.getCurrentUrl());

            driver.close();

            driver.switchTo().window(currentWindowName);
            manager.getDriver().get("https://mvnrepository.com/");

            manager.getDriver().get("https://andreisecuqa.host/");

            driver.quit();


            System.out.println("The driver is closed !");

        }
    }