package com.swaglabs.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BurgerMenuComponent extends BaseComponent {

    private final By allItemsLink = By.xpath("//*[@id='inventory_sidebar_link']");
    private final By aboutLink = By.xpath("//*[@id='about_sidebar_link']");
    private final By logoutLink = By.xpath("//*[@id='logout_sidebar_link']");
    private final By resetAppStateLink = By.xpath("//*[@id='reset_sidebar_link']");
    private final By closeButton = By.xpath("//*[@id='react-burger-cross-btn']");

    public BurgerMenuComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickAllItems() {
        rootElement.findElement(allItemsLink).click();
    }

    public void clickAbout() {
        rootElement.findElement(aboutLink).click();
    }

    public void clickLogout() {
        rootElement.findElement(logoutLink).click();
    }

    public void clickResetAppState() {
        rootElement.findElement(resetAppStateLink).click();
    }

    public void closeMenu() {
        rootElement.findElement(closeButton).click();
    }
}
