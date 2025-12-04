package testsuit_4.tests;

import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import testsuit_4.pages.FormPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SignupFormTest {

    private static final String TEST_NAME = "teste";
    private static final String TEST_LAST_NAME = "teste";
    private static final String TEST_USER = "testetest";
    private static final String TEST_PASSWORD = "Password123!";
    private static final String EXPECTED_NOTIFICATION_TEXT = "Data saved, welcome testetest";
    private static final String TEST_EMAIL = "teste@exemplo.com";
    private FormPage page = new FormPage();

    @BeforeAll
    public static void setUp() {
        Configuration.browserSize = "1280x800";
        Configuration.timeout = 10000;
    }

    @BeforeEach
    public void openPage(){
        page.open();
    }

    @Test
    public void testSuccessfulJoinCommunityNotification() {
        page.fillRequiredFields(TEST_NAME, TEST_LAST_NAME, TEST_USER, TEST_PASSWORD, false, "");
        sleep(500);
        page.vaadinbuttonJoinTheCommunity.click();
        sleep(1000);
        page.getNotificationText().shouldBe(visible).shouldHave(exactText(EXPECTED_NOTIFICATION_TEXT));
    }

    @Test
    public void testSuccessfulJoinCommunityWithEmail() {
        page.fillRequiredFields(TEST_NAME, TEST_LAST_NAME, TEST_USER, TEST_PASSWORD, true, TEST_EMAIL);
        sleep(500);
        page.vaadinbuttonJoinTheCommunity.click();
        sleep(1000);
        page.getNotificationText().shouldBe(visible).shouldHave(exactText(EXPECTED_NOTIFICATION_TEXT));
    }

}