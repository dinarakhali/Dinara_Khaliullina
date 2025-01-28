package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MtsPage;
import pages.MtsPaymentPage;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsTest {

    private WebDriver driver;

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
    }

    @Test
    public void mtsBlockTest() {
        //Открыть сайт
        driver.get("https://www.mts.by/");
        System.out.println("Сайт открыт.");

        //Создаем объект PageObject.
        MtsPage page = new MtsPage(driver);

        //Если появится куки
        page.acceptCookies();

        //Проверить работу ссылки "О сервисе"
        page.linkClick();
        page.acceptCookies();
        if (page.isCorrectTitleDisplayed()) {
            System.out.println("Ссылка работает.");
        } else {
            System.out.println("Ссылка НЕ работает.");
        }
        page.goBack();
        page.acceptCookies();

        //Проверка текста в незаполненных полях в "Услуги связи"
        page.verifyPlaceholderText("phoneField", "Номер телефона");
        page.verifyPlaceholderText("amountField", "Сумма");
        page.verifyPlaceholderText("emailField", "E-mail для отправки чека");

        //Переключение выпадающего списка
        page.selectSecondOption();

        //Проверка текста в незаполненных полях в "Домашний интернет"
        page.verifyPlaceholderText("internetPhoneField", "Номер абонента");
        page.verifyPlaceholderText("internetSumField", "Сумма");
        page.verifyPlaceholderText("internetEmailField", "E-mail для отправки чека");

        //Переключение выпадающего списка
        page.selectThirdOption();

        //Проверка текста в незаполненных полях в "Рассрочка"
        page.verifyPlaceholderText("instalmentNumberField", "Номер счета на 44");
        page.verifyPlaceholderText("instalmentSumField", "Сумма");
        page.verifyPlaceholderText("instalmentEmailField", "E-mail для отправки чека");

        //Переключение выпадающего списка
        page.selectFourthOption();

        //Проверка текста в незаполненных полях в "Задолженность"
        page.verifyPlaceholderText("arrearsNumberField", "Номер счета на 2073");
        page.verifyPlaceholderText("arrearsSumField", "Сумма");
        page.verifyPlaceholderText("arrearsEmailField", "E-mail для отправки чека");

        //Переключение выпадающего списка
        page.selectFirstOption();

        //Проверить заголовок блока "Пополнение"
        String actualTitle = page.getBlockTitle();
        String expectedTitle = "Онлайн пополнение без комиссии";
        assertEquals(expectedTitle, actualTitle, "Название блока НЕ совпадает.");

        //Проверить наличие логотипов платежных систем
        assertTrue(page.isLogosDisplayed(), "Логотипы НЕ отображаются.");

        //Ввести номер телефона и сумму
        page.enterTextInField("phoneField", "297777777");
        page.enterTextInField("amountField", "20");

        //Проверить работу кнопки "Отправить"
        page.clickSubmitButton();
        assertTrue(page.isIframeDisplayed(), "iFrame НЕ появился после нажатия кнопки.");

        //Закрыть браузер и завершить работу драйвера.
        driver.quit();
        System.out.println("* Браузер закрыт.");
    }

    /*
    @Test
    public void mtsPaymentTest() {
        //Открыть сайт
        driver.get("https://www.mts.by/");
        System.out.println("Сайт открыт.");

        //Создаем объекты PageObject
        MtsPage page = new MtsPage(driver);
        MtsPaymentPage paymentPage = new MtsPaymentPage(driver);

        //Если появится куки
        page.acceptCookies();

        //Ввести номер телефона и сумму
        page.enterTextInField("phoneField", "297777777");
        page.enterTextInField("amountField", "20");
        page.clickSubmitButton();

        // Переключаемся в iframe
        paymentPage.switchToIframe();

        // Проверяем номер
        String headerText = paymentPage.getPaymentHeaderText();
        assertTrue(headerText.contains("Номер:375297777777"), "Номер в заголовке НЕ правильный");

        // Проверяем текст на кнопке оплаты
        // Вариант 1: Прямое сравнение
        String buttonText = paymentPage.getPayButtonText();
        assertEquals(buttonText, "Оплатить 20.00 BYN", "Текст на кнопке НЕ правильный!");

        // Проверяем текст подсказок в полях
        //assertEquals("Номер карты", paymentPage.getCardNumberPlaceholder(), "Текст подсказки в поле 'Номер карты' НЕ правильный");
        String labelTextJs = paymentPage.getCardNumberLabelTextWithJs();
        assertEquals(labelTextJs, "Номер карты", "Текст 'Номер карты' через JS НЕ совпадает!");

        assertEquals("Срок действия", paymentPage.getExpirationDatePlaceholder(), "Текст подсказки в поле 'Дата окончания карты' НЕ правильный");
        assertEquals("CVC", paymentPage.getCvcPlaceholder(), "Текст подсказки в поле 'CVC' НЕ правильный");
        assertEquals("Имя держателя (как на карте)", paymentPage.getCardHolderPlaceholder(), "Текст подсказки в поле 'Имя держателя карты' НЕ правильный");

        // Возвращаемся в основной контекст
        paymentPage.switchToDefaultContent();

        //Закрыть браузер и завершить работу драйвера.
        driver.quit();
        System.out.println("* Браузер закрыт.");
    }
    */
}