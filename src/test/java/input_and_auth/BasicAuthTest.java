package input_and_auth;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Testes para Basic Authentication
 * Demonstra interação com autenticação HTTP Basic
 */
public class BasicAuthTest {
    private WebDriver driver;
    private BasicAuthPage basicAuthPage;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        basicAuthPage = new BasicAuthPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Deve autenticar com credenciais válidas (admin/admin)")
    public void testSuccessfulAuthentication() {
        // Arrange
        String username = "admin";
        String password = "admin";

        // Act
        basicAuthPage.navigateWithAuth(username, password);

        // Assert
        assertTrue(basicAuthPage.isAuthenticationSuccessful(),
                "A autenticação deveria ter sido bem-sucedida com admin/admin");

        String successText = basicAuthPage.successMessage.getText();
        assertEquals("Basic Auth", successText,
                "O título da página deveria ser 'Basic Auth'");

        String congratsText = basicAuthPage.getCongratulationsText();
        assertTrue(congratsText.contains("Congratulations"),
                "Deveria exibir mensagem de congratulações");
    }

    @Test
    @DisplayName("Deve autenticar usando método com credenciais padrão")
    public void testDefaultAuthenticationMethod() {
        // Act
        basicAuthPage.navigateWithDefaultAuth();

        // Assert
        assertTrue(basicAuthPage.isAuthenticationSuccessful(),
                "A autenticação com credenciais padrão deveria funcionar");
        assertEquals("Basic Auth", basicAuthPage.successMessage.getText());
    }

    @Test
    @DisplayName("Deve falhar com username incorreto")
    public void testFailedAuthenticationWrongUsername() {
        // Arrange
        String username = "wronguser";
        String password = "admin";  // senha correta, mas user errado

        // Act
        basicAuthPage.navigateWithAuth(username, password);

        // Assert
        assertFalse(basicAuthPage.isAuthenticationSuccessful(),
                "A autenticação deveria falhar com username incorreto");
    }

    @Test
    @DisplayName("Deve falhar com password incorreto")
    public void testFailedAuthenticationWrongPassword() {
        // Arrange
        String username = "admin";  // usuário correto
        String password = "wrongpass";  // senha incorreta

        // Act
        basicAuthPage.navigateWithAuth(username, password);

        // Assert
        assertFalse(basicAuthPage.isAuthenticationSuccessful(),
                "A autenticação deveria falhar com password incorreto");
    }

    @Test
    @DisplayName("Deve autenticar apenas com username correto e password correto")
    public void testAuthenticationWithCorrectCredentials() {
        // Arrange
        String username = "admin";
        String password = "admin";

        // Act
        basicAuthPage.navigateWithAuth(username, password);

        // Assert
        assertTrue(basicAuthPage.isAuthenticationSuccessful());

        // Verifica que os elementos estão presentes na página
        assertNotNull(basicAuthPage.successMessage,
                "A mensagem de sucesso deveria estar presente");
        assertNotNull(basicAuthPage.congratulationsText,
                "O texto de congratulações deveria estar presente");
    }

    @Test
    @DisplayName("Deve validar o conteúdo completo da página após autenticação")
    public void testPageContentAfterAuthentication() {
        // Arrange & Act
        basicAuthPage.navigateWithAuth("admin", "admin");

        // Assert
        String pageTitle = basicAuthPage.successMessage.getText();
        String pageContent = basicAuthPage.getCongratulationsText();

        assertAll("Validação do conteúdo da página",
                () -> assertEquals("Basic Auth", pageTitle),
                () -> assertTrue(pageContent.contains("Congratulations")),
                () -> assertTrue(pageContent.contains("proper credentials")),
                () -> assertTrue(basicAuthPage.isAuthenticationSuccessful())
        );
    }
}