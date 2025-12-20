package tests.models;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;

public class AuthUtils {

    public static void setAuthCookies(AuthResponse authResponse) {
        open("/images/favicon.ico");
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));
        open("/profile");
    }
}