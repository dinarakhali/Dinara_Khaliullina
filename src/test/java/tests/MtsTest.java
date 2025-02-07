package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MtsPage;
import pages.MtsPaymentPage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsTest {

    private WebDriver driver;
    private WebDriverWait wait;
    MtsPage page;
    MtsPaymentPage paymentPage;
    JavascriptExecutor js;

    @Step("{0}") // Метод для логирования шагов
    private void step(String message) {
        System.out.println(message);
    }

    @BeforeAll
    public static void setDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        step("Настраиваем драйвер браузера");
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        page = new MtsPage(driver);
        paymentPage = new MtsPaymentPage(driver);
        js = (JavascriptExecutor) driver;

        step("Открываем сайт и принимаем куки");
        driver.get("https://www.mts.by/");
        page.acceptCookies();
    }

    @AfterEach
    public void closeDriver() {
        step("Закрываем браузер");
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Epic("UI тесты") // Глобальная категория тестов
    @Feature("Тестирование блока пополнения") // Подкатегория
    @Story("Проверка заголовка блока") // Сюжет теста
    @Severity(SeverityLevel.NORMAL) // Важность теста
    @DisplayName("Тест заголовка блока")
    public void blockTest() {
        step("Получаем заголовок блока");
        String actualTitle = page.getBlockTitle();
        step("Сравниваем заголовок с ожидаемым");
        String exceptedTitle = "Онлайн пополнение без комиссии";
        assertEquals(exceptedTitle, actualTitle, "Заголовок блока НЕ правильный");
    }

    @Test
    @Epic("UI тесты")
    @Feature("Тестирование блока пополнения")
    @Story("Проверка наличия логотипов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Тест наличия логотипов платежных систем")
    public void blockLogoTest() {
        assertTrue(page.isLogosDisplayed(), "Логотипы НЕ совпадают");
    }

    @Test
    @Epic("UI тесты")
    @Feature("Тестирование блока пополнения")
    @Story("Проверка ссылки")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Тест ссылки 'О сервисе'")
    public void blockLinkTest() {
        step("Находим и кликаем по ссылке");
        By blockLink = page.getBlockLink();
        page.click(blockLink);
        step("Принимаем куки при появлении");
        page.acceptCookies();
        step("Проверяем title открывшейся страницы");
        assertTrue(page.isCorrectTitleOfLink(), "Ссылка НЕ работает");
    }

    @Test
    @Epic("UI тесты")
    @Feature("Тестирование блока пополнения")
    @Story("Проверка кнопки")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Тест кнопки 'Продолжить'")
    public void continueButtonTest() {
        step("Вводим номер телефона и сумму в поля ввода");
        page.enterTextInField("phoneField", "297777777");
        page.enterTextInField("amountField", "20");
        step("Нажимаем кнопку 'Продолжить'");
        By button = page.getContinueButton();
        page.click(button);
        step("Проверяем появление iFrame");
        assertTrue(page.isIframeDisplayed(), "iFrame НЕ появился, кнопка НЕ работает");
    }

    @Test
    @Epic("UI тесты")
    @Feature("Тестирование блока пополнения")
    @Story("Проверка выпадающих списков и плейсхолдеров")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Тест placeholder-ов в dropdown")
    public void testDropdown() {
        step("Получаем необходимые локаторы и ожидаемые значения плейсхолдерах");
        //Получаем мапу с пунктами и списком плейсхолдеров
        Map<String, List<String>> dropdowns = page.getDropdowns();
        //Получаем значения плейсхолдера и ожидаемый текст в нем
        Map<String, String> expectedPlaceholders = page.getExpectedPlaceholders();
        for (Map.Entry<String, List<String>> entry : dropdowns.entrySet()) {
            String dropdownName = entry.getKey();
            List<String> fields = entry.getValue();

            step("Получаем фактические значения в плейсхолдерах");
            Map<String, String> actualPlaceholders = page.getPlaceholders(dropdownName, fields);

            step("Сравниваем ожидаемые и фактические значения в плейсхолдерах");
            for (String field : fields) {
                Assertions.assertEquals(expectedPlaceholders.get(field), actualPlaceholders.get(field),
                        "Плейсхолдер " + dropdownName + " - " + field + " НЕ совпадает!");
            }
        }
    }

    @Test
    @Epic("UI тесты")
    @Feature("Тестирование iFrame")
    @Story("Проверка текстов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Тест суммы и номера и текста кнопки в iFrame")
    public void iframeTextTest() {
        step("Открываем iFrame");
        page.iFrameOpen();
        step("Сравниваем ожидаемое и фактическое значение суммы в заголовке");
        By sum = paymentPage.getPaymentHeader();
        assertEquals("20.00 BYN", paymentPage.getTextJs(sum), "Сумма в iFrame НЕ правильная");
        step("Сравниваем ожидаемое и фактическое значение номера в тексте");
        By number = paymentPage.getPhoneNumber();
        assertEquals("Оплата: Услуги связи Номер:375297777777", paymentPage.getTextJs(number), "Номер в iFrame НЕ правильная");
        step("Сравниваем ожидаемое и фактическое значение суммы на кнопке");
        By sumButton = paymentPage.getPayButton();
        assertEquals("Оплатить 20.00 BYN", paymentPage.getTextJs(sumButton), "Сумма на кнопке в iFrame НЕ правильная");
        step("Переключаемся на основную страницу");
        driver.switchTo().defaultContent();
    }

    @Test
    @Epic("UI тесты")
    @Feature("Тестирование iFrame")
    @Story("Проверка плейсхолдеров")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Тест отображения placeholder-ов в iFrame")
    public void iframePlaceholdersTest() {
        step("Открываем iFrame");
        page.iFrameOpen();
        step("Сравниваем ожидаемое и фактическое значение плейсхолдера 'Номер карты'");
        By number = paymentPage.getCardNumberField();
        assertEquals("Номер карты", paymentPage.getLabelText(number), "Подсказка в поле 'Номер карты' НЕ правильная");
        step("Сравниваем ожидаемое и фактическое значение плейсхолдера 'Срок действия'");
        By date = paymentPage.getDateField();
        assertEquals("Срок действия", paymentPage.getLabelText(date), "Подсказка в поле 'Номер карты' НЕ правильная");
        step("Сравниваем ожидаемое и фактическое значение плейсхолдера 'CVC'");
        By cvc = paymentPage.getCvcField();
        assertEquals("CVC", paymentPage.getLabelText(cvc), "Подсказка в поле 'Номер карты' НЕ правильная");
        step("Сравниваем ожидаемое и фактическое значение плейсхолдера 'Имя держателя карты'");
        By holder = paymentPage.getCardHolderField();
        assertEquals("Имя держателя (как на карте)", paymentPage.getLabelText(holder), "Подсказка в поле 'Номер карты' НЕ правильная");
        step("Переключаемся на основную страницу");
        driver.switchTo().defaultContent();
    }

    @Test
    @Epic("UI тесты")
    @Feature("Тестирование iFrame")
    @Story("Проверка логотипов")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Тест наличия логотипов в iFrame")
    public void iframeLogoTest() {
        step("Открываем iFrame");
        page.iFrameOpen();
        step("Проверяем отображение логотипов");
        assertTrue(paymentPage.isLogoDisplayed(), "Логотипы в iFrame НЕ правильные");
        step("Переключаемся на основную страницу");
        driver.switchTo().defaultContent();
    }
}