package bookstore;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// page_url = https://vaadin-bookstore-example.demo.vaadin.com/
public class BookstoreMainPage {
    public BookstoreMainPage clickNewBook() {

        // Botão real no Vaadin Bookstore
        SelenideElement newProductButton =
                $x("//vaadin-button[contains(., 'New product')]");

        newProductButton.shouldBe(visible).click();
        return this ;
    }

    public void shouldContainBookWithTitle(String title) {
        $$(shadowCss("input", "vaadin-text-field")).get(0).setValue(title).pressEnter();
        $("vaadin-grid").shouldHave(text(title));
    }

}