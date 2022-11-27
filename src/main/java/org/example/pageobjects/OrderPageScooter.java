package org.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Класс страницы заказа самоката
public class OrderPageScooter {

    private final WebDriver driver;

    // Поле ввода для имени
    By inputFirstName = By.xpath("//input[contains(@placeholder, 'Имя')]");
    // Поле ввода для фамилии
    By inputSecondName = By.xpath("//input[contains(@placeholder, 'Фамилия')]");
    // Поле ввода для адреса
    By inputAddress = By.xpath("//input[contains(@placeholder, 'Адрес')]");
    // Поле для выбора станции метро
    By inputSelectSubwayStation = By.xpath("//input[contains(@placeholder, 'Станция метро')]");
    By subwayStationsList = By.className("select-search__select");
    // Поле ввода для телефона
    By inputPhone = By.xpath("//input[contains(@placeholder, 'Телефон')]");
    // Кнопка "Далее"
    By orderNextButton = By.xpath("//button[text()='Далее']");

    // Заголовок "Про аренду"
    By orderHeaderAboutRent = By.xpath("//div[text()='Про аренду']");
    // Поле ввода "Когда привезти самокат"
    By inputWhen = By.xpath("//input[contains(@placeholder, 'Когда')]");
    By datePickerDaySelected = By.xpath("//div[contains(@class,'react-datepicker__day--selected')]");
    // Поле ввода "Срок аренды"
    By inputRentalPeriod = By.xpath("//div[contains(@class,'Dropdown-placeholder') and contains(text(),'Срок аренды')]");
    // Выбор "Цвет самоката"
    // Поле ввода "Комментарий для курьера"
    // Кнопка формы "Заказать"
    By placeOrderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");

    // Заголовок модального окна "Хотите оформить заказ?"
    By orderModalHeaderConfirm = By.xpath("//div[contains(text(), 'Хотите оформить заказ?')]");
    //Кнопка подтверждения заказа "Да"
    By orderModalYesButton = By.xpath("//button[text()='Да']");

    // Заголовок модального окна "Заказ оформлен"
    By orderModalHeaderOrderCompleted = By.xpath("//div[contains(text(), 'Заказ оформлен')]");
    // Текст модального окна "Заказ оформлен"
    By orderModalCompletedText = By.className("Order_Text__2broi");


    public OrderPageScooter(WebDriver driver) {
        this.driver = driver;
    }

    public void setFirstName(String firstName) {
        driver.findElement(inputFirstName).sendKeys(firstName);
    }

    public void setSecondName(String secondName) {
        driver.findElement(inputSecondName).sendKeys(secondName);
    }

    public void setAddress(String address) {
        driver.findElement(inputAddress).sendKeys(address);
    }

    public void setSubwayStation(String subwayStation) {
        driver.findElement(inputSelectSubwayStation).sendKeys(subwayStation);
        driver.findElement(subwayStationsList).click();
    }

    public void setInputPhone(String phone) {
        driver.findElement(inputPhone).sendKeys(phone);
    }

    public void clickOrderNextButton() {
        driver.findElement(orderNextButton).click();
    }

    public void fillWhoIsTheScooterFor(String firstName, String secondName, String address, String subwayStation, String phone) {
        setFirstName(firstName);
        setSecondName(secondName);
        setAddress(address);
        setSubwayStation(subwayStation);
        setInputPhone(phone);
        clickOrderNextButton();
    }

    public void setInputWhen(String dateWhen) {
        driver.findElement(inputWhen).sendKeys(dateWhen);
        driver.findElement(datePickerDaySelected).click();
    }

    public void setRentalPeriod(String period) {
        driver.findElement(inputRentalPeriod).click();

        By dropDownMenuOption = By.xpath(String.format("//div[contains(@class, 'Dropdown-option') and contains(text(), '%s')]", period));

        WebElement dropDownMenuOptionElement = driver.findElement(dropDownMenuOption);

        if(!dropDownMenuOptionElement.isDisplayed()) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", dropDownMenuOptionElement);
        }
        dropDownMenuOptionElement.click();
    }

    public void clickPlaceOrderButton() {
        driver.findElement(placeOrderButton).click();
    }

    public void completeOrder(String dateWhen, String period) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(orderHeaderAboutRent));

        setInputWhen(dateWhen);
        setRentalPeriod(period);
        clickPlaceOrderButton();
    }

    public void confirmOrderClickYes() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(orderModalHeaderConfirm));
        driver.findElement(orderModalYesButton).click();
    }

    public boolean isOrderCompletedModalVisible() {
        return driver.findElement(orderModalHeaderOrderCompleted).isDisplayed();
    }

    public String getOrderModalCompletedText() {
        return driver.findElement(orderModalCompletedText).getText();
    }
}
