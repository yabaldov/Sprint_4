package org.example;

import org.example.pageobjects.MainPage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    protected WebDriver driver;
    protected MainPage objMainPage;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        objMainPage = new MainPage(driver);
        objMainPage.clickYesEveryoneOkButton();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
