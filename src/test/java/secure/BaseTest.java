package secure;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
  protected WebDriver driver;

  protected ChromeDriver startChromeDriver() {
    ChromeOptions options = new ChromeOptions();
    options.setImplicitWaitTimeout(Duration.ofSeconds(1));
    return startChromeDriver(options);
  }

  protected ChromeDriver startChromeDriver(ChromeOptions options) {
    driver = new ChromeDriver(options);
    return (ChromeDriver) driver;
  }

  @AfterEach
  public void quit() {
    if (driver != null) {
      driver.quit();
    }
  }
}
