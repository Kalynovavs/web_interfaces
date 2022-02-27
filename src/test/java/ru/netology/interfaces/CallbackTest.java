package ru.netology.interfaces;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CallbackTest {
    private WebDriver driver;
    @BeforeEach
    void setUp() {
        //System.setProperty("webdriver.chrome.driver", "C:\\tmp\\chromedriver.exe");
        driver = new ChromeDriver();
    }
    @AfterEach
    void tearDown() {
        driver.quit();
        driver =null;
    }

    @Test
    void shouldSubmitRequestSuccess() {
        open("http://localhost:9999");
        SelenideElement form =$(".form");
        form.$("[data-test-id=name] input").setValue("Василий");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldFormValidateReject() {
        open("http://localhost:9999");
        SelenideElement form =$(".form");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        form.$("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
}
