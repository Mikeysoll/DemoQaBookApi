package tests;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import tests.api.AuthorizationAPI;
import tests.api.BasketAPI;
import tests.base.TestBase;
import tests.models.AddBookResponse;
import tests.models.AuthResponse;
import tests.models.AuthUtils;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static tests.data.TestData.*;

public class BookTests extends TestBase {

    @Test
    public void deleteBookTest() {

        AuthorizationAPI authorization = new AuthorizationAPI();
        BasketAPI basket = new BasketAPI();

        AuthResponse authResponse = step("Вход через API и получение токена", () ->
                    authorization.login(login, password)
        );

        step("Очистка корзины и добавление книги", () -> {
            basket.clearBasket(authResponse.getUserId(), authResponse.getToken());
            basket.addBook(authResponse.getUserId(), authResponse.getToken(), isbn);
        });

        step("Открываем профиль и устанавливаем куки для авторизации", () -> {
            AuthUtils.setAuthCookies(authResponse);
        });

        step("Удаляем книгу из профиля", () -> {
            profilePage.deleteBook()
                    .closeModal();
        });

        step("Проверяем, что книга удалена", () -> {
            profilePage.checkUserName("Biam")
                    .shouldNotHaveBook(isbn);
        });
    }
}
