package com.swaglabs.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryItemPage extends BasePage {
    private final By itemPrice = By.className("inventory_details_price");
    private final By addToCartButtonItemPage = By.cssSelector("button[data-test^='add-to-cart']");

    public InventoryItemPage(WebDriver driver) {
        super(driver);
    }

    public String getPrice() {
        return driver.findElement(itemPrice).getText();
    }

    public void clickAddToCartItemPage() {
        driver.findElement(addToCartButtonItemPage).click();
    }
}
