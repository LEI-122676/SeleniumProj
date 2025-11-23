package secure;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.net.PortProber;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
  protected WebDriver driver;
  protected WebDriverWait wait;
  protected File driverPath;
  protected File browserPath;
  protected String username = "admin";
  protected String password = "myStrongPassword";
  protected String trustStorePassword = "seleniumkeystore";

  public WebElement getLocatedElement(WebDriver driver, By by) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    return wait.until(d -> driver.findElement(by));
  }

  protected FirefoxDriver startFirefoxDriver() {
    return startFirefoxDriver(new FirefoxOptions());
  }

  protected FirefoxDriver startFirefoxDriver(FirefoxOptions options) {
    options.setImplicitWaitTimeout(Duration.ofSeconds(1));
    driver = new FirefoxDriver(options);
    return (FirefoxDriver) driver;
  }

  protected ChromeDriver startChromeDriver() {
    ChromeOptions options = new ChromeOptions();
    options.setImplicitWaitTimeout(Duration.ofSeconds(1));
    return startChromeDriver(options);
  }

  protected ChromeDriver startChromeDriver(ChromeOptions options) {
    driver = new ChromeDriver(options);
    return (ChromeDriver) driver;
  }

  protected static ChromeOptions getDefaultChromeOptions() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--no-sandbox");
    return options;
  }

  protected static EdgeOptions getDefaultEdgeOptions() {
    EdgeOptions options = new EdgeOptions();
    options.addArguments("--no-sandbox");
    return options;
  }

  protected File getTempDirectory(String prefix) {
    File tempDirectory = null;
    try {
      tempDirectory = Files.createTempDirectory(prefix).toFile();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    tempDirectory.deleteOnExit();

    return tempDirectory;
  }

  protected File getTempFile(String prefix, String suffix) {
    File logLocation = null;
    try {
      logLocation = File.createTempFile(prefix, suffix);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    logLocation.deleteOnExit();

    return logLocation;
  }

  protected void enableLogging() {
    Logger logger = Logger.getLogger("");
    logger.setLevel(Level.FINE);
    Arrays.stream(logger.getHandlers()).forEach(handler -> {
      handler.setLevel(Level.FINE);
    });
  }

  @AfterEach
  public void quit() {
    if (driver != null) {
      driver.quit();
    }
  }
}
