package testsuit_4.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

// page_url = https://vaadin-form-example.demo.vaadin.com
public class FormPage {

    public SelenideElement vaadintextfield = $("html > body > vaadin-vertical-layout > vaadin-form-layout > vaadin-text-field:nth-of-type(1)");

    public SelenideElement vaadintextfield2 = $("html > body > vaadin-vertical-layout > vaadin-form-layout > vaadin-text-field:nth-of-type(2)");

    public SelenideElement vaadintextfield3 = $("html > body > vaadin-vertical-layout > vaadin-form-layout > vaadin-text-field:nth-of-type(3)");

    public SelenideElement vaadinpasswordfield = $("html > body > vaadin-vertical-layout > vaadin-form-layout > vaadin-password-field:nth-of-type(1)");

    public SelenideElement vaadinpasswordfield2 = $("html > body > vaadin-vertical-layout > vaadin-form-layout > vaadin-password-field:nth-of-type(2)");

    public SelenideElement vaadincheckboxAllowMarketing = $("vaadin-checkbox[aria-checked='false']");

    public SelenideElement vaadinemailfield = $("vaadin-email-field[tabindex='0']");

    public SelenideElement vaadinbuttonJoinTheCommunity = $("vaadin-button[colspan='2']");

    public void fillRequiredFields(String firstName, String lastName, String user, String password) {
        String script = "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('change'))";

        Selenide.executeJavaScript(script, vaadintextfield, firstName);
        Selenide.executeJavaScript(script, vaadintextfield2, lastName);
        Selenide.executeJavaScript(script, vaadintextfield3, user);
        Selenide.executeJavaScript(script, vaadinpasswordfield, password);
        Selenide.executeJavaScript(script, vaadinpasswordfield2, password);
    }

    public void open() {
        Selenide.open("https://vaadin-form-example.demo.vaadin.com");
    }
    public void fillRequiredFields(String firstName, String lastName, String user, String password, boolean allowMarketing, String email) {
        String script = "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('change'))";

        Selenide.executeJavaScript(script, vaadintextfield, firstName);
        Selenide.executeJavaScript(script, vaadintextfield2, lastName);
        Selenide.executeJavaScript(script, vaadintextfield3, user);
        Selenide.executeJavaScript(script, vaadinpasswordfield, password);
        Selenide.executeJavaScript(script, vaadinpasswordfield2, password);

        if (allowMarketing) {
            vaadincheckboxAllowMarketing.click();
            vaadinemailfield.shouldBe(visible);
            Selenide.executeJavaScript(script, vaadinemailfield, email);
        }
    }

    public SelenideElement getNotificationText() {
        SelenideElement notificationCard = $("vaadin-notification-card[theme='success']");
        return $(notificationCard.getWrappedElement()
                .getShadowRoot()
                .findElement(By.cssSelector("div[part='overlay'] div[part='content']")));
    }
}