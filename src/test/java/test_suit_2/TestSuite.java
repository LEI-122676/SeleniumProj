package test_suit_2;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSuite {

    static LoginPage loginPage;
    static BookstoreMainPage mainPage;
    static BookstoreAdminPage adminPage;

    @BeforeAll
    public static void setupAndLogin() {
        // Configuration
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000; // 10 seconds wait for Vaadin elements

        // Initialize Pages
        loginPage = new LoginPage();

        // Perform Login Flow once
        mainPage = loginPage.open().loginAs("admin", "admin");
    }

    @AfterAll
    public static void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    public void testAddNewCategory() {
        // Navigate to Admin View
        adminPage = mainPage.goToAdminView();

        // Perform Add Action
        adminPage.clickAddCategory().fillCategoryNameAndEnter("LEI-122676");
    }

}