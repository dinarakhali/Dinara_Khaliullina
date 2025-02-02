package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
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

    @BeforeAll
    public static void setDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        page = new MtsPage(driver);
        paymentPage = new MtsPaymentPage(driver);
        js = (JavascriptExecutor) driver;

        //Открыть сайт и принять куки
        driver.get("https://www.mts.by/");
        page.acceptCookies();
    }

    @AfterEach
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("1. Тест заголовка блока")
    public void blockTest() {
        String actualTitle = page.getBlockTitle();
        String exceptedTitle = "Онлайн пополнение без комиссии";
        assertEquals(exceptedTitle, actualTitle, "Заголовок блока НЕ правильный");
    }

    @Test
    @DisplayName("2. Тест наличия логотипов платежных систем")
    public void blockLogoTest() {
        assertTrue(page.isLogosDisplayed(), "Логотипы НЕ совпадают");
    }

    @Test
    @DisplayName("3. Тест ссылки 'О сервисе'")
    public void blockLinkTest() {
        By blockLink = page.getBlockLink();
        page.click(blockLink);
        page.acceptCookies();
        assertTrue(page.isCorrectTitleOfLink(), "Ссылка НЕ работает");
    }

    @Test
    @DisplayName("4. Тест кнопки 'Продолжить'")
    public void continueButtonTest() {
        page.enterTextInField("phoneField", "297777777");
        page.enterTextInField("amountField", "20");
        By button = page.getContinueButton();
        page.click(button);
        assertTrue(page.isIframeDisplayed(), "iFrame НЕ появился, кнопка НЕ работает");
    }

    @Test
    @DisplayName("5. Тест placeholder-ов в dropdown")
    public void testDropdown() {
        //Плейсхолдеры для каждого пункта списка
        Map<String, List<String>> dropdowns = page.getDropdowns();
        // Ожидаемые значения в плейсхолдерах
        Map<String, String> expectedPlaceholders = page.getExpectedPlaceholders();

        for (Map.Entry<String, List<String>> entry : dropdowns.entrySet()) {
            String dropdownName = entry.getKey();
            List<String> fields = entry.getValue();

            //Получаем плейсхолдеры после переключения пункта
            Map<String, String> actualPlaceholders = page.getPlaceholders(dropdownName, fields);

            // Проверяем плейсхолдеры
            for (String field : fields) {
                Assertions.assertEquals(expectedPlaceholders.get(field), actualPlaceholders.get(field),
                        "Плейсхолдер " + dropdownName + " - " + field + " НЕ совпадает!");
            }
        }
    }

    @Test
    @DisplayName("6. Тест суммы и номера и текста кнопки в iFrame")
    public void iframeTextTest() {
        page.iFrameOpen();
        //сумма
        By sum = paymentPage.getPaymentHeader();
        assertEquals("20.00 BYN", paymentPage.getTextJs(sum), "Сумма в iFrame НЕ правильная");
        //номер
        By number = paymentPage.getPhoneNumber();
        assertEquals("Оплата: Услуги связи Номер:375297777777", paymentPage.getTextJs(number), "Номер в iFrame НЕ правильная");
        //сумма на кнопке
        By sumButton = paymentPage.getPayButton();
        assertEquals("Оплатить 20.00 BYN", paymentPage.getTextJs(sumButton), "Сумма на кнопке в iFrame НЕ правильная");
        driver.switchTo().defaultContent();
    }

    @Test
    @DisplayName("7. Тест отображения placeholder-ов в iFrame")
    public void iframePlaceholdersTest() {
        page.iFrameOpen();
        By number = paymentPage.getCardNumberField();
        assertEquals("Номер карты", paymentPage.getLabelText(number), "Подсказка в поле 'Номер карты' НЕ правильная");

        By date = paymentPage.getDateField();
        assertEquals("Срок действия", paymentPage.getLabelText(date), "Подсказка в поле 'Номер карты' НЕ правильная");

        By cvc = paymentPage.getCvcField();
        assertEquals("CVC", paymentPage.getLabelText(cvc), "Подсказка в поле 'Номер карты' НЕ правильная");

        By holder = paymentPage.getCardHolderField();
        assertEquals("Имя держателя (как на карте)", paymentPage.getLabelText(holder), "Подсказка в поле 'Номер карты' НЕ правильная");
        driver.switchTo().defaultContent();
    }

    @Test
    @DisplayName("8. Тест наличия логотипов в iFrame")
    public void iframeLogoTest() {
        page.iFrameOpen();
        assertTrue(paymentPage.isLogoDisplayed(), "Логотипы в iFrame НЕ правильные");
        driver.switchTo().defaultContent();
    }
}