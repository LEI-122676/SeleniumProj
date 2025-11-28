package input_and_auth;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object para a página de Basic Authentication
 * URL: https://the-internet.herokuapp.com/basic_auth
 */
public class BasicAuthPage {

    @FindBy(css = "div.example h3")
    public WebElement successMessage;

    @FindBy(css = "div.example p")
    public WebElement congratulationsText;

    private WebDriver driver;

    public BasicAuthPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Navega para a página com autenticação básica
     * Credenciais padrão: username=admin, password=admin
     * @param username usuário para autenticação
     * @param password senha para autenticação
     */
    public void navigateWithAuth(String username, String password) {
        String url = String.format("https://%s:%s@the-internet.herokuapp.com/basic_auth",
                username, password);
        driver.get(url);
    }

    /**
     * Navega para a página com as credenciais padrão (admin/admin)
     */
    public void navigateWithDefaultAuth() {
        navigateWithAuth("admin", "admin");
    }

    /**
     * Verifica se a autenticação foi bem-sucedida
     * @return true se a mensagem de sucesso está visível
     */
    public boolean isAuthenticationSuccessful() {
        try {
            return successMessage.isDisplayed() &&
                    successMessage.getText().contains("Basic Auth");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtém o texto de congratulações
     * @return texto da mensagem de congratulações
     */
    public String getCongratulationsText() {
        return congratulationsText.getText();
    }
}