package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProfilePage {

    private SelenideElement
            deleteBookButton = $("#delete-record-undefined"),
            userName = $("#userName-value"),
            closeModalButton = $("#closeSmallModal-ok");

    private ElementsCollection
            bookLinks = $$(".mr-2 a");

    public ProfilePage checkUserName(String user) {
        userName.shouldHave(Condition.text(user));
        return this;
    }

    public ProfilePage shouldNotHaveBook(String isbn) {
        bookLinks.findBy(Condition.attribute("href", isbn)).shouldNot(exist);
        return this;

    }

    public ProfilePage deleteBook() {
        deleteBookButton.click();
        return this;
    }

    public ProfilePage closeModal() {
        closeModalButton.click();
        return this;
    }
}