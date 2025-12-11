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
import static tests.data.TestData.*;

public class BookTests extends TestBase {

    @Test
    public void deleteBookTest() {
        AuthResponse authResponse = loginViaAPI();
        clearAndAddBook(authResponse);
        openProfileAndSetCookies(authResponse);
        deleteBook();
        verifyBookDeleted();
    }

    @Step("Вход через API и получение токена")
    private AuthResponse loginViaAPI() {
        AuthorizationAPI authorization = new AuthorizationAPI();
        return authorization.login(login, password);
    }

    @Step("Очистка корзины и добавление книги")
    private void clearAndAddBook(AuthResponse authResponse) {
        BasketAPI basket = new BasketAPI();
        basket.clearBasket(authResponse.getUserId(), authResponse.getToken());
        basket.addBook(authResponse.getUserId(), authResponse.getToken(), isbn);
    }

    @Step("Открываем профиль и устанавливаем куки для авторизации")
    private void openProfileAndSetCookies(AuthResponse authResponse) {
        open("/images/favicon.ico"); // нужен любой URL, чтобы Selenide инициализировал драйвер
        AuthUtils.setAuthCookies(authResponse);
        open("/profile");
    }

    @Step("Удаляем книгу из профиля")
    private void deleteBook() {
        profilePage.deleteBook()
                .closeModal();
    }

    @Step("Проверяем, что книга удалена")
    private void verifyBookDeleted() {
        profilePage.checkUserName("Biam")
                .shouldNotHaveBook(isbn);
    }
}