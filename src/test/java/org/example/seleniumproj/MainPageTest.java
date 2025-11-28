package org.example.seleniumproj;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.jetbrains.com/");
        acceptCookies();
        mainPage = new MainPage(driver);

    }
    private void acceptCookies() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            By cookieButton = By.xpath(
                    "//button[" +
                            "contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'accept') " +
                            "]"
            );
            WebElement accept = wait.until(ExpectedConditions.elementToBeClickable(cookieButton));
            accept.click();
        } catch (Exception ignored) {
            // Cookie banner not found or could not be clicked — continue tests
        }
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void search() {
        mainPage.searchButton.click();

        WebElement searchField = driver.findElement(By.cssSelector("[data-test-id='search-input']"));
        // Alterei de data-test='search-input' para data-test-id='search-input'
        searchField.sendKeys("Selenium");

        WebElement submitButton = driver.findElement(By.cssSelector("button[data-test='full-search-button']"));
        submitButton.click();

        WebElement searchPageField = driver.findElement(By.cssSelector("input[data-test-id='search-input']"));
        // Alterei de data-test='search-input' para data-test-id='search-input'
        assertEquals("Selenium", searchPageField.getAttribute("value"));
    }

    @Test
    public void toolsMenu() {
        mainPage.toolsMenu.click();
        WebElement menuPopup = driver.findElement(By.cssSelector("div[data-test='main-menu-item']"));
        // Alterei de data-test='main-submenu' para data-test='main-menu-item'
        assertTrue(menuPopup.isDisplayed());
    }
    @Test
     public void navigationToAllTools() {
        mainPage.seeDeveloperToolsButton.click();
        mainPage.findYourToolsButton.click();
        // Alterei no MainPage.java o data-test de suggestion-action para suggestion-link

        WebElement productsList = driver.findElement(By.id("products-page"));
        assertTrue(productsList.isDisplayed());
        assertEquals("All Developer Tools and Products by JetBrains", driver.getTitle());
    }



}
