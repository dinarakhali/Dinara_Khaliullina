import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.time.Duration;


public class ReplenishmentTest {
    private WebDriver driver;

    @BeforeAll
    public static void setDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void replenishmentTest() {
        // 1. Открыть сайт
        driver.get("https://www.mts.by/");
        System.out.println("1. Сайт открыт.");

        //Если появится куки
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement cookieButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cookie-agree")));
            cookieButton.click();
            System.out.println("* Куки принято. \n---");
        } catch (TimeoutException | ElementNotInteractableException e) {
            System.out.println("* Куки не запрашиваются. \n---");
        }

        // 2. Проверить работу ссылки "О сервисе"
        WebElement conditionsLink = driver.findElement(By.xpath("//a[text()='Подробнее о сервисе']"));
        conditionsLink.click();

        //Если появится куки
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement cookieButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cookie-agree")));
            cookieButton.click();
            System.out.println("* Куки принято.");
        } catch (TimeoutException | ElementNotInteractableException e) {
            System.out.println("* Куки не запрашиваются.");
        }

        if (driver.getTitle().contains("Порядок оплаты и безопасность интернет платежей")) {
            System.out.println("2. Ссылка 'Подробнее о сервисе' работает. \n---");
        } else {
            System.out.println("2. Ссылка 'Подробнее о сервисе' НЕ работает. \n---");
        }
        driver.navigate().back();

        //Если появится куки
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement cookieButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cookie-agree")));
            cookieButton.click();
            System.out.println("* Куки принято.");
        } catch (TimeoutException | ElementNotInteractableException e) {
            System.out.println("* Куки не запрашиваются.");
        }

        // 3. Найти блок "Пополнение"
        WebElement replBlock = driver.findElement(By.xpath("//*[@class='pay']"));

        if (replBlock.isDisplayed()) {
            System.out.println("3. Блок 'Пополнение' найден.");
        } else {
            System.out.println("3. Блок 'Пополнение' НЕ найден.");
            return;
        }

        // 4. Проверить название блока
        WebElement blockTitle = replBlock.findElement(By.xpath("//h2[contains(text(), 'Онлайн пополнение')]"));
        String actualTitle = blockTitle.getText().replace("\n", " ").trim();
        String expectedTitle = "Онлайн пополнение без комиссии";

        if (actualTitle.equals(expectedTitle)) {
            System.out.println("4. Название блока верное.");
        } else {
            System.out.println("4. Название блока НЕ совпадает. Ожидалось: " + expectedTitle + ", Получено: " + actualTitle + ".");
        }

        // 5. Проверить наличие логотипов платежных систем
        WebElement logo1 = replBlock.findElement(By.xpath("//img[@alt='Visa']"));
        WebElement logo2 = replBlock.findElement(By.xpath("//img[@alt='Verified By Visa']"));
        WebElement logo3 = replBlock.findElement(By.xpath("//img[@alt='MasterCard']"));
        WebElement logo4 = replBlock.findElement(By.xpath("//img[@alt='MasterCard Secure Code']"));
        WebElement logo5 = replBlock.findElement(By.xpath("//img[@alt='Белкарт']"));

        if (logo1.isDisplayed() && logo2.isDisplayed() && logo3.isDisplayed() && logo4.isDisplayed() && logo5.isDisplayed()) {
            System.out.println("5. Логотипы найдены.");
        } else {
            System.out.println("5. Логотипы НЕ найдены.");
        }

        // 6. Ввести номер телефона в поле
        WebElement phoneField = driver.findElement(By.id("connection-phone"));
        phoneField.sendKeys("297777777");
        System.out.println("6. Номер телефона введён.");

        // 7. Ввести сумму в поле
        WebElement amountField = driver.findElement(By.id("connection-sum"));
        amountField.sendKeys("20");
        System.out.println("7. Сумма введена.");

        // 8. Проверить работу кнопки "Отправить"
        WebElement submitButton = driver.findElement(By.xpath("//*[@id='pay-connection']//button[text()='Продолжить']"));
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[contains(@class, 'bepaid-iframe')]")));
            System.out.println("8. iFrame появился. Кнопка работает. \n---");
        } catch (TimeoutException e) {
            System.out.println("8. iFrame НЕ появился. Кнопка НЕ работает. \n---");
        }

        //Закрыть браузер и завершить работу драйвера.
        driver.quit();
        System.out.println("* Браузер закрыт.");
    }
}