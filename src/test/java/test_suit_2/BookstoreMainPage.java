package test_suit_2;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class BookstoreMainPage {


    public BookstoreMainPage() {
        $("body").shouldBe(visible);
    }

    public BookstoreAdminPage goToAdminView() {
        SelenideElement adminLink = $(byText("Admin"));
        adminLink.shouldBe(visible).click();
        return new BookstoreAdminPage();
    }
}