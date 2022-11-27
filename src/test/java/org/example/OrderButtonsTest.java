package org.example;

import org.example.pageobjects.MainPageScooter;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OrderButtonsTest {

    private WebDriver driver;
    private final String orderPageUrl = "https://qa-scooter.praktikum-services.ru/order";

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void topOrderButtonClickOrderPageOpen() {
        // Arrange
        MainPageScooter objMainPage = new MainPageScooter(driver);
        //Act
        objMainPage.clickTopOrderButton();
        //Assert
        MatcherAssert.assertThat(
                "Не открылась страница заказа самоката.",
                driver.getCurrentUrl(),
                is(orderPageUrl)
        );
    }

    @Test
    public void bottomOrderButtonClickOrderPageOpen() {
        // Arrange
        MainPageScooter objMainPage = new MainPageScooter(driver);
        //Act
        objMainPage.scrollAndClickBottomOrderButton();
        //Assert
        MatcherAssert.assertThat(
                "Не открылась страница заказа самоката.",
                driver.getCurrentUrl(),
                is(orderPageUrl)
        );
    }
}
