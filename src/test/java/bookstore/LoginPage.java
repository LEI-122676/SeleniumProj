package bookstore;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// page_url = https://vaadin-bookstore-example.demo.vaadin.com/
public class LoginPage {

    public LoginPage openPage() {
        open("/"); // abre o baseUrl definido na BaseTest
        return this;
    }

    public void loginAs(String username, String password) {
        // Espera que a página carregue
        // Username
        SelenideElement usernameField =
                $("input[id*='user'], input[name*='user'], input[autocomplete='username']");
        usernameField.shouldBe(visible).setValue(username);

        // Campo de password
        SelenideElement passwordField =
                $("input[type='password'], input[id*='pass'], input[name*='pass']");
        passwordField.shouldBe(visible).setValue(password);

        // Botão de login
        SelenideElement loginButton =
                $("vaadin-button[part='vaadin-login-submit']");
        loginButton.shouldBe(enabled).click();
    }

    public void loginAsAdmin() {
        loginAs("admin", "admin");
    }
}

