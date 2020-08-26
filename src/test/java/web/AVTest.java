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

public class AVTest {

    @BeforeEach
     public void avCloseModalTest() {
        // открыть сайт av.ru
        open("https://av.ru");
        //выбрать регион Москва
        $(byXpath("//div[@class='b-region-modal__button b-button b-button_green']")).click();
    }

    @Test
    void avSearchTest() {
        // клик на кнопку поиска
        $(byXpath("//button[@class='b-button b-header-search__btn js-quicksearch']")).click();
        // вводим в строку поиска vici
        $(byName("text")).setValue("vici").pressEnter();
        // проверяем, что результат поиска не ноль
        $(byXpath("//span[@class='b-product-menu__count']")).shouldNotHave(text("(0)"));
        // проверяем, что первый элемент в названии содержит Vici, название - в атрибуте элемента
        $(byXpath("//div[@class='b-grid__item']/div")).shouldHave(attributeMatching("data-name", ".*VICI.*" ));
    }
}
