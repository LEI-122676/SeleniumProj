package  input_and_auth;

import org.junit.jupiter.api.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testes para Input numérico
 * Demonstra interação com campos de entrada de números
 */
public class InputsTest {
    private WebDriver driver;
    private InputsPage inputsPage;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        inputsPage = new InputsPage(driver);
        inputsPage.navigate();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Deve inserir um número válido no campo")
    public void testEnterValidNumber() {
        // Arrange
        String numberToEnter = "42";

        // Act
        inputsPage.enterNumber(numberToEnter);

        // Assert
        assertEquals(numberToEnter, inputsPage.getValue(),
                "O valor inserido deveria ser " + numberToEnter);
    }

    @Test
    @DisplayName("Deve incrementar o valor usando seta para cima")
    public void testIncrementValue() {
        // Arrange
        inputsPage.enterNumber("10");

        // Act
        inputsPage.incrementValue();

        // Assert
        assertEquals("11", inputsPage.getValue(),
                "O valor deveria ter incrementado de 10 para 11");
    }

    @Test
    @DisplayName("Deve decrementar o valor usando seta para baixo")
    public void testDecrementValue() {
        // Arrange
        inputsPage.enterNumber("10");

        // Act
        inputsPage.decrementValue();

        // Assert
        assertEquals("9", inputsPage.getValue(),
                "O valor deveria ter decrementado de 10 para 9");
    }

    @Test
    @DisplayName("Deve limpar o campo de input")
    public void testClearInput() {
        // Arrange
        inputsPage.enterNumber("100");

        // Act
        inputsPage.clearInput();

        // Assert
        assertEquals("", inputsPage.getValue(),
                "O campo deveria estar vazio após limpar");
    }

    @Test
    @DisplayName("Deve aceitar números negativos")
    public void testEnterNegativeNumber() {
        // Arrange
        String negativeNumber = "-25";

        // Act
        inputsPage.enterNumber(negativeNumber);

        // Assert
        assertEquals(negativeNumber, inputsPage.getValue(),
                "Deveria aceitar números negativos");
    }

    @Test
    @DisplayName("Deve realizar múltiplos incrementos consecutivos")
    public void testMultipleIncrements() {
        // Arrange
        inputsPage.enterNumber("0");

        // Act
        inputsPage.incrementValue();
        inputsPage.incrementValue();
        inputsPage.incrementValue();

        // Assert
        assertEquals("3", inputsPage.getValue(),
                "Após 3 incrementos de 0, o valor deveria ser 3");
    }

    @Test
    @DisplayName("Deve realizar múltiplos decrementos consecutivos")
    public void testMultipleDecrements() {
        // Arrange
        inputsPage.enterNumber("5");

        // Act
        inputsPage.decrementValue();
        inputsPage.decrementValue();

        // Assert
        assertEquals("3", inputsPage.getValue(),
                "Após 2 decrementos de 5, o valor deveria ser 3");
    }

    @Test
    @DisplayName("Deve alternar entre incremento e decremento")
    public void testAlternateIncrementDecrement() {
        // Arrange
        inputsPage.enterNumber("10");

        // Act
        inputsPage.incrementValue();  // 11
        inputsPage.incrementValue();  // 12
        inputsPage.decrementValue();  // 11
        inputsPage.incrementValue();  // 12

        // Assert
        assertEquals("12", inputsPage.getValue(),
                "O valor final deveria ser 12");
    }

    @Test
    @DisplayName("Deve verificar que o campo está visível na página")
    public void testInputFieldIsDisplayed() {
        // Assert
        assertTrue(inputsPage.isInputDisplayed(),
                "O campo de input deveria estar visível");
    }

    @Test
    @DisplayName("Deve substituir valor ao inserir novo número")
    public void testReplaceValue() {
        // Arrange
        inputsPage.enterNumber("100");

        // Act
        inputsPage.enterNumber("200");

        // Assert
        assertEquals("200", inputsPage.getValue(),
                "O valor deveria ter sido substituído para 200");
    }
}