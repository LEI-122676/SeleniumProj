package bookstore;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

// page_url = https://vaadin-bookstore-example.demo.vaadin.com/Inventory/new
public class ProductForm {
    private SelenideElement productNameInput() {
        return $$(shadowCss("input", "vaadin-text-field")).get(1);
    }

    // segundo => Price
    private SelenideElement priceInput() {
        return $$(shadowCss("input", "vaadin-text-field")).get(2);
    }

    // terceiro => In Stock
    private SelenideElement inStockInput() {
        return $$(shadowCss("input", "vaadin-text-field")).get(3);
    }

    public ProductForm setProductName(String name) {
        productNameInput().shouldBe(visible).setValue(name);
        return this;
    }

    public ProductForm setPrice(String price) {
        priceInput().shouldBe(visible).setValue(price);
        return this;
    }

    public ProductForm setInStock(String qty) {
        inStockInput().shouldBe(visible).setValue(qty);
        return this;
    }

    public ProductForm selectAvailability(String value) {
        // assume que só há um vaadin-select no formulário (o de Availability)
        SelenideElement availabilitySelect = $("vaadin-select");
        availabilitySelect.shouldBe(visible).click();

        // abre o dropdown e escolhe o vaadin-item com o texto desejado (ex: "Coming")
        $$("vaadin-item").findBy(text(value))
                .shouldBe(visible)
                .click();

        return this;
    }

    public ProductForm selectCategory(String categoryText) {
        // assume que as categorias são vaadin-checkbox com o texto da categoria
        SelenideElement checkbox =
                $$("vaadin-checkbox").findBy(text(categoryText));
        checkbox.shouldBe(visible).click();
        return this;
    }

    public void save() {
        // Botão Save
        SelenideElement saveButton =
                $x("//vaadin-button[contains(.,'Save')]");
        saveButton.shouldBe(enabled).click();
    }

}