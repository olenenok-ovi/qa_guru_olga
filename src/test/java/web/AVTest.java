package web;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperties;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static controllers.propertyLoader.*;

public class AVTest {

    @BeforeEach
     public void avCloseModalTest() {
        // открыть сайт av.ru
        open("https://av.ru");
        //выбрать регион Москва
        $(".b-region-modal__button").click();
    }

    @Test
    void avSearchTest() {
        // клик на кнопку поиска
        $(".js-quicksearch").click();
        // вводим в строку поиска vici
        $(byName("text")).setValue("vici").pressEnter();
        // проверяем, что результат поиска не ноль
        $(".b-product-menu__count").shouldNotHave(text("(0)"));
        // проверяем, что первый элемент в названии содержит Vici, название - в атрибуте элемента
        $(".b-product").shouldHave(attributeMatching("data-name", ".*VICI.*" ));
    }

    @Test
    void avAuthTest(){
        // инициализация данных
        String mail = loadProperty(EMAIL);
        String password = loadProperty(PASSWORD);

        // клик на кнопку войти
        $(".js-auth-modal").click();

        // установка пары логин-пароль
        $(byName("j_username")).setValue(mail);
        $(byName("j_password")).setValue(password);
        $(".js-login").click();

        // проверка, что в заголовке появилось имя пользователя
        $(".b-header-dropdown__title_login").shouldHave(text("Авто"));
    }
}
