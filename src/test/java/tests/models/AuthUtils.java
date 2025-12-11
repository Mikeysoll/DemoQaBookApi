package tests.models;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;
import tests.models.AuthResponse;

public class AuthUtils {

    public static void setAuthCookies(AuthResponse authResponse) {
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));
    }
}