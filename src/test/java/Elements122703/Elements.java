package Elements122703;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://www.jetbrains.com/
public class Elements {

    @FindBy(xpath= "//input[@type='checkbox']")
    public WebElement checkBox;

    @FindBy(xpath = "//select[@id='dropdown']")
    public WebElement dropdown;


    public Elements(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
