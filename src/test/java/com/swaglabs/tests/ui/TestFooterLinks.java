package com.swaglabs.tests.ui;

import com.swaglabs.tests.ui.testrunners.BaseTestRunner;
import com.swaglabs.component.FooterComponent;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.*;

public class TestFooterLinks extends BaseTestRunner {
    @Test()
    @Description("Verify that links from footer work correctly.")
    @Feature("Footer links correctness")
    @Issue("4")
    @Owner("Fylyk Viktoriia")

    public void testFooterLinks() {
        inventoryPage.scrollToFooter();
        FooterComponent footerComponent = new FooterComponent(driver, inventoryPage.getFooterRoot());
        assertTrue(footerComponent.isDisplayed(), "Footer is not visible");

        String originalTab = driver.getWindowHandle();

        assertTrue(footerComponent.isTwitterLinkDisplayed(), "X link is not visible or working");
        footerComponent.clickTwitter();
        footerComponent.switchToNewTab(originalTab);
        assertTrue(driver.getCurrentUrl().contains("x.com/saucelabs"), "Twitter/X URL is incorrect");
        driver.close();
        driver.switchTo().window(originalTab);


        assertTrue(footerComponent.isFacebookLinkDisplayed(), "Facebook link is not visible or working");
        footerComponent.clickFacebook();
        footerComponent.switchToNewTab(originalTab);
        assertTrue(driver.getCurrentUrl().contains("facebook.com/saucelabs"), "Facebook URL is incorrect");
        driver.close();
        driver.switchTo().window(originalTab);

        assertTrue(footerComponent.isLinkedInLinkDisplayed(), "LinkedIn link is not visible or working");
        footerComponent.clickLinkedIn();
        footerComponent.switchToNewTab(originalTab);
        assertTrue(driver.getCurrentUrl().contains("linkedin.com/company/sauce-labs"), "LinkedIn URL is incorrect");
        driver.close();
        driver.switchTo().window(originalTab);

        assertEquals(footerComponent.getCopyrightText(), FooterComponent.COPYRIGHT_TEXT);

    }

}
