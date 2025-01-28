package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class MtsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    //Локаторы
    //Заголовок блока
    private By blockTitle = By.xpath("//h2[contains(text(), 'Онлайн пополнение')]");
    //Кнопка куки
    private By cookieButton = By.id("cookie-agree");
    //Всплывающее окно
    private By iframe = By.xpath("//iframe[contains(@class, 'bepaid-iframe')]");
    //Логотипы платежных систем
    private List<By> logos = List.of(
            By.xpath("//img[@alt='Visa']"),
            By.xpath("//img[@alt='Verified By Visa']"),
            By.xpath("//img[@alt='MasterCard']"),
            By.xpath("//img[@alt='MasterCard Secure Code']"),
            By.xpath("//img[@alt='Белкарт']")
    );
    //Ссылка "Подробнее о сервисе"
    private By link = By.xpath("//a[text()='Подробнее о сервисе']");

    //Кнопка открытия выпадающего списка
    private By dropdownButton = By.cssSelector(".select__header");
    //Текущий выбранный пункт
    private By currentSelection = By.cssSelector(".select__now");
    // Список элементов
    private By dropdownList = By.cssSelector(".select__list");
    //Элементы списка
    private By dropdownItems = By.cssSelector(".select__item");
    private By submitButton = By.xpath("//*[@id='pay-connection']//button[text()='Продолжить']");

    private Map<String, By> fieldLocators = new HashMap<>();

    //Локаторы пунктов выпадающего меню
    private By firstOption = By.xpath("//li[contains(., 'Услуги связи')]");
    private By secondOption = By.xpath("//li[contains(., 'Домашний интернет')]");
    private By thirdOption = By.xpath("//li[contains(., 'Рассрочка')]");
    private By fourthOption = By.xpath("//li[contains(., 'Задолженность')]");

    //Конструктор.
    public MtsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(9));

        fieldLocators.put("phoneField", By.id("connection-phone"));
        fieldLocators.put("amountField", By.id("connection-sum"));
        fieldLocators.put("emailField", By.id("connection-email"));
        fieldLocators.put("internetPhoneField", By.id("internet-phone"));
        fieldLocators.put("internetSumField", By.id("internet-sum"));
        fieldLocators.put("internetEmailField", By.id("internet-email"));
        fieldLocators.put("instalmentNumberField", By.id("score-instalment"));
        fieldLocators.put("instalmentSumField", By.id("instalment-sum"));
        fieldLocators.put("instalmentEmailField", By.id("instalment-email"));
        fieldLocators.put("arrearsNumberField", By.id("score-arrears"));
        fieldLocators.put("arrearsSumField", By.id("arrears-sum"));
        fieldLocators.put("arrearsEmailField", By.id("arrears-email"));
    }

    //Метод если появится куки.
    public void acceptCookies() {
        try {
            WebElement cookie = wait.until(ExpectedConditions.presenceOfElementLocated(cookieButton));
            cookie.click();
            System.out.println("* Куки приняты.");
        } catch (Exception e) {
            System.out.println("* Куки не отображаются.");
        }
    }

    //Метод проверки заголовка блока.
    public String getBlockTitle() {
        return driver.findElement(blockTitle).getText().replace("\n", " ").trim();
    }

    //Метод проверки наличия логотипов.
    public boolean isLogosDisplayed() {
        for (By logo : logos) {
            try {
                if (!driver.findElement(logo).isDisplayed()) {
                    return false;
                }
            } catch (NoSuchElementException e) {
                return false;
            }
        }
        return true;
    }

    //Метод проверки надписей в незаполненных полях
    public void verifyPlaceholderText(String fieldName, String expectedText) {
        By locator = fieldLocators.get(fieldName);
        if (locator == null) {
            throw new IllegalArgumentException("Поле с именем '" + fieldName + "' не найдено.");
        }

        String actualPlaceholder = driver.findElement(locator).getAttribute("placeholder");
        if (!expectedText.equals(actualPlaceholder)) {
            throw new AssertionError("Ожидаемый текст: '" + expectedText + "', но был: '" + actualPlaceholder + "' для поля: " + fieldName);
        }
    }

    //Метод переключения выпадающего списка
    public void clickWithJs(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    // Методы для выбора каждого пункта выпадающего списка
    public void selectFirstOption() {
        clickWithJs(firstOption);
    }

    public void selectSecondOption() {
        clickWithJs(secondOption);
    }

    public void selectThirdOption() {
        clickWithJs(thirdOption);
    }

    public void selectFourthOption() {
        clickWithJs(fourthOption);
    }

    //Метод заполнения поля.
    public void enterTextInField(String fieldName, String text) {
        By locator = fieldLocators.get(fieldName);
        if (locator == null) {
            throw new IllegalArgumentException("Поле с именем '" + fieldName + "' не найдено.");
        }
        driver.findElement(locator).sendKeys(text);
    }

    //Метод нажать кнопку "Продолжить".
    public void clickSubmitButton() {
        driver.findElement(submitButton).click();
    }

    //Метод проверки, что кнопка "Продолжить" работает.
    public boolean isIframeDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(iframe));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Метод клик по ссылке.
    public void linkClick() {
        driver.findElement(link).click();
    }

    // Метод проверки заголовка страницы.
    public boolean isCorrectTitleDisplayed() {
        return driver.getTitle().contains("Порядок оплаты и безопасность интернет платежей");
    }

    // Метод для возврата на предыдущую страницу.
    public void goBack() {
        driver.navigate().back();
    }
}