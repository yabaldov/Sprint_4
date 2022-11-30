package org.example;

import org.example.pageobjects.MainPage;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;

public class OrderButtonsTest extends BaseTest {

    private final String orderPageUrl = "https://qa-scooter.praktikum-services.ru/order";

    @Test
    public void topOrderButtonClickOrderPageOpen() {
        // Arrange
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
        MainPage objMainPage = new MainPage(driver);
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
