package com.swaglabs.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FooterComponent extends BaseComponent {
    private final By twitterLink = By.cssSelector("a[href='https://twitter.com/saucelabs']");
    private final By facebookLink = By.cssSelector("a[href='https://www.facebook.com/saucelabs']");
    private final By linkedinLink = By.cssSelector("a[href='https://www.linkedin.com/company/sauce-labs/']");
    private final By copyright = By.className("footer_copy");
    public static final String COPYRIGHT_TEXT = "© 2025 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy";

    public FooterComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public boolean isDisplayed() {
        return rootElement.isDisplayed();
    }

    public void clickTwitter() {
        rootElement.findElement(twitterLink).click();
    }

    public void clickFacebook() {
        rootElement.findElement(facebookLink).click();
    }

    public void clickLinkedIn() {
        rootElement.findElement(linkedinLink).click();
    }

    public String getCopyrightText() {
        return rootElement.findElement(copyright).getText();
    }

    public boolean isTwitterLinkDisplayed() {
        return rootElement.findElement(twitterLink).isDisplayed();
    }

    public boolean isFacebookLinkDisplayed() {
        return rootElement.findElement(facebookLink).isDisplayed();
    }

    public boolean isLinkedInLinkDisplayed() {
        return rootElement.findElement(linkedinLink).isDisplayed();
    }

    public void switchToNewTab(String originalTab) {
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalTab)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

}
