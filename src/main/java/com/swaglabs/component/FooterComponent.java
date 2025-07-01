package com.swaglabs.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FooterComponent extends BaseComponent {

    private final By twitterLink = By.cssSelector("a[href*='twitter.com']");
    //*[@id="page_wrapper"]/footer/ul/li[1]/a
    //a[normalize-space()='Twitter']
    // a[href='https://twitter.com/saucelabs']
    private final By facebookLink = By.cssSelector("a[href*='facebook.com']");
    private final By linkedinLink = By.cssSelector("a[href*='linkedin.com']");
    private final By copyright = By.className("footer_copy");
    //div[@class='footer_copy']

    public FooterComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
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
}
