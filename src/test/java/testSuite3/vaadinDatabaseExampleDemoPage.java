package testSuite3;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;


public class vaadinDatabaseExampleDemoPage {

    private final String URL = "https://vaadin-database-example.demo.vaadin.com";

    // Grid
    private SelenideElement grid = $("vaadin-grid");

    // Cabeçalhos (coleção -> findBy)
    private SelenideElement titleHeader =
            $$("vaadin-grid-cell-content").findBy(text("Title"));

    private SelenideElement yearHeader =
            $$("vaadin-grid-cell-content").findBy(text("Release"));

    private SelenideElement directorHeader =
            $$("vaadin-grid-cell-content").findBy(text("Director"));

    // Primeiro link IMDb
    private SelenideElement firstImdbLink =
            $$("a").first();

    public void openPage() {
        open(URL);
        grid.shouldBe(visible);
    }

    public void sortByTitle() {
        titleHeader.click();
    }

    public void sortByYear() {
        yearHeader.click();
    }

    public void clickFirstImdb() {
        firstImdbLink.shouldBe(visible).click();
    }

    public void checkImdbOpened() {
        switchTo().window(1);
        webdriver().shouldHave(urlContaining("imdb.com"));
    }

    public void checkGridHasRows() {
        $$("vaadin-grid-cell-content").first().shouldBe(visible);
    }
}
