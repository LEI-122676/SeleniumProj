package secure;

import java.io.File;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FileUploadTest extends BaseChromeTest {
    // website: https://the-internet.herokuapp.com/upload
    @Test
        public void fileUploadTest() {
        driver.get("https://the-internet.herokuapp.com/upload");
        String realFileName = "shrek.png";
        File uploadFile = new File("src/test/java/secure/" + realFileName);

        WebElement fileInput = driver.findElement(By.cssSelector("input[type=file]"));
        fileInput.sendKeys(uploadFile.getAbsolutePath());
        driver.findElement(By.id("file-submit")).click();

        WebElement fileName = driver.findElement(By.id("uploaded-files"));
        Assertions.assertEquals(realFileName, fileName.getText());
    }

}
