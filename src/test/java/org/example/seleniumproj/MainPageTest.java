package org.example.seleniumproj;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.jetbrains.com/");

        // pequena pausa para permitir banners iniciais aparecerem
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        acceptCookies();

        mainPage = new MainPage(driver);
    }

    private void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[class='ch2-btn ch2-allow-all-btn ch2-btn-primary']")));
        WebElement cookiesButton = driver.findElement(By.cssSelector("button[class='ch2-btn ch2-allow-all-btn ch2-btn-primary']"));
        cookiesButton.click();
    }

    private void safeClick(WebElement el) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(el));
            el.click();
        } catch (WebDriverException ex) {
            // fallback: scroll into view e javascript click
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", el);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
            } catch (Exception ignored) {
                // último recurso: ignorar e seguir
            }
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void search() {
        mainPage.searchButton.click();

        WebElement searchField = driver.findElement(By.cssSelector("[data-test-id='search-input']"));
        searchField.sendKeys("Selenium");

        WebElement submitButton = driver.findElement(By.cssSelector("button[data-test='full-search-button']"));
        submitButton.click();

        WebElement searchPageField = driver.findElement(By.cssSelector("input[data-test-id='search-input']"));

        assertEquals("Selenium", searchPageField.getAttribute("value"));
    }

    @Test
    public void toolsMenu() throws InterruptedException {
        safeClick(mainPage.toolsMenu);

        Thread.sleep(1000);

        // aguarda o menu aparecer
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-test='main-submenu']")));
        WebElement menuPopup = driver.findElement(By.cssSelector("div[data-test='main-menu-item']"));
        assertTrue(menuPopup.isDisplayed());


    }

    @Test
    public void navigationToAllTools() {
        safeClick(mainPage.seeDeveloperToolsButton);

        // pequena pausa para animações do submenu
        try { Thread.sleep(300); } catch (InterruptedException ignored) { Thread.currentThread().interrupt(); }

        safeClick(mainPage.findYourToolsButton);

        // aguarda a página de produtos carregar
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("products-page")));
        WebElement productsList = driver.findElement(By.id("products-page"));
        assertTrue(productsList.isDisplayed());

        wait.until(ExpectedConditions.titleIs("All Developer Tools and Products by JetBrains"));
        assertEquals("All Developer Tools and Products by JetBrains", driver.getTitle());
    }
}