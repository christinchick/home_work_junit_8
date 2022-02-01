package com.christimchick;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

public class ParameterizedTestHome {

    @DisplayName("Тест с ValueSource")
    @ValueSource(strings = {"Volvo S80", "Toyota Carina"})
    @ParameterizedTest(name = "Тестирование общего алгоритма поиска с тестовыми данными: {0}")
    void valueSearchTest(String testData) {
            open("https://www.drive.ru");
            $(".site-search-query").setValue(testData).click();
            $("button[type='submit']").click();
            $$("div.gsc-webResult")
                    .first()
                    .shouldHave(Condition.text(testData));
        }

    @DisplayName("Тест с CsvSource")
    @CsvSource(value = {
           "Volvo S80, Volvo S80 сочетает в себе качества кресла",
           "Toyota Carina, В 1998 году Toyota Carina E канула в Лету" })
    @ParameterizedTest(name = "Тестирование общего алгоритма поиска с тестовыми данными: {0}")
    void cvsTest(String testData, String expectedResult) {
            open("https://www.drive.ru");
            $(".site-search-query").setValue(testData).click();
            $("button[type='submit']").click();
            $$("div.gsc-webResult")
                    .first()
                    .shouldHave(Condition.text(expectedResult));
    }
    static Stream<Arguments> commonMetodSearchTest() {
        return Stream.of(
                Arguments.of("Volvo S80", "Volvo S80 сочетает в себе качества кресла"),
                Arguments.of("Toyota Carina", "В 1998 году Toyota Carina E канула в Лету")
        );
    }
    @DisplayName("Тест с MethodSource")
    @MethodSource("commonMetodSearchTest")
    @ParameterizedTest(name = "Тестирование общего алгоритма поиска с тестовыми данными: {0}")
    void methodSearchTest(String testData, String expectedResult) {
        open("https://www.drive.ru");
        $(".site-search-query").setValue(testData).click();
        $("button[type='submit']").click();
        $$("div.gsc-webResult")
                .first()
                .shouldHave(Condition.text(expectedResult));

    }
}