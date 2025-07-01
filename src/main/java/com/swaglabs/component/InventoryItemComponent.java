package com.swaglabs.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InventoryItemComponent extends BaseComponent {

    private final By titleLink = By.cssSelector("a[id$='_title_link']");
    private final By name = By.className("inventory_item_name");
    private final By imageLink = By.cssSelector("a[id$='_img_link']");
    private final By addToCartButton = By.cssSelector("button[data-test^='add-to-cart']");
    private final By price = By.className("inventory_item_price");

    public InventoryItemComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public void clickTitle() {
        rootElement.findElement(titleLink).click();
    }

    public void clickImage() {
        rootElement.findElement(imageLink).click();
    }

    public void clickAddToCart() {
        rootElement.findElement(addToCartButton).click();
    }

    public String getName() {
        return rootElement.findElement(name).getText();
    }

    public String getPrice() {
        return rootElement.findElement(price).getText();
    }
}

