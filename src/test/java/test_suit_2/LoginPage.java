package test_suit_2;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    // We start at the Admin URL, which redirects to login if unauthenticated
    private static final String URL = "https://vaadin-bookstore-example.demo.vaadin.com/Admin";

    private final SelenideElement usernameField = $("input[label='Username']"); // Common Vaadin selector or use name="username"
    private final SelenideElement passwordField = $("input[label='Password']");
    private final SelenideElement loginButton = $(byText("Log in"));

    public LoginPage open() {
        Selenide.open(URL);
        return this;
    }

    public BookstoreMainPage loginAs(String username, String password) {
        if (!usernameField.exists()) {
            $("input[name='username']").setValue(username);
            $("input[name='password']").setValue(password);
        } else {
            usernameField.setValue(username);
            passwordField.setValue(password);
        }

        loginButton.click();

        // Return the next page in the flow
        return new BookstoreMainPage();
    }
}