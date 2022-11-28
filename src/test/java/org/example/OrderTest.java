package org.example;

import org.example.pageobjects.OrderPage;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

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

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][] {
                {"Фёдор", "Перестукин", "Ленина ул., д. 1", "Сокольники", "+79905554433", "01.12.2022", "двое суток"},
                {"Виктор", "Полесов", "Перелешинский пер., д. 7", "Библиотека имени Ленина", "+79995554433", "01.01.2023", "семеро суток"},
                {"Елена", "Грицацуева", "Плеханова ул., д. 100", "Маяковская", "+79995554434", "27.04.1927", "сутки"},
                {"Иван", "Востриков", "Тов. Губернского ул., д. 1", "Спортивная", "+79985554430", "28.11.2022", "четверо суток"},
        };
    }

    @Test
    public void shouldPlaceOrderSuccessfully() {
        // Arrange
        objMainPage.clickTopOrderButton();
        OrderPage objOrderPage = new OrderPage(driver);

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
