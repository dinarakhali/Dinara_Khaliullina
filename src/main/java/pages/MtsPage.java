package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MtsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    //Локаторы
    private By blockTitle = By.xpath("//h2[contains(text(), 'Онлайн пополнение')]"); //заголовок блока
    private By cookieButton = By.id("cookie-agree"); // кнопка куки
    private By iframe = By.className("bepaid-iframe");
    private By blockLink = By.xpath("//a[text()='Подробнее о сервисе']"); //сслыка на блоке
    private By dropdownButton = By.cssSelector(".select__header"); //нажимаемая часть
    private By currentSelection = By.cssSelector(".select__now"); //текущий выбранный пункт
    private By dropdownItems = By.cssSelector(".select__item"); //элементы списка
    private By continueButton = By.xpath("//*[@id='pay-connection']//button[text()='Продолжить']"); //кнопка на блоке

    //Логотипы платежных систем
    private List<String> logos = List.of(
            "//img[@alt='Visa']",
            "//img[@alt='Verified By Visa']",
            "//img[@alt='MasterCard']",
            "//img[@alt='MasterCard Secure Code']",
            "//img[@alt='Белкарт']"
    );
    //Мапа для плейсхолдеров
    Map<String, By> fieldLocators = new HashMap<>();
    Map<String, String> expectedPlaceholders = new HashMap<>();
    Map<String, List<String>> dropdowns = new HashMap<>();

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

        //Заполняем мапу значениями плейсхолдеров для каждого пункта выпад.списка
        dropdowns.put("Домашний интернет", List.of("internetPhoneField", "internetSumField", "internetEmailField"));
        dropdowns.put("Рассрочка", List.of("instalmentNumberField", "instalmentSumField", "instalmentEmailField"));
        dropdowns.put("Задолженность", List.of("arrearsNumberField", "arrearsSumField", "arrearsEmailField"));
        dropdowns.put("Услуги связи", List.of("phoneField", "amountField", "emailField"));

        //Заполняем в мапу плейсхолдер и значение
        expectedPlaceholders.put("internetPhoneField", "Номер абонента");
        expectedPlaceholders.put("internetSumField", "Сумма");
        expectedPlaceholders.put("internetEmailField", "E-mail для отправки чека");

        expectedPlaceholders.put("instalmentNumberField", "Номер счета на 44");
        expectedPlaceholders.put("instalmentSumField", "Сумма");
        expectedPlaceholders.put("instalmentEmailField", "E-mail для отправки чека");

        expectedPlaceholders.put("arrearsNumberField", "Номер счета на 2073");
        expectedPlaceholders.put("arrearsSumField", "Сумма");
        expectedPlaceholders.put("arrearsEmailField", "E-mail для отправки чека");

        expectedPlaceholders.put("phoneField", "Номер телефона");
        expectedPlaceholders.put("amountField", "Сумма");
        expectedPlaceholders.put("emailField", "E-mail для отправки чека");
    }

    //Гетеры для вызываемых локаторов
    public By getBlockLink() {
        return blockLink;
    }

    public By getContinueButton() {
        return continueButton;
    }

    public Map<String, String> getExpectedPlaceholders() {
        return expectedPlaceholders;
    }

    public Map<String, List<String>> getDropdowns() {
        return dropdowns;
    }

    //Метод если появится куки.
    public void acceptCookies() {
        try {
            WebElement cookie = wait.until(ExpectedConditions.presenceOfElementLocated(cookieButton));
            cookie.click();
        } catch (TimeoutException | ElementNotInteractableException ignored) {
            // Может не появиться, это нормально - игнорируем ошибку
        }
    }

    //Метод проверки заголовка блока.
    public String getBlockTitle() {
        return driver.findElement(blockTitle).getText().replace("\n", " ").trim();
    }

    //Метод проверки наличия логотипов.
    public boolean isLogosDisplayed() {
        for (String locator : logos) {
            WebElement logo = driver.findElement(By.xpath(locator));
            if (!logo.isDisplayed()) {
                System.out.println("Логотип с XPath " + locator + " НЕ найден");
                return false;
            }
        }
        return true;
    }

    // Метод проверки заголовка у ссылки.
    public boolean isCorrectTitleOfLink() {
        return driver.getTitle().contains("Порядок оплаты и безопасность интернет платежей");
    }

    //Метод нажать кнопку или ссылку
    public void click(By locator) {
        driver.findElement(locator).click();
    }

    //Метод заполнения поля.
    public void enterTextInField(String fieldName, String text) {
        By locator = fieldLocators.get(fieldName);
        if (locator == null) {
            throw new IllegalArgumentException("Поле с именем '" + fieldName + "' не найдено.");
        }
        driver.findElement(locator).sendKeys(text);
    }

    //Метод проверки, что кнопка "Продолжить" работает.
    public boolean isIframeDisplayed() {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
            WebElement iframeContent = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
            return iframeContent.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    // Метод выбора пункта в выпадающем списке
    public void selectDropdownOpt(String expectedOption) {
        wait.until(ExpectedConditions.elementToBeClickable(dropdownButton)).click(); // Открываем список
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdownItems));

        for (WebElement option : options) {
            if (option.getText().trim().equals(expectedOption)) {
                try {
                    wait.until(ExpectedConditions.elementToBeClickable(option)).click(); // Обычный клик
                } catch (ElementClickInterceptedException e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option); // JavaScript клик
                }
                wait.until(ExpectedConditions.textToBePresentInElementLocated(currentSelection, expectedOption)); // Проверяем, что выбор применился
                return;
            }
        }
        throw new NoSuchElementException("Опция '" + expectedOption + "' не найдена в выпадающем списке.");
    }

    // Метод проверки плейсхолдера у нужного поля
    public Map<String, String> getPlaceholders(String dropdownOption, List<String> fieldKeys) {
        selectDropdownOpt(dropdownOption); // Выбираем пункт списка

        Map<String, String> placeholders = new HashMap<>();
        for (String fieldKey : fieldKeys) {
            By fieldLocator = fieldLocators.get(fieldKey);
            if (fieldLocator == null) {
                throw new NoSuchElementException("Ключ '" + fieldKey + "' не найден в fieldLocators.");
            }
            WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(fieldLocator));
            placeholders.put(fieldKey, field.getAttribute("placeholder"));
        }
        return placeholders;
    }

    // Метод для возврата на предыдущую страницу.
    public void goBack() {
        driver.navigate().back();
    }

    //Метод для открытия iFrame
    public void iFrameOpen() {
        enterTextInField("phoneField", "297777777");
        enterTextInField("amountField", "20");
        driver.findElement(continueButton).click();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
    }
}