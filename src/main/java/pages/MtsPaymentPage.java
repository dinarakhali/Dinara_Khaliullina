package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;


public class MtsPaymentPage {
    private WebDriver driver;

    //Локаторы внутри фрейма
    private By cardNumberField = By.xpath("//input[@formcontrolname='creditCard']");
    private By dateField = By.xpath("//input[@formcontrolname='expirationDate']");
    private By cvcField = By.xpath("//input[@formcontrolname='cvc']");
    private By cardHolderField = By.xpath("//input[@formcontrolname='holder']");

    private By phoneNumber = By.xpath("//span[contains(text(), 'Номер:') and contains(text(), '375297777777')]");
    private By paymentHeader = By.cssSelector("app-payment-container .pay-description__cost");
    private By payButton = By.xpath("//button[contains(text(), 'Оплатить')]");

    private List<By> logos = List.of(
            By.cssSelector("img[src*='visa-system.svg']"),
            By.cssSelector("img[src*='mastercard-system.svg']"),
            By.cssSelector("img[src*='belkart-system.svg']"),
            By.cssSelector("img[src*='mir-system-ru.svg']")
    );

    //Конструктор
    public MtsPaymentPage(WebDriver driver) {
        this.driver = driver;
    }

    //Геттеры для локаторов (текст)
    public By getPaymentHeader() {
        return paymentHeader;
    }

    public By getPhoneNumber() {
        return phoneNumber;
    }

    public By getPayButton() {
        return payButton;
    }

    //Геттеры для локаторов полей данных карт
    public By getCardNumberField() {
        return cardNumberField;
    }

    public By getDateField() {
        return dateField;
    }

    public By getCvcField() {
        return cvcField;
    }

    public By getCardHolderField() {
        return cardHolderField;
    }

    // Проверка в полях банк.карты в iFrame
    public String getLabelText(By locator) {
        WebElement inputField = driver.findElement(locator);
        WebElement label = inputField.findElement(By.xpath("./following-sibling::label"));
        return label.getText();
    }

    // Проверка текста подсказки в iFrame
    public String getTextJs(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(locator);
        String text = (String) js.executeScript("return arguments[0].innerText;", element);
        return text.trim();
    }

    public boolean isLogoDisplayed() {
        for (By locator : logos) {
            try {
                WebElement logo = driver.findElement(locator);
                if (!logo.isDisplayed()) {
                    System.out.println("Логотип НЕ виден: " + locator);
                    return false;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Логотип НЕ найден: " + locator);
                return false;
            }
        }
        driver.switchTo().defaultContent();
        return true;
    }
}