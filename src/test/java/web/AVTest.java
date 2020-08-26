package web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperties;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static controllers.propertyLoader.*;

public class AVTest {

    @BeforeEach
     public void avCloseModalTest() {
        // открыть сайт av.ru
        open("https://av.ru");
        //выбрать регион Москва
        $(".b-region-modal__button").click();
    }

    @AfterEach
    public void avCoseBrowser(){
        clearBrowserCookies();
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

    @Test
    void avChangeRegion(){
        //в шапке нажать кнопку смены региона
        $(".b-header-dropdown_top").click();

        //выбрать Санкт-Петербург
        $(".js-region-change").click();

        //Проверить, что выбран Санкт-Петербург
        $(".b-header-dropdown__title").shouldHave(text("Санкт-Петербург"));
    }

    @Test
    void avSelectTime(){
        //кликнуть на смену времени
        $(".b-header-nav__timeslot-choose").click();

        //Ввод адреса
        $(byName("address")).setValue("Россия, Москва, Выползов переулок, 7").pressEnter();

        //Ввод телефона
        $(byName("phone")).setValue("1234567890").pressEnter();

        // Нажать на кнопку выбрать время
        $(".js-save-address").click();

        //Выбор слота доставки
        $(".b-button_reactive").click();

        //Проверить, что отображается правильный таймслот
        $(".b-header-nav__timeslot-date").shouldHave(text("За 60 минут"));
    }

    @Test
    void avOpenNews() {

        //Кликаем на новинки в меню
        $(byText("Новинки")).click();
        $(byText("Новинки онлайн")).click();

        //Проверяем, что у товаров нужный шильдик
        $(".b-product-tag").shouldHave(text("Новинка Онлайн"));

    }
}
