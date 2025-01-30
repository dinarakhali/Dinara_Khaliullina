import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ReplenishmentTest {
    private static WebDriver driver;
    private WebDriverWait wait;

    //Метод для куки
    private void clickCookies() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement cookieButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cookie-agree")));
            cookieButton.click();
        } catch (TimeoutException | ElementNotInteractableException e) {
        }
    }

    @BeforeAll
    public static void setDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Открыть сайт и принять куки
        driver.get("https://www.mts.by/");
        clickCookies();
    }

    @AfterEach
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterAll
    public static void exitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("1. Тест названия блока")

    public void replenishmentTest() {
        //Найти блок "Пополнение"
        WebElement replBlock = driver.findElement(By.xpath("//*[@class='pay']"));
        assertTrue(replBlock.isDisplayed(), "Блока пополнение НЕТ.");

        //Проверить название блока
        WebElement blockTitle = replBlock.findElement(By.xpath("//h2[contains(text(), 'Онлайн пополнение')]"));
        String actualTitle = blockTitle.getText().replace("\n", " ").trim();
        String expectedTitle = "Онлайн пополнение без комиссии";
        assertEquals(expectedTitle, actualTitle, "Название блока НЕ правильное");
    }

    @Test
    @DisplayName("2. Тест наличия логотипов платежных систем")
    public void logoTest() {
        List<String> logosXpaths = List.of(
                "//img[@alt='Visa']",
                "//img[@alt='Verified By Visa']",
                "//img[@alt='MasterCard']",
                "//img[@alt='MasterCard Secure Code']",
                "//img[@alt='Белкарт']"
        );

        for (String locator : logosXpaths) {
            WebElement logo = driver.findElement(By.xpath(locator));
            assertTrue(logo.isDisplayed(), "Логотипа с XPath: " + locator + " НЕТ");
        }
    }

    @Test
    @DisplayName("3. Тест ссылки 'О сервисе'")
    public void linkTest() {
        WebElement link = driver.findElement(By.xpath("//a[text()='Подробнее о сервисе']"));
        link.click();
        clickCookies();

        WebElement content = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//h3[contains(text(),'Оплата банковской картой')]")));
        assertTrue(content.isDisplayed());
    }

    @Test
    @DisplayName("4. Тест кнопки 'Продолжить'")
    public void continueButtonTest() {
        //Ввести номер телефона
        WebElement phoneField = driver.findElement(By.id("connection-phone"));
        phoneField.sendKeys("297777777");

        //Ввести сумму
        WebElement amountField = driver.findElement(By.id("connection-sum"));
        amountField.sendKeys("20");

        WebElement submitButton = driver.findElement(By.xpath("//*[@id='pay-connection']//button[text()='Продолжить']"));
        submitButton.click();

        //Проверить проклятый iframe
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@class, 'bepaid-iframe')]")));
            WebElement iframeContent = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
            assertTrue(iframeContent.isDisplayed(), "iFrame НЕ появился. Кнопка НЕ работает.");
        } catch (TimeoutException e) {
            Assertions.fail("iFrame НЕ появился.");
        } finally {
            driver.switchTo().defaultContent();
        }
    }
}