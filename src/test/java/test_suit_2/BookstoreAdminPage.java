package test_suit_2;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.shadowCss;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.actions;

public class BookstoreAdminPage {

    private final SelenideElement newCategoryButton = $(byText("New category")); // Adjusted text based on demo conventions

    private final SelenideElement categoryNameInput = $("input");

    public BookstoreAdminPage clickAddCategory() {
        if (newCategoryButton.exists()) {
            newCategoryButton.click();
        } else {
            $(byText("Add New Category")).click();
        }
        return this;
    }

    public void fillCategoryNameAndEnter(String name) {
        // <input part="value" tabindex="0" aria-labelledby="vaadin-text-field-input-61" required="">

        SelenideElement categoryNameInput = $$(shadowCss("input", "vaadin-text-field"))
                .filter(visible)
                .filter(enabled)
                .last();

        categoryNameInput.setValue(name).pressEnter();
    }

}