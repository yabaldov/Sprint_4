package org.example;

import org.example.pageobjects.MainPageScooter;
import org.example.pageobjects.OrderPageScooter;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;

    private final String firstName;
    private final String secondName;
    private final String address;
    private final String subwayStation;
    private final String phone;
    private final String dateWhen;
    private final String period;

    public OrderTest(
        String firstName,
        String secondName,
        String address,
        String subwayStation,
        String phone,
        String dateWhen,
        String period
    ) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
        this.subwayStation = subwayStation;
        this.phone = phone;
        this.dateWhen = dateWhen;
        this.period = period;
    }

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][] {
                {"Виктор", "Полесов", "Перелешинский пер., д. 7", "Библиотека имени Ленина", "+79995554433", "01.01.2023", "семеро суток"},
                {"Елена", "Грицацуева", "Плеханова ул., д. 100", "Маяковская", "+79995554434", "27.04.1927", "сутки"},
                {"Иван", "Востриков", "Тов. Губернского ул., д. 1", "Спортивная", "+79985554430", "28.11.2022", "четверо суток"},
        };
    }

    @Test
    public void shouldPlaceOrderSuccessfully() {
        // Arrange
        MainPageScooter objMainPage = new MainPageScooter(driver);
        objMainPage.clickTopOrderButton();

        OrderPageScooter objOrderPage = new OrderPageScooter(driver);

        // Act
        objOrderPage.fillWhoIsTheScooterFor(firstName, secondName, address, subwayStation, phone);
        objOrderPage.completeOrder(dateWhen, period);
        objOrderPage.confirmOrderClickYes();

        // Assert
        MatcherAssert.assertThat(
                "Заголовок окна подтверждения заказа не отображается.",
                objOrderPage.isOrderCompletedModalVisible(),
                is(true)
        );
        MatcherAssert.assertThat("Текст окна подтверждение заказа не содержит данных о заказе.",
                objOrderPage.getOrderModalCompletedText(),
                containsString("Номер заказа:")
        );
    }
}
