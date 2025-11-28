package testSuite3;

import static com.codeborne.selenide.Selenide.*;
import org.junit.jupiter.api.*;
import com.codeborne.selenide.Configuration;

public class vaadinDatabaseExampleDemoTest {

    vaadinDatabaseExampleDemoPage page = new vaadinDatabaseExampleDemoPage();

    @BeforeAll
    static void setup() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 8000;
    }

    @BeforeEach
    void openApplication() {
        page.openPage();
    }

    @Test
    @DisplayName("Grid carrega com dados")
    void testGridLoads() {
        page.checkGridHasRows();
    }

    @Test
    @DisplayName("Ordenar por título")
    void testSortByTitle() {
        page.sortByTitle();
        page.checkGridHasRows();
    }

    @Test
    @DisplayName("Ordenar por ano")
    void testSortByYear() {
        page.sortByYear();
        page.checkGridHasRows();
    }

    @Test
    @DisplayName("Abrir link IMDb")
    void testOpenImdbLink() {
        page.clickFirstImdb();
        page.checkImdbOpened();
    }
}
