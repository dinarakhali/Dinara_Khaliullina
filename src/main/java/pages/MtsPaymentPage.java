package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MtsPaymentPage {
    private WebDriver driver;

    //Локаторы внутри фрейма
    private By iframe = By.cssSelector(".bepaid-iframe");

    private By cardNumberField = By.xpath("//label[contains(text(), 'Номер карты')]");
    private By dateField = By.xpath("//label[contains(text(), 'Срок действия')]");
    private By cvcField = By.xpath("//label[contains(text(), 'CVC')]");
    private By cardHolderField = By.xpath("//label[contains(text(), 'Имя держателя (как на карте)')]");

    private By phoneNumber = By.xpath("//span[contains(text(), 'Номер:') and contains(text(), '375297777777')]");
    private By paymentHeader = By.xpath("//span[contains(text(), 'Оплата:')]");

    // Локатор для суммы на кнопке
    private By payButton = By.xpath("//button[contains(text(), 'Оплатить')]");

    //Конструктор
    public MtsPaymentPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для проверки текста подсказки в поле ввода
    public String getPlaceholderText(By field) {
        return driver.findElement(field).getAttribute("placeholder");
    }

    // Проверка текста подсказки для "Номер карты"
    public String getCardNumberLabelTextWithJs() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(cardNumberField);
        String text = (String) js.executeScript("return arguments[0].textContent;", element); // Приводим к строке
        return text.trim();
    }

    //public String getCardNumberPlaceholder() {
    //    return getPlaceholderText(cardNumberField);
    //}

    // Проверка текста подсказки для "Дата окончания карты"
    public String getExpirationDatePlaceholder() {
        return getPlaceholderText(dateField);
    }

    // Проверка текста подсказки для "CVC"
    public String getCvcPlaceholder() {
        return getPlaceholderText(cvcField);
    }

    // Проверка текста подсказки для "Имя держателя карты"
    public String getCardHolderPlaceholder() {
        return getPlaceholderText(cardHolderField);
    }

    // Проверка текста заголовка с суммой
    public String getPaymentHeaderText() {
        return driver.findElement(paymentHeader).getText();
    }

    // Проверка номера телефона в заголовке
    public String getPhoneNumberText() {
        return driver.findElement(phoneNumber).getText();
    }

    // Проверка текста на кнопке оплаты
    public String getPayButtonText() {
        return driver.findElement(payButton).getText().trim();
    }

    // Переключение в iframe
    public void switchToIframe() {
        WebElement iframe = driver.findElement(By.cssSelector("iframe.bepaid-iframe")); // Локатор для iframe
        driver.switchTo().frame(iframe);
    }

    // Возврат в основной контекст
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
}