package input_and_auth;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object para a página de Inputs
 * URL: https://the-internet.herokuapp.com/inputs
 */
public class InputsPage {

    @FindBy(css = "input[type='number']")
    public WebElement numberInput;

    @FindBy(css = "div.example h3")
    public WebElement pageTitle;

    private WebDriver driver;

    public InputsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Navega para a página de inputs
     */
    public void navigate() {
        driver.get("https://the-internet.herokuapp.com/inputs");
    }

    /**
     * Insere um número no campo de input
     * @param number número a ser inserido
     */
    public void enterNumber(String number) {
        numberInput.clear();
        numberInput.sendKeys(number);
    }

    /**
     * Incrementa o valor usando a seta para cima
     */
    public void incrementValue() {
        numberInput.sendKeys(Keys.ARROW_UP);
    }

    /**
     * Decrementa o valor usando a seta para baixo
     */
    public void decrementValue() {
        numberInput.sendKeys(Keys.ARROW_DOWN);
    }

    /**
     * Obtém o valor atual do input
     * @return valor do campo de input
     */
    public String getValue() {
        return numberInput.getAttribute("value");
    }

    /**
     * Limpa o campo de input
     */
    public void clearInput() {
        numberInput.clear();
    }

    /**
     * Verifica se o campo de input está visível
     * @return true se o input está visível
     */
    public boolean isInputDisplayed() {
        return numberInput.isDisplayed();
    }
}