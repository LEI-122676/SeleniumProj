package Elements122703;

import Elements122703.Elements;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class ElementsTest {
    private WebDriver driver;
    private Elements Elements;
    private WebDriverWait wait;
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Elements = new Elements(driver);
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkBox() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        boolean isChecked = Elements.checkBox.isSelected();
        assertFalse(isChecked);
        Elements.checkBox.click();
        isChecked = Elements.checkBox.isSelected();
        assertTrue(isChecked);
        Elements.checkBox.click();
        isChecked = Elements.checkBox.isSelected();
        assertFalse(isChecked);
    }

    @Test
    public void dropdown() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        Elements.dropdown.click();
        WebElement option1 = driver.findElement(By.xpath("//option[@value='1']"));
        WebElement option2 = driver.findElement(By.xpath("//option[@value='2']"));
        option1.click();
        assertTrue(option1.isSelected());
        option2.click();
        assertTrue(option2.isSelected());
    }








}
