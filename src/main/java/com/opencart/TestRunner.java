package com.opencart;

import com.opencart.managers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class TestRunner {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = DriverManager.getInstance().getDriver();
        driver.get("https://docs.google.com/spreadsheets/d/1r4zBeEAyjE4iGTijoxUawxr0mIfBvZuBh3yIsQJG0Y0/edit#gid=0");
        String currentWindowName = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://github.com/TestAutomationPanfilElena/JavaUiAutomationFramework/branches");
        Thread.sleep(500);
        driver.close();
        driver.switchTo().window(currentWindowName);
        driver.get("https://protv.md/");
        driver.quit();
        System.out.println("The execution was finished");
    }
}