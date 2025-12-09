package tests;
import org.openqa.selenium.Cookie;
import org.junit.jupiter.api.Test;
import tests.api.AuthorizationAPI;
import tests.api.BasketAPI;
import tests.base.TestBase;
import tests.models.AddBookResponse;
import tests.models.AuthResponse;

import static com.codeborne.selenide.Condition.*;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static tests.data.TestData.*;


public class BookTests extends TestBase {
    @Test
    public void deleteBookTest(){
        AuthorizationAPI authorization = new AuthorizationAPI();
        AuthResponse authResponse = authorization.login(login, password);

        BasketAPI basket = new BasketAPI();
        basket.clearBasket(authResponse.getUserId(), authResponse.getToken());
        AddBookResponse add = basket.addBook(authResponse.getUserId(), authResponse.getToken(), isbn);

        open("/images/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));

        open("/profile");
        $("#userName-value").shouldHave(text("Biam"));
        $(".mr-2 a").shouldHave(attributeMatching("href", ".*" + isbn));
        $("#delete-record-undefined").click();
        $("#closeSmallModal-ok").click();
    }

}