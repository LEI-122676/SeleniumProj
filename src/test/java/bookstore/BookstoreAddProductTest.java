package bookstore;

import bookstore.ProductForm;
import bookstore.BookstoreMainPage;
import bookstore.LoginPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@Epic("Bookstore")
@Feature("Gestão de Livros")
public class BookstoreAddProductTest {

    @BeforeAll
    static void setUpAll() {

        Configuration.baseUrl = "https://vaadin-bookstore-example.demo.vaadin.com";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 8000; // 8 segundos

        // Integração com Allure
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );
    }

    @Test
    @Story("Adicionar novo livro com sucesso")
    void AdicionarNovoLivro() {
        String titulo = "Livro de Teste Selenium";

        // 1) Login como admin
        LoginPage loginPage = new LoginPage().openPage();
        loginPage.loginAsAdmin();

        // 2) Abrir formulário "New book"
        BookstoreMainPage mainPage = new BookstoreMainPage();
        mainPage.clickNewBook();

        // 3) Preencher formulário e guardar
        ProductForm form = new ProductForm()
                .setProductName("Livro de Teste Selenium")
                .setPrice("19.99")
                .setInStock("50")
                .selectAvailability("Available  ")
                .selectCategory("Romance");
        form.save();

        // 4) Verificar que o livro aparece na grelha
        mainPage.shouldContainBookWithTitle(titulo);
    }
}