package content;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class DynamicContentPage {
    private WebDriver driver;

    @FindBy(css = "#content .row")
    private List<WebElement> contentRows;

    @FindBy(css = "#content .row img")
    private List<WebElement> contentImages;

    @FindBy(css = "#content .row .large-10")
    private List<WebElement> contentTexts;

    @FindBy(xpath = "//a[contains(@href, '?with_content=static')]")
    private WebElement staticContentLink;

    @FindBy(xpath = "//a[text()='click here']")
    private WebElement clickHereLink;

    public DynamicContentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getNumberOfRows() {
        return contentRows.size();
    }

    public List<WebElement> getContentImages() {
        return contentImages;
    }

    public List<WebElement> getContentTexts() {
        return contentTexts;
    }

    public String getImageSrc(int index) {
        if (index >= 0 && index < contentImages.size()) {
            return contentImages.get(index).getAttribute("src");
        }
        return null;
    }

    public String getTextContent(int index) {
        if (index >= 0 && index < contentTexts.size()) {
            return contentTexts.get(index).getText();
        }
        return null;
    }

    public void clickRefreshLink() {
        clickHereLink.click();
    }

    public void clickStaticContentLink() {
        staticContentLink.click();
    }

    public boolean isImageDisplayed(int index) {
        if (index >= 0 && index < contentImages.size()) {
            return contentImages.get(index).isDisplayed();
        }
        return false;
    }

    public boolean isTextDisplayed(int index) {
        if (index >= 0 && index < contentTexts.size()) {
            return contentTexts.get(index).isDisplayed();
        }
        return false;
    }
}