package org.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Класс главной страницы
public class MainPage {

    private final WebDriver driver;
    private final String siteUrl = "https://qa-scooter.praktikum-services.ru/";

    // И здесь куки! Кнопка "да все привыкли"
    private final By yesEveryoneOkButton = By.id("rcc-confirm-button");

    // Заголовок раздела "Вопросы о важном"
    private final By mainQuestionsSection = By.xpath("//div[text()='Вопросы о важном']");

    private final By[] mainQuestionAccordion = {
            By.id("accordion__heading-0"),  // Сколько это стоит? И как оплатить?
            By.id("accordion__heading-1"),  // Хочу сразу несколько самокатов! Так можно?
            By.id("accordion__heading-2"),  // Как рассчитывается время аренды?
            By.id("accordion__heading-3"),  // Можно ли заказать самокат прямо на сегодня?
            By.id("accordion__heading-4"),  // Можно ли продлить заказ или вернуть самокат раньше?
            By.id("accordion__heading-5"),  // Вы привозите зарядку вместе с самокатом?
            By.id("accordion__heading-6"),  // Можно ли отменить заказ?
            By.id("accordion__heading-7"),  // Я жизу за МКАДом, привезёте?
    };

    private final By[] mainAnswerAccordion = {
            By.xpath("//div[@id='accordion__panel-0']/p"),  // Сутки — 400 рублей. Оплата курьеру — наличными или картой.
            By.xpath("//div[@id='accordion__panel-1']/p"),  // Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.
            By.xpath("//div[@id='accordion__panel-2']/p"),  // Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.
            By.xpath("//div[@id='accordion__panel-3']/p"),  // Только начиная с завтрашнего дня. Но скоро станем расторопнее.
            By.xpath("//div[@id='accordion__panel-4']/p"),  // Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.
            By.xpath("//div[@id='accordion__panel-5']/p"),  // Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.
            By.xpath("//div[@id='accordion__panel-6']/p"),  // Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.
            By.xpath("//div[@id='accordion__panel-7']/p"),  // Да, обязательно. Всем самокатов! И Москве, и Московской области.
    };

    private final By topOrderButton = By.xpath("//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");
    private final By bottomOrderButton = By.xpath("//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get(siteUrl);
    }

    public void clickYesEveryoneOkButton() {
        driver.findElement(yesEveryoneOkButton).click();
    }

    public void scrollToMainQuestions() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(mainQuestionsSection));
    }

    public void clickQuestion(int questionNumber) {
        driver.findElement(mainQuestionAccordion[questionNumber]).click();
    }

    public String getAnswer(int answerNumber) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(mainAnswerAccordion[answerNumber]));
        return driver.findElement(mainAnswerAccordion[answerNumber]).getText();
    }

    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

    public void clickBottomOrderButton() {
        driver.findElement(bottomOrderButton).click();
    }

    public void scrollToBottomOrderButton() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(bottomOrderButton));
    }

    public void scrollAndClickBottomOrderButton() {
        scrollToBottomOrderButton();
        clickBottomOrderButton();
    }
}
